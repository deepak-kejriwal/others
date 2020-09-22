
public class GoogleReorderSpace {

	public static void main(String[] args) {
		String input = " ";
		System.out.println(reorderSpaces(input));

	}

	public static String reorderSpaces(String text) {
		char[] chars = text.toCharArray();

		int space[] = { 0 };
		int word[] = { 0 };

		int j = alignRight(chars, space, word);

		reorderSpace(chars, space, word, j);

		return new String(chars);
	}

	private static void reorderSpace(char[] chars, int[] space, int[] word, int j) {
		int n = chars.length;
		int i = 0;
		int between =word[0] >1? space[0] / (word[0] - 1):space[0];

		while (i < n && j < n) {

			while (j < n && chars[j] != ' ') {
				chars[i++] = chars[j++];
			}
			
			j++;
			
			for (int k = 0; k < between && i < n; ++k) {
				chars[i++] = ' ';
			}
		}
	}

	private static int alignRight(char[] chars, int[] space, int[] word) {
		int n = chars.length;
		int i = n - 1;
		int j = n - 1;
		int charStart = 0;
		while (i >= 0 && j >= 0) {

			while (j >= 0 && chars[j] == ' ') {
				space[0]++;
				j--;
			}

			if(j>=0) {
				word[0]++;
			}
			while (j >= 0 && chars[j] != ' ') {
				charStart = i;
				chars[i--] = chars[j--];			
			}

			
			
			if (j >= 0) {
				
				space[0]++;
				chars[i--] = chars[j--];
			}

		}
		return charStart;
	}
}
