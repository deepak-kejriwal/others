import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
		int n=4;
		System.out.println(n<<1);
AtomicInteger ai;
Stack<Integer> stk=new Stack<>();
stk.push(5);
stk.push(6);
		List<Integer> list=new ArrayList<>(stk);
		stk.pop();
		list.stream().collect(Collectors.groupingBy(x->x,TreeMap::new,Collectors.toList()));

		List<Integer> list1 = Stream.of(1, 0, 0).collect(Collectors.toList());
		List<Integer> list2 = Stream.of(1, 0, 0).collect(Collectors.toList());
		List<Integer> list3 = Stream.of(1, 9, 1).collect(Collectors.toList());
		List<List<Integer>> area = new ArrayList<>();
		area.add(list1);
		area.add(list2);
		area.add(list3);
		int distance = minimumDistance(3, 3, area);
		System.out.println(distance);
	}

	public static int minimumDistance(int numRows, int numColumns, List<List<Integer>> area) {
		if (numRows < 1 || numColumns < 1) {
			return -1;
		}
		if (numRows > 1000 || numColumns > 1000) {
			return -1;
		}
		return findDistance(0,numRows, 0,numColumns, area);
	}
	
	
	
	public static int findDistance(int rowIndex,int numRows, int colIndex,int numColumns, List<List<Integer>> area) {
		if (rowIndex < 0 || colIndex < 0) {
			return -1;
		}
		if (rowIndex >= numRows || colIndex >= numColumns) {
			return -1;
		}
		if (area.get(rowIndex).get(colIndex) == 0) {
			return -1;
		}
		if (area.get(rowIndex).get(colIndex) == 9) {
			return 0;
		}
		int distance = findDistance(rowIndex + 1,numRows, colIndex,numColumns, area);
		if (distance == -1) {
			distance = findDistance(rowIndex,numRows, colIndex + 1,numColumns,area);
		}
		if (distance == -1) {
			distance = findDistance(rowIndex - 1, numRows,colIndex,numColumns, area);
		}
		if (distance == -1) {
			distance = findDistance(rowIndex,numRows, colIndex - 1,numColumns,area);
		}
		return distance + 1;
	}

}
