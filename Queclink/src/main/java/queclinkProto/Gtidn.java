//GTIDN: Enter into idling status

package queclinkProto;

import org.apache.commons.codec.binary.Base64;

import conversion.ScopeEventCode;
import conversion.ScopeReportType;
import scopeProto.EventHeaderProto.EventHeader;
import scopeProto.StartOfExcessiveIdleProto.StartOfExcessiveIdle;
import utilities.Tokenizer;


public class Gtidn extends QueclinkReport {
	
	private PositionRelatedHeader greenHeader;
	private double mileage;
	
	public Gtidn (final String asciiMessage){
		
		greenHeader = new PositionRelatedHeader ();
		Tokenizer tok = new Tokenizer (asciiMessage);
		
		protocolVersion = tok.nextToken();
		uniqueId = tok.nextToken();
		deviceName = tok.nextToken();
		tok.nextToken(); //Reserved
		tok.nextToken(); //Reserved
		greenHeader.setFields(tok);
		mileage = tok.nextDouble();
		sendTime = toSeconds(tok.nextToken());
		countNumber = tok.nextHex();
	}

	@Override
	public int getTemplateId() {
		return ScopeEventCode.StartOfExcessiveIdle;
	}

	@Override
	public String encode() {
		
		ScopeReportType reportType = new ScopeReportType ("GTDIN");
		
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
		
		StartOfExcessiveIdle scopeEvent = StartOfExcessiveIdle
			.newBuilder()
			.setHeader(header)
			.build();
		
		return Base64.encodeBase64String (scopeEvent.toByteArray ());
		
	}
}
