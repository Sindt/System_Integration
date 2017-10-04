package loanbroker.enricher.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import loanbroker.endpoint.Consumer;
import loanbroker.enricher.controller.CreditService;

@Startup
@Singleton
public class CreditReceiver {

	@PostConstruct
	public void init() {
		Thread consumerThread = null;
		try {
			CreditService serivce = new CreditService();
			Consumer consumer = new Consumer("Test", "Credit", false);
			consumerThread = new Thread(consumer);
			consumerThread.start();

			while (consumerThread.isAlive()) {
				byte[] message = null;
				if ((message = consumer.getMessage()) != null) {
					serivce.enrichMessage(message);
					consumer.setMessage(null);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			consumerThread.interrupt();
		}

	}

}
