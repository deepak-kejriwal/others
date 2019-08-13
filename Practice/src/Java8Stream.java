import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
/**
 * Implementations of {@link Collector} that implement various useful reduction
 * operations, such as accumulating elements into collections, summarizing
 * elements according to various criteria, etc.
 *
 * <p>
 * The following are examples of using the predefined collectors to perform
 * common mutable reduction tasks:
 *
 * <pre>
 * {@code
 *     // Accumulate names into a List
 *     List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());
 *
 *     // Accumulate names into a TreeSet
 *     Set<String> set = people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));
 *
 *     // Convert elements to strings and concatenate them, separated by commas
 *     String joined = things.stream()
 *                           .map(Object::toString)
 *                           .collect(Collectors.joining(", "));
 *
 *     // Compute sum of salaries of employee
 *     int total = employees.stream()
 *                          .collect(Collectors.summingInt(Employee::getSalary)));
 *
 *     // Group employees by department
 *     Map<Department, List<Employee>> byDept
 *         = employees.stream()
 *                    .collect(Collectors.groupingBy(Employee::getDepartment));
 *
 *     // Compute sum of salaries by department
 *     Map<Department, Integer> totalByDept
 *         = employees.stream()
 *                    .collect(Collectors.groupingBy(Employee::getDepartment,
 *                                                   Collectors.summingInt(Employee::getSalary)));
 *
 *     // Partition students into passing and failing
 *     Map<Boolean, List<Student>> passingFailing =
 *         students.stream()
 *                 .collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));
 *
 * }
 * </pre>
 *
 */
@SuppressWarnings("unused")
public class Java8Stream {

	/**
	 * Example, where we group score by name and calculating average at the same
	 * time. groupingBy(key), generally give Map<key,List<value>>.
	 * groupingBy(key,averagingDouble()) will give average of List<Value>, hence
	 * output is Map<Key,Value>
	 * 
	 * Later we used that output to find max value in map
	 * 
	 */
	public static void getMaxAverageScore() {
		String[][] input = { { "deepak", "20" }, { "deepak", "31" }, { "kumar", "24" }, { "kejriwal", "23" } };// {name,score}
		Stream<String[]> st = Arrays.stream(input);
		Map<String, Double> map = st.collect(Collectors.groupingBy(record -> record[0],
				Collectors.averagingDouble(record -> Double.parseDouble(record[1]))));
		Double maxAvgScore = map.values().stream().mapToDouble(x -> x).max().getAsDouble();// .orElseGet(()->0);
		System.out.println(maxAvgScore);
		// Output is 25.5 (Max of (20+31,24,23))
	}

	/**
	 * To join list of strings to single string
	 * 
	 * @param list of strings
	 */
	public static void joinListOfStrings(List<String> list) {
		// Method-1, using stream
		String str1 = list.stream().collect(Collectors.joining());
		// Method-2, using String.join
		String str2 = String.join("", list);
	}

	/**
	 * To sort the map by value and return as LinkedHashMap to preserve sorted order
	 * 
	 * @param map which need to be sorted by value
	 * @return sorted map
	 */

	public static Map<String, Integer> hashMapSortByValue(Map<String, Integer> input) {
		Stream<Entry<String, Integer>> entries = input.entrySet().stream();
		Map<String, Integer> sorted = entries.sorted(comparingByValue())
				.collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
		// toMap(key,value,mergeFunction if duplicate key exist,type of map)
		return sorted;
	}

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

}
