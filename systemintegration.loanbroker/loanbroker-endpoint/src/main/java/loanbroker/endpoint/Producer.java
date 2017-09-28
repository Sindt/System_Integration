package loanbroker.endpoint;

import java.io.IOException;

import com.rabbitmq.client.ConnectionFactory;

public class Producer extends EndPoint {

	public Producer(String endpointName, ConnectionFactory factory) throws IOException {
		super(endpointName, factory);
	}

	public void sendMessageBasic(byte[] message) throws IOException  {
	    channel.basicPublish("",endPointName, null, message);
	}
}
