package loanbroker.enricher.bank.client;

import org.json.JSONObject;

import rulebase.boundary.RuleBaseWS;
import rulebase.boundary.RuleBaseWSServiceLocator;

public class BankClient {

	private RuleBaseWSServiceLocator locator = new RuleBaseWSServiceLocator();

	public String[] getBanks(JSONObject message) {
		String[] bankList = null;
		try {
			int loanAmount = message.getInt("loanAmount");
			int loanDuration = message.getInt("loanDuration");
			int creditScore = message.getInt("creditScore");
			RuleBaseWS stub = locator.getRuleBaseWSPort();
			bankList = stub.getBanks(loanAmount, loanDuration, creditScore);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bankList;
	}

}
