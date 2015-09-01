package server;

import improvement.Report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import queclinkProto.*;
import scopeProto.ResponsePrototype;
import utilities.Tokenizer;

import com.google.gson.Gson;

import commands.Command;
import conversion.QueclinkToScope;
import conversion.ReportBuilder;

public class UdpServer extends Thread {
	
	static final int bufferSize = 65536;
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
				//ArrayList <String> allReports = new ArrayList (); //Lista de reportes Queclink
				
				while (tok.hasMoreTokens()){
					String reportMessage = tok.nextToken();
					//allReports.add (reportMessage);
					System.out.println ("Incoming report " + reportMessage + "\n");
					
					Report report =  new Report (reportMessage);
					report.send();
					report.save();
					
					//Old version
					/*
					QueclinkReport report = ReportBuilder.buildReport (reportMessage);
					
					if (report != null){ //Si el reporte Queclink fue convertido exitosamente
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
			fWriter = new FileWriter ("MENSAJES/MensajesEnviados.txt", false); //false para Sobreescribir
			BufferedWriter writer = new BufferedWriter (fWriter);
			writer.write(mobileId + " " + message + "\n");
			writer.close ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void saveReports (ArrayList <String> reports, ArrayList <Boolean> sent, ResponsePrototype scope,
		ServerResponse serverResponse){
		
		//reports: Lista de todos los reportes Queclink que llegan (n)
		//sent:    Indica si un reporte fue enviado (n)
		//scope:   
		//serverResponse: 
		
		int responseIndex = 0;
		
		for (int i = 0; i < reports.size (); ++i){
			
			String toSave = reports.get(i);
			String scopeString = "";
			String serverString = "";
			String mobileId = getMobileIdFrom (toSave);
			
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
			
			saveString (toSave, mobileId);
		}
	}
	
	private static void saveString (final String string, final String mobileId){
		
		DateFormat format = new SimpleDateFormat ("yyyy_MM_dd");
		String dateString = format.format (new Date ());
		String dir = "EVENTS/EVENTS_" + dateString;
		String fileName = mobileId + ".txt";
		
		File directory = new File (dir);
		String path = directory.getAbsolutePath() + "/" + fileName;
		
		if (!directory.exists())
			if (directory.mkdir() == false)
				System.out.println ("Error. No se pudo crear el directorio " + directory.getAbsolutePath());
		
		try {
			FileWriter fWriter = new FileWriter (dir + "/" + fileName, true);
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
	
	public static String getMobileIdFrom (final String queclinkString){
		
		String ans = "0";
		
		Tokenizer tok = new Tokenizer (queclinkString);
		
		if (tok.countTokens() >= 3){
			tok.nextToken(); //+RESP:GTXXX
			tok.nextToken(); //Protocol version
			ans = tok.nextToken(); //uniqueId
		}
		
		return ans;
		
	}
}
