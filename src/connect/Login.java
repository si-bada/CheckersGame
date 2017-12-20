package connect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Login extends JFrame implements ActionListener {
	private static JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public Login() {
		setLayout(null);
		this.setVisible(true);
		this.setSize(500,400);
		this.setLocation(500, 200);
		loadConnecction();
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Monotype Corsiva", Font.BOLD, 15));
		lblNewLabel.setForeground(new Color(51, 0, 0));
		lblNewLabel.setBounds(56, 99, 46, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Monotype Corsiva", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(102, 0, 0));
		lblNewLabel_1.setBounds(56, 181, 101, 17);
		add(lblNewLabel_1);
		
		textField=new JTextField();
		textField.setBounds(258, 96, 101, 20);
		add(textField);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(258, 179, 101, 20);
		add(passwordField);
		
		JLabel lblConnect = new JLabel("CONNECT");
		lblConnect.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 25));
		lblConnect.setForeground(new Color(255, 255, 153));
		lblConnect.setBounds(149, 11, 134, 56);
		add(lblConnect);
		
		JButton btnValider = new JButton("VALIDER");
		btnValider.setBounds(168, 248, 89, 23);
		btnValider.addActionListener(this);
		add(btnValider);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Bada\\Downloads\\DameServeur\\src\\background.png"));
		label.setBounds(0, 0, 1031, 893);
		add(label);
	
	}
	public boolean LoginExist() {
		String password=passwordField.getText();
		String pseudo=textField.getText();
			String req="select * from profile where Pseudo='"+pseudo+"'"+"and MotDePasse='"+password+"'";
			System.out.println(req);
			ResultSet rs=ConnectionDataBase.executeQuery(req);
				try {
					if(rs.next()){
						
						JOptionPane.showMessageDialog(this, "Connected : Welcome "+textField.getText(),"All is Good", JOptionPane.INFORMATION_MESSAGE);	
						return true;
					} 
					else
					JOptionPane.showMessageDialog(this, "Erreur d'autentification. Essayer de Nouveau","Erreur",JOptionPane.ERROR_MESSAGE);
					initAllData();
					return false;
				} catch (HeadlessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
		}
private void loadConnecction() {
		
		ConnectionDataBase.loadDriver("com.mysql.jdbc.Driver");		
		ConnectionDataBase.connect("jdbc:mysql://localhost/issat","root","");
	}
private void initAllData()
{
	textField.setText("");
	passwordField.setText("");
	
}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		LoginExist();
		this.setVisible(false);
	}
	public static String getPseudo(){
		return textField.getText();
	}

}
