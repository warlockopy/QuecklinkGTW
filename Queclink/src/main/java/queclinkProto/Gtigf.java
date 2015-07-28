//+RESP:GTIGF: Ignition off report
//Cambiamos engine off, por tripstartp para mayor compatibilidad con la plataforma

package queclinkProto;

import org.apache.commons.codec.binary.Base64;

import conversion.ScopeEventCode;
import conversion.ScopeReportType;
import scopeProto.EngineStopProto.EngineStop;
import scopeProto.EventHeaderProto.EventHeader;
import scopeProto.TripShutdownProto.TripShutdown;
import utilities.Tokenizer;
import utilities.TripWorkAround;

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
		
		//Primero hourMeterCount y luego mileage en GV55, al revés en GV200,GMT100
		String version = getQueclinkVersion ();
		System.out.println ("Model: " + version);
		
		if (version.equals("GV200") || version.equals("GMT100")){
			mileage = tok.nextDouble();
			hourMeterCount = tok.nextToken();
		}
		else if (version.equals ("GV55")){ //GV55
			hourMeterCount = tok.nextToken();
			mileage = tok.nextDouble();
		}
		else{ //Otras versiones
			hourMeterCount = tok.nextToken();
			mileage = tok.nextDouble();
		}
			
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
				//.setOdometer(toKm (mileage))
				.setOdometer(trip.getTripDistanceMeters() )
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
		
		//****
		System.out.println ("****************\nFin de viaje:");
		System.out.println ("Distancia del viaje: " + trip.getTripDistanceMeters() + " m.");
		System.out.println ("Odómetro: " + mileage + " km.");
		System.out.println ("****************");
		//****
				
		return Base64.encodeBase64String (scopeEvent.toByteArray());
	}

	@Override
	public int getTemplateId() {
		//return ScopeEventCode.EngineStop;
		return ScopeEventCode.TripShutdown;
	}

}
