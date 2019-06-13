package week1.LinkedList;

/**
 * 
 * @author Deepak Kejriwal
 *
 */

/*
 * 
 * Level: Easy Odd Even Linked List: Given a Linked List L, separate it into 2
 * Linked Lists. One contains L's odd nodes and the other contains L's even
 * nodes. For example: Input: Head -> 1 -> 2 -> 3 -> 4 -> 5 Result 1: Head -> 1
 * -> 3 -> 5 Result 2: Head -> 2 -> 4 Note: Odd and Even here refer to the
 * node's position, not value.
 * 
 */
public class LinkedListEvenOddSplit {
	public Pair<LinkedList> getOddEven(LinkedList input) {
		LinkedList odd = new LinkedList();
		LinkedList even = new LinkedList();
		Node current = input.getHead();
		int index = 0;
		while (current != null) {
			index++;
			LinkedList destination = index % 2 == 0 ? even : odd;
			destination.append(current);
			current = current.getNext();
		}
		even.getTail().setNext(null);
		odd.getTail().setNext(null);
		return new Pair<LinkedList>(odd, even);
	}

	class Pair<T> {
		T odd;
		T even;

		Pair(T odd, T even) {
			this.odd = odd;
			this.even = even;
		}
	}
}
