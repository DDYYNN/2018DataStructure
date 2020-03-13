package www.Q9_1.com;

class Rectangle {
	private int ulx, uly;
	private int lrx, lry;
	
	public void showArea(){
		int xLen = lrx-ulx;
		int yLen = lry-uly;
		System.out.println("Area = " + (xLen * yLen));
	}
	
	private boolean inProperRange(int n){
		if (n<0 || n>100) return false;
		else return true;
	}
	
	public void setRectangle(int uplftx, int uplfty, int lowrightx, int lowrighty){
		if ( lowrightx<uplftx || lowrighty<uplfty ){
			System.out.println("Invalid rectangle!");
			return;
		}
		
		if (!inProperRange(uplftx) || !inProperRange(uplfty)){
			System.out.println("Invalid upper-left value!");
			return;
		}
		
		if (!inProperRange(lowrightx) || !inProperRange(lowrighty)){
			System.out.println("Invalid lower-right value!");
			return;
		}
		
		ulx = uplftx; uly = uplfty;
		lrx = lowrightx; lry = lowrighty;
	}
}

class RectangleTest {
	public static void main(String[] args){
		Rectangle rec = new Rectangle();
		Rectangle recx = new Rectangle();
		
		recx.setRectangle(-3,-1,2,7);
		rec.setRectangle(15, 16, 78, 69);
		rec.showArea();
	}
}