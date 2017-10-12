package loanbroker.enricher.credit.controller;

import org.json.JSONObject;

import loanbroker.endpoint.Producer;
import loanbroker.enricher.credit.client.CreditClient;

public class CreditService {

	private static final String EXCHANGE_NAME = "kkc-enricher-creditscore";

	private CreditClient client;
	private Producer producer;

	public CreditService() {
		try {
			producer = new Producer(EXCHANGE_NAME);
			client = new CreditClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean enrichMessage(byte[] messagebytes) {
		JSONObject message = transformBytesToJson(messagebytes);
		System.out.println(message);
		if (message != null) {
			int creditScore;
			try {
				creditScore = client.getCreditScore(message);
				System.out.println(creditScore);
			} catch (Exception e) {
				e.printStackTrace();
				// Hvis clienten ikke svare, så sæt CS til 1
				creditScore = 1;
			}

			message.put("creditScore", creditScore);
			try {
				producer.sendMessageBasic(message.toString().getBytes());
				return true;
			} catch (Exception e) {
			}
		}
		return false;
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
