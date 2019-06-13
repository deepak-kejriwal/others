package week5;

import java.util.ArrayList;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class GenerateParenthesis {

	private void addParenApproach1(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {
		if (leftRem < 0 || rightRem < leftRem)
			return;// invalid state

		if (leftRem == 0 && rightRem == 0) {/* Out of left and right parentheses */
			list.add(new String(str));
		} else {
			str[index] = '('; // Add left and recurse
			addParenApproach1(list, leftRem - 1, rightRem, str, index + 1);

			str[index] = ')'; // Add right and recurse
			addParenApproach1(list, leftRem, rightRem - 1, str, index + 1);
		}
	}

	private void addParenApproach2(ArrayList<String> list, int leftRem, int rightRem, StringBuilder str, int index) {


		if (leftRem == 0 && rightRem == 0) {/* Out of left and right parentheses */
			list.add(new String(str));
			return;
		} 
		if(leftRem>0) {
			StringBuilder left=new StringBuilder(str);
			left.append('('); // Add left and recurse
			addParenApproach2(list, leftRem - 1, rightRem, left, index + 1);
		}
		if(rightRem>leftRem) {
			StringBuilder right=new StringBuilder(str);
			right.append( ')'); // Add right and recurse
			addParenApproach2(list, leftRem, rightRem - 1, right, index + 1);
		}
	}
	
	public ArrayList<String> generateParens(int count) {
		char[] str = new char[count * 2];
		StringBuilder sb=new StringBuilder();
		ArrayList<String> list = new ArrayList<String>();
		addParenApproach2(list, count, count, sb, 0);
		return list;
	}

	public static void main(String[] args) {
		GenerateParenthesis gp = new GenerateParenthesis();
		System.out.println(gp.generateParens(3));
	}

}
