package rabbit.rec;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import rabbit.model.Student;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
public class Receiever 
{
	private static final String QUEUE_NAME="test_queue";
	public static void createConnAndPostMsgs() throws Exception {
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
		System.out.println("Message Consumer started");
		
		Consumer consumer = new DefaultConsumer(channel) {
			 @Override
			  public void handleDelivery(String consumerTag, Envelope envelope,
			                             AMQP.BasicProperties properties, byte[] body)
			      throws IOException {
				String message=null;
				Object object=deserializeMessage(body);
				if(object==null)
				{
					message = new String(body, "UTF-8");
				}
				else
				{
					message=object.toString();
				}
			    System.out.println(" [x] Received '" + message + "'");
			  }

			private Object deserializeMessage(byte[] body) throws IOException 
			{
				Object object=null;
				ByteArrayInputStream bis=null;
				ObjectInputStream ois=null;
				try
				{
					bis=new ByteArrayInputStream(body);
					ois=new ObjectInputStream(bis);
					object=ois.readObject();
				}
				catch(Exception e)
				{
					//don't print
					//e.printStackTrace();
					object=null;
				}
				finally
				{
					if(ois!=null)ois.close();
					if(bis!=null) bis.close();
				}
				//System.out.println("deser:"+object);
				return object;
			}
			};
			channel.basicConsume(QUEUE_NAME, true, consumer);
	}
	
	public static void main(String[] args) throws Exception {
		createConnAndPostMsgs();
	}

}
