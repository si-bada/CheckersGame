package pack;

import java.io.Serializable;

public class Message implements  Serializable {
String Nom;
String Message;
public Message(String Nom1,String Message1){
	Nom = Nom1;
	Message = Message1;
}
}
