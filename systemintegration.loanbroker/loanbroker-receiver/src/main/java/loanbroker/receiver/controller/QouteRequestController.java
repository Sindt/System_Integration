package loanbroker.receiver.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import loanbroker.endpoint.Producer;

public class QouteRequestController {

	private Producer producer;

	public QouteRequestController() {
		init();
	}

	public void init() {
		try {
			String queueName = "Test";
			producer = new Producer(queueName);
		} catch (IOException e) {
		}
	}

	public ResponseBuilder sendMessageBasic(JsonObject input) {

		try {
			byte[] message = input.toString().getBytes();
			producer.sendMessageBasic(message);
			return Response.status(Status.OK).entity(message);
		} catch (Exception e) {
			JsonObject json = Json.createObjectBuilder().add("error", e.toString()).build();
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
