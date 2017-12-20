package connect;
public class Profile {
	String Pseudo;
	String Nom;
	String Prenom;
	String Motdepasse;
	String wins;
	String Games;
	
	public String getPseudo() {
		// TODO Auto-generated method stub
		return Pseudo;
	}
	public void setPseudo(String Pseudo1) {
		// TODO Auto-generated method stub
		Pseudo = Pseudo1;
	}
	public String getMotdepasse() {
		// TODO Auto-generated method stub
		return Motdepasse;
	}
	public void setMotdepasse(String Motdepasse) {
		// TODO Auto-generated method stub
		Pseudo = Motdepasse;
	}
	public String getNom() {
		// TODO Auto-generated method stub
		return Nom;
	}
	public void setNom(String Nom1) {
		// TODO Auto-generated method stub
		Nom=Nom1;
	}
	public String getPrenom() {
		// TODO Auto-generated method stub
		return Prenom;
	}
	public void setPrenom(String Prenom1) {
		// TODO Auto-generated method stub
		Prenom=Prenom1;
	}
	public void setWins(String wins1) {
		// TODO Auto-generated method stub
		wins=wins1;
	}
	public void setGames(String Games1) {
		// TODO Auto-generated method stub
		Games=Games1;
	}
	public String getWins() {
		// TODO Auto-generated method stub
		return wins;
	}
	public String getGames() {
		// TODO Auto-generated method stub
		return Games;
	}
public Profile(String Pseudo,String Nom,String Prenom,String Motdepasse)
	{
	this.Nom=Nom;
	this.Prenom=Prenom;
	this.Pseudo=Pseudo;
	this.Motdepasse=Motdepasse;
	
	}
public Profile(String Pseudo,String wins,String Games)
{
this.Pseudo=Pseudo;
this.wins=wins;
this.Games=Games;

}
}
