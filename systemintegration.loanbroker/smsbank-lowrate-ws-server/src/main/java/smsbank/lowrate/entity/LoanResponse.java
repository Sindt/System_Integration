package smsbank.lowrate.entity;

public class LoanResponse {

	private double interestRate;
	private String ssn;

	public LoanResponse(double interestRate, String ssn) {
		this.interestRate = interestRate;
		this.ssn = ssn;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

}
