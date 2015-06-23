package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.Gson;

import QueclinkProto.*;
import ScopeProtoJava.ResponsePrototype;

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
				
				ArrayList <Boolean> valid = new ArrayList ();
				ArrayList <String> allReports = new ArrayList ();
				
				while (tok.hasMoreTokens()){
					String reportMessage = tok.nextToken();
					allReports.add (reportMessage);
					System.out.println ("Incoming report " + reportMessage + "\n");
					
					//Show IP address and port
					System.out.println ("from IP " + incoming.getAddress() + " port " + incoming.getPort());
					
					QueclinkReport report = ReportBuilder.buildReport (reportMessage);
					
					if (report != null){
						qlReports.add(report);
						valid.add(true);
					}
					else{
						System.out.println ("Invalid report\n");
						valid.add (false);
					}
						
				}
				
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
					
				}
			}
			
		}
		catch (IOException e){
			System.err.println("IOException " + e);
		}
	}
	
	private void saveReports (ArrayList <String> reports, ArrayList <Boolean> sent, ResponsePrototype scope,
		ServerResponse serverResponse){
		
		int responseIndex = 0;
		
		for (int i = 0; i < reports.size (); ++i){
			
			String toSave = reports.get(i);
			String scopeString = "";
			String serverString = "";
			
			if (sent.get (i)){
				scopeString = scope.getJsonMessageAt (responseIndex);
				serverString = serverResponse.getResultAt(responseIndex);
				++responseIndex;
			}
			
			if (sent.get (i)){
				toSave += "\n--------\n" + scopeString + "\n--------\n" + serverString + "\n";
			}
			else
				toSave += "\n--------\n" + "Invalid report" + "\n";
			
			saveString (toSave);
		}
	}
	
	private static void saveString (final String string){
		
		DateFormat format = new SimpleDateFormat ("yyyy_MM_dd");
		String dateString = format.format (new Date ());
		String fileName = "EVENTS_" + dateString + ".txt";
		
		try {
			FileWriter fWriter = new FileWriter (fileName, true);
			BufferedWriter writer = new BufferedWriter (fWriter);
			
			writer.write(string);
			writer.newLine();
			
			writer.close ();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private HttpOutput sendToScopeServer (final String scopeString) {
		
		System.out.println ("Sending " + scopeString + "\n");
		HttpOutput ans = null;
		
		try {
			ans = HttpRest.httpsClientC (scopeString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ans;
	}
}
