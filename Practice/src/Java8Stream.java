import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class Java8Stream {

	/**
	 * To convert Integer array to specific ArrayList and not List. toList() returns
	 * LinkedList
	 * 
	 * @param Integer[] nums
	 * @return ArrayList<Integer>
	 */
	public static ArrayList<Integer> ArrayToArrayList(Integer[] nums) {
		return Arrays.stream(nums).collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Conversion of List to Boxed Array
	 * 
	 * @param List<Integer>
	 * 
	 * @return Integer[]
	 * 
	 * 
	 */
	public static Integer[] ListToArray(List<Integer> list) {
		return list.stream().toArray(Integer[]::new);
	}

	/**
	 * Conversion of List to Primitive Array
	 * 
	 * @param List<Integer>
	 * 
	 * @return int[]
	 * 
	 * 
	 */
	public static int[] ListToArrayPrimitive(List<Integer> list) {
		return list.stream().mapToInt(x -> x).toArray();
	}

	/**
	 * Conversion of primitive array to boxed array
	 * 
	 * @param int[]
	 * @return Integer[]
	 */
	public static Integer[] intToInteger(int[] nums) {
		return Arrays.stream(nums).boxed().toArray(Integer[]::new);
	}

	/**
	 * Conversion of boxed array to primitive array
	 * 
	 * @param Integer[]
	 * @return int[]
	 */
	public static int[] integerToInt(Integer[] nums) {
		return Arrays.stream(nums).mapToInt(x -> x).toArray();
	}

	public static void main(String[] args) {
		testListToArray();
		testListToArrayPrimitive();
	}

	public static void testListToArray() {
		System.out.print("testListToArray: input: ");
		List<Integer> input = Stream.of(1, 2, 3).collect(Collectors.toList());
		System.out.println(input);
		System.out.print("testListToArray: output: ");
		Integer[] output = ListToArray(input);
		System.out.print(Arrays.toString(output));
		System.out.println();
	}

	public static void testListToArrayPrimitive() {
		System.out.print("testListToArrayPrimitive: input: ");
		List<Integer> input = Stream.of(1, 2, 3).collect(Collectors.toList());
		System.out.println(input);
		System.out.print("testListToArrayPrimitive: output: ");
		int[] output = ListToArrayPrimitive(input);
		System.out.print(Arrays.toString(output));
		System.out.println();
	}
}
