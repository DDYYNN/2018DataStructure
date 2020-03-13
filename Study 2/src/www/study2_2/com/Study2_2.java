package www.study2_2.com;

// "파는 행위"를 기준으로 코드 작성

class FruitBuyer {
	final int APPLE_PRICE = 1000;					// 상수 생성 
	int myMoney = 5000;								// Fruitbuyer의 두 인자(myMoney, numOfApple)
	int numOfApple = 0;

	public int buyApple(int money) {				// Fruitbuyer의 행위 : money를 주고 산다.
		int num = money / APPLE_PRICE;
		numOfApple += num;
		myMoney -= money;
		return num;
	}

	public void showBuyResult() {
		System.out.println("Balance = " + myMoney);
		System.out.println("Apples bought : " + numOfApple);
	}
}

class FruitSeller {
	int myMoney = 0;
	int numOfApple = 20;

	public void saleApple(FruitBuyer buyer, int money){
		numOfApple -= buyer.buyApple(money);		// Fruitseller의 행위 : buyer에게 money를 받고 판다.
		myMoney += money;
	}

	public void showSaleResult() {
		System.out.println("Remaining Apples : " + numOfApple);
		System.out.println("Revenue : " + myMoney);
	}
}

class FruitSalesMain1 {
	public static void main(String[] args) {
		FruitSeller seller = new FruitSeller();
		FruitBuyer buyer = new FruitBuyer();
		
		seller.saleApple(buyer, 2000);
		
		seller.showSaleResult();
		buyer.showBuyResult();
	}
}

