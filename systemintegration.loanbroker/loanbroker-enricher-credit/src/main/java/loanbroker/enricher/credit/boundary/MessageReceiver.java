package loanbroker.enricher.credit.boundary;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import loanbroker.endpoint.Consumer;
import loanbroker.enricher.credit.controller.CreditService;

@Startup
@Singleton
public class MessageReceiver {

	private static final String EXCHANGE_NAME = "kkc-receiver";
	private static final String CONSUMER_TAG = "Credit";

	private CreditService serivce;
	private Consumer consumer;

	@PostConstruct
	public void init() {
		try {
			serivce = new CreditService();
			consumer = new Consumer(EXCHANGE_NAME, CONSUMER_TAG, false);
			onMessage();
		} catch (IOException e) {
		}

	}

	public void onMessage() {
		Thread consumerThread = null;
		try {
			consumerThread = new Thread(consumer);
			consumerThread.start();

			while (consumerThread.isAlive()) {
				byte[] message = consumer.getMessage();
				if (message != null) {
					serivce.enrichMessage(message);
					consumer.setMessage(null);
				}
				// Waits 1 sec before checking for new message
				Thread.sleep(1000);
			}
		} catch (Exception e) {
		} finally {
			consumerThread.interrupt();
		}

	}

}
