import java.util.HashMap;

/**
 * 
 * @author Deepak Kejriwal
 * 
 * @reference https://medium.com/@krishankantsinghal/my-first-blog-on-medium-583159139237
 *
 */

class Node {
	int value;
	int key;
	Node left;
	Node right;
}

public class LRUCacheAlternate {

	HashMap<Integer, Node> hashmap;
	Node start, end;
	private static final int DEFAULT_MAX_ENTRIES = 100;
	int maxEntry; // Capacity of LRU Cache

	public LRUCacheAlternate() {
		this(DEFAULT_MAX_ENTRIES);
	}

	public LRUCacheAlternate(int maxEntry) {
		this.maxEntry = maxEntry;
		hashmap = new HashMap<>();
		start = new Node();
		end = new Node();
		start.right = end;
		end.left = start;
	}

	public int getNode(int key) {
		if (hashmap.containsKey(key)) // Key Already Exist, just update the
		{
			Node Node = hashmap.get(key);
			removeNode(Node);
			addAtTop(Node);
			return Node.value;
		}
		return -1;
	}

	public void putNode(int key, int value) {
		if (hashmap.containsKey(key)) // Key Already Exist, just update the value and move it to top
		{
			Node Node = hashmap.get(key);
			Node.value = value;
			removeNode(Node);
			addAtTop(Node);
		} else {
			Node newnode = new Node();
			newnode.value = value;
			newnode.key = key;
			if (hashmap.size() >= maxEntry) // We have reached maximum capacity so need to make room for new element.
			{
				hashmap.remove(end.left.key);
				removeNode(end.left);
				addAtTop(newnode);

			} else {
				addAtTop(newnode);
			}

			hashmap.put(key, newnode);
		}
	}

	private void addAtTop(Node node) {
		node.right = start.right;
		start.right = node;
		node.left = start;
		node.right.left = node;
	}

	private void removeNode(Node node) {
		node.left.right = node.right;
		node.right.left = node.left;
	}

	public static void main(String[] args) throws java.lang.Exception {
		// your code goes here
		LRUCacheAlternate lrucache = new LRUCacheAlternate(4);// Here i am setting 4 to test the LRU cache
		lrucache.putNode(1, 1);
		lrucache.putNode(10, 10);
		lrucache.putNode(15, 15);
		lrucache.putNode(12, 12);
		lrucache.getNode(10);
		lrucache.putNode(18, 18);
		lrucache.putNode(13, 13);

		System.out.println(lrucache.getNode(1));
		System.out.println(lrucache.getNode(10));
		System.out.println(lrucache.getNode(15));
		// Output is -1, 10, -1, as we access key=10, it moved at end and 15 was
		// eligible for removal before 10
	}
}