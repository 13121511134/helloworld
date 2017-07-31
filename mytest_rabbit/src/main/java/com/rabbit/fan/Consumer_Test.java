package com.rabbit.fan;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer_Test{
	   //队列名称  
    private final static String QUEUE_NAME = "hello";  
  
    public static void main(String[] argv) throws java.io.IOException,  
            java.lang.InterruptedException, TimeoutException  
    {  
        //打开连接和创建频道，与发送端一样  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("localhost");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");  
        //4.x版本建议的方式
        Consumer consumer = new DefaultConsumer(channel) {
        	  @Override
        	  public void handleDelivery(String consumerTag, Envelope envelope,
        	                             AMQP.BasicProperties properties, byte[] body)
        	      throws IOException {
        	    String message = new String(body, "UTF-8");
        	    System.out.println(" [x] Received '" + message + "'");
        	  }
        	};
        	channel.basicQos(3);
        	channel.basicConsume(QUEUE_NAME, true, consumer);
        //3.x版本方式，已经被弃用
//        QueueingConsumer consumer = new QueueingConsumer(channel);  
//        //指定消费队列  
//        channel.basicQos(arg0);
//        channel.basicConsume(QUEUE_NAME, true, consumer);  
//        while (true)  
//        {  
//            //nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）  
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
//            String message = new String(delivery.getBody());  
//            System.out.println(" [x] Received '" + message + "'");  
//        }  
  
    }  
}
