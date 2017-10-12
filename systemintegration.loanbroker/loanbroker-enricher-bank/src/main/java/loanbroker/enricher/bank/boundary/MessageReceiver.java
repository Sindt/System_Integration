package loanbroker.enricher.bank.boundary;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import loanbroker.endpoint.Consumer;
import loanbroker.enricher.bank.controller.BankService;

@Startup
@Singleton
public class MessageReceiver {

	private static final String EXCHANGE_NAME = "kkc-enricher-creditscore";
	private static final String CONSUMER_TAG = "Bank";

	private BankService service;
	private Consumer consumer;

	@PostConstruct
	public void init() {
		try {
			service = new BankService();
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
			while (true) {
				synchronized (consumer) {
					System.out.println("Waiting.. bank");
					consumer.wait();
					System.out.println("Modtaget bank");
					byte[] message = consumer.getMessage();
					if (service.enrichMessage(message)) {
						consumer.setMessage(null);
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
