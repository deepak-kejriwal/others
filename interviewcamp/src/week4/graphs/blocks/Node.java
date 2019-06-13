package week4.graphs.blocks;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class Node {
	List<Node> neighbors;
	int data;
	int color;
	State state;
	int level;

	public Node(int data) {
		super();
		this.data = data;
		level = -1;
		state = State.UNVISITED;
		neighbors = new ArrayList<Node>();
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void addNeighbor(Node node) {
		neighbors.add(node);
	}

	public List<Node> getNeighbors() {
		return neighbors;
	}
}
