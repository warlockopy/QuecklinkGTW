//+RESP:GTPFA: Power off report

package queclinkProto;

import org.apache.commons.codec.binary.Base64;

import conversion.ScopeEventCode;
import conversion.ScopeReportType;
import scopeProto.EventHeaderProto.EventHeader;
import scopeProto.MainPowerLowProto.MainPowerLow;
import utilities.Tokenizer;

public class Gtpfa extends QueclinkReport{
	
	public Gtpfa (final String asciiMessage){
		Tokenizer tok = new Tokenizer (asciiMessage);
		
		protocolVersion = tok.nextToken ();
		uniqueId = tok.nextToken ();
		deviceName = tok.nextToken ();
		sendTime = toSeconds (tok.nextToken ());
		countNumber = tok.nextHex ();
	}
	
	@Override
	public String encode() {
		
		ScopeReportType reportType = new ScopeReportType ("GTPFA");
		
		EventHeader header = EventHeader
				.newBuilder()
				.setDescription(reportType.getDescription ())
				.setUnitId(uniqueId)
				.setTemplateId(getTemplateId())
				.build ();
		
		MainPowerLow scopeEvent = MainPowerLow
				.newBuilder()
				.setHeader(header)
				.build();
			
			return Base64.encodeBase64String(scopeEvent.toByteArray ());
	}

	@Override
	public int getTemplateId() {
		return ScopeEventCode.MainPowerLow;
	}

}
