//GTBTC: Backup battery starts charging report

package queclinkProto;

import org.apache.commons.codec.binary.Base64;

import scopeProto.EventHeaderProto.EventHeader;
import scopeProto.MainPowerHighProto.MainPowerHigh;
import utilities.Tokenizer;

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