package utilities;

import java.util.ArrayList;

import conversion.QueclinkToScope;
import conversion.ReportBuilder;
import QueclinkProto.QueclinkReport;

public class Converter {
	
	public static String queclinkToScope (ArrayList <String> qReports){
		String ans = "";
		ArrayList <QueclinkReport> reportObjects = new ArrayList ();
		
		for (String qReport : qReports){
			QueclinkReport report = ReportBuilder.buildReport (qReport);
			
			if (report != null)
				reportObjects.add(report);
		}
		
		if (!reportObjects.isEmpty ())
			ans = QueclinkToScope.toScopeString(reportObjects);
		
		return ans;
	}
	
}
