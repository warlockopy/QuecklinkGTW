package scopeProto;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;

public class MessagesPostPrototype {
	private int id;
	private String unit_id;
	private int template_id;
	private String body;
	
	public MessagesPostPrototype (int id, String unitId, int tempId){
		this.id = id;
		unit_id = unitId;
		template_id = tempId;
	}
	
	public MessagesPostPrototype (){
		
	}
	
	public MessagesPostPrototype (MessagesPostPrototype other){
		id = other.getId ();
		unit_id = other.getUnitId ();
		template_id = other.getTemplateId ();
		body = other.getBody ();
	}
	
	public void setId (int id){
		this.id = id;
	}
	
	public void setUnitId (String uid){
		unit_id = uid;
	}
	
	public void setTemplateId (int tmpId){
		template_id = tmpId;
	}
	
	public void setBody (Object o){
		Gson gson = new Gson ();
		String tmp = gson.toJson (o);
		body = Base64.encodeBase64String(tmp.getBytes());
	}
	
	public void setEncodedBody (final String body){
		this.body = body;
	}
	
	public int getId () { return id; }
	public int getTemplateId () { return template_id; }
	public String getUnitId () { return unit_id; }
	public String getBody () { return body; }
}
