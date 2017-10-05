package loanbroker.endpoint;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer extends EndPoint implements Runnable {

	private String consumerTag;
	private byte[] message;
	private boolean autoAck = true;

	public Consumer(String endpointName, String consumerTag, boolean autoAck) throws IOException {
		super(endpointName);
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
			channel.basicConsume(endPointName, autoAck, consumerTag, handler(channel));			
			while (true) {
				Thread.sleep(500);
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
				message = body;

				while (message != null) {
					try {
						if (!autoAck) {
							channel.basicAck(envelope.getDeliveryTag(), false);
						}
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
				}

			}
		};
	}

}
