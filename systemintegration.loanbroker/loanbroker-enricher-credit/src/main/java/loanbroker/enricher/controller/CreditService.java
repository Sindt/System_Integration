package loanbroker.enricher.controller;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class CreditService {

	public void enrichMessage(byte[] messagebytes) {
		JsonObject message = transformBytesToJson(messagebytes);
		System.out.println(message);

	}

	public JsonObject transformBytesToJson(byte[] body) {
		String json = new String(body);
		try (JsonReader jsonReader = Json.createReader(new StringReader(json))) {
			return jsonReader.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
