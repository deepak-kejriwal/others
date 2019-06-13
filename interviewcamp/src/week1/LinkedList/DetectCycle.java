package week1.LinkedList;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class DetectCycle {

	public static boolean hasCycle(Node head) {
		Node fast = head, slow = head;
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			if (fast == slow)
				return true;
		}
		return false;
	}

	public static int findCycleLength(Node head) {
		Node fast = head, slow = head;
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			if (fast == slow)
				break;
		}
		if (fast == null || fast.getNext() == null) // no cycle found
			return -1;
		fast = fast.getNext();
		int nodesPassed = 1;
		while (fast != slow) {
			fast = fast.getNext();
			nodesPassed += 1;
		}
		return nodesPassed;
	}

	public static Node findBeginningOfCycle(Node head) {
		Node fast = head, slow = head;
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			if (fast == slow)
				break;
		}
		if (fast == null || fast.getNext() == null) // no cycle found
			return null;

		slow = head;
		while (fast != slow) {
			slow = slow.getNext();
			fast = fast.getNext();
		}
		/* Both now point to the start of the loop. */
		return fast;
	}
}
