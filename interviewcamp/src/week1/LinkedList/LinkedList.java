package week1.LinkedList;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class LinkedList<K,V> {
	Node<K,V> head;
	Node<K,V> tail;

	public LinkedList() {
		
	}
	// Getters and Setters, can skip in interview
	public LinkedList(Node<K,V> head, Node<K,V> tail) {
		super();
		this.head = head;
		this.tail = tail;
	}

	public Node<K,V> getHead() {
		return head;
	}

	public void setHead(Node<K,V> head) {
		this.head = head;
	}

	public Node<K,V> getTail() {
		return tail;
	}

	public void setTail(Node<K,V> tail) {
		this.tail = tail;
	}

	/*
	 * Get the nth element of the list
	 */
	public Node get(int n) {
		Node node = head;
		for (int i = 0; i < n - 1; i++) { // move forward n-1 times
			if (node != null)
				node = node.getNext();
			else
				throw new IndexOutOfBoundsException("No node at index " + Integer.toString(n));
		}
		if (node == null)
			throw new IndexOutOfBoundsException("No node at index " + Integer.toString(n));
		return node;
	}


	/*
	 * Get function - without using LinkedList class. Only difference here is that
	 * we pass head and tail as arguments.
	 *
	 * Note: Be careful with this implementation (without a class). * While it works
	 * in simple read operations, it doesn't work well if you want to modify the
	 * list.
	 *
	 * For example, if you need to change the head and tail pointers, simply
	 * reassigning them in this function call will not change the pointers. We
	 * recommend making list modification functions a part of the LinkedList class.
	 */
	public Node get(int n, Node head, Node tail) {
		Node node = head;
		for (int i = 0; i < n - 1; i++) { // move forward n-1 times
			if (node != null)
				node = node.getNext();
			else
				throw new IndexOutOfBoundsException("No node at index " + Integer.toString(n));
		}
		return node;
	}
	
	public void append(Node toAdd) {
		if (head == null) {
		head = toAdd;
		} else {
		tail.setNext(toAdd);
		}
		tail = toAdd;
		}
	
	public void delete(Node n) {
		if (n == null)
			return;
		if (n == head)
			head = n.getNext();
		if (n == tail)
			tail = n.getPrev();
		if (n.getPrev() != null)
			n.getPrev().setNext(n.getNext());
		if (n.getNext() != null)
			n.getNext().setPrev(n.getPrev());
		
	}
}
