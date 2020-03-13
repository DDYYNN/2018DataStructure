package www.weightgraph.com;

import java.util.*;

public class Graph{
	private final ArrayList<Vertex> vertexes;
	private final ArrayList<Edge> edges;
	
	public Graph(ArrayList<Vertex> vertexes, ArrayList<Edge> edges){
		this.vertexes = vertexes;
		this.edges = edges;
	}

	public ArrayList<Vertex> getVertexes() {
		return vertexes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}
	
	
}
