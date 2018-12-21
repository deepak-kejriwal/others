package com.coders;

import java.lang.ref.SoftReference;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class InMemoryCache<T> implements Cache<T> {
	private static final int CLEAN_UP_PERIOD_IN_SEC = 5;
	/** The default expiration time in millisecond */
	private final long defaultExpire;
	private final ConcurrentHashMap<String, SoftReference<CacheObject<T>>> cache = new ConcurrentHashMap<>();

	public InMemoryCache(final long defaultExpire) {
		this.defaultExpire = defaultExpire;
		Thread cleanerThread = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep(CLEAN_UP_PERIOD_IN_SEC * 1000);
					cache.entrySet().removeIf(entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get)
							.map(CacheObject::isExpired).orElse(false));
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		cleanerThread.setDaemon(true);
		cleanerThread.start();
	}

	public InMemoryCache() {
		this(5000);
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
			cache.put(key, new SoftReference<>(new CacheObject<T>(value, expiryTime)));
		}
	}

	@Override
	public void remove(String key) {
		cache.remove(key);
	}

	@Override
	public T get(String key) {
		return Optional.ofNullable(cache.get(key)).map(SoftReference::get)
				.filter(cacheObject -> !cacheObject.isExpired()).map(CacheObject::getValue).orElse(null);
	}

	@Override
	public void clear() {
		cache.clear();
	}

	@Override
	public long size() {
		return cache.entrySet().stream().filter(entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get)
				.map(cacheObject -> !cacheObject.isExpired()).orElse(false)).count();
	}

	private static class CacheObject<T> {

		public CacheObject(T value, long expiryTime) {

			this.value = value;
			this.expiryTime = expiryTime;
		}

		private T value;
		private long expiryTime;

		public T getValue() {
			return value;
		}

		boolean isExpired() {
			return System.currentTimeMillis() > expiryTime;
		}
	}

}
