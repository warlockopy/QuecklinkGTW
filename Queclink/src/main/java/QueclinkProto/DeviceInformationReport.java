/*
 * State: The current motion state of the device (hexadecimal	)
 * 16: Tow
 * 1A: Fake tow
 * 11: Ignition off rest
 * 12: Ignition off motion
 * 21: ignition on rest
 * 22: Ignition on motion
 * 41: Sensor rest
 * 42: Sensor motion
 */

package QueclinkProto;

public abstract class DeviceInformationReport extends QueclinkReport {
	
	private int state;
	private int iccid;
	private int csqRssi;
	private int csqBer;
	private boolean mainSupply;
	private boolean outputStatus5V;
	private boolean backupBatteryOn;
	private double backupBatteryVcc;
	private boolean charging;
	private boolean ledOn;
	private boolean gpsOnNeed;
	private int externalGpsAntenna;
	private long lastFixUtcTime;
	private int analogInputVcc;
	private int digitalInput;
	private int digitalOutput;
	private String timeZoneOffset;
	private boolean daylightSaving;
	
	public DeviceInformationReport (final String asciiMessage){
		Tokenizer tok = new Tokenizer (asciiMessage);
		
		protocolVersion = tok.nextToken ();
		uniqueId = tok.nextToken ();
		deviceName = tok.nextToken ();
		state = tok.nextInt ();
		iccid = tok.nextInt ();
		csqRssi = tok.nextInt ();
		csqBer = tok.nextInt ();
		mainSupply = tok.nextBoolean ();
		outputStatus5V = tok.nextBoolean ();
		backupBatteryOn = tok.nextBoolean ();
		backupBatteryVcc = tok.nextDouble ();
		charging = tok.nextBoolean ();
		ledOn = tok.nextBoolean ();
		gpsOnNeed = tok.nextBoolean ();
		externalGpsAntenna = tok.nextInt ();
		lastFixUtcTime = toSeconds (tok.nextToken ());
		analogInputVcc = tok.nextInt ();
		digitalInput = tok.nextHex ();
		digitalOutput = tok.nextHex ();
		timeZoneOffset = tok.nextToken ();
		daylightSaving = tok.nextBoolean ();
		sendTime = toSeconds (tok.nextToken ());
		countNumber = tok.nextInt ();
	}
	
	@Override
	public abstract String encode();

	@Override
	public abstract int getTemplateId();
	
}
