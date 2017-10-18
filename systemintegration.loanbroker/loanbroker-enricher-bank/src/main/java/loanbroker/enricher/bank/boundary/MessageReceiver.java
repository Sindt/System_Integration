package loanbroker.enricher.bank.boundary;

import loanbroker.endpoint.Consumer;
import loanbroker.enricher.bank.controller.BankService;

public class MessageReceiver {

	private static final String EXCHANGE_NAME = "kkc-enricher-credit";
	private static final String CONSUMER_TAG = "Bank";

	private BankService service = new BankService();
	private Consumer consumer;

	public void onMessage() {
		try {
			consumer = new Consumer(EXCHANGE_NAME, CONSUMER_TAG, false);
			Thread consumerThread = new Thread(consumer);
			consumerThread.start();
			while (true) {
				synchronized (consumer) {
					try {
						System.out.println(CONSUMER_TAG + " waiting..");
						consumer.wait();
						byte[] message = consumer.getMessage();
						System.out.println(message);
						service.enrichMessage(message);
						consumer.setMessage(null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
