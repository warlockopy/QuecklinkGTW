package QueclinkProto;

import java.text.ParseException;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;

import ScopeProtoJava.EventHeaderProto.EventHeader;
import ScopeProtoJava.PeriodicPositionProto.PeriodicPosition;

public class Gtfri extends QueclinkReport {
	
	protected int analogInputVcc;
	protected int reportId;
	protected int number;
	protected PositionRelatedHeader greenHeader;
	protected double mileage;
	protected String hourMeterCount;
	protected int multiAnalogVcc1;
	protected int multiAnalogVcc2;
	protected int multiAnalogVcc3;
	protected int digitalInput;
	protected int digitalOutput;
	
	public Gtfri (){
		
	}

	public Gtfri (final String asciiMessage){
		
		greenHeader = new PositionRelatedHeader ();
		Tokenizer tok = new Tokenizer (asciiMessage);
		
		protocolVersion = tok.nextToken ();
		uniqueId = tok.nextToken ();
		deviceName = tok.nextToken ();
		analogInputVcc = tok.nextInt();
		reportId = tok.nextInt ();
		number = tok.nextInt ();
		greenHeader.setFields(tok);
		mileage = tok.nextDouble ();
		hourMeterCount = tok.nextToken ();
		multiAnalogVcc1 = tok.nextInt ();
		multiAnalogVcc2 = tok.nextInt ();
		multiAnalogVcc2 = tok.nextInt ();
		digitalInput = tok.nextHex ();
		digitalOutput = tok.nextHex ();
		tok.nextToken ();	//Reserved
		tok.nextToken ();	//Reserved
		sendTime = toSeconds (tok.nextToken());
		countNumber = tok.nextHex ();
	}
	
	public int getGeneralStatus (){
		//return (digitalInput & 1);
		// Tomar la ignicion por voltage.
		return analogInputVcc > 12700 ? 0x001 : 0x000;
	}

	@Override
	public String encode() {
		
		ScopeReportType reportType = new ScopeReportType ("GTFRI");
		
		EventHeader header = EventHeader
				.newBuilder()
				.setDescription(reportType.getDescription())
				.setDirection(greenHeader.getAzimuth())
				.setLatitude(greenHeader.getLatitude())
				.setLongitude(greenHeader.getLongitude())
				.setOdometer(toKm (mileage))
				.setSource(8)
				.setSpeed((int) greenHeader.getSpeed())
				.setTemplateId(reportType.getTemplateId())
				.setUnitId(uniqueId)
				.setUtcTimestampSeconds(greenHeader.getUtcTime())
				.setInputStatus(digitalInput)
				.setOutputStatus(digitalOutput)
				.setGeneralStatus(getGeneralStatus ())
				.build ();
		
		PeriodicPosition scopeEvent = PeriodicPosition
			.newBuilder()
			.setHeader(header)
			.build();
		
		return Base64.encodeBase64String (scopeEvent.toByteArray ());
	}

	@Override
	public int getTemplateId() {
		return ScopeEventCode.PeriodicPosition;
	}
	
	@Override
	public String toString (){
		return new Gson ().toJson(this);
	}
	
}
