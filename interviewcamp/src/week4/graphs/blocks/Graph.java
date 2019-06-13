package week4.graphs.blocks;

import java.util.List;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class Graph {
	List<Node> nodes;

	public Graph(List<Node> nodes) {
		super();
		this.nodes = nodes;
	}

	public void addNode(Node node) {
		nodes.add(node);
	}

	public List<Node> getNodes() {
		return nodes;
	}
}
