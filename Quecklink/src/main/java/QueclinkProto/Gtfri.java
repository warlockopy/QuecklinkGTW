package QueclinkProto;

import java.text.ParseException;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;

import ScopeProtoJava.EventHeaderProto.EventHeader;
import ScopeProtoJava.PeriodicPositionProto.PeriodicPosition;

public final class Gtfri extends QueclinkReport {
	
	private int analogInputVcc;
	private int reportId;
	private int number;
	private PositionRelatedHeader greenHeader;
	private double mileage;
	private String hourMeterCount;
	private int multiAnalogVcc1;
	private int multiAnalogVcc2;
	private int multiAnalogVcc3;
	private int deviceStatus;
	

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
		tok.nextToken ();	//Reserved
		tok.nextToken ();	//Reserved
		tok.nextToken ();	//Reserved
		tok.nextToken ();	//Reserved
		sendTime = toSeconds (tok.nextToken());
		countNumber = tok.nextHex ();
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
				.setOdometer((int)mileage)
				.setSource(8)
				.setSpeed((int) greenHeader.getSpeed())
				.setTemplateId(reportType.getTemplateId())
				.setUnitId(uniqueId)
				.setUtcTimestampSeconds(greenHeader.getUtcTime())
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
	
}
