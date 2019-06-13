package week4.graphs;

import week4.graphs.blocks.Graph;
import week4.graphs.blocks.Node;
import week4.graphs.blocks.State;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class ConnectedComponents {

	public static void colorConnectedComponents(Graph graph, int target) {
		int color = 0;
		for (Node node : graph.getNodes()) {
			if (node.getState() == State.UNVISITED) {
				dfsVisit(node, color++);
			}
		}
	}

	public static void dfsVisit(Node node, int color) {
		node.setState(State.VISITING);
		node.setColor(color);
		for (Node neighbor : node.getNeighbors()) {
			if (neighbor.getState() == State.UNVISITED) {
				dfsVisit(neighbor, color);
			}
		}
		node.setState(State.VISITED);
	}
}
