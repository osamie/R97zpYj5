package snippet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

//import server.PollServer;


public class VoterClient {
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket sendReceiveSocket;
	private static int portNum;
	private String IPAddress;
	   
	public VoterClient(int port,String IP){
		IPAddress = IP;
		portNum = port;
		try {
	         // Bind a Datagram socket to any available port on the local host machine. 
	    	 sendReceiveSocket = new DatagramSocket();
	     } catch (SocketException se) {   // Can't create the socket.
	         se.printStackTrace();
	         System.exit(1);
	     }
	 }
	
	 public void vote(Long pollID, int choice)
	 {
		 try {
	          // Java stores characters as 16-bit Unicode values, but 
	          // DatagramPackets store their messages as byte arrays.
	         String message = pollID + " " + choice;
	         
	         byte msg[] = message.getBytes();
	    	 sendPacket = new DatagramPacket(msg, msg.length, InetAddress.getByName(IPAddress), portNum);
	         sendReceiveSocket.send(sendPacket);         
	     }
	     catch (UnknownHostException e1)  { e1.printStackTrace(); System.exit(1); }
	     catch (IOException e2) { e2.printStackTrace(); System.exit(1);  }
	 }
	 

	/**
	 * TODO Validation of user's input: Before voting, ensures that the userInput adheres to right format for voting
	 * 		FORMAT: "!-><pollID:int>,<optionNumber:int>"
	 * @param str
	 * @return
	 */
	private boolean validateUserInput(String str) {
		//vote(str);
		return true;
	}
	 
	 public void close()
	 {
		 this.sendReceiveSocket.close();
	 }
	 /**
	 * @param args
	 */
	public static void main(String[] args) {
//		int portNum = PollServer.VOTING_PORT;	
//		if (args.length > 0) {
//		    try {
//		        portNum = Integer.parseInt(args[0]);
//		    } catch (NumberFormatException e) {
//		        System.err.println("Argument" + " must be an integer");
//		        System.exit(1);
//		    }
//		}
//		Client client = new Client(portNum, "127.0.0.1");
//		
//		while(client.sendReceiveSocket.isBound()){
//			System.out.print("\nSend vote(Format:<pollID> <optionNumber> ): ");
//			InputStreamReader converter = new InputStreamReader(System.in);
//			BufferedReader in = new BufferedReader(converter);
//			
//			try {
//				String str = in.readLine();
//				if (!client.validateUserInput(str)) {
//					System.out.println("Unvalidated vote request sent \nUSAGE: '!->1234,2'");
//					continue;
//				}
//				System.out.println("vote sent");
//			} catch (IOException e) {
//				System.err.println("I/O exception. Cause: " + e.getCause());
//				continue;
//			}
//		}
		
	}

}
