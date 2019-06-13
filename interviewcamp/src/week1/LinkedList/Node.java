package week1.LinkedList;

/**
* 
* @author Deepak Kejriwal
*
*/
public class Node<K, V> {
	Node<K, V> next;
	Node<K, V> prev;
	V data;
	K key;
	// Getters and Setters, can skip in interview
	public Node(Node<K, V> next, V data) {
	this.next = next;
	this.data = data;
	}
	
	public Node(K key, V data) {
	this.key = key;
	this.data = data;
	}
	
	public Node<K, V> getNext() {
	return next;
	}
	public void setNext(Node<K, V> next) {
	this.next = next;
	}
	public V getData() {
	return data;
	}
	public void setData(V data) {
	this.data = data;
	}
	public Node<K, V> getPrev() {
		return prev;
	}
	public void setPrev(Node<K, V> prev) {
		this.prev = prev;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}
	
}
