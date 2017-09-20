import java.io.*;
import java.net.*;
import java.security.*;
public class MessageSender
{	public static void main (String args[]) throws Exception
	{	BufferedReader stdin = new BufferedReader
(new InputStreamReader (System.in));
		
		System.out.print ("Please enter your message: ");
		String message = stdin.readLine();
		if (message != null)
		{	
			MessageDigest md = MessageDigest.getInstance
("MD5");
			
			md.update (message.getBytes());
		
			byte digest [] = md.digest();
			
			final InetAddress receiver_addr =
InetAddress.getByName ("niovi.aueb.gr");
			final int receiver_port = 7030;
			Socket sock = new Socket (receiver_addr,
receiver_port);
			ObjectOutputStream out = new ObjectOutputStream
(sock.getOutputStream());
			ObjectInputStream in = new ObjectInputStream
(sock.getInputStream());
			out.writeObject (message);
			out.writeObject (digest);
			
			System.out.println (in.readObject());
		}
	} // main
} 
