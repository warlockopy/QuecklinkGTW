package main;

import java.util.Scanner;

import conversion.ReportBuilder;
import queclinkProto.QueclinkReport;
import server.UdpServer;
import utilities.Converter;


public class Main {
	
	public static void main (String args []) {
		
		Scanner sc = new Scanner (System.in);
		
		//<Test>
		
		//while (sc.hasNext())
		//	System.out.println(ReportBuilder.buildReport(sc.next()).encode());
		
		//System.exit(0);
		//</Test>
		
		UdpServer server = new UdpServer (5000); //Local port 5000
		server.start();
		
		while (sc.hasNext ()){
			if (sc.next().compareToIgnoreCase("exit") == 0)
				break;
		}
		
		server.interrupt ();
		sc.close();
		echo ("\nTerminated");
		
		System.exit (0);
		
	}
	
	public static void echo (Object s){
		System.out.println (s);
	}

}
