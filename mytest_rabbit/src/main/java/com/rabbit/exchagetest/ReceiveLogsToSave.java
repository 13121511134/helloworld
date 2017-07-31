package com.rabbit.exchagetest;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;  
  
public class ReceiveLogsToSave  
{  
    private final static String EXCHANGE_NAME = "ex_log";  
  
    public static void main(String[] argv) throws java.io.IOException,  
            java.lang.InterruptedException, TimeoutException  
    {  
        // 创建连接和频道  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("localhost");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
  
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);  
        // 创建一个非持久的、唯一的且自动删除的队列  
        String queueName = channel.queueDeclare().getQueue();  
        // 为转发器指定队列，设置binding  
        channel.queueBind(queueName, EXCHANGE_NAME, "");  
  
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");  
  
        Consumer consumer = new DefaultConsumer(channel){
        	@Override
        	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
        			throws IOException {
        		print2File(new String(body));  
        	}
        };  
        // 指定接收者，第二个参数为自动应答，无需手动应答  
        channel.basicConsume(queueName, true, consumer);  
  
    }  
  @Test
  public void test() throws UnsupportedEncodingException{
	  DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
	  System.out.println(LocalDateTime.now().format(formatter)); 
  }
    private static void print2File(String msg)  
    {  
        try  
        {  
            String dir = ReceiveLogsToSave.class.getClassLoader().getResource("").getPath();  
            String logFileName = new SimpleDateFormat("yyyy-MM-dd")  
                    .format(new Date());  
            File file = new File(URLDecoder.decode(dir, "utf-8"), logFileName+".txt");  
            FileOutputStream fos = new FileOutputStream(file, true);  
            fos.write((msg + "\r\n").getBytes());  
            fos.flush();  
            fos.close();  
        } catch (FileNotFoundException e)  
        {  
            e.printStackTrace();  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
}  