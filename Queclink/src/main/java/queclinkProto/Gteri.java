package queclinkProto;

import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;

import scopeProto.EventHeaderProto.EventHeader;
import scopeProto.PeriodicPositionProto.PeriodicPosition;
import scopeProto.PeriodicTemperatureProto.PeriodicTemperature;
import scopeProto.Temperature1NormalProto.Temperature1Normal;
import scopeProto.Temperature2NormalProto.Temperature2Normal;
import utilities.Tokenizer;

import com.google.gson.Gson;

import conversion.ScopeEventCode;
import device.OneWireDevice;

public class Gteri extends Gtfri {
	
	private int ERIMask;
	private int UARTDeviceType; //0:None, 1:Digit fuel sensor, 2:AC100 1 wire bus.
	private String digitFuelSensorData;
	private int AC100DevicesNumber;
	
	private ArrayList <OneWireDevice> devices;
	
	public Gteri (final String asciiMessage){
		greenHeader = new PositionRelatedHeader ();
		devices = new ArrayList ();
		templateIds = new ArrayList ();
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
		
		if ((ERIMask & 1) == 1) //If bit 0 of ERIMask is set
			digitFuelSensorData = tok.nextToken();
		
		//One wire devices (bit1 of ERIMask)
		if ((ERIMask & 2) == 2){
		
			AC100DevicesNumber = tok.nextInt();
		
			for (int k = 0; k < AC100DevicesNumber; ++k){
				String oneWireDeviceId = tok.nextToken();
				int oneWireDeviceType = tok.nextInt();
				int oneWireDeviceData = tok.nextHex();
				
				if ((oneWireDeviceData & (1 << 15)) != 0)
					oneWireDeviceData |= 0xFFFF0000;
			
				devices.add (new OneWireDevice (oneWireDeviceId, oneWireDeviceType, oneWireDeviceData));
			}
		}
		
		sendTime = toSeconds (tok.nextToken());
		countNumber = tok.nextHex ();
	}
	
	@Override
	public String encode (){
		String ans = "";
		
		EventHeader periodicPositionHeader, temperature1NormalHeader, temperature2NormalHeader;
		EventHeader periodicTemperatureHeader;
		
		//Periodic position
		//********************
		periodicPositionHeader = EventHeader
				.newBuilder()
				.setDescription("PeriodicPosition")
				.setDirection(greenHeader.getAzimuth())
				.setLatitude(greenHeader.getLatitude())
				.setLongitude(greenHeader.getLongitude())
				.setOdometer(toKm (mileage))
				.setSource(8)
				.setSpeed((int) greenHeader.getSpeed())
				.setTemplateId(ScopeEventCode.PeriodicPosition)
				.setUnitId(uniqueId)
				.setUtcTimestampSeconds(greenHeader.getUtcTime())
				.setInputStatus(digitalInput)
				.setOutputStatus(digitalOutput)
				.setGeneralStatus(getGeneralStatus ())
				.build ();
		
		PeriodicPosition periodicPosition = PeriodicPosition
			.newBuilder()
			.setHeader(periodicPositionHeader)
			.build();
		
		ans = Base64.encodeBase64String (periodicPosition.toByteArray ());
		addTemplateId (ScopeEventCode.PeriodicPosition);
		//********************
		
		/*
		//Temperature 1 Normal
		//********************
		if (devices.size() > 0){
			temperature1NormalHeader = EventHeader
					.newBuilder()
					.setDescription("Temperature1Normal")
					.setDirection(greenHeader.getAzimuth())
					.setLatitude(greenHeader.getLatitude())
					.setLongitude(greenHeader.getLongitude())
					.setOdometer(toKm (mileage))
					.setSource(8)
					.setSpeed((int) greenHeader.getSpeed())
					.setTemplateId(ScopeEventCode.Temperature1Normal)
					.setUnitId(uniqueId)
					.setUtcTimestampSeconds(greenHeader.getUtcTime())
					.setInputStatus(digitalInput)
					.setOutputStatus(digitalOutput)
					.setGeneralStatus(getGeneralStatus ())
					.build ();
		
			Temperature1Normal temperature1Normal = Temperature1Normal
					.newBuilder()
					.setHeader(temperature1NormalHeader)
					.setValueDegrees((int) Math.round(getTemperatureAt (0)))
					.build();
		
			ans += " " + Base64.encodeBase64String (temperature1Normal.toByteArray ());
			addTemplateId (ScopeEventCode.Temperature1Normal);
		}
		//********************
		
		//Temperature 2 Normal
		//********************
		if (devices.size() > 1){
			temperature2NormalHeader = EventHeader
					.newBuilder()
					.setDescription("Temperature2Normal")
					.setDirection(greenHeader.getAzimuth())
					.setLatitude(greenHeader.getLatitude())
					.setLongitude(greenHeader.getLongitude())
					.setOdometer(toKm (mileage))
					.setSource(8)
					.setSpeed((int) greenHeader.getSpeed())
					.setTemplateId(ScopeEventCode.Temperature2Normal)
					.setUnitId(uniqueId)
					.setUtcTimestampSeconds(greenHeader.getUtcTime())
					.setInputStatus(digitalInput)
					.setOutputStatus(digitalOutput)
					.setGeneralStatus(getGeneralStatus ())
					.build ();
		
			Temperature2Normal temperature2Normal = Temperature2Normal
					.newBuilder()
					.setHeader(temperature2NormalHeader)
					.setValueDegrees((int) Math.round(getTemperatureAt (1)))
					.build();
		
			ans += " " + Base64.encodeBase64String (temperature2Normal.toByteArray ());
			addTemplateId (ScopeEventCode.Temperature2Normal);
		}
		//********************
		*/
		
		//Periodic Temperature (up to 4 sensors)
		//**************************************
		if (devices.size() > 0){
			periodicTemperatureHeader = EventHeader
					.newBuilder()
					.setDescription("PeriodicTemperature")
					.setDirection(greenHeader.getAzimuth())
					.setLatitude(greenHeader.getLatitude())
					.setLongitude(greenHeader.getLongitude())
					.setOdometer(toKm (mileage))
					.setSource(8)
					.setSpeed((int) greenHeader.getSpeed())
					.setTemplateId(ScopeEventCode.Temperature2Normal)
					.setUnitId(uniqueId)
					.setUtcTimestampSeconds(greenHeader.getUtcTime())
					.setInputStatus(digitalInput)
					.setOutputStatus(digitalOutput)
					.setGeneralStatus(getGeneralStatus ())
					.build ();
				
			PeriodicTemperature periodicTemperature = null;
			
			if (devices.size () == 1){ // 1 sensor
				periodicTemperature = PeriodicTemperature
						.newBuilder ()
						.setHeader(periodicTemperatureHeader)
						.setSensor1((int) Math.round(getTemperatureAt (0)))
						.setSensor1Valid(true)
						.build ();
			} else if (devices.size () == 2){ // 2 sensors
				periodicTemperature = PeriodicTemperature
						.newBuilder ()
						.setHeader(periodicTemperatureHeader)
						.setSensor1((int) Math.round(getTemperatureAt (0)))
						.setSensor1Valid(true)
						.setSensor2((int) Math.round(getTemperatureAt (1)))
						.setSensor2Valid(true)
						.build ();
			} else if (devices.size () == 3){ // 3 sensors
				periodicTemperature = PeriodicTemperature
						.newBuilder ()
						.setHeader(periodicTemperatureHeader)
						.setSensor1((int) Math.round(getTemperatureAt (0)))
						.setSensor1Valid(true)
						.setSensor2((int) Math.round(getTemperatureAt (1)))
						.setSensor2Valid(true)
						.setSensor3((int) Math.round(getTemperatureAt (2)))
						.setSensor3Valid(true)
						.build ();
			} else {// 4 or more sensors
				periodicTemperature = PeriodicTemperature
						.newBuilder ()
						.setHeader(periodicTemperatureHeader)
						.setSensor1((int) Math.round(getTemperatureAt (0)))
						.setSensor1Valid(true)
						.setSensor2((int) Math.round(getTemperatureAt (1)))
						.setSensor2Valid(true)
						.setSensor3((int) Math.round(getTemperatureAt (3)))
						.setSensor3Valid(true)
						.setSensor4((int) Math.round(getTemperatureAt (3)))
						.setSensor4Valid(true)
						.build ();
			}
				
			ans += " " + Base64.encodeBase64String (periodicTemperature.toByteArray ());
			addTemplateId (ScopeEventCode.PeriodicTemperature);
		}
		//************************************
		
		return ans;
	}
	
	private double getTemperatureAt (int index){
		double ans = 0.0;
		
		if (index < devices.size())
			ans = devices.get(index).getTemperatureCelsius();
		
		return ans;
	}
	
	
	@Override
	public String toString (){
		int n = AC100DevicesNumber;
		String ans = null;
		
		if (n > 0){
			ans = "Devices (" + n + "):\n********************************";
			
			for (OneWireDevice device : devices)
				ans += "\n" + device + "\n********************************";
		}
		else
			ans = "No devices.";
		
		return ans;
	}
	
	
}
