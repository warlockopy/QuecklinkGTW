package device;

public class OneWireDevice {
	
	private String id;
	private int type;
	private int data;
	
	public OneWireDevice (final String id, int type, int data){
		setId (id);
		setType (type);
		setData (data);
	}
	
	public void setId (String id) { this.id = id; }
	public void setType (int type) { this.type = type; }
	public void setData (int data) { this.data = data; }
	
	public String getId () { return id; }
	public int getType () { return type; }
	public int getData () { return data; }
	
	public double getTemperatureCelsius (){
		return data * 0.0625;
	}
}
