package server;

import improvement.Report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

import commands.Command;

public class UdpServer extends Thread {
	
	private static final int bufferSize = 65536;
	private int localPortNumber;
	private DatagramSocket sock = null;
	
	public UdpServer (int localPortNumber){
		this.localPortNumber = localPortNumber;
	}
	
	public void run (){
		
		try{
			sock = new DatagramSocket (localPortNumber);
			byte [] buffer = new byte [bufferSize];
			DatagramPacket incoming = new DatagramPacket (buffer, buffer.length);
			
			while (true){
				
				System.out.println ("Waiting for incoming data...\n");
				
				sock.receive(incoming);
				byte [] data = incoming.getData ();
				
				String incomingMessage = new String (data, 0, incoming.getLength ());
				StringTokenizer tok = new StringTokenizer (incomingMessage);
				
				while (tok.hasMoreTokens()){
					String reportMessage = tok.nextToken();
					System.out.println ("Incoming report " + reportMessage + "\n");
					
					Report report =  new Report (reportMessage);
					report.send();
					
					if (report.getConversionSuccess ())
						report.save();
						
				}
				
			}
			
		}
		catch (IOException e){
			System.err.println("IOException " + e);
		}
	}
	
	@Deprecated
	private void sendMessage (final String message, final String mobileId, final InetAddress ipAddress, int port){
		
		Command command = new Command (message, mobileId);
		DatagramPacket sendPacket;
		byte [] sendData = command.getMessage().getBytes ();
		sendPacket = new DatagramPacket (sendData, sendData.length, ipAddress, port);
		
		try {
			sock.send (sendPacket);
			System.out.println ("--Message sent to " + mobileId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Save
		FileWriter fWriter;
		
		try {
			fWriter = new FileWriter ("MENSAJES/MensajesEnviados.txt", false); //false para sobreescribir
			BufferedWriter writer = new BufferedWriter (fWriter);
			writer.write(mobileId + " " + message + "\n");
			writer.close ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
