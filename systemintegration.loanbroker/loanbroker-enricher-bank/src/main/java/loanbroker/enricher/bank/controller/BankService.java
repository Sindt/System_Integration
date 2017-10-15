package loanbroker.enricher.bank.controller;

import org.json.JSONObject;

import loanbroker.endpoint.Producer;
import loanbroker.enricher.bank.client.BankClient;

public class BankService {

	private static final String EXCHANGE_NAME = "kkc-enricher-bank";

	private BankClient client;
	private Producer producer;

	public BankService() {
		try {
			producer = new Producer(EXCHANGE_NAME);
			client = new BankClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject enrichMessage(byte[] messagebytes) {

		JSONObject message = transformBytesToJson(messagebytes);
		if (message != null) {
			String[] banks = client.getBanks(message);
			try {
				message.put("banks", banks);
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
		}
		return null;

	}

}
