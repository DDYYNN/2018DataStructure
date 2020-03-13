package www.Q9_2.com;

class Point {
	int xPos, yPos;
	
	public Point(int x, int y){
		xPos = x;
		yPos = y;
	}
	
	public void showPointInfo() { System.out.println("["+xPos+", "+yPos+"]"); }
	
	public static double pointDist(Point A, Point B){
		return Math.sqrt(Math.pow((B.xPos - A.xPos),2) + Math.pow((B.yPos - A.yPos),2));
	}
}

class Circle {
	Point center;
	int rad;
	
	public Circle(int x, int y, int r){
		center = new Point(x,y);
		rad = r;
	}
	
	public void showCircleInfo(){
		System.out.println("radius = " + rad);
		center.showPointInfo();
	}
}

class Ring {
	Circle c1, c2;
	private int state;
	
	public Ring(int x1, int y1, int r1, int x2, int y2, int r2){
		c1 = new Circle(x1, y1, r1);
		c2 = new Circle(x2, y2, r2);

		int radsum = c1.rad + c2.rad;
		int raddiff = Math.abs(c1.rad - c2.rad);
		
		if (centerDist() == 0) state = 1;
		else if (centerDist() > 0 && centerDist() < raddiff) state = 2;
		else if (centerDist() == raddiff) state = 3;
		else if (centerDist() > raddiff && centerDist() < radsum) state = 4;
		else if (centerDist() == radsum) state = 5;
		else if (centerDist() > radsum) state = 6;
	}
	
	public void showRingInfo(){
		System.out.println("Circle 1 Info...");
		c1.showCircleInfo();
		System.out.println("Circle 2 Info...");
		c2.showCircleInfo();
		if (state < 4){
			if (c1.rad > c2.rad) System.out.println("Circle 1 bounds Circle 2.");
			else if (c1.rad < c2.rad) System.out.println("Circle 2 bounds Circle 1.");
			else System.out.println("Two Circles are congruent.");
		}
	}
	
	double centerDist(){
		return Point.pointDist(c1.center, c2.center);
	}
	
	public void showRingState(){
		switch(state){
		case 1 :
			System.out.println("=> The circles are concentric."); break;
		case 2 :
			System.out.println("=> The outer circle contains the inner one."); break;
		case 3 : 
			System.out.println("=> The inner circle is inscribed in the outer one."); break;
		case 4 :
			System.out.println("=> The circles meet in two points."); break;
		case 5 : 
			System.out.println("=> The circles circumscribe."); break;
		case 6 :
			System.out.println("=> The circles never meet."); break;
		}
	}
	
	public int meetPoints(){
		int result = 0;
		
		switch(state){
		case 1 : case 2 : case 6 :
			result = 0;
			break;
		case 3 : case 5 :
			result = 1;
			break;
		case 4 :
			result = 2;
			break;
		}
		
		return result;
	}
}

class RingMain{
	public static void main(String[]args){
		Ring ring = new Ring(1,1,2,4,5,3);
		ring.showRingInfo();
		System.out.println("Center Distance = " + ring.centerDist());
		ring.showRingState();
		System.out.println("=> Meet at " + ring.meetPoints() + " point(s).");
	}
}