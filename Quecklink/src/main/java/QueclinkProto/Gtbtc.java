//GTBTC: Backup battery starts charging report

package QueclinkProto;

import org.apache.commons.codec.binary.Base64;

import ScopeProtoJava.EventHeaderProto.EventHeader;
import ScopeProtoJava.MainPowerHighProto.MainPowerHigh;

public class Gtbtc extends QueclinkReport {
	
	private PositionRelatedHeader greenHeader;
	
	public Gtbtc (final String asciiMessage){
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTemplateId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}