package smsbank.lowrate.boundary;

import javax.jws.WebService;

import smsbank.lowrate.entity.LoanResponse;

@WebService
public class SmsBankLowRateWS {

	private static final double BASERATE = 15;

	public LoanResponse loanRequest(int loanDuration, int creditScore, int loanAmount, String ssn) {

		double interestRate = getInterestRate(loanDuration, creditScore, loanAmount);
		return new LoanResponse(interestRate, ssn);
	}

	public double getInterestRate(int loanDuration, int creditScore, int loanAmount) {
		double rate = BASERATE;
		double loan = loanAmount;
		double percent = 1 - (loan / 1000000);
		for (int i = 0; i < loanDuration; i++) {
			rate *= percent;
		}
		return rate;
	}

}
