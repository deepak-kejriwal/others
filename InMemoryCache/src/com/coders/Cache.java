package com.coders;

/**
* 
* @author deepak1037
*
*/
public interface Cache<T> {
	
	void put(String key, T value);
    void put(String key, T value, long periodInMillis);
    
    void remove(String key);
 
    T get(String key);
 
    void clear();
 
    long size();
}
