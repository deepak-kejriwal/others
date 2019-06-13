package week4.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import util.blocks.Pair;
import week4.graphs.blocks.Graph;
import week4.graphs.blocks.Node;
import week4.graphs.blocks.State;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class BipartiteGraph {
	public static Pair<List<Node>> bipartite(Graph graph) {
		// set all levels to -1, all states to UNVISITED
		for (Node node : graph.getNodes()) {
			node.setState(State.UNVISITED);
			node.setLevel(-1);
		}
		List<Node> group1 = new ArrayList<Node>();
		List<Node> group2 = new ArrayList<Node>();

		for (Node node : graph.getNodes()) {
			if (node.getState() == State.UNVISITED) {
				Pair<List<Node>> groups = getBipartiteGroups(graph, node);
				if (groups == null) {// if any component is not bipartite, graph is not bipartite
					return null;
				}
				group1.addAll(groups.getFirst());
				group2.addAll(groups.getSecond());
			}
		}
		return new Pair<List<Node>>(group1, group2);
	}

	public static Pair<List<Node>> getBipartiteGroups(Graph graph, Node start) {
		Queue<Node> q = new LinkedList<Node>();
		List<Node> oddNodes = new ArrayList<Node>();
		List<Node> evenNodes = new ArrayList<Node>();
		q.add(start);
		start.setLevel(0);
		start.setState(State.VISITING);
		while (!q.isEmpty()) {
			Node current = q.remove();
			// process current
			if (current.getLevel() % 2 == 0)
				evenNodes.add(current);
			else
				oddNodes.add(current);
			for (Node neighbor : current.getNeighbors()) {
				if (neighbor.getState() == State.UNVISITED) {
					neighbor.setLevel(current.getLevel() + 1);
					q.add(neighbor);
					neighbor.setState(State.VISITING);
				} else if (neighbor.getLevel() == current.getLevel()) {
					// odd cycle found
					return null;
				}
			}
			current.setState(State.VISITED);
		}
		return new Pair<List<Node>>(oddNodes, evenNodes);
	}
}
