package loanbroker.receiver.boundary;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class QuoteRequestResourceTest {

	QuoteRequestResource res = new QuoteRequestResource();

	@Test
	public void test() throws IOException, TimeoutException {

		ConnectionFactory connfac = new ConnectionFactory();
		connfac.setHost("datdb.cphbusiness.dk");
		connfac.setPort(5672);
		connfac.setUsername("student");
		connfac.setPassword("cph");
		Connection connection = connfac.newConnection();
		Channel channel = connection.createChannel();
	}
}
