package QueclinkProto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class TripWorkAround {
	public int idT;
	public double millasT;
	public long utctimeT;
	public TripWorkAround() {
	}
	
	public static int TripStartupworkAround(String id, Double millaje, long time){
		String tripfile = id +"trip.txt";
		int tripid;
		
		TripWorkAround TripData = new TripWorkAround();
		
		TripData.millasT = millaje;
		TripData.utctimeT = time;
		
		if (FileExist(tripfile)!=0){
			TripWorkAround OldTripData = new TripWorkAround();
			LeerTripfile(tripfile, OldTripData);
			TripData.idT = OldTripData.idT +1;
			EscribirTripfile (tripfile, TripData);
			return TripData.idT;
		}
		else{
			TripData.idT = 1;
			EscribirTripfile(tripfile, TripData);
			return 1;
		}
	}
	
	public static int TripShutdownworkAround(String id, double millaje, long time){
		String tripfile = id +"trip.txt";
		int tripid;
		TripWorkAround TripData = new TripWorkAround();
		
		if (FileExist(tripfile)!= 0){
			LeerTripfile(tripfile, TripData);
			tripid = TripData.idT;
			millaje = millaje - TripData.millasT;
			time = time - TripData.utctimeT;
		}{
			EscribirTripfile(tripfile, TripData);
			millaje = 0;
			time = 0;
			tripid = 0;
		}
		return tripid;
	}
	public static void EscribirTripfile(String archivo, TripWorkAround data){
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		try
        {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            pw.println(data.idT);
            pw.println(data.millasT);
            pw.println(data.utctimeT);
 
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
	public static int LeerTripfile(String archivo, TripWorkAround data){
		File fichero = null;
		FileReader fr = null;
		BufferedReader br = null;
		String linea = "";
		
		try{
			fichero = new File (archivo);
			fr = new FileReader (fichero);
			br = new BufferedReader(fr);
			
			
			linea = br.readLine();
			data.idT = Integer.parseInt(linea);
			linea = br.readLine();
			data.millasT = Double.parseDouble(linea);
			linea = br.readLine();
			data.utctimeT = Long.parseLong(linea);
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
	
	public static int FileExist(String archivo){
		File f = new File(archivo);
		if(f.exists() && !f.isDirectory()) return 1;
			
		return 0;
	}
}
