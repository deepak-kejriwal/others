import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PriorityQueueVsLinkedList {

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 2, 4, 6, 8 };
		
		List<Integer> list = IntStream.range(1, 101).boxed().collect(Collectors.toList());
		Collections.shuffle(list);
		long start = System.nanoTime();
		testPQ(list);
		long end = System.nanoTime();
		System.out.println(end - start);
		start = System.nanoTime();
		testLinkedList(list);
		end = System.nanoTime();
		System.out.println(end - start);
		// System.out.println(list);

	}

	private static void testLinkedList(List<Integer> list) {
		LinkedList<Integer> ll = new LinkedList<>();
		for (Integer i : list) {
			ll.add(i);
		}
		Collections.sort(ll);
		while (!ll.isEmpty()) {
			int i = ll.removeFirst();
			// System.out.println();
		}
	}

	private static void testPQ(List<Integer> list) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (Integer i : list) {
			pq.add(i);
		}
		while (!pq.isEmpty()) {
			int i = pq.remove();
			// System.out.println();
		}
	}

}
