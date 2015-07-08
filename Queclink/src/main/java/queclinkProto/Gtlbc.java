package queclinkProto;

import utilities.Tokenizer;

public class Gtlbc extends QueclinkReport {
	
	private String callNumber;
	private PositionRelatedHeader greenHeader;
	
	public Gtlbc (final String asciiMessage){
		greenHeader = new PositionRelatedHeader ();
		Tokenizer tok = new Tokenizer (asciiMessage);
		
		protocolVersion = tok.nextToken();
		uniqueId = tok.nextToken();
		deviceName = tok.nextToken();
		callNumber = tok.nextToken();
		greenHeader.setFields(tok);
		sendTime = QueclinkReport.toSeconds(tok.nextToken());
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
