package loanbroker.receiver.controller;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.rabbitmq.client.ConnectionFactory;

import loanbroker.endpoint.Producer;

public class QouteRequestController {

	private Producer producer;

	public QouteRequestController() {
		init();
	}

	public void init() {
		try {
			String queueName = "Test";

			ConnectionFactory connfac = new ConnectionFactory();
			connfac.setHost("datdb.cphbusiness.dk");
			connfac.setPort(5672);
			connfac.setUsername("student");
			connfac.setPassword("cph");

			producer = new Producer(queueName, connfac);
		} catch (IOException e) {
		}
	}

	public ResponseBuilder sendMessageBasic(JsonObject input) {

		try {
			byte[] message = input.toString().getBytes();
			producer.sendMessageBasic(message);
			producer.close();
			return Response.status(Status.OK).entity(message);
		} catch (Exception e) {
			JsonObject json = Json.createObjectBuilder().add("error", e.toString()).build();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(json);
		}
	}

}
