package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.*;

import QueclinkProto.*;

public class UdpServer extends Thread {
	
	static final int bufferSize = 65536;
	private int localPortNumber;
	
	public UdpServer (int localPortNumber){
		this.localPortNumber = localPortNumber;
	}
	
	public void run (){
		DatagramSocket sock = null;
		
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
				ArrayList <QueclinkReport> qlReports = new ArrayList ();
				
				while (tok.hasMoreTokens()){
					String reportMessage = tok.nextToken();
					System.out.println ("Incoming report " + reportMessage + "\n");
					
					QueclinkReport report = ReportBuilder.buildReport(reportMessage);
					
					if (report != null)
						qlReports.add(report);
					else
						System.out.println ("Invalid report\n");
				}
				
				if (!qlReports.isEmpty()){
					String scopeString = QueclinkToScope.toScopeString(qlReports);
					sendToScopeServer (scopeString);
				}
			}
			
		}
		catch (IOException e){
			System.err.println("IOException " + e);
		}
	}
	
	private void sendToScopeServer (final String scopeString) {
		
		System.out.println ("Sending " + scopeString + "\n");
		
		try {
			int httpResult = HttpRest.httpsClientC(scopeString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
