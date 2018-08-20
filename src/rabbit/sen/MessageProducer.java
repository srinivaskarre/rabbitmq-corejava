package rabbit.sen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import rabbit.model.Person;
import rabbit.model.Student;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessageProducer {
	
	private static final String QUEUE_NAME="test_queue";
	
	public static void main(String... args) throws Exception
	{
		/*
		 * Connection params
		 */
		ConnectionFactory connFactory=new ConnectionFactory();
		connFactory.setHost("localhost");
		Connection conn=connFactory.newConnection();
		Channel channel=conn.createChannel();
		
		/*
		 * queue details
		 */
		channel.queueDeclare(QUEUE_NAME,false,false,false,null);
		postMsgs(channel);
		
		Student s=new Student();
		s.setName("sk");
		s.setColg("MANH");
		
		channel.basicPublish("", QUEUE_NAME, null,serailizeObjects(s));
		
		Person p=new Person();
		p.setAge(24);
		channel.basicPublish("", QUEUE_NAME, null, serailizeObjects(p));
		
		System.exit(0);
		
	}

	private static void postMsgs(Channel channel) throws IOException, InterruptedException {
		String msg="Hello Macha!!";
		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		System.out.println("Message Producer started");
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		Thread.sleep(1000L);
		channel.basicPublish("", QUEUE_NAME, null, (""+System.currentTimeMillis()).getBytes());
		
	}

	private static byte[] serailizeObjects(Object obj) throws Exception
	{
		ByteArrayOutputStream bos=null;
		ObjectOutputStream oos=null;
		try
		{
			bos=new ByteArrayOutputStream(512);
			oos=new ObjectOutputStream(bos);
			oos.writeObject(obj);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(oos!=null)oos.close();
			if(bos!=null) bos.close();
		}
		return bos.toByteArray();
	}

}
