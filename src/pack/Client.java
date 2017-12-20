package pack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Client extends JPanel implements ActionListener{
	JTextArea TextArea;
	JTextField Message;
	JButton Envoyer,Telecharger,Fichier;
	JPanel	Bottom,east;
	MonService ser;
	ThreadEcoute thr ;
	private String nom;
	private String Mes;
	public Client() throws RemoteException, NotBoundException{
		initialiser();
		Registry reg = LocateRegistry.getRegistry("127.0.0.1",2051);
		Remote rem=reg.lookup("test rmi");
		 ser=(MonService)rem;
		 //nom=JOptionPane.showInputDialog("donner votre nom");
		 thr = new ThreadEcoute();
			thr.start();
		
	}

	private void initialiser() {
		//this.setTitle("GATTOUS");
		this.setPreferredSize(new Dimension(250, 200));
		this.setLayout(new BorderLayout());
		TextArea = new JTextArea();
		Message = new JTextField(40);
		Envoyer = new JButton("Enovyer");
		Telecharger = new JButton("Telecharger");
		Fichier = new JButton("Envoyer Fichier");
		Bottom = new JPanel();
		east = new JPanel();
		east.setLayout(new GridLayout(2, 1));
		Bottom.add(Message);
		Bottom.add(Envoyer);
		//east.add(Telecharger);
		//east.add(Fichier);
		this.add(Bottom,BorderLayout.SOUTH);
		this.add(TextArea,BorderLayout.CENTER);
		this.add(east,BorderLayout.EAST);
		this.setVisible(true);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Envoyer.addActionListener(this);
		
		
	}
	public static void main(String[] arg) throws RemoteException, NotBoundException{
		new Client();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==Envoyer){
			try {
				ser.AjouterMessage(new Message(nom,Message.getText()));
				
				Message.setText("");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if(arg0.getSource()==Fichier){
			JFileChooser jfc = new JFileChooser();
			jfc.showOpenDialog(this);
			File f = jfc.getSelectedFile();
			String nom_fich =f.getName();
			long taille = f.length();
			byte [] b=new byte[(int) taille];
			try {
				FileInputStream fis =new FileInputStream(f);
				fis.read(b);
				fis.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ser.AjouterFichier(new MesFich(nom_fich,b));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	class ThreadEcoute extends Thread  {
		@Override
		public void run() {
			while(true){
				try {
					TextArea.setText(ser.getAll());
				} catch (RemoteException e) {}
			}
			
		}
	}
}
