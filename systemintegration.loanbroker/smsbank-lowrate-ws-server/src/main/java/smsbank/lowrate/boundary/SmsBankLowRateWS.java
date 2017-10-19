package smsbank.lowrate.boundary;

import javax.jws.WebService;

import smsbank.lowrate.controller.SmsBankService;
import smsbank.lowrate.entity.LoanResponse;

@WebService
public class SmsBankLowRateWS {

	private static final double BASERATE = 15;

	private final SmsBankService service = new SmsBankService();

	public LoanResponse loanRequest(int loanDuration, int creditScore, int loanAmount, String ssn) {
		double interestRate = getInterestRate(loanDuration, creditScore, loanAmount);
		System.out.println("interest : " + interestRate);
		LoanResponse loanResponse = new LoanResponse(interestRate, ssn);
		service.sendLoanResponse(loanResponse);
		return loanResponse;
	}

	private double getInterestRate(int loanDuration, int creditScore, int loanAmount) {
		double rate = BASERATE;
		double loan = loanAmount;
		double percent = 1 - (loan / 1000000);
		for (int i = 0; i < loanDuration; i++) {
			rate *= percent;
		}
		return rate;
	}

}
