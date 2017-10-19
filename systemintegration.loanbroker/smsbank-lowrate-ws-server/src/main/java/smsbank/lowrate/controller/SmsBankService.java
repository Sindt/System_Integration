package smsbank.lowrate.controller;

import org.json.JSONObject;

import loanbroker.endpoint.Producer;
import smsbank.lowrate.entity.LoanResponse;

public class SmsBankService {

	private static final String EXCHANGE_NAME = "kkc-smsbank-lowrate";

	private Producer producer;

	public SmsBankService() {
		try {
			producer = new Producer(EXCHANGE_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendLoanResponse(LoanResponse loanResponse) {
		try {
			JSONObject message = new JSONObject().put("interestRate", loanResponse.getInterestRate()).put("ssn",
					loanResponse.getSsn());
			producer.sendMessageBasic(message.toString().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
