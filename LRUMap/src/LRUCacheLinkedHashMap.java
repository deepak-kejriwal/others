
/**
* 
* @author Deepak Kejriwal
*
*/
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_MAX_ENTRIES = 100;
	int maxEntry; // Capacity of LRU Cache

	public LRUCacheLinkedHashMap() {
		this(DEFAULT_MAX_ENTRIES);
	}

	public LRUCacheLinkedHashMap(int maxEntry) {
		super(16, 0.75f, true);
		this.maxEntry = maxEntry;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > maxEntry;
	}

	public static void main(String[] args) throws java.lang.Exception {
		// your code goes here
		LRUCacheLinkedHashMap<Integer, Integer> lrucache = new LRUCacheLinkedHashMap<>(4);// Here i am setting 4 to test
																							// the LRU cache
		lrucache.put(1, 1);
		lrucache.put(10, 10);
		lrucache.put(15, 15);
		lrucache.put(12, 12);
		lrucache.get(10);
		lrucache.put(18, 18);
		lrucache.put(13, 13);

		System.out.println(lrucache.get(1));
		System.out.println(lrucache.get(10));
		System.out.println(lrucache.get(15));
		// Output is null, 10, nul, as we access key=10, it moved at end and 15 was
		// eligible for removal before 10
	}
}
