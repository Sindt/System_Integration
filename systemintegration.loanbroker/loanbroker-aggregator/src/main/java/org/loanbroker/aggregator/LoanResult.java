package org.loanbroker.aggregator;

public class LoanResponse {
	String ssn, bank;
	double interestRate;

	public LoanResponse(String ssn, String bank, double interestRate) {
		this.ssn = ssn;
		this.bank = bank;
		this.interestRate = interestRate;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public double getInterest_rate() {
		return interest_rate;
	}

	public void setInterest_rate(double interest_rate) {
		this.interest_rate = interest_rate;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

}
