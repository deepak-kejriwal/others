import java.util.HashMap;

/**
* 
* @author Deepak Kejriwal
* 
* @reference https://medium.com/@krishankantsinghal/my-first-blog-on-medium-583159139237
*
*/

class Entry {
	int value;
	int key;
	Entry left;
	Entry right;
}
public class LRUCache {

	HashMap<Integer, Entry> hashmap;
	Entry start, end;
	int capacity; //Capacity of LRU Cache
						
	
	public LRUCache() {
		this(100);
	}
	
	public LRUCache(int capacity) {
		this.capacity=capacity;
		hashmap = new HashMap<Integer, Entry>();
	}

	public int getEntry(int key) {
		if (hashmap.containsKey(key)) // Key Already Exist, just update the
		{
			Entry entry = hashmap.get(key);
			removeNode(entry);
			addAtTop(entry);
			return entry.value;
		}
		return -1;
	}

	public void putEntry(int key, int value) {
		if (hashmap.containsKey(key)) // Key Already Exist, just update the value and move it to top
		{
			Entry entry = hashmap.get(key);
			entry.value = value;
			removeNode(entry);
			addAtTop(entry);
		} else {
			Entry newnode = new Entry();
			newnode.value = value;
			newnode.key = key;
			if (hashmap.size() >= capacity) // We have reached maximum capacity so need to make room for new element.
			{
				hashmap.remove(end.key);
				removeNode(end);				
				addAtTop(newnode);

			} else {
				addAtTop(newnode);
			}

			hashmap.put(key, newnode);
		}
	}
	public void addAtTop(Entry node) {
		node.right = start;
		node.left = null;
		if (start != null)
			start.left = node;
		start = node;
		if (end == null)
			end = start;
	}

	public void removeNode(Entry node) {

		if (node.left != null) {// Assume node is 5 and list is 1<-->5<-->6
			node.left.right = node.right;
		} else {
			start = node.right;// Assume node is 1 and list is 1<-->5<-->6
		}

		if (node.right != null) {// Assume node is 5 and list is 1<-->5<-->6
			node.right.left = node.left;
		} else {
			end = node.left;// Assume node is 6 and list is 1<-->5<-->6
		}
	}
	public static void main(String[] args) throws java.lang.Exception {
		// your code goes here
		LRUCache lrucache = new LRUCache(4);// Here i am setting 4 to test the LRU cache
		lrucache.putEntry(1, 1);
		lrucache.putEntry(10, 10);
		lrucache.putEntry(15, 15);
		lrucache.putEntry(12, 12);
		lrucache.getEntry(10);
		lrucache.putEntry(18, 18);
		lrucache.putEntry(13, 13);

		System.out.println(lrucache.getEntry(1));
		System.out.println(lrucache.getEntry(10));
		System.out.println(lrucache.getEntry(15));
		//Output is -1, 10, -1, as we access key=10, it moved at end and 15 was eligible for removal before 10
	}
}