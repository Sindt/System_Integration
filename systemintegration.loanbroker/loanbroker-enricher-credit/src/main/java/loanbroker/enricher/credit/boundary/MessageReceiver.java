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

	private CreditService service;
	private Consumer consumer;

	@PostConstruct
	public void init() {
		try {
			service = new CreditService();
			consumer = new Consumer(EXCHANGE_NAME, CONSUMER_TAG, false);
			onMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onMessage() {
		Thread consumerThread = null;
		try {
			consumerThread = new Thread(consumer);
			consumerThread.start();
			while (true) {
				synchronized (consumer) {
					System.out.println("Waiting.. creditscore");
					consumer.wait();
					byte[] message = consumer.getMessage();
					if (service.enrichMessage(message)) {
						consumer.setMessage(null);
						continue;
					}
				}
			}
		} catch (Exception e) {
		}
	}
}
