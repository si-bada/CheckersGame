package connect;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
@SuppressWarnings("serial")
class LeaderBoard extends AbstractTableModel {
	ArrayList<Profile> data = new ArrayList<Profile>();
	int nb_ligne;
	int col;
	ResultSetMetaData rsmd;
	public LeaderBoard(ResultSet rs) {
		// TODO Auto-generated constructor stub
		try {
			rsmd =rs.getMetaData();
			col=rsmd.getColumnCount();
			while(rs.next())
			{
				nb_ligne++;
				Profile e = new Profile(rs.getString(1),rs.getString(2),rs.getString(3));
				data.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
@Override
public String getColumnName(int arg0) {
	// TODO Auto-generated method stub
	try {
		return rsmd.getColumnName(arg0+1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	return null;
}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return col;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return nb_ligne;
	}

	@Override
	public Object getValueAt(int l, int col) {
		// TODO Auto-generated method stub
		String ch="";
		Profile e;
		try {
			e = data.get(l);
		
		if(col==0)
		{
			ch=e.getPseudo();
		}
		if(col==1)
		{
			ch=e.getWins();
		}
		if(col==2)
		{
			ch=e.getGames();
		}
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ch;
	}
	public void setValueAt(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		//super.setValueAt(arg0, arg1, arg2);	
		String req = " update etudiant set wins ="+arg0+" where pseudo="+data.get(5).Pseudo;
		ConnectionDataBase.executeUpdate(req);
		data.get(arg1).setWins(arg0.toString());
		
	}
		public void ajouterLigne(Object[] ligne) {
		// TODO Auto-generated method stub
		Profile e = new Profile(ligne[0].toString(), ligne[1].toString(),ligne[2].toString());
		data.add(e);
		nb_ligne++;
		fireTableDataChanged();
	}
}
