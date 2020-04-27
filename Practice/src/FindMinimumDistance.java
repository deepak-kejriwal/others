import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindMinimumDistance {
	private static int arraySize = 1000;

	public static void main(String[] args) {
		List<List<Integer>> area = testData102();	
		int distance = new FindMinimumDistanceAndPath().minimumDistance(area.size(), area.get(0).size(), area);
		System.out.println(distance);
	}

	static List<List<Integer>> testData100() {
		List<Integer> list1 = Stream.of(1, 0, 1, 1, 9).collect(Collectors.toList());
		List<Integer> list2 = Stream.of(1, 0, 1, 0, 1).collect(Collectors.toList());
		List<Integer> list3 = Stream.of(1, 0, 1, 1, 0).collect(Collectors.toList());
		List<Integer> list4 = Stream.of(1, 0, 0, 1, 0).collect(Collectors.toList());
		List<Integer> list5 = Stream.of(1, 0, 1, 1, 1).collect(Collectors.toList());
		List<Integer> list6 = Stream.of(1, 1, 1, 1, 1).collect(Collectors.toList());
		List<Integer> list7 = Stream.of(1, 1, 1, 1, 0).collect(Collectors.toList());
		List<List<Integer>> area = new ArrayList<>();
		area.add(list1);
		area.add(list2);
		area.add(list3);
		area.add(list4);
		area.add(list5);
		area.add(list6);
		area.add(list7);
		return area;
	}
	
	static List<List<Integer>> testData102() {
		List<Integer> list1 = Stream.of(1, 0, 9,1).collect(Collectors.toList());
		List<Integer> list2 = Stream.of(1, 1, 0,1).collect(Collectors.toList());
		List<Integer> list3 = Stream.of(1, 1, 1,1).collect(Collectors.toList());
		List<List<Integer>> area = new ArrayList<>();
		area.add(list1);
		area.add(list2);
		area.add(list3);
		
		return area;
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
	static List<List<Integer>> testData101() {
		List<Integer> list1 = Stream.of(1, 0, 0).collect(Collectors.toList());
		List<Integer> list2 = Stream.of(1, 0, 0).collect(Collectors.toList());
		List<Integer> list3 = Stream.of(1, 9, 1).collect(Collectors.toList());
		List<List<Integer>> area = new ArrayList<>();
		area.add(list1);
		area.add(list2);
		area.add(list3);
		return area;
	}
	public int minimumDistance(int numRows, int numColumns, List<List<Integer>> area) {
		if (numRows < 1 || numColumns < 1) {
			return -1;
		}
		if (numRows > 1000 || numColumns > 1000) {
			return -1;
		}
		boolean[][] visited = new boolean[numRows][numColumns];
		
		return findDistance(numRows, numColumns, area, visited);
	}

	public int findDistance(int numRows, int numColumns, List<List<Integer>> area, boolean[][] visited) {
		QItem source = new QItem(0, 0, 0);
		QItem newItem;
		LinkedList<QItem> list = new LinkedList<>();
		list.add(source);
		visited[0][0] = true;
		int distance = -1;
		while (!list.isEmpty()) {
			QItem item = list.removeFirst();
			int x = item.row;
			int y = item.col;
			int d = item.dist;
			if (area.get(x).get(y) == 9) {
				distance = d;
				break;
			}

			if (x + 1 < numRows) {
				if (area.get(x + 1).get(y) != 0 && !visited[x + 1][y]) {
					newItem = new QItem(x + 1, y, d + 1);
					list.add(newItem);
					visited[x + 1][y] = true;
				}
			}
			if (x - 1 >= 0) {
				if (area.get(x - 1).get(y) != 0 && !visited[x - 1][y]) {
					newItem = new QItem(x - 1, y, d + 1);
					list.add(newItem);
					visited[x - 1][y] = true;
				}
			}

			if (y + 1 < numColumns) {
				if (area.get(x).get(y + 1) != 0 && !visited[x][y + 1]) {
					newItem = new QItem(x, y + 1, d + 1);
					list.add(newItem);
					visited[x][y + 1] = true;
				}
			}
			if (y - 1 >= 0) {
				if (area.get(x).get(y - 1) != 0 && !visited[x][y - 1]) {
					newItem = new QItem(x, y - 1, d + 1);
					list.add(newItem);
					visited[x][y - 1] = true;
				}
			}

		}
		return distance;
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
