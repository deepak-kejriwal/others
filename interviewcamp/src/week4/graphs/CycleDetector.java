package week4.graphs;

import week4.graphs.blocks.Graph;
import week4.graphs.blocks.Node;
import week4.graphs.blocks.State;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class CycleDetector {
	
	public static boolean hasCycle(Graph graph) {

		for (Node node : graph.getNodes()) {
			if (node.getState() == State.UNVISITED && hasCycleDfsVisit(node))
				return true;
		}
		return false;
	}

	public static boolean hasCycleDfsVisit(Node node) {
		node.setState(State.VISITING);
		for (Node neighbor : node.getNeighbors()) {
			if (neighbor.getState() == State.UNVISITED && hasCycleDfsVisit(neighbor))
				return true;
			else if (neighbor.getState() == State.VISITING)
				return true;
		}
		node.setState(State.VISITED);
		return false;
	}
}
