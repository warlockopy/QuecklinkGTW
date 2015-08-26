package queclinkProto;

import java.util.Date;

import utilities.Tokenizer;

public class PositionRelatedHeader {
	
	private int gpsAccuracy;
	private double speed;
	private int azimuth;
	private double altitude;
	private double longitude;
	private double latitude;
	private long utcTime;
	private int mcc;
	private int mnc;
	private int lac;
	private int cellId;
	
	public PositionRelatedHeader (){
		
	}
	
	public void setFields (Tokenizer tok){
		gpsAccuracy = tok.nextInt();
		speed = tok.nextDouble();
		azimuth = tok.nextInt();
		altitude = tok.nextDouble();
		longitude = tok.nextDouble();
		latitude = tok.nextDouble();
		utcTime = QueclinkReport.toSeconds(tok.nextToken());
		mcc = tok.nextInt();
		mnc = tok.nextInt();
		lac = tok.nextHex();
		cellId = tok.nextHex();
		tok.nextToken(); //Reserved
	}
	
	public void setFields (Tokenizer tok, boolean oneReservedField){
		gpsAccuracy = tok.nextInt();
		speed = tok.nextDouble();
		azimuth = tok.nextInt();
		altitude = tok.nextDouble();
		longitude = tok.nextDouble();
		latitude = tok.nextDouble();
		utcTime = QueclinkReport.toSeconds(tok.nextToken());
		mcc = tok.nextInt();
		mnc = tok.nextInt();
		lac = tok.nextHex();
		cellId = tok.nextHex();
		
		if (oneReservedField)
			tok.nextToken(); //Reserved
	}
	
	public void setGpsAccuracy (int value) { gpsAccuracy = value; }
	public void setSpeed (double value) { speed = value; }
	public void setAzimuth (int value) { azimuth = value; }
	public void setAltitude (double value) { altitude = value; }
	public void setLongitude (double value) { longitude = value; }
	public void setLatitude (double value) { latitude = value; }
	public void setUtcTime (long value) { utcTime = value; }
	public void setMcc (int value) { mcc = value; }
	public void setMnc (int value) { mnc = value; } 
	public void setLac (int value) { lac = value; }
	public void setCellId (int value) { cellId = value; } 
	
	public int getAzimuth (){ return azimuth; }
	public double getAltitude (){ return altitude; }
	public double getLongitude (){ return longitude; }
	public double getLatitude (){ return latitude; }
	public double getSpeed (){ return speed; }
	public long getUtcTime (){ return utcTime; }
}
