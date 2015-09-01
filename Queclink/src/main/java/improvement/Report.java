package improvement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import conversion.QueclinkToScope;
import server.HttpOutput;
import server.HttpRest;
import utilities.Tokenizer;


public class Report{
	
	private String queclinkReport;
	private String scopeReport;
	private String serverResponse;
	private boolean conversionSuccess;
	
	//Static variables
	
	
	//Constants
	static final String baseDirectory = "/home/sistema/git/Log/Queclink/EVENTS/";
	static final String separatorLine = "\n----------------";
	
	public Report (final String queclinkReport){
		this.queclinkReport = queclinkReport;
		convert ();
	}
	
	//Send to Scope server
	public void send (){
		if (conversionSuccess){
			try {
				System.out.println ("Sending " + scopeReport);
				HttpOutput httpOutput = HttpRest.httpsClientC(scopeReport);
				serverResponse = httpOutput.getOutput();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println ("Nothing was sent");
	}
	
	//scopeString <- convertQueclinkToScope (queclinkReport)
	private void convert (){
		scopeReport = QueclinkToScope.convert(queclinkReport);
		conversionSuccess = scopeReport != null;
	}
	
	public void save (){
		save (baseDirectory + getDateString () + "/");
	}
	
	public void save (final String directoryString){
		final String mobileId = getMobileIdFrom (queclinkReport);
		final String fileName = mobileId + ".txt";
		
		//Create directory if it does not exist
		File directory = new File (directoryString);
		if (!directory.exists())
			if (!directory.mkdir())
				System.out.println ("Cannot create directory \"" + directoryString + "\"");
		
		try {
			FileWriter fWriter = new FileWriter (directoryString + fileName, true);
			BufferedWriter writer = new BufferedWriter (fWriter);
			
			writer.write(queclinkReport + separatorLine);
			writer.newLine();
			
			if (conversionSuccess){
				writer.write(scopeReport + separatorLine);
				writer.newLine();
				writer.write(serverResponse + separatorLine);
				writer.newLine();
			}
			
			writer.newLine();
			writer.close ();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String getMobileIdFrom (final String queclinkString){
		
		String ans = "0";
		
		Tokenizer tok = new Tokenizer (queclinkString);
		
		if (tok.countTokens() >= 3){
			tok.nextToken(); //+RESP:GTXXX
			tok.nextToken(); //Protocol version
			ans = tok.nextToken(); //uniqueId
		}
		
		return ans;
		
	}
	
	private String getDateString (){
		DateFormat format = new SimpleDateFormat ("yyyy_MM_dd");
		String dateString = format.format (new Date ());
		
		return dateString;
	}
	
}
