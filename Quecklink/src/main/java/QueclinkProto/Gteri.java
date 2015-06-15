package QueclinkProto;

import java.util.ArrayList;

import device.OneWireDevice;

public class Gteri extends Gtfri {
	
	private int ERIMask;
	private int UARTDeviceType;
	private String digitFuelSensorData;
	private int AC100DevicesNumber;
	
	private ArrayList <OneWireDevice> devices;
	
	public Gteri (final String asciiMessage){
		greenHeader = new PositionRelatedHeader ();
		devices = new ArrayList ();
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
		
		//One wire devices
		AC100DevicesNumber = tok.nextInt();
		
		for (int k = 0; k < AC100DevicesNumber; ++k){
			String oneWireDeviceId = tok.nextToken();
			int oneWireDeviceType = tok.nextInt();
			int oneWireDeviceData = tok.nextHex();
			
			devices.add (new OneWireDevice (oneWireDeviceId, oneWireDeviceType, oneWireDeviceData));
		}
		
		sendTime = toSeconds (tok.nextToken());
		countNumber = tok.nextHex ();
	}
}
