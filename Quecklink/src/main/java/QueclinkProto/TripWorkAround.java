package QueclinkProto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class TripWorkAround {
	private int tripId;
	private double mileage;
	private long utcTime;
	
	public TripWorkAround(double mileage, long time) {
		this.mileage = mileage;
		this.utcTime = time;
	}
	
	public TripWorkAround() {
	}
	
	public int getTripId () { return tripId; }
	public long getUtcTime () { return utcTime; }
	public double getMileage () { return mileage; }
	public int getDurationSeconds () { return (int) utcTime; }
	public int getTripDistanceMeters () { return (int) (mileage * 1000); }
	
	public void setTripId (int tripId) { this.tripId = tripId; }
	public void setMileage (double mileage) { this.mileage = mileage; }
	public void setUtcTime (long utcTime) { this.utcTime = utcTime; }
	
	public static int tripStartupWorkAround(String id, double mileage, long time){
		String tripFile = id + "trip.txt";
		
		TripWorkAround tripData = new TripWorkAround (mileage, time);
		
		if (fileExists (tripFile) != 0){
			TripWorkAround oldTripData = new TripWorkAround ();
			LeerTripFile (tripFile, oldTripData);
			tripData.setTripId (oldTripData.getTripId () + 1);
			escribirTripFile (tripFile, tripData);
		}
		else{
			tripData.setTripId (1);
			escribirTripFile(tripFile, tripData);
		}
		
		return tripData.getTripId ();
	}
	
	public static TripWorkAround tripShutdownWorkAround(String id, double mileage, long time){
		String tripFile = id + "trip.txt";
		int tripId = 0;
		double deltaMileage = 0;
		long deltaTime = 0;
		TripWorkAround tripData = new TripWorkAround();
		
		if (fileExists (tripFile) != 0){
			LeerTripFile (tripFile, tripData);
			tripId = tripData.getTripId ();
			deltaMileage = mileage - tripData.getMileage ();
			deltaTime = time - tripData.getUtcTime ();
			time -= tripData.getUtcTime ();
		}
		else {
			escribirTripFile (tripFile, tripData);
		}
		
		TripWorkAround ans = new TripWorkAround (deltaMileage, deltaTime);
		ans.setTripId (tripId);
		
		return ans;
	}
	
	public static void escribirTripFile(String archivo, TripWorkAround data){
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		try
        {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            pw.println(data.getTripId ());
            pw.println(data.getMileage ());
            pw.println(data.getUtcTime ());
 
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
	
	public static int LeerTripFile(String archivo, TripWorkAround data){
		File fichero = null;
		FileReader fr = null;
		BufferedReader br = null;
		String linea = "";
		
		try{
			fichero = new File (archivo);
			fr = new FileReader (fichero);
			br = new BufferedReader(fr);
			
			
			linea = br.readLine();
			data.setTripId (Integer.parseInt(linea));
			linea = br.readLine();
			data.setMileage (Double.parseDouble (linea));
			linea = br.readLine();
			data.setUtcTime (Long.parseLong(linea));
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
	/*public static int ActualizarTripfile(String archivo, double millas, long tiempoutc){
		int valor;
		valor = LeerTripfile(archivo, millas, tiempoutc);
		EscribirTripfile(archivo,++valor, millas,ti);
		return valor;
	};*/
	
	public static int fileExists (String archivo){
		File f = new File(archivo);

		return f.exists() && !f.isDirectory() ? 1 : 0;		
		
	}
}
