/* * Position Related Reports have the same format
 * -GTTOW
 * -GTDIS
 * -GTIOB
 * -GTGEO
 * -GTSPD
 * -GTSOS
 * -GTRTL
 * -GTDOG
 * -GTIGL
 * -GTHBM
 * */

package queclinkProto;

import scopeProto.EventHeaderProto.EventHeader;
import utilities.Tokenizer;

import com.google.protobuf.AbstractMessage.Builder;

public abstract class PositionRelatedReport extends QueclinkReport {
	
	protected int reportId;
	protected int number;
	PositionRelatedHeader greenHeader;
	protected double mileage;
	
	public PositionRelatedReport (final String asciiMessage){
		
		greenHeader = new PositionRelatedHeader ();
		Tokenizer tok = new Tokenizer (asciiMessage);
		
		protocolVersion = tok.nextToken ();
		uniqueId = tok.nextToken ();
		deviceName = tok.nextToken ();
		tok.nextToken (); //Reserved
		reportId = tok.nextInt ();
		number = tok.nextHex ();
		greenHeader.setFields(tok);
		mileage = tok.nextInt ();
		sendTime = toSeconds (tok.nextToken ());
		countNumber = tok.nextHex ();
	}
	
	public PositionRelatedReport (){
		
	}

	@Override
	public abstract String encode();
	
	public String getUnitId (){
		return uniqueId;
	}
}
