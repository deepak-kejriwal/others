import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class TestOrderNSort {

	public static void main(String[] args) {
		List<Integer> list1 = IntStream.rangeClosed(1, 2000000).boxed().collect(Collectors.toList());
		Collections.shuffle(list1);
		list1 = list1.stream().limit(1000000).collect(Collectors.toList());
		List<Integer> list2 = IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList());
		Collections.copy(list2, list1);
		long start1=System.currentTimeMillis();
		defaultSort(list1);
		long end1=System.currentTimeMillis();
		long start2=System.currentTimeMillis();
		list2=customSort(list2);
		long end2=System.currentTimeMillis();
		//System.out.println(list1);
		System.out.println("Total Time: "+(end1-start1));
		//System.out.println(list2);
		System.out.println("Total Time: "+(end2-start2));

	}

	public static void defaultSort(List<Integer> list1) {
		Collections.sort(list1);
	}

	public static List<Integer> customSort(List<Integer> list2) {
		int size = list2.stream().mapToInt(x -> x).max().getAsInt();
		int[] arr = new int[size+1];
		for (int i : list2) {
			arr[i] += 1;
		}
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			if (arr[i] == 0) {
				continue;
			}
			while (arr[i] != 0) {
				result.add(i);
				arr[i] -= 1;
			}
		}
		return result;
	}
}
