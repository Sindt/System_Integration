package loanbroker.recipientlist.translator.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import loanbroker.endpoint.Producer;

public class RecipientListService {

	private static String EXCHANGE_NAME = "kkc-Translator-";

	private Producer producer;

	public RecipientListService() {
	}

	public void sendMessageBasic(byte[] messagebytes) {
		try {
			System.out.println("Transforming to JSON");
			JSONObject message = transformBytesToJson(messagebytes);
			System.out.println(message);
			List<String> banks = getRecipients(message);
			for (String bank : banks) {
				message.put("banks", bank);
				System.out.println(message);
				producer = new Producer(EXCHANGE_NAME + bank);
				producer.sendMessageBasic(message.toString().getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getRecipients(JSONObject message) {
		Object bankArray = message.get("banks");
		List<String> result = new ArrayList<>();

		if (bankArray instanceof JSONArray) {
			JSONArray arr = (JSONArray) bankArray;
			for (Object object : arr) {
				result.add(object.toString());
			}
		} else {
			String[] banks = (String[]) bankArray;
			for (Object bank : banks) {
				System.out.println(bank);
				result.add(bank.toString());
			}
		}

		return result;

	}

	private JSONObject transformBytesToJson(byte[] body) {
		try {
			return new JSONObject(new String(body));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
