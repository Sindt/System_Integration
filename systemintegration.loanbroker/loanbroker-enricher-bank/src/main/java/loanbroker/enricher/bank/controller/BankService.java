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

	public boolean enrichMessage(byte[] messagebytes) {
		JSONObject message = transformBytesToJson(messagebytes);
		if (message != null) {
			try {
				producer.sendMessageBasic(message.toString().getBytes());
				return true;
			} catch (Exception e) {
			} finally {
				try {
					producer.close();
				} catch (Exception e) {
				}
			}
		}
		return false;

	}

	public JSONObject transformBytesToJson(byte[] body) {
		try {
			return new JSONObject(new String(body));
		} catch (Exception e) {
		}
		return null;

	}

}
