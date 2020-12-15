import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class GoogleTopNUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	List<String> getTopNUsers(FileReader reader, int N){
		String logLine;
		Map<String, Integer> countMap = new HashMap<>();
		List<String> ansList = new ArrayList<>();
		
		
		
		PriorityQueue<Pair<String, Integer>> queue = new PriorityQueue<>((first, second)->Integer.compare(first.getValue(), second.getValue()));
		
		while(hasLogLines(reader)) {
			logLine = getLine(reader);
			String userName = getUserName(logLine);
			int wordCount = getWordCount(logLine.substring(logLine.indexOf('>')));
			countMap.put(userName, countMap.getOrDefault(userName, 0)+wordCount);
		}
		
		for(String userName: countMap.keySet()) {
			queue.add(new Pair<String, Integer>(userName, countMap.get(userName)));
			if(queue.size()>N) {
				queue.poll();
			}
		}
		
		while(!queue.isEmpty()) {
			ansList.add(queue.poll().getKey());
		}
		
		return ansList;
	}
	
	private String getLine(FileReader reader) {
		// TODO Auto-generated method stub
		return null;
	}

	class Pair<T, V>{
		T key;
		V value;
		
		Pair(T key, V value){
			this.key = key;
			this.value = value;
		}

		public T getKey() {
			return key;
		}

		public void setKey(T key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
		
		
	}
	boolean hasLogLines(FileReader reader) {
		return false;
	}
	
	String getUserName(String logLine) {
		int startIdx = logLine.indexOf('<');
		int endidx = logLine.indexOf('>');
		return logLine.substring(startIdx+1, endidx);
	}
	
	int getWordCount(String userText) {
		char[] input = userText.toCharArray();
		
		return 0;
	}

}
