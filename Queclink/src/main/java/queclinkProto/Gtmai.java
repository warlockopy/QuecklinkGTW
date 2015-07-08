package queclinkProto;

import utilities.Tokenizer;

public class Gtmai extends QueclinkReport {
	
	private int analogInputVcc;
	private int reportId;
	private int number;
	private PositionRelatedHeader greenHeader;
	private double mileage;
	
	public Gtmai (final String asciiMessage){
		
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
		sendTime = toSeconds (tok.nextToken());
		countNumber = tok.nextHex ();
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