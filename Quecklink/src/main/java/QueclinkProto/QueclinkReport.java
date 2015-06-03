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
}
