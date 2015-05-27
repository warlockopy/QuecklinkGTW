package QueclinkProto;

public class ScopeReportType {
	private int id;
	private String description;
	
	public ScopeReportType (final String reportType){
		if (reportType.equals("GTFRI")){
			id = ScopeEventCode.PeriodicPosition;
			description = "PeriodicPosition";
		}
		else if (reportType.equals("GTIGN")){
			id = ScopeEventCode.EngineStart;
			description = "EngineStart";
		}
		else if (reportType.equals("GTIGF")){
			id = ScopeEventCode.EngineStop;
			description = "EngineStop";
		}
		else if (reportType.equals("GTMPN")){
			id = ScopeEventCode.MainPowerHigh;
			description = "MainPowerHigh";
		}
		else if (reportType.equals("GTMPF")){
			id = ScopeEventCode.MainPowerLow;
			description = "MainPowerLow";
		}
		else if (reportType.equals("GTIDN")){
			id = ScopeEventCode.StartOfExcessiveIdle;
			description = "StartOfExcessiveIdle";
		}
		
		else{
			id = ScopeEventCode.UnknownEvent;
			description = "UnknownEvent";
		}
	}
	
	public int getTemplateId (){
		return id;
	}
	
	public String getDescription (){
		return description;
	}
}
