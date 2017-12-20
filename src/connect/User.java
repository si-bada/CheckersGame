package connect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class User extends JFrame implements ActionListener {
	private JTextField JTextFieldPseudo;
	private JTextField jTextFieldNom;
	private JTextField jTextFieldPrenom;
	private JPasswordField jPassword;
	private LeaderBoard leaderboard=null;
	private JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public User() {
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(500,400);
		this.setLocation(500, 200);
		loadConnecction();
		JLabel lblNewLabel = new JLabel("Pseudo");
		lblNewLabel.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setToolTipText("votre pseudo");
		lblNewLabel.setBounds(65, 85, 58, 14);
		this.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setToolTipText("votre nom");
		lblNewLabel_1.setBounds(61, 137, 46, 14);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prenom");
		lblNewLabel_2.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setToolTipText("votre prenom");
		lblNewLabel_2.setBounds(61, 196, 74, 14);
		this.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mot de passe");
		lblNewLabel_3.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setForeground(new Color(128, 0, 0));
		lblNewLabel_3.setToolTipText("votre mot de passe");
		lblNewLabel_3.setBounds(65, 247, 86, 14);
		this.add(lblNewLabel_3);
		
		JTextFieldPseudo = new JTextField();
		JTextFieldPseudo.setBounds(236, 82, 86, 20);
		this.add(JTextFieldPseudo);
		JTextFieldPseudo.setColumns(10);
		JTextFieldPseudo.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				PseudoExist();
			}
		});
		
		jTextFieldNom = new JTextField();
		jTextFieldNom.setBounds(236, 134, 86, 20);
		this.add(jTextFieldNom);
		jTextFieldNom.setColumns(10);
		
		jTextFieldPrenom = new JTextField();
		jTextFieldPrenom.setBounds(236, 193, 86, 20);
		this.add(jTextFieldPrenom);
		jTextFieldPrenom.setColumns(10);
		
		jPassword = new JPasswordField();
		jPassword.setBounds(236, 244, 86, 20);
		this.add(jPassword);
		
		JLabel lblNewLabel_4 = new JLabel("WELCOME !!");
		lblNewLabel_4.setFont(new Font("Jokerman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_4.setForeground(new Color(51, 0, 0));
		lblNewLabel_4.setBounds(133, 25, 189, 46);
		this.add(lblNewLabel_4);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(this);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFocusable(false);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\validate.png"));
		btnNewButton.setBounds(366, 42, 74, 72);
		add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setFocusable(false);
		label.setForeground(new Color(139, 0, 0));
		label.setIcon(new ImageIcon("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\background.png"));
		label.setBounds(0, 0, 1031, 893);
		add(label);

	}
	protected void PseudoExist() {
		String pseudo=JTextFieldPseudo.getText();
			String req="select count(*) from profile where Pseudo='"+pseudo+"'";
			System.out.println(req);
			ResultSet rs=ConnectionDataBase.executeQuery(req);
			int count=-1;
			try {
				if(rs.next())
					count=rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(count==1)
				{initAllData();
				JOptionPane.showMessageDialog(this, "Pseudo existe déja","Erreur",JOptionPane.ERROR_MESSAGE);}
		}
private void loadConnecction() {
		
		ConnectionDataBase.loadDriver("com.mysql.jdbc.Driver");
		//exemple:
		//"sun.jdbc.odbc.JdbcOdbcDriver");
		
		
		ConnectionDataBase.connect("jdbc:mysql://localhost/issat","root","");
		//exemple:
		//"jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};dbq=C:/Users/Abdallah/Desktop/TpBaseV0/EtudiantFI01.mdb");
		//ConnectionDataBase.connect("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};dbq=C:/Users/Abdallah/Desktop/TpBaseV0/EtudiantFI01.mdb");
	}
private void initAllData()
{
	JTextFieldPseudo.setText("");
	jTextFieldNom.setText("");
	jTextFieldPrenom.setText("");
	jPassword.setText("");
	
}
protected void ajouterUser() {
	String errorMessage="";
	String Pseudo=JTextFieldPseudo.getText();
	if(Pseudo.equals(""))
		errorMessage+="Vous devez saisir un Pseudo.\n";
	
	String nom=jTextFieldNom.getText();
	if(nom.equals(""))
		errorMessage+="Vous devez saisir un nom.\n";
	
	String prenom=jTextFieldPrenom.getText();
	if(prenom.equals(""))
		errorMessage+="Vous devez saisir un prenom.\n";
	
	String password=jPassword.getText();
	if(password.equals(""))
		errorMessage+="Vous devez saisir un mot de passe.\n";
	
	if(errorMessage.equals(""))
	{
		String req="insert into profile values (?,?,?,?,?,?)";
		try {
			PreparedStatement ps=ConnectionDataBase.con.prepareStatement(req);
			ps.setString(1, Pseudo);
			ps.setString(2, nom);
			ps.setString(3, prenom);
			ps.setString(4, password);
			ps.setString(5, "0");
			ps.setString(6, "0");
			//ou on peut faire 
			//
			int result=ps.executeUpdate();
			if(result!=-1)
			{
				initAllData();
				Object ligne[]={Pseudo,nom,prenom,password,"0","0"};
				leaderboard.ajouterLigne(ligne);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	else
		JOptionPane.showMessageDialog(this, errorMessage,"Erreur",JOptionPane.ERROR_MESSAGE);
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==btnNewButton){
		ajouterUser();
		this.setVisible(false);
	}
}
public static void main(String [] arg0){
	new User();
}
}
