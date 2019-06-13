package week1.LinkedList;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class DeleteNode {

	Node head;
	Node tail;

	public DeleteNode() {
		head = null;
		tail = null;
	}

	/*
	 * 
	 * Level: Medium Given a linked list and pointers to a node N and its previous
	 * node Prev, delete N from the linked list.
	 * 
	 */
	public void delete(Node n, Node prev) {
		if (n == null)
			return;
		if (n == head)
			head = n.getNext();
		if (n == tail)
			tail = prev;
		if (prev != null)
			prev.setNext(n.getNext());
	}

	/*
	 * 
	 * Level: Easy Follow Up: Given a node N in a Linked List, can you delete it
	 * without the previous node in O(1) time?
	 * 
	 */
	public void deleteWithoutPrev(Node n) {
		Node next = n.getNext();
		if (next == null)
			return; // cannot delete
		n.setData(next.getData());
		delete(next /* toDelete */, n /* prev */);
	}
}
