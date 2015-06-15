package QueclinkProto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ScopeProtoJava.EventHeaderProto.EventHeader;

public abstract class QueclinkReport {
	
	private static SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddhhmmss");
	
	protected String protocolVersion;
	protected String uniqueId;
	protected String deviceName;
	protected long sendTime;
	protected int countNumber;
	
	public final double MILE2KM = 1.609344;
	
	public abstract String encode ();
	
	public static long toSeconds (final String utcTime){
		Date date;
		long seconds = 0;
		
		try {
			date = format.parse(utcTime);
			seconds = date.getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return seconds;
	}
	
	public abstract int getTemplateId ();
	
	public String getUnitId (){
		return uniqueId;
	}
	
	public int toKm (double mileage){
		return (int) (mileage * 1.0);
	}
	
	public String getQueclinkVersion (){
		
		String code = "??";
		String ans = "Unknown";
		
		if (protocolVersion.length() > 2)
			code = protocolVersion.substring(0, 2);
		
		if (code.equals("02"))      ans = "GL200";
		else if (code.equals("04")) ans = "GV200";
		else if (code.equals("06")) ans = "GV300";
		else if (code.equals("08")) ans = "GMT100";
		else if (code.equals("0F")) ans = "GV55";
		else if (code.equals("10")) ans = "GV55 LITE";
		else if (code.equals("11")) ans = "GL500";
		else if (code.equals("1A")) ans = "GL300";
		else if (code.equals("1F")) ans = "GV500";
		else    ans = "<Unknown code " + code + ">";
		
		return ans;
	}
}
