import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class GoogleTopNUser2 {

	public static void main(String[] args) throws IOException {
		GoogleTopNUser2 impl = new GoogleTopNUser2();
		FileReader fileReader = new FileReader("C:\\2\\git\\others\\Practice\\src\\test.txt");
		System.out.println(impl.getTopNUsers(fileReader, 2));

	}

	List<String> getTopNUsers(FileReader reader, int N) throws IOException {

		Map<String, Integer> countMap = new HashMap<>();

		PriorityQueue<String> queue = new PriorityQueue<>(
				(first, second) -> Integer.compare(countMap.get(first), countMap.get(second)));

		BufferedReader bufferedReader = new BufferedReader(reader);
		String logLine;
		while ((logLine = bufferedReader.readLine()) != null) {
			LineInfo userInfo = getLineInfo(logLine);
			if(userInfo!=null) {
				countMap.put(userInfo.userName, countMap.getOrDefault(userInfo.userName, 0) + userInfo.wordcount);
				queue.add(userInfo.userName);
				if (queue.size() > N) {
					queue.poll();
				}
			}
		}
		bufferedReader.close();
		return new ArrayList<>(queue);
	}

	private LineInfo getLineInfo(String logLine) {
		int n = logLine.length();
		int i = 0;
		if (i < n) {
			int startIdx = -1;
			
			//iterate until start of user found
			while (i < n && logLine.charAt(i++) != '<');
			
			startIdx = i;
			
			//iterate until end of user found
			while (i < n && logLine.charAt(i++) != '>');
			
			String userName = logLine.substring(startIdx, i - 1);
			int wordCount = 0;
			while (i < n) {
				
				//iterate until there is no whitespace, if whitespace word ends.
				while (i < n && !Character.isWhitespace(logLine.charAt(i++)));
				wordCount++;
			}
			return new LineInfo(userName, wordCount);
		}
		return null;
	}

	class LineInfo {
		String userName;
		int wordcount;

		LineInfo(String userName, int wordcount) {
			this.userName = userName;
			this.wordcount = wordcount;
		}
	}

}
