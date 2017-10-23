/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanbroker.bankreceiver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Kasper
 */
public class CollectJSON {

    String QUEUE_NAME = "kkc-agg-smslow";

    public void send(String message) throws IOException, TimeoutException {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date time = new Date();
        System.out.println(df.format(time) + " Received: " + message);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("datdb.cphbusiness.dk");
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            
            channel.close();
        }
    }
}
