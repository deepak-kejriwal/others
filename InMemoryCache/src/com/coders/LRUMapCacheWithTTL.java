package com.coders;

import java.lang.ref.SoftReference;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Deepak Kejriwal
 * 
 * @reference https://explainjava.com/simple-in-memory-cache-java/
 *
 */
public class LRUMapCacheWithTTL<T> implements Cache<T> {

	private final ConcurrentHashMap<String, SoftReference<CacheObject<T>>> cache = new ConcurrentHashMap<>();
	private final DelayQueue<CacheObject<T>> cleaningUpQueue = new DelayQueue<>();
	private static final int DEFAULT_EXPIRE_TIME = 5000;
	/** The default expiration time in millisecond */
	private final long defaultExpire;

	private static final int DEFAULT_MAX_ENTRIES = 2;
	int maxEntry; // Capacity of LRU Cache
	
	public LRUMapCacheWithTTL(int maxEntry,final long defaultExpire) {
		this.maxEntry=maxEntry;
		this.defaultExpire = defaultExpire;
		Thread cleanerThread = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					CacheObject<T> delayedCacheObject = cleaningUpQueue.take();
					cache.remove(delayedCacheObject.getKey());
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		cleanerThread.setDaemon(true);
		cleanerThread.start();
	}

	public LRUMapCacheWithTTL() {
		this(DEFAULT_MAX_ENTRIES,DEFAULT_EXPIRE_TIME);
	}
	
	public LRUMapCacheWithTTL(int maxEntry) {
		this(maxEntry,DEFAULT_EXPIRE_TIME);
	}

	@Override
	public void put(String key, T value) {
		put(key, value, defaultExpire);
	}

	@Override
	public void put(String key, T value, long periodInMillis) {
		if (key == null) {
			return;
		}
		if (value == null) {
			cache.remove(key);
		} else {
			if(cache.size()>=maxEntry) {
				CacheObject<T> cacheObject=cleaningUpQueue.peek();
				cache.remove(cacheObject.getKey());
				cleaningUpQueue.remove(cacheObject);
			}
			long expiryTime = System.currentTimeMillis() + periodInMillis;
			CacheObject<T> cacheObject=new CacheObject<T>(key, value, expiryTime);
			SoftReference<CacheObject<T>> reference = new SoftReference<>(cacheObject);
			cache.put(key, reference);
			cleaningUpQueue.put(cacheObject);
		}
	}

	@Override
	public void remove(String key) {
		cache.remove(key);
	}

	@Override
	public T get(String key) {
		Optional<CacheObject<T>> cacheObjectOpt=Optional.ofNullable(cache.get(key)).map(SoftReference::get);
		if(cacheObjectOpt.isPresent()) {
			CacheObject<T> cacheObject=cacheObjectOpt.get();
			cleaningUpQueue.remove(cacheObject);
			cacheObject.setExpiryTime(System.currentTimeMillis()+defaultExpire);
			cleaningUpQueue.put(cacheObject);
			return cacheObject.getValue();
		}
		return null;
	}

	@Override
	public void clear() {
		cache.clear();
	}

	@Override
	public long size() {
		return cache.size();
	}

	public T getHead() {
		return cleaningUpQueue.peek().getValue();
	}
	private static class CacheObject<T> implements Delayed {

		public CacheObject(String key, T value, long expiryTime) {
			this.key = key;
			this.value = value;
			this.expiryTime = expiryTime;
		}

		private final String key;
		private final T value;
		private long expiryTime;

		public String getKey() {
			return key;
		}

		public T getValue() {
			return value;
		}

		
		public void setExpiryTime(long expiryTime) {
			this.expiryTime = expiryTime;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(expiryTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Delayed o) {
			return Long.compare(expiryTime, o != null ? ((CacheObject<T>) o).expiryTime : 0);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (expiryTime ^ (expiryTime >>> 32));
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result
					+ ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CacheObject<T> other = (CacheObject<T>) obj;
			if (expiryTime != other.expiryTime) {
				return false;
			}
			if (key == null && other.key != null) {
				return false;
			} else if (!key.equals(other.key)) {
				return false;
			}
			if (value == null && other.value != null) {
				return false;
			} else if (other.value == null) {
				return false;
			} else if (!value.equals(other.value)) {
				return false;
			}
			return true;
		}
	}
	
	public static void main(String[] args) throws Exception {
		LRUMapCacheWithTTL<Integer> cache=new LRUMapCacheWithTTL<>();
		cache.put("1", 100);
		cache.put("2", 200,1000);
		cache.put("3", 300);
		System.out.println(cache.getHead());
		System.out.println("Trying cache get");
		System.out.println(cache.get("1"));
		System.out.println(cache.get("2"));
		System.out.println(cache.get("3"));
		System.out.println(cache.getHead());
		System.out.println("Trying cache get again");
		System.out.println(cache.get("1"));
		System.out.println(cache.get("2"));
		//Thread.sleep(6000);
		System.out.println("Trying cache get after 6 sec");
		System.out.println(cache.get("1"));
		System.out.println(cache.get("2"));
	}
}
