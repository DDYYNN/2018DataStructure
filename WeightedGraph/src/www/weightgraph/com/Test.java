package www.weightgraph.com;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {
	private ArrayList<Vertex> nodes;
	private ArrayList<Edge> edges;

	public Test() {
		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		// 정점(노드) 추가.
		for (int i = 0; i <= 7; i++) {
			Vertex location = new Vertex("Node_" + (i), "Node_" + (i));
			nodes.add(location);
		}
		// 간선(경로) 추가.
		addLane("1-2", 1, 2, 10);
		addLane("1-3", 1, 3, 4);
		addLane("1-4", 1, 4, 2);
		addLane("2-4", 2, 4, 7);
		addLane("2-5", 2, 5, 12);
		addLane("3-4", 3, 4, 15);
		addLane("3-6", 3, 6, 13);
		addLane("4-5", 4, 5, 18);
		addLane("4-6", 4, 6, 20);
		addLane("5-6", 5, 6, 9);
		addLane("5-7", 5, 7, 13);
		addLane("6-7", 6, 7, 1);
		Graph graph = new Graph(nodes, edges);
		DijkstraSearch dijkstra = new DijkstraSearch(graph);
		dijkstra.execute(nodes.get(1));
		LinkedList<Vertex> path = dijkstra.getPath(nodes.get(7));
		for (Vertex vertex : path) {
			System.out.println(vertex);
		}
	}

	public static void main(String[] args) {
		new Test();
	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
		Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
		edges.add(lane);
	}
}
