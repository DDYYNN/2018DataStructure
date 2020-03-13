package www.Q7_1_2.com;

class MarbleKid{
	int numOfMarbles;
	public MarbleKid(int n){
		numOfMarbles = n;
	}
	
	public void resultMarble(){
		System.out.println("Marbles = " + numOfMarbles);
	}
	
	public void winMarble(MarbleKid loser, int n){			// Transaction method returns void.
															// Win and Lose are defined in one method together.
		if(loser.numOfMarbles < n){							// Wins all remaining marbles if loser has less than requested.
			numOfMarbles += loser.numOfMarbles;
			loser.numOfMarbles = 0;
			return;
		}
		numOfMarbles += n;
		loser.numOfMarbles -= n;
	}
	
//	public int loseMarble(int n){
//		if(numOfMarbles < n){
//			int tmp = numOfMarbles;
//			numOfMarbles = 0;
//			return tmp;
//		}
//		
//		numOfMarbles -= n;
//		return n;
//	}
//	
//	public void winMarble(MarbleKid loser, int n){
//		loser.loseMarble(n);
//		numOfMarbles += n;
//	}
}

class MarbleMain{
	public static void main(String[] args){
		MarbleKid kid1 = new MarbleKid(15);
		MarbleKid kid2 = new MarbleKid(9);
		
		kid1.winMarble(kid2, 2);
		kid2.winMarble(kid1, 7);
		
		kid1.resultMarble();
		kid2.resultMarble();
	}
}