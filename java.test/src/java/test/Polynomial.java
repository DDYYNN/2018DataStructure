package java.test;

import java.util.Vector;
// Skeleton of the Polynomial ADT

public class Polynomial {
	Vector<Integer> _coef = new Vector<Integer>();
	Vector<Integer> _exp = new Vector<Integer>();
	int num;
  // Create an empty polynomial
	public Polynomial() {
		num = 0;
	}

  // Create a single-term polynomial
	public Polynomial(int coef, int exp) {
		if(coef != 0){
			_coef.add(coef);
			_exp.add(exp);
			num = 1;
		}
		else
			num = 0;
	}

  // Add opnd to 'this' polynomial; 'this' is returned
	public Polynomial add(Polynomial opnd) {
		Polynomial temp = new Polynomial();
		int totnum = num + opnd.num;
		int i = 0, j = 0, k = 0;
		while(totnum > k){
			if(i==num){
				temp._exp.add(opnd._exp.elementAt(j));
				temp._coef.add(opnd._coef.elementAt(j));
				j++;
				k++;
			}
			else if (j==opnd.num){
				temp._exp.add(_exp.elementAt(i));
				temp._coef.add(_coef.elementAt(i));
				i++;
				k++;
			}
			else if(opnd._coef.elementAt(j) == 0){
				j++;
				totnum--;
			}
			else if(_exp.elementAt(i)>opnd._exp.elementAt(j)){
				temp._exp.add(_exp.elementAt(i));
				temp._coef.add(_coef.elementAt(i));
				i++;
				k++;
			}
			else if (_exp.elementAt(i)<opnd._exp.elementAt(j)){
				temp._exp.add(opnd._exp.elementAt(j));
				temp._coef.add(opnd._coef.elementAt(j));
				j++;
				k++;
			}
			else{
				if(_coef.elementAt(i) + opnd._coef.elementAt(j)==0){
					totnum -= 2;
					i++;
					j++;
				}
				else{
					temp._exp.add(opnd._exp.elementAt(j));
					temp._coef.add(_coef.elementAt(i)+opnd._coef.elementAt(j));
					i++;
					j++;
					k++;
					totnum--;
				}
			}
		}
		this.num = totnum;
		this._exp.removeAllElements();
		this._coef.removeAllElements();
		for(int n = 0; n < totnum; n++){
			this._coef.add(temp._coef.elementAt(n));
			this._exp.add(temp._exp.elementAt(n));
		}
		return this;
	}

  // Subtract opnd from 'this' polynomial; 'this' is returned
	public Polynomial sub(Polynomial opnd) {
		Polynomial temp = new Polynomial();
		int totnum = num + opnd.num;
		int i = 0, j = 0, k = 0;
		while(totnum > k){
			if(i==num){
				temp._exp.add(opnd._exp.elementAt(j));
				temp._coef.add(opnd._coef.elementAt(j));
				j++;
				k++;
			}
			else if (j==opnd.num){
				temp._exp.add(_exp.elementAt(i));
				temp._coef.add(_coef.elementAt(i));
				i++;
				k++;
			}
			else if(opnd._coef.elementAt(j) == 0){
				j++;
				totnum--;
			}
			else if(_exp.elementAt(i)>opnd._exp.elementAt(j)){
				temp._exp.add(_exp.elementAt(i));
				temp._coef.add(_coef.elementAt(i));
				i++;
				k++;
			}
			else if (_exp.elementAt(i)<opnd._exp.elementAt(j)){
				temp._exp.add(opnd._exp.elementAt(j));
				temp._coef.add(opnd._coef.elementAt(j));
				j++;
				k++;
			}
			else{
				if(_coef.elementAt(i) - opnd._coef.elementAt(j)==0){
					totnum -= 2;
					i++;
					j++;
				}
				else{
					temp._exp.add(opnd._exp.elementAt(j));
					temp._coef.add(_coef.elementAt(i)-opnd._coef.elementAt(j));
					i++;
					j++;
					k++;
					totnum--;
				}
			}
		}
		this.num = totnum;
		this._exp.removeAllElements();
		this._coef.removeAllElements();
		for(int n = 0; n < totnum; n++){
			this._coef.add(temp._coef.elementAt(n));
			this._exp.add(temp._exp.elementAt(n));
		}
		return this;
	}

  // Print the terms of 'this' polynomial in decreasing order of exponents.
  // No pair of terms can share the same exponent in the printout.
	public void print() {
		for(int i = 0; i < num; i++){
			System.out.printf("%d %d ",_coef.elementAt(i),_exp.elementAt(i));
		}
	}
}
