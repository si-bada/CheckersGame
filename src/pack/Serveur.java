package pack;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Serveur extends UnicastRemoteObject implements MonService {
	String S ="bjr";
	ArrayList<Message> disc = new ArrayList<Message>();
	ArrayList<MesFich> list_fuch = new ArrayList<MesFich>();
	public Serveur() throws RemoteException, AlreadyBoundException{
	Registry reg = LocateRegistry.createRegistry(2051);
	reg.bind("test rmi",this);	
}
	public  String getInfo() throws RemoteException{
		return S;
	}
	public void setInfo(String S1) throws RemoteException{
		S=S1;
	}
public static void main(String[] arg) throws RemoteException, AlreadyBoundException{
	new Serveur();
}
public String getAll() throws RemoteException {
	String ch ="";
	for(int i=0;i<disc.size();i++)
	{ch+=disc.get(i).Nom+":"+disc.get(i).Message+"\n";}
	return ch;	
}
public void AjouterMessage(Message Mes) throws RemoteException {
	// TODO Auto-generated method stub
	disc.add(Mes);
}

@Override
public ArrayList<MesFich> getAllFiles() throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void AjouterFichier(MesFich f) throws RemoteException {

}
}
