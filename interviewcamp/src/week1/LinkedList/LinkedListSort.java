package week1.LinkedList;

/**
* 
* @author Deepak Kejriwal
*
*/
/*
 * Level: Easy
You are given a Linked List with nodes that have values 0, 1 or 2. Sort the linked list.
 * 
 */
public class LinkedListSort {

	public static LinkedList sortList(LinkedList input) {
		if (input == null)
		return new LinkedList(); // empty list
		LinkedList list0 = new LinkedList();
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();
		Node<String,Integer> current = input.head;
		while(current != null) {
		switch (current.getData()) {
		case 0: list0.append(current); break;
		case 1: list1.append(current); break;
		case 2: list2.append(current); break;
		default: throw new IllegalArgumentException(
		"Invalid value: " + current.getData());
		}
		current = current.getNext();
		}
		LinkedList result = new LinkedList();
		// Check if list is empty before adding.
		if (list0.head != null) {
		result.append(list0.head);
		result.tail = list0.tail;
		}
		if (list1.head != null) {
		result.append(list1.head);
		result.tail = list1.tail;
		}
		if (list2.head != null) {
		result.append(list2.head);
		result.tail = list2.tail;
		}
		return result;
	}
}
