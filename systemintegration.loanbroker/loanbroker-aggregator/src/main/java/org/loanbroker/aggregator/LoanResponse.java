package org.loanbroker.aggregator;

public class LoanResults {

	public LoanResults(int i, ArrayList<BankResponse> list) {
		this.i = i;
		this.list = list;
	}

	int i;
	ArrayList<BankResponse> list;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setList(ArrayList<BankResponse> list) {
		this.a = a;
	}

	public ArrayList<BankResponse> getList() {
		return list;
	}

}
