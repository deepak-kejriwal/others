import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Pattern;

public class WordMachine {
	public static final int MAX = 0xFFFFF;
	public static final int MIN = 0;
	private static final Pattern NUMERIC = Pattern.compile("\\d+");
	private final Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) {
		// String test = "13 DUP 4 POP 5 DUP + DUP + -";
		String test = "5 6 + -";
		// String test = "3 DUP 5 - -";
		WordMachine machine = new WordMachine();
		System.out.println(machine.solution(test));
	}

	public int solution(String S) {
		try {
			Arrays.stream(S.split(" ")).forEach(this::applyCommand);
			return pop();
		} catch (IllegalArgumentException e) {
			return -1;
		}
	}

	private void applyCommand(String s) {
		if (NUMERIC.matcher(s).matches()) {
			push(Integer.valueOf(s));
		} else {
			switch (s) {
			case "POP":
				this.pop();
				break;
			case "DUP":
				this.dup();
				break;
			case "+":
				this.add();
				break;
			case "-":
				this.sub();
				break;
			default:
				throw new IllegalArgumentException(s);

			}
		}
	}

	private void push(int i) {
		withinRange(i);
		stack.push(i);
	}

	private int pop() {
		return stack.pop();
	}

	private void dup() { // Create duplicate of topmost number and add to stack
		hasElements(1);
		push(stack.peek());
	}

	private void add() { // Pops the two topmost elements from stack, add them and push the result back to stack
		hasElements(2);
		push(stack.pop() + stack.pop());
	}

	private void sub() { // Pops the two topmost elements from stack, subtract the second number of stack from topmost and push the result back to stack
		hasElements(2);
		push(stack.pop() - stack.pop());
	}

	private int hasElements(int i) { //Stack should have minimum i elements
		if (stack.size() < i) {
			throw new IllegalArgumentException("Too few elements available");
		}
		return i;
	}

	private int withinRange(int i) { //Number should be with MIN-MAX range.
		if (i < MIN || i > MAX) {
			throw new IllegalArgumentException("Input under/overflow");
		}
		return i;
	}

}