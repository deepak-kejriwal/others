import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MsftQ1 {
	private static String S = "ccaaffddecee";

	public static void main(String[] args) {
		int[] frequency = new int[26];

		for (char c : S.toCharArray()) {
			frequency[c - 'a']++;
		}

		// Arrays.stream(frequency).boxed().toArray(Integer[]::new);
		Arrays.sort(frequency);
		Set<Integer> taken = new HashSet<>();
		int result = 0;
		for (int i = 25; i >= 0; --i) {
			int c = frequency[i];

			while (taken.contains(c)) {
				c--;
				result++;
			}

			if (c != 0) {
				taken.add(c);
			}
		}
		System.out.println(result);
	}

}

/*
 * ccaaffddecee 1+3
 * 
 * a-2 c-3 d-1 e-0
 * 
 * 
 * ccaaffddecee
 * 
 * a-2 f-2 d-2 e-3 c-3
 * 
 * 
 * 1+1 c-3 e-2 d-1
 * 
 * c-3 a-2 f-2 d-2 e-3
 * 
 */