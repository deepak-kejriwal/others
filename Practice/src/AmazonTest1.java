import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AmazonTest1 {

	public static void main(String[] args) {
		  HashMap<Integer,Integer> map = new HashMap<>();
		 // map.put(1, 5);
		  map.computeIfPresent(1, (x,y) -> y+1);
		  
	
		  System.out.println(map);
		//AmazonTest1 amazonTest1 = new AmazonTest1();
		//System.out.println(amazonTest1.removeInvalidParentheses("(r(()()("));
	}

	private Set<String> validExpressions = new HashSet<String>();

	public List<String> removeInvalidParentheses(String s) {
		List<String> output = new ArrayList<>();
		removeHelper(s, output, 0, 0, '(', ')');
		return output;
	}

	public void removeHelper(String s, List<String> output, int iStart, int jStart, char openParen, char closedParen) {
		//if(iStart>=s.length())return;
		int numOpenParen = 0, numClosedParen = 0;
		for (int i = iStart; i < s.length(); i++) {
			if (s.charAt(i) == openParen)
				numOpenParen++;
			if (s.charAt(i) == closedParen)
				numClosedParen++;
			if (numClosedParen > numOpenParen) { // We have an extra closed paren we need to remove
				for (int j = iStart; j <= i; j++) // Try removing one at each position, skipping duplicates
					if (s.charAt(j) == closedParen && (j == iStart || s.charAt(j - 1) != closedParen))
						// Recursion: iStart = i since we now have valid # closed parenthesis thru i.
						// jStart = j prevents duplicates
						removeHelper(s.substring(0, j) + s.substring(j + 1, s.length()), output, i, j, openParen,
								closedParen);
				return; // Stop here. The recursive calls handle the rest of the string.
			}
		}
		// No invalid closed parenthesis detected. Now check opposite direction, or
		// reverse back to original direction.
		String reversed = new StringBuilder(s).reverse().toString();
		if (openParen == '(')
			removeHelper(reversed, output, 0, 0, ')', '(');
		else if(numOpenParen==numClosedParen)
			output.add(reversed);
	}
}
