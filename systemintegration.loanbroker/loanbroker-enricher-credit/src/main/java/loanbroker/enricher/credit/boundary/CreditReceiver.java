package loanbroker.enricher.credit.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import loanbroker.endpoint.Consumer;
import loanbroker.enricher.credit.controller.CreditService;

@Startup
@Singleton
public class CreditReceiver {

	private static final String EXCHANGE_NAME = "kkc-receiver";
	private static final String CONSUMER_TAG = "Credit";

	@PostConstruct
	public void init() {
		Thread consumerThread = null;
		try {
			CreditService serivce = new CreditService();
			Consumer consumer = new Consumer(EXCHANGE_NAME, CONSUMER_TAG, false);
			consumerThread = new Thread(consumer);
			consumerThread.start();

			while (consumerThread.isAlive()) {

				byte[] message = consumer.getMessage();
				if (message != null) {
					if (serivce.enrichMessage(message)) {
						consumer.setMessage(null);
					} else {
						continue;
					}
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
