package QueclinkProto;

public class Gteri extends Gtfri {
	
	private int ERIMask;
	private int UARTDeviceType;
	private String digitFuelSensorData;
	private int AC100DevicesNumber;
	private String oneWireDeviceId;
	private String oneWireDeviceType;
	private String oneWireDeviceData;
	
	public Gteri (final String asciiMessage){
		greenHeader = new PositionRelatedHeader ();
		Tokenizer tok = new Tokenizer (asciiMessage);
		
		protocolVersion = tok.nextToken ();
		uniqueId = tok.nextToken ();
		deviceName = tok.nextToken ();
		ERIMask = tok.nextHex();
		analogInputVcc = tok.nextInt();
		reportId = tok.nextInt ();
		number = tok.nextInt ();
		greenHeader.setFields(tok);
		mileage = tok.nextDouble ();
		hourMeterCount = tok.nextToken ();
		multiAnalogVcc1 = tok.nextInt ();
		multiAnalogVcc2 = tok.nextInt ();
		multiAnalogVcc3 = tok.nextInt ();
		digitalInput = tok.nextHex ();
		digitalOutput = tok.nextHex ();
		UARTDeviceType = tok.nextInt();
		digitFuelSensorData = tok.nextToken();
		AC100DevicesNumber = tok.nextInt();
		oneWireDeviceId = tok.nextToken();
		oneWireDeviceType = tok.nextToken();
		oneWireDeviceData = tok.nextToken();
		sendTime = toSeconds (tok.nextToken());
		countNumber = tok.nextHex ();
	}
}
