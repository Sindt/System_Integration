package loanbroker.endpoint;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer extends EndPoint implements Runnable {

	private String consumerTag;
	private byte[] message;
	private boolean autoAck = true;

	public Consumer(String endPointName, String consumerTag, boolean autoAck) throws IOException, TimeoutException {
		super(endPointName);
		this.consumerTag = consumerTag;
		this.autoAck = autoAck;
	}

	public byte[] getMessage() {
		return message;
	}

	public void setMessage(byte[] message) {
		this.message = message;
	}

	@Override
	public void run() {
		try {
			while (true) {
				synchronized (this) {
					channel.basicConsume(endPointName, autoAck, consumerTag, handler(channel));
					System.out.println("Waiting consumer..");
					while (true) {
						if (message != null) {
							System.out.println("Notify!");
							notify();
						}
						wait(500);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private DefaultConsumer handler(Channel channel) {
		return new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				synchronized (this) {
					message = body;
					System.out.println(body.toString());
					if (!autoAck) {
						channel.basicAck(envelope.getDeliveryTag(), false);
					}
				}
			}
		};
	}

}
