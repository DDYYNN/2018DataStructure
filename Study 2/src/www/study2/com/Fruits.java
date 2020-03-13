package www.study2.com;

class FruitSeller {
	final int APPLE_PRICE = 1000;
	// final은 C언어에서의 const 선언과 같다. const와의 차이점은, final은 선언 이후 초기화가 1번에 한해 가능하다.
	// 즉 다음과 같은 문법이 가능하다 :
	// final int APPLE_PRICE;
	// APPLE_PRICE = 1000;
	int myMoney = 0;
	int numOfApple = 20;

	public int saleApple(int money) {
		int num = money / 1000;
		numOfApple -= num;
		myMoney += money;
		return num;
	}

	public void showSaleResult() {
		System.out.println("Remaining Apples : " + numOfApple);
		System.out.println("Revenue : " + myMoney);
	}
}

class FruitBuyer {
	int myMoney = 5000;
	int numOfApple = 0;

	public void buyApple(FruitSeller seller, int money) {
		numOfApple += seller.saleApple(money);
		myMoney -= money;
	}

	public void showBuyResult() {
		System.out.println("Balance = " + myMoney);
		System.out.println("Apples bought : " + numOfApple);
	}
}

class FruitSalesMain1 {
	public static void main(String[] args) {
		FruitSeller seller = new FruitSeller();
		FruitBuyer buyer = new FruitBuyer();

		buyer.buyApple(seller, 2000);

		System.out.println("Seller : ");
		seller.showSaleResult();

		System.out.println("Buyer : ");
		buyer.showBuyResult();
	}
}
