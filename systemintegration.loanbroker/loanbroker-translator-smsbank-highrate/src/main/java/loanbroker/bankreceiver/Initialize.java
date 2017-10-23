/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanbroker.bankreceiver;

import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import loanbroker.endpoint.Consumer;


/**
 *
 * @author Kasper
 */
public class Initialize {
    private static final String EXCHANGE_NAME = "kkc-Translator-smsBankHighRate";
    private static final String CONSUMER_TAG = "smsHighRateBank";

    private Consumer consumer;
    

    public void onMessage() throws Exception {

        consumer = new Consumer(EXCHANGE_NAME, CONSUMER_TAG, false);

//        ReceiveXML rx = new ReceiveXML();
//        Thread t1 = new Thread(rx);
        Thread t2 = new Thread(consumer);

//        t1.start();
        t2.start();
        while (true) {
            synchronized (consumer) {
                try {
                    System.out.println(CONSUMER_TAG + " waiting..");
                    consumer.wait();
                    
                    String message = Arrays.toString(consumer.getMessage());
                    Produce p = new Produce();
                    try {
                        p.send(message);
                    } catch (TimeoutException | ParserConfigurationException ex) {
                        System.out.println(ex.getStackTrace());
                    }
                    consumer.setMessage(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
