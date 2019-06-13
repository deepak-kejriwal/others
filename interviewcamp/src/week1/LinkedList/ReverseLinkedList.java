package week1.LinkedList;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class ReverseLinkedList {

	// Reverse a single linked list
	private static Node reverse(Node head) {
		Node prev = null;
		Node curr = head;

		while (curr != null) {
			Node tmp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmp;
		}
		return prev;
	}

}
