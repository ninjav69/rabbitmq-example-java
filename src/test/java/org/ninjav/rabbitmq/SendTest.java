/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ninjav.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author ninjav
 */
public class SendTest {

    private static final String QUEUE_NAME = "hello";

    @Test
    public void canSendMessageToQueue() 
            throws IOException, TimeoutException {
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        for (int i = 0; i < 1000000; i++) {
            String message = "Hello world " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        }
        
        channel.close();
        connection.close();
    }
}
