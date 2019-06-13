package week1.LinkedList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class ShortestSubstring {

	public String getShortestSubString(String doc, String[] words) {
		String result = null;
		HashSet<String> wordSet = buildWordSet(words);
		HashMap<String, Node<String,Integer>> nodeMap = new HashMap<>();
		LinkedList<String,Integer> linkedList = new LinkedList<String,Integer>();
		/*
		 * We move any complicated string processing in a WordIterator. This lets us
		 * focus on the core algorithm instead of spending time on handling string
		 * corner cases. If the interviewer asks you to implement it, you can do that
		 * later. In most cases, the interviewer will not ask you to implement it.
		 */
		WordIterator iter = new WordIterator(doc);
		while (iter.hasNext()) {
			WordIndex wordIndex = iter.next();
			String word = wordIndex.getWord();
			if (!wordSet.contains(word))
				continue;
			if (nodeMap.containsKey(word)) {
				Node<String,Integer> toDelete = nodeMap.get(word);
				linkedList.delete(toDelete);
			}
			Node<String,Integer> newNode = new Node<>(word, wordIndex.getIndex());
			linkedList.append(newNode);
			nodeMap.put(word, newNode);

			if (nodeMap.size() == wordSet.size()) {
				Integer startIndex = linkedList.head.getData();
				int endIndex = linkedList.tail.getData() + linkedList.tail.getKey().length() - 1;
				if (result == null || (endIndex - startIndex + 1) < result.length()) {
					result = doc.substring(startIndex, endIndex + 1);
				}
			}
		}
		return result;
	}

	/*
	 * Helper Code: Ask interviewer if they want you to implement these.
	 */
	private HashSet<String> buildWordSet(String[] words) {
		HashSet<String> wordSet = new HashSet<>();
		for (String word : words) {
			wordSet.add(word);
		}
		return wordSet;
	}

	private class WordIndex {
		String word;
		int index;

		public WordIndex(String word, int index) {
			super();
			this.word = word;
			this.index = index;
		}

		public String getWord() {
			return word;
		}

		public int getIndex() {
			return index;
		}
	}

	/*
	 * In this iterator, the position variable will always be at the start of the
	 * next word in the string.
	 */
	public class WordIterator implements Iterator<WordIndex> {
		String str;
		int position;

		public WordIterator(String str) {
			this.str = str.trim(); // eliminate trailing whitespaces
			this.position = 0;
// advance position to next alphabet in str
			advanceToNextAlphabet();
		}/*
			 * Advances the position variable to the next alphabet
			 */

		private void advanceToNextAlphabet() {
			while (position < str.length() && !Character.isAlphabetic(str.charAt(position))) {
				position++;
			}
		}

		@Override
		public boolean hasNext() {
			return position < str.length();
		}

		@Override
		public WordIndex next() {
			if (!hasNext())
				return null;
			int wordStartIndex = position;
//advance position to next non-alphabet
			while (position < str.length() && Character.isAlphabetic(str.charAt(position))) {
				position++;
			}
			int wordEndIndex = position - 1;
			advanceToNextAlphabet();
			return new WordIndex(str.substring(wordStartIndex, wordEndIndex + 1), wordStartIndex);
		}
	}
}
