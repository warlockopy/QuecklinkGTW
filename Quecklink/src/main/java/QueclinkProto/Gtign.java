//+RESP:GTIGN: Ignition on report

package QueclinkProto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;

import ScopeProtoJava.EngineStartProto.EngineStart;
import ScopeProtoJava.EventHeaderProto.EventHeader;
import ScopeProtoJava.TripStartupProto.TripStartup;

public class Gtign extends QueclinkReport{
	
	private int durationOfIgnitionOff;
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
	
	public Gtign (final String asciiMessage){
		Tokenizer tok = new Tokenizer (asciiMessage);
		
		protocolVersion = tok.nextToken ();
		uniqueId = tok.nextToken ();
		deviceName = tok.nextToken ();
		durationOfIgnitionOff = tok.nextInt ();
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
		
		//El orden relativo entre hourMeterCount y mileage depende del modelo
		if (getQueclinkVersion () == "GV200"){ //posiblemente en el GMT100 este sea el orden también (verificar)
			mileage = tok.nextDouble ();
			hourMeterCount = tok.nextToken ();
		}
		else{ //<Verificar manual de otros modelos>
			hourMeterCount = tok.nextToken ();
			mileage = tok.nextDouble ();
		}
		
		sendTime = toSeconds (tok.nextToken ());
		countNumber = tok.nextHex ();
	}
	
	@Override
	public String encode() {
		ScopeReportType scope = new ScopeReportType ("GTIGN");
		int tripId = TripWorkAround.tripStartupWorkAround (uniqueId, mileage, utcTime);
		
		EventHeader header = EventHeader
				.newBuilder()
				.setDescription(scope.getDescription ())
				.setDirection(heading)
				.setLatitude(latitude)
				.setLongitude(longitude)
				.setOdometer(toKm (mileage))
				.setSource(8)
				.setSpeed((int) speed)
				.setTemplateId(scope.getTemplateId ())
				.setUnitId(uniqueId)
				.setUtcTimestampSeconds(utcTime)
				.build();
		
		/*EngineStart scopeEvent = EngineStart
				.newBuilder()
				.setHeader(header)
				.build();*/
		// Cambio por Trip con su parche
		
		TripStartup scopeEvent = TripStartup
				.newBuilder()
				.setHeader(header)
				.setTripId(tripId)
				.build ();
				
		return Base64.encodeBase64String (scopeEvent.toByteArray());
	}
	

	@Override
	public int getTemplateId() {
		//return ScopeEventCode.EngineStart;
		return ScopeEventCode.TripStartup;
	}
	
}
