import java.util.ArrayList;

public class Station {
	private String idNum;
	private String name;
	private String line;
	private ArrayList<Edge> edgeList;

	public Station(String idNum, String name, String line) {
		this.idNum = idNum;
		this.name = name;
		this.line = line;
		edgeList = new ArrayList<>();
	}

	public String getIdNum() {
		return idNum;
	}

	public String getName() {
		return name;
	}

	public String getLine() {
		return line;
	}

	public ArrayList<Edge> getEdgeList() {
		return edgeList;
	}

	public void addEdge(Edge e) {
		edgeList.add(e);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (idNum == null) {
			if (other.idNum != null)
				return false;
		} else if (!idNum.equals(other.idNum))
			return false;
		return true;
	}
}
