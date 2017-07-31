package com.rabbit.queuetest;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;  
  
public class Work  
{  
    //队列名称  
    private final static String QUEUE_NAME = "taskqueue";  
  
    public static void main(String[] argv) throws java.io.IOException,  
            java.lang.InterruptedException, TimeoutException  
    {  
        //区分不同工作进程的输出  
        final int hashCode = Work.class.hashCode();  
        //创建连接和频道  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("localhost");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //声明队列  第二个参数：持久化标识
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);  
        System.out.println(hashCode  
                + " [*] Waiting for messages. To exit press CTRL+C");  
        Consumer consumer =new DefaultConsumer(channel){
        	@Override
        	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
        			throws IOException {
        		String message = new String(body);  

        		System.out.println(hashCode + " [x] Received '" + message + "'");  
        		try {
        			doWork(message);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}  
        		System.out.println(hashCode + " [x] Done"); 
        		//手动发送应答,默认自动应答  
                channel.basicAck(envelope.getDeliveryTag(), false);  
        	}
        	
        };
//        QueueingConsumer consumer = new QueueingConsumer(channel);  
        // 指定消费队列  
        channel.basicConsume(QUEUE_NAME, false,consumer);  
//        while (true)  
//        {  
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
//            String message = new String(delivery.getBody());  
//  
//            System.out.println(hashCode + " [x] Received '" + message + "'");  
//            doWork(message);  
//            System.out.println(hashCode + " [x] Done");  
//  
//        }  
  
    }  
  
    /** 
     * 每个点耗时1s 
     * @param task 
     * @throws InterruptedException 
     */  
    private static void doWork(String task) throws InterruptedException  
    {  
        for (char ch : task.toCharArray())  
        {  
            if (ch == '.')  
                Thread.sleep(1000);  
        }  
    }  
}  
