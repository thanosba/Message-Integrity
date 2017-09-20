import java.io.*;
import java.net.*;
import java.security.*;
public class MessageReceiver
{	public static void main (String args[]) throws Exception
	{	
		
		final int local_port = 7030;
		ServerSocket receiverSocket = new ServerSocket
(local_port);
		Socket senderSocket = receiverSocket.accept();
		ObjectInputStream in = new ObjectInputStream
(senderSocket.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream
(senderSocket.getOutputStream());
		String message = (String) in.readObject();
		byte [] digest = (byte[]) in.readObject();
		
		MessageDigest md = MessageDigest.getInstance ("MD5");
		
		md.update (message.getBytes());
		
		if (MessageDigest.isEqual (md.digest(), digest))
			out.writeObject ("\nYour message was valid! ");
		else
			out.writeObject ("\nYour message was corrupted! ");
	} 
} 
