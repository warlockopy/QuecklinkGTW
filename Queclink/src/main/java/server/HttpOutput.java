package server;

public class HttpOutput {
	
	private int code;
	private String output;
	
	public HttpOutput (int code, final String output){
		this.code = code;
		this.output = output;
	}
	
	public int getCode (){
		return code;
	}
	
	public String getOutput (){
		return output;
	}
	
	@Override
	public String toString (){
		return code + " " + output;
	}
	
}
