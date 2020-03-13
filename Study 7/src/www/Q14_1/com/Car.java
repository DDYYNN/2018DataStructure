package www.Q14_1.com;

class Car {
	int gasolineGauge;
	public Car(int gasoline){ this.gasolineGauge = gasoline; }
}

class HybridCar extends Car {
	int electricGauge;
	public HybridCar(int gasoline, int electric){
		super(gasoline);
		this.electricGauge = electric;
	}
}

class HybridWaterCar extends HybridCar {
	int waterGauge;
	public HybridWaterCar(int gasoline, int electric, int water){
		super(gasoline, electric);
		this.waterGauge = water;
	}
	
	public void showCurrentGauge(){
		System.out.println("잔여 가솔린 : " + gasolineGauge);
		System.out.println("잔여 전기량 : " + electricGauge);
		System.out.println("잔여 워터량 : " + waterGauge);
	}
}

class CarTest {
	public static void main(String[] args){
		HybridWaterCar car1 = new HybridWaterCar(100, 50, 30);
		car1.showCurrentGauge();
	}
}
