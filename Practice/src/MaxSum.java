import java.util.HashMap;
import java.util.Map;

public class MaxSum {

	public static void main(String[] args) {
		int[] input1 = { 51, 17, 71, 42 };
		int[] input2 = { 42, 33, 60 };
		int[] input3 = { 51, 32, 43 };
		MaxSum impl = new MaxSum();
		System.out.println(impl.solution(input2));
	}

	// Return the digit sum of n
	private int digitSum(int n) {
		int sum = 0;
		while (n != 0) {
			sum += (n % 10);
			n /= 10;
		}
		return sum;
	}

	public int solution(int arr[]) {

		int max = -1;
		Map<Integer, Integer> map = new HashMap<>(); // Key is sum of digits of number and value is the number itself

		for (int n : arr) {
			
			int sum = digitSum(n); // find the sum of digits of number

			if (!map.containsKey(sum)) { 
				map.put(sum, n);
			} else {
				
				max = Math.max(max, map.get(sum) + n); // If sum exist then add with current number and update the max
				
				map.put(sum, Math.max(map.get(sum), n)); // Update the value in map with max value 
			}
		}

		return max;

	}

}
