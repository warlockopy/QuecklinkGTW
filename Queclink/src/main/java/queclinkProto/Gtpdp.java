package queclinkProto;

import org.apache.commons.codec.binary.Base64;

import conversion.ScopeReportType;
import scopeProto.EventHeaderProto.EventHeader;
import scopeProto.MainPowerHighProto.MainPowerHigh;
import utilities.Tokenizer;

public class Gtpdp extends QueclinkReport{
	
	public Gtpdp (final String asciiMessage){
		Tokenizer tok = new Tokenizer (asciiMessage);
		
		protocolVersion = tok.nextToken ();
		uniqueId = tok.nextToken ();
		deviceName = tok.nextToken ();
		sendTime = toSeconds (tok.nextToken ());
		countNumber = tok.nextHex ();
	}
	
	@Override
	public String encode() {
		
		ScopeReportType reportType = new ScopeReportType ("GTPNA");
		
		EventHeader header = EventHeader
				.newBuilder()
				.setDescription(reportType.getDescription ())
				.setUnitId(uniqueId)
				.setTemplateId(getTemplateId())
				.build ();
		
		MainPowerHigh scopeEvent = MainPowerHigh
				.newBuilder()
				.setHeader(header)
				.build();
			
			return Base64.encodeBase64String(scopeEvent.toByteArray ());
	}

	@Override
	public int getTemplateId() {
		// TODO Auto-generated method stub
		return 0;
	}

}