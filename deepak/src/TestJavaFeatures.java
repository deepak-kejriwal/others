import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class TestJavaFeatures {
	public static void main2(String[] args) throws Exception {}

	public static void main(String[] args) throws Exception {
		List<String> testInsertion=new ArrayList<>();
		
		List<String> testSizeDefault=new ArrayList<>();
		List<String> testSize10=new ArrayList<>(10);
		System.out.println("DeafultSize: "+testSizeDefault);
		System.out.println("DeafultSize10: "+testSize10);
		Map<String, List<Integer>> map = new HashMap<>();
		map.putIfAbsent("dk", new ArrayList<>());
		map.get("dk").add(13);
		map.putIfAbsent("dk", new ArrayList<>());
		map.get("dk").add(14);
		int[] arr=IntStream.range(1, 1000).toArray();

		int[] nums = { 1, 2, 7, 6, 1, 5 };
		Arrays.sort(nums);
		int len = Arrays.binarySearch(nums, 8);
		System.out.println(len);
		Class cls = Class.forName("");
		Object obj = cls.getMethod("toString");
		double d1 = 5.1;
		double d2 = 0.1;
		double sum = d1 + d2;
		if (sum == 5.2) {
			System.out.println(sum);
		} else {
			System.out.println("incorrect");
		}
		HashMap hm = new HashMap();
		 List<Character> tmp = new ArrayList<>();
		

		/*
		 * 
		 * List<String> list = Stream.of("abc", "defg",
		 * "hi").collect(Collectors.toList()); List<Integer> listInt =
		 * list.stream().map((@NotNull String str) -> { return str.length();
		 * 
		 * }).collect(Collectors.toList()); System.out.println(listInt);
		 * LinkedList<Integer>[] lists=new LinkedList[10]; System.out.println(lists);
		 * Stack<Integer> stk=new Stack<>(); stk.firstElement();
		 */

	}

}
