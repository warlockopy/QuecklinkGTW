package conversion;

import java.util.ArrayList;
import java.util.StringTokenizer;

import queclinkProto.QueclinkReport;
import scopeProto.MessagesPostPrototype;
import scopeProto.ResponsePrototype;

import com.google.gson.Gson;

public class QueclinkToScope {
	
	private static Gson gson = new Gson ();
	
	public static String toScopeString (ArrayList <QueclinkReport> datos){
		
		ResponsePrototype response = new ResponsePrototype ();
		
		for (QueclinkReport report : datos){
			
			//report could consist of several JSON strings separated by spaces (e.g. GTERI)
			
			StringTokenizer tok = new StringTokenizer (report.encode ());
			int index = 0;
			
			while (tok.hasMoreTokens()){
				MessagesPostPrototype message = new MessagesPostPrototype ();
				message.setTemplateId(report.getTemplateIdAt (index++));
				message.setUnitId (report.getUnitId());
				message.setEncodedBody (tok.nextToken());
				
				response.addMessage (message);
			}
			
			/*
			MessagesPostPrototype message = new MessagesPostPrototype ();
			message.setTemplateId(report.getTemplateId ());
			message.setUnitId (report.getUnitId ());
			message.setEncodedBody (report.encode ());
			
			response.addMessage (message);
			*/
		}
		
		return gson.toJson (response);
	}
	
}
