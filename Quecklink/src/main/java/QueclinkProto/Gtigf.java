//+RESP:GTIGF: Ignition off report
//Cambiamos engine off, por tripstartp para mayor compatibilidad con la plataforma

package QueclinkProto;

import org.apache.commons.codec.binary.Base64;

import ScopeProtoJava.EngineStopProto.EngineStop;
import ScopeProtoJava.EventHeaderProto.EventHeader;
import ScopeProtoJava.TripShutdownProto.TripShutdown;

public class Gtigf extends QueclinkReport{
	
	private int durationOfIgnitionOn;
	private int gpsAccuracy;
	private double speed;
	private int heading;
	private double altitude;
	private double longitude;
	private double latitude;
	private long utcTime;
	private int mcc;
	private int mnc;
	private int lac;
	private int cellId;
	private double mileage;
	private String hourMeterCount;
	
	public Gtigf (final String asciiMessage){
		Tokenizer tok = new Tokenizer (asciiMessage);
		
		protocolVersion = tok.nextToken ();
		uniqueId = tok.nextToken ();
		deviceName = tok.nextToken ();
		durationOfIgnitionOn = tok.nextInt ();
		gpsAccuracy = tok.nextInt ();
		speed = tok.nextDouble ();
		heading = tok.nextInt ();
		altitude = tok.nextDouble ();
		longitude = tok.nextDouble ();
		latitude = tok.nextDouble ();
		utcTime = toSeconds (tok.nextToken ());
		
		mcc = tok.nextInt ();
		mnc = tok.nextInt ();
		lac = tok.nextHex ();
		cellId = tok.nextHex ();
		tok.nextToken (); //Reserved
		hourMeterCount = tok.nextToken ();
		mileage = tok.nextDouble ();	
		sendTime = toSeconds (tok.nextToken ());
		countNumber = tok.nextHex ();
	}
	
	@Override
	public String encode() {
		ScopeReportType scope = new ScopeReportType ("GTIGF");
		TripWorkAround trip = TripWorkAround.tripShutdownWorkAround (uniqueId, mileage, utcTime);

		EventHeader header = EventHeader
				.newBuilder()
				.setDescription(scope.getDescription ())
				.setDirection(heading)
				.setLatitude(latitude)
				.setLongitude(longitude)
				.setOdometer((int)mileage)
				.setSource(8)
				.setSpeed((int) speed)
				.setTemplateId(scope.getTemplateId ())
				.setUnitId(uniqueId)
				.setUtcTimestampSeconds(utcTime)
				.build();
		
		/*EngineStop scopeEvent = EngineStop
				.newBuilder()
				.setHeader(header)
				.build();*/
		// Se cambia por trip y workaround
		
		
		TripShutdown scopeEvent = TripShutdown
				.newBuilder()
				.setHeader(header)
				.setTripDistanceMeters(trip.getTripDistanceMeters ())
				.setTripDurationSeconds(trip.getDurationSeconds ())
				.setTripId(trip.getTripId())
				.build();
				
		return Base64.encodeBase64String (scopeEvent.toByteArray());
	}

	@Override
	public int getTemplateId() {
		//return ScopeEventCode.EngineStop;
		return ScopeEventCode.TripShutdown;
	}

}
