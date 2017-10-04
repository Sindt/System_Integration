package loanbroker.enricher.credit.client;

import org.bank.credit.web.service.CreditScoreService_PortType;
import org.bank.credit.web.service.CreditScoreService_ServiceLocator;
import org.json.JSONObject;

public class CreditClient {

	private CreditScoreService_ServiceLocator locator = new CreditScoreService_ServiceLocator();

	public int getCreditScore(JSONObject message) {
		try {
			CreditScoreService_PortType stub = locator.getCreditScoreServicePort();
			return stub.creditScore(message.getString("ssn"));
		} catch (Exception e) {
		}
		return 0;

	}

}
