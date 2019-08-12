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

	public static Integer[] ListToArray(List<Integer> list) {
		return list.stream().toArray(Integer[]::new);
	}

	public static Integer[] intToInteger(int[] nums) {
		return Arrays.stream(nums).boxed().toArray(Integer[]::new);
	}

	public static int[] integerToInt(Integer[] nums) {
		return Arrays.stream(nums).mapToInt(x -> x).toArray();
	}

	public static int[] ListToArrayPrimitive(List<Integer> list) {
		return list.stream().mapToInt(x -> x).toArray();
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
