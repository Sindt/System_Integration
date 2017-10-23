package org.loanbroker.aggregator;

public class LoanResults {

	public LoanResults(int i, ArrayList<LoanResponse> list) {
		this.i = i;
		this.list = list;
	}

	int i;
	ArrayList<LoanResponse> list;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setList(ArrayList<LoanResponse> list) {
		this.a = a;
	}

	public ArrayList<LoanResponse> getList() {
		return list;
	}

}
