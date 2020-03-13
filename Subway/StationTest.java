import java.util.Scanner;

public class StationTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Graph g = new Graph();
		while (true) {
			String bf = sc.nextLine();
			if (bf.equals(""))
				break;
			String[] bufs = bf.split(" ");

			g.addStation(bufs[0], bufs[1], bufs[2]);
		}
		while (true) {
			String bf = sc.nextLine();
			if (bf.equalsIgnoreCase(""))
				break;
			String[] bufs = bf.split(" ");

			g.addEdge(bufs[0], bufs[1], Integer.parseInt(bufs[2]));
		}

		System.out.println(g);
		while (true) {
			String bf = sc.nextLine();
			if (bf.equalsIgnoreCase("quit"))
				break;
			String[] bufs = bf.split(" ");
			g.findShortest(bufs[0], bufs[1]);
			g.findMinTransfer(bufs[0], bufs[1]);

		}
	}
}
