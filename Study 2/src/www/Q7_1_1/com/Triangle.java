package www.Q7_1_1.com;

public class Triangle{
	int base;
	int height;
	
	public Triangle(int b, int h){
		base = b;
		height = h;
	}
	
	public void refactorTriangle(int b, int h){
		base = b;
		height = h;
	}
	
	public double areaTriangle(){
		return 0.5 * base * height;
	}
	
	public void printFactors(){
		System.out.println("Dimensions");
		System.out.println("Base = "+base);
		System.out.println("Height = "+height);
	}
}

class TriangleMain{
	public static void main(String[] args){
		Triangle tr1 = new Triangle(20, 10);
		Triangle tr2 = new Triangle(35, 12);
		
		tr1.refactorTriangle(30, 20);
		tr1.printFactors();
		
		System.out.println("Area = " + tr2.areaTriangle());
	}
}
