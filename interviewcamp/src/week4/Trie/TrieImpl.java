package week4.Trie;

import week4.Trie.blocks.Node;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class TrieImpl {

	Node root;

	public TrieImpl() {
		root = new Node();
	}

	public void insert(String word) {
		char[] ch = word.toCharArray();
		Node current = root;
		for (int i = 0; i < ch.length; i++) {
			if (!current.contains(ch[i]))
				current.addNode(ch[i]);
			current = current.getNode(ch[i]);
		}
		current.setWord(true);
	}

	public boolean hasPrefix(String prefix) {
		char[] ch = prefix.toCharArray();
		Node current = root;
		for (int i = 0; i < ch.length; i++) {
			if (!current.contains(ch[i]))
				return false;
			current = current.getNode(ch[i]);
		}
		return true;
	}

	public boolean hasWord(String word) {
		char[] ch = word.toCharArray();
		Node current = root;
		for (int i = 0; i < ch.length; i++) {
			if (!current.contains(ch[i]))
				return false;
			current = current.getNode(ch[i]);
		}
		return current.isWord();
	}

	public void delete(String word) {
		if (word == null || word.isEmpty())
			return;
		Node lastNode = getLastNode(word);
		if (lastNode == null || !lastNode.isWord())
			return; // not found
		lastNode.setWord(false);
		// if last node has child, then there is another
		// word from there, don't delete any node.
		if (lastNode.getMap().size() > 0) {
			return;
		}
		// find the node to remove
		removeLastNodeWithMultipleChildren(word);
	}

	private Node getLastNode(String word) {
		char[] ch = word.toCharArray();
		Node current = root;
		for (int i = 0; i < ch.length; i++) {
			if (!current.contains(ch[i]))
				return null;
			current = current.getNode(ch[i]);
		}
		return current;
	}

	private void removeLastNodeWithMultipleChildren(String word) {
		char[] ch = word.toCharArray();
		Node lastNodeWithMultipleChildren = null;
		char childToBreak = 0;
		Node current = root;
		// find lowest parent with multiple nodes or word ending
		for (int i = 0; i < ch.length - 1; i++) {
			// get ith node
			if (!current.contains(ch[i])) // word not in trie
				return;
			current = current.getNode(ch[i]);
			if (current.getMap().size() > 1 || current.isWord()) {
				lastNodeWithMultipleChildren = current;
				childToBreak = ch[i + 1];
			}
		}
		if (lastNodeWithMultipleChildren != null) {
			lastNodeWithMultipleChildren.getMap().remove(childToBreak);
		} else {
			root.getMap().remove(ch[0]);
		}
	}
}
