import java.io.*;
import java.net.*;
import java.security.*;
public class MessageSender
{	public static void main (String args[]) throws Exception
	{	BufferedReader stdin = new BufferedReader
(new InputStreamReader (System.in));
		// ζήτα από το χρήστη να σου δώσει το μήνυμά του
		System.out.print ("Please enter your message: ");
		String message = stdin.readLine();
		if (message != null)
		{	// δημιούργησε ένα αντικείμενο-σύνοψη
			MessageDigest md = MessageDigest.getInstance
("MD5");
			// ενημέρωσέ το με τα προς σύνοψη δεδομένα
			md.update (message.getBytes());
		// υπολόγισε τη σύνοψη του μηνύματος
			byte digest [] = md.digest();
			// στείλε στον παραλήπτη το μήνυμα και τη σύνοψή
			// του
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
			// εμφάνισε την αναφορά του παραλήπτη
			System.out.println (in.readObject());
		}
	} // main
} // MessageSender
