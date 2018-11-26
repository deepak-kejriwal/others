import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test3 {
private static int arraySize=1000;
	
	public static void main(String[] args) {

		int distance = new Test3().minimumDistance(arraySize, arraySize, createData1000());
		System.out.println(distance);
	}
	public static void main1(String[] args) {
		List<Integer> list1 = Stream.of(1, 0, 0).collect(Collectors.toList());
		List<Integer> list2 = Stream.of(1, 0, 0).collect(Collectors.toList());
		List<Integer> list3 = Stream.of(1, 9, 1).collect(Collectors.toList());
		List<List<Integer>> area = new ArrayList<>();
		area.add(list1);
		area.add(list2);
		area.add(list3);
		int distance = new Test3().minimumDistance1(3, 3, area);
		System.out.println(distance);
	}

	static List<List<Integer>> createData1000() {
		List<List<Integer>> outerList=new ArrayList();
		List<Integer> innerList;
		for(int i=0;i<arraySize;i++) {
			 innerList=new ArrayList();
			for(int j=0;j<arraySize;j++) {
				innerList.add(1);
			}
			outerList.add(innerList);
		}
		outerList.get(800).set(850, 9);
		return outerList;
	}

	public int minimumDistance(int numRows, int numColumns, List<List<Integer>> area) {
		if (numRows < 1 || numColumns < 1) {
			return -1;
		}
		if (numRows > 1000 || numColumns > 1000) {
			return -1;
		}

		QItem source = new QItem(0, 0, 0);
		QItem newItem;
		LinkedList<QItem> list = new LinkedList<>();
		list.add(source);
		while (!list.isEmpty()) {
			QItem item = list.removeLast();
			int x = item.row;
			int y = item.col;
			int d = item.dist;
			if (area.get(x).get(y) == 9) {
				return d;
			}
			if (x < numRows - 1) {
				newItem = new QItem(x + 1, y, d + 1);
				if (area.get(x + 1).get(y) == 1) {

					list.add(newItem);
				} else if (area.get(x + 1).get(y) == 9) {
					return newItem.dist;
				}

			}
			if (y < numColumns - 1) {
				newItem = new QItem(x, y + 1, d + 1);
				if (area.get(x).get(y + 1) == 1) {

					list.add(newItem);
				} else if (area.get(x).get(y + 1) == 9) {
					return newItem.dist;
				}

			}

		}

		return 0;
	}

	public int minimumDistance1(int numRows, int numColumns, List<List<Integer>> area) {
		if (numRows < 1 || numColumns < 1) {
			return -1;
		}
		if (numRows > 1000 || numColumns > 1000) {
			return -1;
		}
		return findDistance(0, numRows, 0, numColumns, area);
	}

	public static int findDistance(int rowIndex, int numRows, int colIndex, int numColumns, List<List<Integer>> area) {
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
		int distance = findDistance(rowIndex + 1, numRows, colIndex, numColumns, area);
		if (distance == -1) {
			distance = findDistance(rowIndex, numRows, colIndex + 1, numColumns, area);
		}
		
		if (distance == -1) {
			distance = findDistance(rowIndex - 1, numRows, colIndex, numColumns, area);
		}
		if (distance == -1) {
			distance = findDistance(rowIndex, numRows, colIndex - 1, numColumns, area);
		}
		return distance + 1;
	}

	public static int findDistance1(int rowIndex, int numRows, int colIndex, int numColumns, List<List<Integer>> area) {
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
		int distance = findDistance(rowIndex + 1, numRows, colIndex, numColumns, area);
		if (distance == -1) {
			distance = findDistance(rowIndex, numRows, colIndex + 1, numColumns, area);
		}
		if (distance == -1) {
			distance = findDistance(rowIndex - 1, numRows, colIndex, numColumns, area);
		}
		if (distance == -1) {
			distance = findDistance(rowIndex, numRows, colIndex - 1, numColumns, area);
		}
		return distance + 1;
	}

	class QItem {
		int row;
		int col;
		int dist;
		QItem(int x, int y, int w)

		{
			row = x;
			col = y;
			dist = w;
		}
	}
}
