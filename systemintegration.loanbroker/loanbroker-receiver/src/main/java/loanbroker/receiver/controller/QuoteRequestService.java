package loanbroker.receiver.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import loanbroker.endpoint.Producer;

public class QuoteRequestService {

	private static final String EXCHANGE_NAME = "kkc-receiver";

	private Producer producer;

	public QuoteRequestService() {
		try {
			producer = new Producer(EXCHANGE_NAME);
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}

	public ResponseBuilder sendMessageBasic(JSONObject input) {

		try {
			byte[] message = input.toString().getBytes();
			producer.sendMessageBasic(message);
			return Response.status(Status.OK).entity(message);

		} catch (Exception e) {
			JSONObject json = new JSONObject().put("error", e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(json);
		} finally {
			try {
				producer.close();
			} catch (IOException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
