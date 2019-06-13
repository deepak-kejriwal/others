package week1.ArraysAndStringsI;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class ReverseWords {
	
	public static String reverseWords(String s) {

		StringBuilder builder = new StringBuilder();
		int wordEnd = s.length();
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				if (builder.length() > 0) // not empty, add a space
					builder.append(' ');
				builder.append(s.substring(i + 1, wordEnd));
				wordEnd = i;
			}
		}
		String firstWord = s.substring(0, wordEnd);
		// always good to check for null
		if (firstWord != null && firstWord.length() > 0) {
			if (builder.length() > 0)
				builder.append(' ');
			builder.append(firstWord);
		}
		return builder.toString();
	}
	/*
	 * Notice that above, you used the following lines twice:
	 *
	 * if (builder.length() > 0) builder.append(' '); builder.append(firstWord);
	 *
	 * It is good to point that out. Mention that in the real world, you will
	 * extract them into a separate function.
	 */

}
