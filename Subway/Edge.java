
public class Edge {
	private Station source;
	private Station destination;
	private int weight;
	private boolean transfer;

	public Edge(Station source, Station destination, int weight, boolean transfer) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.transfer = transfer;
	}

	public Station getSource() {
		return source;
	}

	public Station getDestination() {
		return destination;
	}

	public int getWeight() {
		return weight;
	}

	public boolean isTransfer() {
		return transfer;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		}
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!(source.equals(other.source) && destination.equals(other.destination)))
			return false;
		return true;
	}
}
