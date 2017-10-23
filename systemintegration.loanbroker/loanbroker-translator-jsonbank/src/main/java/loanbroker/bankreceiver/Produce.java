/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanbroker.bankreceiver;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONObject;

/**
 *
 * @author Kasper
 */
public class Produce {

    private static final String EXCHANGE_NAME = "cphbusiness.bankJSON";

    public void send(String message) throws IOException, TimeoutException, ParserConfigurationException {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date time = new Date();
        System.out.println(df.format(time) + " Received: " + message);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("datdb.cphbusiness.dk");
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            
            JSONObject json = new JSONObject(message);
            
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties().builder();
            
            builder.replyTo("kkc-jsonBank-normalizer");
            
            AMQP.BasicProperties prop = builder.build();
            
            
            channel.basicPublish(EXCHANGE_NAME, "", prop, json.toString().getBytes());
            System.out.println(" [x] Sent '" + json.toString() + "'");
            
            channel.close();
        }
    }
}
