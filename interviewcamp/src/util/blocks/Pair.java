package util.blocks;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class Pair<T> {
	T group1;
	T group2;

	public Pair(T group1, T group2) {

		this.group1 = group1;
		this.group2 = group2;
	}

	public T getFirst() {
		return group1;
	}

	public T getSecond() {
		return group2;
	}
}
