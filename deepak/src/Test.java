import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Test {

	public static void main(String[] args) {
		
		
		String startDate="2018-12-10T00:00:00Z";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
		LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
		System.out.println(startDateTime);
	}
	private static int logBase2(int num) {
		return (int) (Math.log(num) / Math.log(2));
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

	public static int findDistance1(int rowIndex,int numRows, int colIndex,int numColumns, List<List<Integer>> area) {
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
