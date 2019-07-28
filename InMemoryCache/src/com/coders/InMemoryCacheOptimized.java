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
public class InMemoryCacheOptimized<T> implements Cache<T> {

	private final ConcurrentHashMap<String, SoftReference<T>> cache = new ConcurrentHashMap<>();
	private final DelayQueue<CacheObject<T>> cleaningUpQueue = new DelayQueue<>();
	private static final int DEFAULT_EXPIRE_TIME = 5000;
	/** The default expiration time in millisecond */
	private final long defaultExpire;

	public InMemoryCacheOptimized(final long defaultExpire) {
		this.defaultExpire = defaultExpire;
		Thread cleanerThread = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					CacheObject<T> delayedCacheObject = cleaningUpQueue.take();
					cache.remove(delayedCacheObject.getKey(), delayedCacheObject.getReference());
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		cleanerThread.setDaemon(true);
		cleanerThread.start();
	}

	public InMemoryCacheOptimized() {
		this(DEFAULT_EXPIRE_TIME);
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
			long expiryTime = System.currentTimeMillis() + periodInMillis;
			SoftReference<T> reference = new SoftReference<>(value);
			cache.put(key, reference);
			cleaningUpQueue.put(new CacheObject<T>(key, reference, expiryTime));
		}
	}

	@Override
	public void remove(String key) {
		cache.remove(key);
	}

	@Override
	public T get(String key) {
		return Optional.ofNullable(cache.get(key)).map(SoftReference::get).orElse(null);
	}

	@Override
	public void clear() {
		cache.clear();
	}

	@Override
	public long size() {
		return cache.size();
	}

	private static class CacheObject<T> implements Delayed {

		public CacheObject(String key, SoftReference<T> reference, long expiryTime) {
			this.key = key;
			this.reference = reference;
			this.expiryTime = expiryTime;
		}

		private final String key;
		private final SoftReference<T> reference;
		private final long expiryTime;

		public String getKey() {
			return key;
		}

		public SoftReference<T> getReference() {
			return reference;
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
/*
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (expiryTime ^ (expiryTime >>> 32));
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result
					+ ((reference == null) ? 0 : reference.get() == null ? 0 : reference.get().hashCode());
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
			if (reference == null && other.reference != null) {
				return false;
			} else if (other.reference == null) {
				return false;
			} else if (reference.get() == null && other.reference.get() != null) {
				return false;
			} else if (!reference.get().equals(other.reference.get())) {
				return false;
			}
			return true;
		}*/
	}
	
	public static void main(String[] args) throws Exception {
		InMemoryCacheOptimized<Integer> cache=new InMemoryCacheOptimized<>();
		cache.put("1", 100);
		cache.put("2", 200);
		System.out.println("Trying cache get");
		System.out.println(cache.get("1"));
		System.out.println(cache.get("2"));
		System.out.println("Trying cache get again");
		System.out.println(cache.get("1"));
		System.out.println(cache.get("2"));
		Thread.sleep(6000);
		System.out.println("Trying cache get after 6 sec");
		System.out.println(cache.get("1"));
		System.out.println(cache.get("2"));
	}
}
