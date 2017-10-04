package loanbroker.endpoint;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class EndPoint {

	protected Channel channel;
	protected String endPointName;
	protected Connection connection;

	public EndPoint(String endpointName) throws IOException {
		this.endPointName = endpointName;

		try {
			ConnectionFactory connfac = new ConnectionFactory();
			connfac.setHost("datdb.cphbusiness.dk");
			connfac.setPort(5672);
			connfac.setUsername("student");
			connfac.setPassword("cph");
			connection = connfac.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare(endpointName, false, false, false, null);

		} catch (TimeoutException e) {
			e.printStackTrace();
		}

	}

	public void close() throws IOException, TimeoutException {
		this.channel.close();
		this.connection.close();
	}

}
