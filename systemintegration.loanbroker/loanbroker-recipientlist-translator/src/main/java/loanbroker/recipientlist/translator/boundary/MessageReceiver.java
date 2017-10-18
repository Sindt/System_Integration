package loanbroker.recipientlist.translator.boundary;

import loanbroker.endpoint.Consumer;
import loanbroker.recipientlist.translator.controller.RecipientListService;

public class MessageReceiver {

	private static final String EXCHANGE_NAME = "kkc-enricher-bank";
	private static final String CONSUMER_TAG = "RCPT-Translator";

	private Consumer consumer;
	private RecipientListService service = new RecipientListService();

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
						service.sendMessageBasic(message);
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