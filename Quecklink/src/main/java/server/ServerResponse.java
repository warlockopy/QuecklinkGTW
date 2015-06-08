package server;

import java.util.ArrayList;

public class ServerResponse {
	
	private String batch_id;
	private ArrayList <Result> results;
	
	public ServerResponse (){
		batch_id = "None";
		results = new ArrayList ();
	}
	
	public int size (){
		return results.size ();
	}
	
	public String getResultAt (int index){
		return results.get(index).getResult();
	}
	
	@Override
	public String toString (){
		String ans = "batchId = " + batch_id + "\n";
		
		for (Result result : results)
			ans += "   " + result + "\n";
		
		return ans;
	}
}
