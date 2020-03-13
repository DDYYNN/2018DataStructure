package www.study8_1.com;

class Computer {
	String owner;
	
	public Computer(String name) { owner = name; }
	public void calculate() { System.out.println("계산 실시");}
}

class NotebookComp extends Computer {
	int battery;
	
	public NotebookComp(String name, int initChag){
		super(name);
		battery = initChag;
	}
	
	public void charging() { battery += 5; }
	public void movingCal(){
		if(battery < 1){
			System.out.println("충전이 필요합니다.");
			return;
		}
	
		System.out.print("이동하면서 ");
		calculate();
		battery -= 1;
		
	}
}

class TabletNotebook extends NotebookComp{
	String regstPenModel;
	
	public TabletNotebook(String name, int initChag, String pen){
		super(name, initChag);
		regstPenModel = pen;
	}
	
	public void write(String penInfo){
		if(battery < 1){
			System.out.println("충전이 필요합니다.");
			return;
		}
		if(regstPenModel.compareTo(penInfo) != 0){
			System.out.println("등록된 펜이 아닙니다.");
			 return;
		}
		
		System.out.println("필기 내용 처리!");
		battery -= 1;
	}
}

public class IsAInheritance {
	public static void main(String[] args){
		NotebookComp nc = new NotebookComp("이수종", 5);
		TabletNotebook tn = new TabletNotebook("정수영", 5, "ISE-241-242");
		nc.movingCal();
		tn.write("ISE-241-242");
	}
}
