package conversion;

import queclinkProto.*;

public class ReportBuilder {
	public static QueclinkReport buildReport (final String receivedMessage){
		
		if (receivedMessage.length() < 13){
			System.err.println ("Message " + receivedMessage + " is too short");
			return null;
		}
		
		if (!receivedMessage.substring(0, 6).equals("+RESP:")){
			System.err.println ("Missing prefix +RESP:");
			return null;
		}
		
		if (!receivedMessage.endsWith("$")){
			System.err.println ("Missing tail character $:");
			return null;
		}
		
		String messageData = receivedMessage.substring(12, receivedMessage.length() - 1);
		String reportType = receivedMessage.substring(6, 11);
		
		if (reportType.equals("GTFRI")) return new Gtfri (messageData);
		else if (reportType.equals("GTIGF")) return new Gtigf (messageData);
		else if (reportType.equals("GTIGN")) return new Gtign (messageData);
		else if (reportType.equals("GTMPN")) return new Gtmpn (messageData);
		else if (reportType.equals("GTMPF")) return new Gtmpf (messageData);
		else if (reportType.equals("GTIDN")) return new Gtidn (messageData);
		else if (reportType.equals("GTERI")) return new Gteri (messageData);
		else{
			System.err.println ("Unknown report " + reportType);
			return null;
		}
	}
}
