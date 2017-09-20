import java.io.*;
import java.net.*;
import java.security.*;
public class MessageReceiver
{	public static void main (String args[]) throws Exception
	{	// παράλαβε από τον αποστολέα το μήνυμα και τη σύνοψή
		// του
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
		// δημιούργησε ένα αντικείμενο-σύνοψη
		MessageDigest md = MessageDigest.getInstance ("MD5");
		// ενημέρωσέ το με τα προς σύνοψη δεδομένα
		md.update (message.getBytes());
		// σύγκρινέ τη σύνοψή τους με αυτήν που έλαβες και
		// στείλε την κατάλληλη αναφορά στον αποστολέα
		if (MessageDigest.isEqual (md.digest(), digest))
			out.writeObject ("\nYour message was valid! ");
		else
			out.writeObject ("\nYour message was corrupted! ");
	} // main
} // MessageReceiver
