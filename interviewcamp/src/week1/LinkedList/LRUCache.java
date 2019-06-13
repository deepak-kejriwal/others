package week1.LinkedList;

import java.util.HashMap;

/**
 * 
 * @author Deepak Kejriwal
 *
 */

public class LRUCache<K, V> {
	// Maps keys to nodes
	HashMap<K, Node<K, V>> map;
	// Linked List variables
	Node<K, V> head;
	Node<K, V> tail;
	// Maximum nodes the cache can hold.
	int capacity;

	public LRUCache(int capacity) {
		this.map = new HashMap<>();
		this.capacity = capacity;
	}

	// Read a value from cache.
	public V read(K key) {
		Node<K, V> node = map.get(key);
		if (node == null)
			return null;
		remove(key); // remove from linked hash table
		add(node.getKey(), node.getData()); // add back to front
		return node.getData();
	}

	// Write a value to cache.
	public void write(K key, V value) {

		if (map.size() == capacity) { // cache is full, evict the head
			remove(head.getKey());
		}
		// In this implementation, we create a new node every time.
		// If you want, you can also move the same node to the end.
		add(key, value);
	}

	// Removed a node from the Linked Hash Table
	private void remove(K key) {
		if (!map.containsKey(key))
			return;
		Node<K, V> toRemove = map.get(key);
		removeFromLinkedList(toRemove);
		map.remove(key);
	}

	// Add a node to the end of the Linked Hash Table
	private void add(K key, V value) {
		Node<K, V> newNode = new Node<>(key, value);
		appendToLinkedList(newNode);
		map.put(key, newNode);
	}

	/*
	 * These are helper functions we use above. Ask the interviewer if they want you
	 * to implement these.
	 */
	// Append Function, same as the technique we applied in earlier section.
	// This one is for a Doubly Linked List.
	public void appendToLinkedList(Node<K, V> toAdd) {
		if (head == null) {
			head = toAdd;
		} else {
			tail.setNext(toAdd);
			toAdd.setPrev(tail);
		}
		tail = toAdd;
	}

	// Removes a Node from a doubly Linked List. Practice this function as well.
	public void removeFromLinkedList(Node<K, V> toRemove) {
		if (toRemove.getPrev() != null)
			toRemove.getPrev().setNext(toRemove.getNext());
		if (toRemove.getNext() != null)
			toRemove.getNext().setPrev(toRemove.getPrev());
		if (toRemove == head)
			head = toRemove.getNext();
		if (toRemove == tail)
			tail = toRemove.getPrev();
	}
}
