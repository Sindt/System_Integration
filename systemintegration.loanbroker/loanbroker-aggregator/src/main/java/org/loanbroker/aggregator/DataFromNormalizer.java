package org.loanbroker.aggregator;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import loanbroker.endpoint.Consumer;

public class DataFromNormalizer {
	
	private static final String EXCHANGE_NAME = "kkc-agg-jsonbank";
	private static final String CONSUMER_TAG = "jsonBank";
	
	private Consumer consumer;
	
	public void onMessage() throws exception {
		
		consumer = new Consumer(EXCHANGE_NAME, CONSUMER_TAG, false);
		
		Thread t1 = new Thread(consumer);
		
		t1.start();
		while(true) {
			synchronized(consumer) {
				try {
					System.out.println(CONSUMER_TAG + " waiting..");
					consumer.wait();
					
					String message = Arrays.toString(consumer.getMessage());
					Produce p = new Produce();
					try {
						p.send(message);
					} catch (TimeoutException | ParserConfigurationException ex) {
						System.out.printn(ex.getStackTrace());
					}
					consumer.setMessage(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
	
	}



}
