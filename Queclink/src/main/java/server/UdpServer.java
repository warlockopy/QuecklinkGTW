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
				//InetAddress ipAddress = incoming.getAddress();
				//int port = incoming.getPort();
				
				String incomingMessage = new String (data, 0, incoming.getLength ());
				//String mobileId = getMobileIdFrom (incomingMessage);
				
				StringTokenizer tok = new StringTokenizer (incomingMessage);
				//ArrayList <QueclinkReport> qlReports = new ArrayList ();
				//ArrayList <Boolean> valid = new ArrayList ();
				//ArrayList <String> allReports = new ArrayList ();
				
				while (tok.hasMoreTokens()){
					String reportMessage = tok.nextToken();
					//allReports.add (reportMessage);
					System.out.println ("Incoming report " + reportMessage + "\n");
					
					Report report =  new Report (reportMessage);
					report.send();
					
					if (report.getConversionSuccess ())
						report.save();
					
					//Old version
					/*
					QueclinkReport report = ReportBuilder.buildReport (reportMessage);
					
					if (report != null){
						qlReports.add(report);
						valid.add(true);
					}
					else{
						//System.out.println ("Invalid report\n");
						valid.add (false);
					}*/
						
				}
				
				//Old version
				/*
				if (!qlReports.isEmpty()){
					String scopeString = QueclinkToScope.toScopeString(qlReports);
					HttpOutput serverOutput = sendToScopeServer (scopeString);
					String output = serverOutput.getOutput();
					int httpCode = serverOutput.getCode();
					
					if (httpCode == 200){
						Gson gson = new Gson ();
						ServerResponse serverResponse = gson.fromJson (output, ServerResponse.class);
						ResponsePrototype responsePrototype = gson.fromJson(scopeString, ResponsePrototype.class);
					
						saveReports (allReports, valid, responsePrototype, serverResponse);
					}
					
				}*/
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
		
		//Guardar
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
