import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class Java8StreamTest {
	public static void main(String[] args) {
		List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());
		String str = list.stream().collect(Collectors.joining());
		System.out.println(str);
		String str2 = String.join("", list);
		System.out.println(str2);
	}

	public static void testListToArray() {
		System.out.print("testListToArray: input: ");
		List<Integer> input = Stream.of(1, 2, 3).collect(Collectors.toList());
		System.out.println(input);
		System.out.print("testListToArray: output: ");
		Integer[] output = Java8Stream.ListToArray(input);
		System.out.print(Arrays.toString(output));
		System.out.println();
	}

	public static void testListToArrayPrimitive() {
		System.out.print("testListToArrayPrimitive: input: ");
		List<Integer> input = Stream.of(1, 2, 3).collect(Collectors.toList());
		System.out.println(input);
		System.out.print("testListToArrayPrimitive: output: ");
		int[] output = Java8Stream.ListToArrayPrimitive(input);
		System.out.print(Arrays.toString(output));
		System.out.println();
	}
}
