package scopeProto;

import java.util.ArrayList;

import com.google.gson.Gson;

public class ResponsePrototype {
	private String batch_id;
	private ArrayList <MessagesPostPrototype> messages;
	private static final String tabla = "0123456789abcdef";
	
	public ResponsePrototype (){
		messages = new ArrayList ();
		batch_id = "";
		
		for (int i = 0; i < 32; ++i){
			
			if (i == 8 || i == 12 || i == 16 || i == 20) batch_id += "-";
			
			int pos = (int) (Math.random() * 100000) % 16;
			batch_id += tabla.substring(pos, pos + 1);
		}
	}
	
	public void addMessage (final MessagesPostPrototype msg){
		MessagesPostPrototype newMessage = new MessagesPostPrototype (msg);
		
		newMessage.setId (messages.size () + 1);
		messages.add (newMessage);
	}
	
	public int countMessages (){
		return messages.size ();
	}
	
	public String getJsonMessageAt (int index){
		return new Gson ().toJson (messages.get (index));
	}
}
