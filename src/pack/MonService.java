package pack;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
	
public interface MonService extends Remote {
	public String getInfo() throws RemoteException;
	public void setInfo(String S1) throws RemoteException;
	public void AjouterMessage(Message Mes) throws RemoteException;
	public String getAll() throws RemoteException;
	public void AjouterFichier(MesFich f) throws RemoteException;
	public ArrayList<MesFich> getAllFiles()throws RemoteException;

}
