package QueclinkProto;

import java.util.ArrayList;

import com.google.gson.Gson;

import ScopeProtoJava.MessagesPostPrototype;
import ScopeProtoJava.ResponsePrototype;

public class QueclinkToScope {
	
	private static Gson gson = new Gson ();
	
	public static String toScopeString (ArrayList <QueclinkReport> datos){
		
		ResponsePrototype response = new ResponsePrototype ();
		
		for (QueclinkReport report : datos){
			
			MessagesPostPrototype message = new MessagesPostPrototype ();
			message.setTemplateId(report.getTemplateId ());
			message.setUnitId (report.getUnitId ());
			message.setEncodedBody (report.encode ());
			
			response.addMessage (message);
		}
		
		return gson.toJson (response);
	}
	
}
