package server;

public class Result {
	
	private int id;
	private String result;
	
	public Result (){
		id = 0;
		result = "Not sent";
	}
	
	public String getResult (){
		return result;
	}
	
	private int getId (){
		return id;
	}
	
	@Override
	public String toString (){
		return "id = " + id + ", response = \"" + result + "\"";
	}
	
}
