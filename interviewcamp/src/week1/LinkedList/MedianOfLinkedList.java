package week1.LinkedList;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
/*
 * Level: Medium Find the median node of a linked list. For example: 1 -> 2 -> 3
 * -> 4 -> 5 Median node is 3.
 * 
 */
public class MedianOfLinkedList {

	/*
	 * Note: We passed head and tail as arguments here, but be careful with this
	 * approach. If your function modifies the linked list, this will not work.
	 *
	 * For example, if you need to change the head and tail pointers, simply
	 * reassigning them in this function call will not change the original
	 * variables. In most cases, we recommend making list modification functions a
	 * part of the LinkedList class.
	 */
	public static Node findMedian(Node head, Node tail) {
		if (head == null || tail == null)
			return null;
		Node fast = head, slow = head;
		while (fast!=null&&fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}

		return slow;
	}
}
