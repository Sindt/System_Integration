package loanbroker.enricher.credit.controller;

import org.json.JSONObject;

import loanbroker.endpoint.Producer;
import loanbroker.enricher.credit.client.CreditClient;

public class CreditService {

	private static final String EXCHANGE_NAME = "kkc-enricher-credit";

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

	public JSONObject enrichMessage(byte[] messagebytes) {
		JSONObject message = transformBytesToJson(messagebytes);
		if (message != null) {
			// Hvis clienten ikke svare, så sæt CS til 1
			int creditScore = 1;
			try {
				creditScore = client.getCreditScore(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			message.put("creditScore", creditScore);
			try {
				producer.sendMessageBasic(message.toString().getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return message;
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
