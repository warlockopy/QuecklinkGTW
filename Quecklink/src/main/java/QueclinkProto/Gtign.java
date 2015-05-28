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
		cellId = tok.nextInt ();
		tok.nextToken (); //Reserved
		hourMeterCount = tok.nextToken ();
		mileage = tok.nextDouble ();
		sendTime = toSeconds (tok.nextToken ());
		countNumber = tok.nextHex ();
	}
	
	@Override
	public String encode() {
		ScopeReportType scope = new ScopeReportType ("GTIGN");
		
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
		
		EngineStart scopeEvent = EngineStart
				.newBuilder()
				.setHeader(header)
				.build();
				
		return Base64.encodeBase64String (scopeEvent.toByteArray());
	}
	

	@Override
	public int getTemplateId() {
		return ScopeEventCode.EngineStart;
	}
	
	public int TripStartworkAround(String id){
		String tripfile = id +"trip.txt";
		int tripid;
		
		if (FileExist(tripfile)!=0){
			tripid = ActualizarTripfile (tripfile);
		}
		else{
			EscribirTripfile(tripfile,1);
			return 1;
		}
		return tripid;
	}
	
	public void TripShutdownworkAround(String id){
		
	}
	public void EscribirTripfile(String archivo, int valor){
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		try
        {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            pw.println(valor);
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	};
	public int LeerTripfile(String archivo){
		File fichero = null;
		FileReader fr = null;
		BufferedReader br = null;
		String linea = "";
		
		try{
			fichero = new File (archivo);
			fr = new FileReader (fichero);
			br = new BufferedReader(fr);
			
			
			linea = br.readLine();
		}
		catch (Exception e){
			 e.printStackTrace();
		}
		finally{
			if( null != fr ){   
	               try {
					fr.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}     
	        }  
		}
		return Integer.parseInt(linea);
	};
	public int ActualizarTripfile(String archivo){
		int valor;
		valor = LeerTripfile(archivo);
		EscribirTripfile(archivo,++valor);
		return valor;
	};
	
	public int FileExist(String archivo){
		File f = new File(archivo);
		if(f.exists() && !f.isDirectory()) return 1;
			
		return 0;
	}
	
}
