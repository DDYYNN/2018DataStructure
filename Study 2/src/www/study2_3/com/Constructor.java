package www.study2_3.com;

class FruitSeller {
	int myMoney = 0;
	int numOfApple;
	final int APPLE_PRICE;			// 생성자에 의해 딱 한번 초기화 가능하므로 이제 final을 쓸 수 있다!

	public FruitSeller(int applenum, int price){
		numOfApple = applenum;
		APPLE_PRICE = price;
	}
	
	public int saleApple(int money) {
		int num = money / APPLE_PRICE;
		numOfApple -= num;
		myMoney += money;
		return num;
	}

	public void showSaleResult() {
		System.out.println("Remaining Apples : " + numOfApple);
		System.out.println("Revenue : +" + myMoney);
	}
}

class FruitBuyer {
	int myMoney = 10000;
	int numOfApple = 0;

	public void buyApple(FruitSeller seller, int money) {
		numOfApple += seller.saleApple(money);
		myMoney -= money;
	}

	public void showBuyResult() {
		System.out.println("Balance : " + myMoney);
		System.out.println("Apples bought : " + numOfApple);
	}
}

class FruitSalesMain1 {
	public static void main(String[] args) {
		FruitSeller seller1 = new FruitSeller(30, 1500);
		FruitSeller seller2 = new FruitSeller(20, 1000);
		
		FruitBuyer buyer = new FruitBuyer();
		buyer.buyApple(seller1, 4500);
		buyer.buyApple(seller2, 2000);

		System.out.println("Seller 1 : ");
		seller1.showSaleResult();
		System.out.println("Seller 2 : ");
		seller2.showSaleResult();

		System.out.println("Buyer : ");
		buyer.showBuyResult();
	}
}
