package loanbroker.enricher.credit.boundary;

import loanbroker.endpoint.Consumer;
import loanbroker.enricher.credit.controller.CreditService;

public class MessageReceiver {

	private static final String EXCHANGE_NAME = "kkc-receiver";
	private static final String CONSUMER_TAG = "Credit";

	private CreditService service = new CreditService();
	private Consumer consumer;

	public void onMessage() {
		try {
			consumer = new Consumer(EXCHANGE_NAME, CONSUMER_TAG, false);
			Thread consumerThread = new Thread(consumer);
			consumerThread.start();
			while (true) {
				synchronized (consumer) {
					try {
						System.out.println("waiting..");
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
