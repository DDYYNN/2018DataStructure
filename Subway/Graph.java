import java.util.*;

public class Graph {
	private int numStations;
	private HashMap<String, Integer> idNum_indx;
	private HashMap<String, ArrayList<String>> name_idNum;
	private ArrayList<Station> stnList;

	public Graph() {
		numStations = 0;
		idNum_indx = new HashMap<>();
		name_idNum = new HashMap<>();
		stnList = new ArrayList<>();
	}

	public void addStation(String idNum, String name, String line) {
		idNum_indx.put(idNum, numStations);
		Station here = new Station(idNum, name, line);
		ArrayList<String> idNumListFormer = name_idNum.get(name);
		if (idNumListFormer != null) {
			for (String idNumFormer : idNumListFormer) {
				Station former = stnList.get(idNum_indx.get(idNumFormer));
				former.addEdge(new Edge(former, here, 5, true));
				here.addEdge(new Edge(here, former, 5, true));
			}
			idNumListFormer.add(idNum);
		} else {
			ArrayList<String> idNumList = new ArrayList<>();
			idNumList.add(idNum);
			name_idNum.put(name, idNumList);
		}
		stnList.add(here);
		numStations++;
	}

	public void addEdge(String sourceId, String dstnId, int weight) {
		Station source = stnList.get(idNum_indx.get(sourceId));
		Station dstn = stnList.get(idNum_indx.get(dstnId));
		source.addEdge(new Edge(source, dstn, weight, false));
	}

	public void findShortest(String startName, String dstnName) {
		HashSet<Station> visitedStations = new HashSet<>();
		ArrayList<Edge> visitedEdges = new ArrayList<>();
		Distance[] distance = new Distance[numStations];
		boolean[] visited = new boolean[numStations];

		int duration = 0;

		for (int i = 0; i < numStations; i++) {
			distance[i] = new Distance(Integer.MAX_VALUE, Integer.MAX_VALUE);
			visited[i] = false;
		}

		int startIndx = idNum_indx.get(name_idNum.get(startName).get(0));
		Station start = stnList.get(startIndx);
		visitedStations.add(start);
		distance[startIndx] = new Distance(0, false);
		visited[startIndx] = true;
		int dstnIndx = idNum_indx.get(name_idNum.get(dstnName).get(0));
		Station dstn = stnList.get(dstnIndx);

		for (Edge adjEdge : start.getEdgeList()) {
			int adjIndx = idNum_indx.get(adjEdge.getDestination().getIdNum());
			if (adjEdge.isTransfer()) {
				distance[adjIndx] = new Distance(0, true);
			} else {
				distance[adjIndx] = new Distance(adjEdge.getWeight(), false);
			}
			visitedEdges.add(adjEdge);
		}

		for (int i = 1; i < numStations; i++) {
			Distance mindist = new Distance(Integer.MAX_VALUE, Integer.MAX_VALUE);
			int minIndx = 0;
			for (int j = 0; j < numStations; j++) {
				if (visited[j])
					continue;
				if (distance[j].compareTo(mindist) < 0) {
					mindist = distance[j];
					minIndx = j;
				}
			}
			Station minStation = stnList.get(minIndx);
			visitedStations.add(minStation);
			visited[minIndx] = true;
			for (Edge adjEdge : minStation.getEdgeList()) {
				int adjIndx = idNum_indx.get(adjEdge.getDestination().getIdNum());
				if (!visited[adjIndx]) {
					Distance cmp = distance[minIndx].add(adjEdge.getWeight(), adjEdge.isTransfer());
					if (distance[adjIndx].compareTo(cmp) > 0) {
						distance[adjIndx] = cmp;
						visitedEdges.add(adjEdge);
					}
				}
			}
			if (minStation.getName().equals(dstn.getName())) {
				duration = distance[minIndx].getWeight();
				break;
			}
		}

		ArrayList<Edge> route = DFSroute(visitedEdges, start, dstn);

		printRoute(route, duration);
	}

	public void findMinTransfer(String startName, String dstnName) {
		ArrayList<Station> visitedStations = new ArrayList<>();
		ArrayList<Edge> visitedEdges = new ArrayList<>();
		Distance[] distance = new Distance[numStations];
		boolean[] visited = new boolean[numStations];

		int duration = 0;

		for (int i = 0; i < numStations; i++) {
			distance[i] = new Distance(Integer.MAX_VALUE, Integer.MAX_VALUE);
			visited[i] = false;
		}

		int startIndx = idNum_indx.get(name_idNum.get(startName).get(0));
		Station start = stnList.get(startIndx);
		visitedStations.add(start);
		distance[startIndx] = new Distance(0, false);
		visited[startIndx] = true;
		int dstnIndx = idNum_indx.get(name_idNum.get(dstnName).get(0));
		Station dstn = stnList.get(dstnIndx);

		for (Edge adjEdge : start.getEdgeList()) {
			int adjIndx = idNum_indx.get(adjEdge.getDestination().getIdNum());
			if (adjEdge.isTransfer()) {
				distance[adjIndx] = new Distance(0, true);
			} else {
				distance[adjIndx] = new Distance(adjEdge.getWeight(), false);
			}
			visitedEdges.add(adjEdge);
		}

		for (int i = 1; i < numStations; i++) {
			Distance mindist = new Distance(Integer.MAX_VALUE, Integer.MAX_VALUE);
			int minIndx = 0;
			for (int j = 0; j < numStations; j++) {
				if (visited[j])
					continue;
				if (distance[j].compareToTransfer(mindist) < 0) {
					mindist = distance[j];
					minIndx = j;
				}
			}
			Station minStation = stnList.get(minIndx);
			visitedStations.add(minStation);
			visited[minIndx] = true;
			for (Edge adjEdge : minStation.getEdgeList()) {
				int adjIndx = idNum_indx.get(adjEdge.getDestination().getIdNum());
				if (!visited[adjIndx]) {
					Distance cmp = distance[minIndx].add(adjEdge.getWeight(), adjEdge.isTransfer());
					if (distance[adjIndx].compareToTransfer(cmp) > 0) {
						distance[adjIndx] = cmp;
						visitedEdges.add(adjEdge);
					}
				}
			}
			// if (minStation.getName().equals(dstn.getName())) {
			// 	duration = distance[minIndx].getWeight();
			// 	break;
			// }
		}

		ArrayList<Edge> route = DFSroute(visitedEdges, start, dstn);

		printRoute(route, duration);
	}

	private ArrayList<Edge> DFSroute(ArrayList<Edge> visitedEdges, Station start, Station dstn) {
		Stack<Edge> stack = new Stack<>();
		HashMap<Edge, Boolean> visit = new HashMap<>();
		for (Edge e : visitedEdges) {
			visit.put(e, false);
		}

		stack.push(new Edge(start, start, 0, false));
		while (!stack.isEmpty()) {
			Station topStn = stack.peek().getDestination();
			ArrayList<Edge> topEdgeList = topStn.getEdgeList();
			Edge unvisitedEdge = null;
			for (Edge e : topEdgeList) {
				if (visitedEdges.contains(e) && visit.get(e) == false)
					unvisitedEdge = e;
			}
			if (unvisitedEdge == null) {
				stack.pop();
			} else {
				stack.push(unvisitedEdge);
				visit.put(unvisitedEdge, true);
				if (unvisitedEdge.getDestination().getName().equals(dstn.getName()))
					break;
			}
		}
		Stack<Edge> tmp = new Stack<>();
		while (!stack.isEmpty()) {
			tmp.push(stack.pop());
		}
		tmp.pop();
		ArrayList<Edge> result = new ArrayList<>();
		while (!tmp.isEmpty()) {
			result.add(tmp.pop());
		}
		return result;
	}

	private void printRoute(ArrayList<Edge> route, int duration) {
		int i = 0;
		while (i < route.size()) {
			Edge thisEdge = route.get(i);
			Station start = thisEdge.getSource();
			if (thisEdge.isTransfer()) {
				if (i > 0) {
					System.out.print("[" + start.getName() + "] ");
					i++;
				}
			} else {
				System.out.print(start.getName() + " ");
			}
			i++;
		}
		Station dstn = route.get(i - 1).getDestination();
		System.out.println(dstn.getName());

		System.out.println(duration);
	}
}

class Distance implements Comparable<Distance> {
	private int weight;
	private int isTransfer;

	public Distance(int wgt, int transfer) {
		weight = wgt;
		isTransfer = transfer;
	}

	public Distance(int wgt, boolean transfer) {
		weight = wgt;
		isTransfer = transfer ? 1 : 0;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Distance o) {
		if (weight < o.weight)
			return -1;
		else if (weight > o.weight)
			return 1;
		else {
			if (isTransfer < o.isTransfer)
				return -1;
			else if (isTransfer > o.isTransfer)
				return 1;
			else
				return 0;
		}
	}

	public int compareToTransfer(Distance o) {
		if (isTransfer < o.isTransfer)
			return -1;
		else if (isTransfer > o.isTransfer)
			return 1;
		else {
			if (weight < o.weight)
				return -1;
			else if (weight > o.weight)
				return 1;
			else
				return 0;
		}
	}

	public Distance add(int w, int t) {
		int wgt = weight + w;
		int trns = isTransfer + t;
		return new Distance(wgt, trns);
	}

	public Distance add(int w, boolean t) {
		return this.add(w, t ? 1 : 0);
	}
}
