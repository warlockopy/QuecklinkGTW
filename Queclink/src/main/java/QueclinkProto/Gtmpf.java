//GTMPF: Main power off report

package QueclinkProto;

import org.apache.commons.codec.binary.Base64;

import ScopeProtoJava.EventHeaderProto.EventHeader;
import ScopeProtoJava.MainPowerLowProto.MainPowerLow;

public class Gtmpf extends QueclinkReport {
	
	private PositionRelatedHeader greenHeader;
	
	public Gtmpf (final String asciiMessage){
		greenHeader = new PositionRelatedHeader ();
		Tokenizer tok = new Tokenizer (asciiMessage);
		
		protocolVersion = tok.nextToken();
		uniqueId = tok.nextToken();
		deviceName = tok.nextToken();
		greenHeader.setFields(tok);
		sendTime = toSeconds(tok.nextToken());
		countNumber = tok.nextHex();
	}

	@Override
	public String encode() {
		
		ScopeReportType reportType = new ScopeReportType ("GTMPF");
		
		EventHeader header = EventHeader
				.newBuilder()
				.setDescription(reportType.getDescription())
				.setDirection(greenHeader.getAzimuth())
				.setLatitude(greenHeader.getLatitude())
				.setLongitude(greenHeader.getLongitude())
				.setSource(8)
				.setSpeed((int) greenHeader.getSpeed())
				.setTemplateId(reportType.getTemplateId())
				.setUnitId(uniqueId)
				.setUtcTimestampSeconds(greenHeader.getUtcTime())
				.build ();
		
		MainPowerLow scopeEvent = MainPowerLow
			.newBuilder()
			.setHeader(header)
			.build();
		
		return Base64.encodeBase64String (scopeEvent.toByteArray ());
		
	}

	@Override
	public int getTemplateId() {
		return ScopeEventCode.MainPowerLow;
	}	
	
}
