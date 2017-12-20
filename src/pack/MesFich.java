package pack;

import java.io.Serializable;

public class MesFich implements Serializable {
	String nom;
	byte [] b;
	public MesFich(String nom1,byte [] b1){
		nom = nom1;
		b=b1;
	}
}
