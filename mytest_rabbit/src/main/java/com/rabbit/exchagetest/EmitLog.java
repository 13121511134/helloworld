package com.rabbit.exchagetest;
  
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
  
public class EmitLog  
{  
    private final static String EXCHANGE_NAME = "ex_log";  
  
    public static void main(String[] args) throws IOException, TimeoutException  
    {  
        // 创建连接和频道  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("localhost");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        // 声明转发器和类型  
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);  
          
        for(int i= 0;i<5;i++){
        	DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        	String message = LocalDateTime.now().format(formatter)+" : log something";  
        	// 往转发器上发送消息  
        	channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());  
        	
        	System.out.println(" [x] Sent '" + message + "'");  
        	try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
  
        channel.close();  
        connection.close();  
  
    }  
  
}  
