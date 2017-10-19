package loanbroker.translator.smsbank.client;

import org.json.JSONObject;

import smsbank.lowrate.boundary.SmsBankLowRateWS;
import smsbank.lowrate.boundary.SmsBankLowRateWSServiceLocator;

public class SmsBankClient {

	SmsBankLowRateWSServiceLocator locator = new SmsBankLowRateWSServiceLocator();

	public void sendRequestToBank(byte[] body) {
		try {
			JSONObject json = transformBytesToJson(body);
			SmsBankLowRateWS stub = locator.getSmsBankLowRateWSPort();
			int loanDuration = json.getInt("loanDuration");
			int creditScore = json.getInt("creditScore");
			int loanAmount = json.getInt("loanAmount");
			String ssn = json.getString("ssn");
			System.out.println("SmsBankClient " + json);
			stub.loanRequest(loanDuration, creditScore, loanAmount, ssn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject transformBytesToJson(byte[] body) {
		try {
			return new JSONObject(new String(body));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
