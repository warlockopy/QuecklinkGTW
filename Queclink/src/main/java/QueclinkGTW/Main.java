package QueclinkGTW;
import java.util.ArrayList;
import java.util.Scanner;

import QueclinkProto.Gteri;
import QueclinkProto.Gtigf;
import server.UdpServer;
import utilities.Converter;

public class Main {
	
	public static void main (String args []) {
		
		Scanner sc = new Scanner (System.in);
		
		
		/*
		ArrayList <String> reports = new ArrayList ();
		String s;
		
		while ((s = sc.next ()).equals("end") == false){
			reports.add(s);
		}
		
		String scope = Converter.queclinkToScope(reports);
		
		echo (scope);
		
		
		System.exit(0);
		//*/
		
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
