//GTMPN: Main power on report
//El GV55 no envía este reporte

package QueclinkProto;

import org.apache.commons.codec.binary.Base64;

import conversion.ScopeEventCode;
import conversion.ScopeReportType;
import utilities.Tokenizer;
import ScopeProtoJava.EventHeaderProto.EventHeader;
import ScopeProtoJava.MainPowerHighProto.MainPowerHigh;

public class Gtmpn extends QueclinkReport {
	
	private PositionRelatedHeader greenHeader;
	
	public Gtmpn (final String asciiMessage){
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
		
		ScopeReportType reportType = new ScopeReportType ("GTMPN");
		
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
		
		MainPowerHigh scopeEvent = MainPowerHigh
			.newBuilder()
			.setHeader(header)
			.build();
		
		return Base64.encodeBase64String (scopeEvent.toByteArray ());
		
	}

	@Override
	public int getTemplateId() {
		return ScopeEventCode.MainPowerHigh;
	}	
	
}
