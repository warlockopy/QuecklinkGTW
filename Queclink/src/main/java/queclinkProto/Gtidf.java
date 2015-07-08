//GTIDF: Leave idling status

package queclinkProto;

import conversion.ScopeEventCode;
import utilities.Tokenizer;


public class Gtidf extends QueclinkReport {
	
	private int motionState;
	private int durationOfIdlingStatus;
	private PositionRelatedHeader greenHeader;
	
	public Gtidf (final String asciiMessage){
		
		greenHeader = new PositionRelatedHeader ();
		Tokenizer tok = new Tokenizer (asciiMessage);
		
		protocolVersion = tok.nextToken();
		uniqueId = tok.nextToken();
		deviceName = tok.nextToken();
		motionState = tok.nextInt();
		durationOfIdlingStatus = tok.nextInt();
		greenHeader.setFields(tok);
		sendTime = toSeconds(tok.nextToken());
		countNumber = tok.nextHex();
	}

	@Override
	public int getTemplateId() {
		return ScopeEventCode.IdleSpeedEnd;
	}

	@Override
	public String encode() {
		// TODO Auto-generated method stub
		return null;
	}
}
