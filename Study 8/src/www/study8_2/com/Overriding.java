package www.study8_2.com;

class Speaker{
	private int volumeRate;
	public void showCurrentState(){
		System.out.println("볼륨 크기 : " + volumeRate);
	}
	public void setvolume(int vol){
		volumeRate = vol;
	}
}

class BassEnSpeaker extends Speaker{
	private int bassRate;
	
	public void showCurrentState(){
		super.showCurrentState();
		System.out.println("베이스 크기 : " + bassRate);
	}
	
	public void setBaseRate(int base){
		bassRate = base;
	}
}

public class Overriding {
	public static void main(String[] args){
		BassEnSpeaker bs = new BassEnSpeaker();
		bs.setvolume(10);
		bs.setBaseRate(20);
		bs.showCurrentState();
	}
}
