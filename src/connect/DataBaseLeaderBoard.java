package connect;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DataBaseLeaderBoard  extends JFrame{
	private JTable jTableUsers;
	private LeaderBoard leaderBoard;
	public DataBaseLeaderBoard () {
		this.setSize(500,200);
		this.setLocationRelativeTo(null);
		loadConnection();
		jTableUsers = new JTable();
		
		initJTableUsers();
		JScrollPane p=new JScrollPane(jTableUsers);
		
		this.add(p);
		this.setVisible(true);
	}
	private void initJTableUsers() {
		// TODO Auto-generated method stub
			String req="select Pseudo,wins,Games from profile";
			ResultSet rs=ConnectionDataBase.executeQuery(req);
			leaderBoard=new LeaderBoard(rs);
			jTableUsers.setModel(leaderBoard);
			//		JComboBox comboBox = new JComboBox(names);
					//jTableUsers.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(comboBox));

			
			jTableUsers.getTableHeader().setReorderingAllowed(false); 
			//		jTableEtudiants.setAutoResizeMode(JTable.AUTO_RESIZE_OFF );
		}

private void loadConnection() {
		
		ConnectionDataBase.loadDriver("com.mysql.jdbc.Driver");
		//exemple:
		//"sun.jdbc.odbc.JdbcOdbcDriver");
		
		
		ConnectionDataBase.connect("jdbc:mysql://localhost/issat","root","");
		//exemple:
		//"jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};dbq=C:/Users/Abdallah/Desktop/TpBaseV0/EtudiantFI01.mdb");
		//ConnectionDataBase.connect("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};dbq=C:/Users/Abdallah/Desktop/TpBaseV0/EtudiantFI01.mdb");
	}
	public static void main(String[] args) {
		new DataBaseLeaderBoard();
	}

}
