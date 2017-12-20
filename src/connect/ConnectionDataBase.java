package connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class ConnectionDataBase
{
	public static Connection con=null;
	private static Statement st=null;
	private static ResultSet rs=null;
	private static Class c=null;
	public static void loadDriver(String driverName)
	{
		try
		{
			c=Class.forName(driverName);
			System.out.println("Chargement de drivers"+c);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Erreur chargement de drivers"+c);
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erreur de chargement de drivrer "+driverName,"Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void connect(String dataBaseType)
	{
		if(c==null)
		{
			JOptionPane.showMessageDialog(null,"Erreur de chargement de drivrer","Erreur",JOptionPane.ERROR_MESSAGE);
			return ;
		}
		try
		{	
			con=DriverManager.getConnection(dataBaseType);
			st=con.createStatement();
			/**
			  st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			 **/
			System.out.println("Connexion etablie");
		}
		catch(SQLException e)
		{
			System.out.println("Erreur de Connexion"+e);
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erreur de connexion avec la base :\n"+dataBaseType,"Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void connect(String dataBaseType,String user,String password)
	{
		if(c==null)
		{
			JOptionPane.showMessageDialog(null,"Erreur de chargement de drivrer","Erreur",JOptionPane.ERROR_MESSAGE);
			return ;
		}
		try
		{	
			con=DriverManager.getConnection(dataBaseType,user,password);
			st=con.createStatement();
			System.out.println("Connexion etablie");
		}
		catch(SQLException e)
		{
			System.out.println("Erreur de Connexion"+e);
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erreur de connexion avec la base :\n"+dataBaseType,"Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	public static int executeUpdate(String req)
	{
		if(con==null)
		{
			JOptionPane.showMessageDialog(null,"Erreur de connexion avec la base","Erreur",JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		int nb=-1;
		try
		{
			nb=st.executeUpdate(req);
		}
		catch(SQLException e)
		{
			System.out.println("Erreur de mise a jour "+e);
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erreur de mise a jour!","Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return nb;
		
	}
	public static ResultSet executeQuery(String req)
	{
		if(con==null)
		{
			JOptionPane.showMessageDialog(null,"Erreur de connexion avec la base","Erreur",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		try
		{
			rs=st.executeQuery(req);
			return rs;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erreur de selection!"+e,"Erreur",JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public static void deconnection()
	{
		try
		{
			if(st!=null)
			st.close();
		}
		catch(SQLException e)
		{
			System.out.println("Erreur "+e);
			JOptionPane.showMessageDialog(null,"Erreur de fermeture!","Erreur",JOptionPane.ERROR_MESSAGE);
		}
		try
		{
			if(con!=null)
			con.close();
			//JOptionPane.showMessageDialog(null,"Vous êtes déconnecter","Déconnexion",JOptionPane.OK_OPTION);
		}
		catch(SQLException e)
		{
			System.out.println("Erreur "+e);
			JOptionPane.showMessageDialog(null,"Erreur de déconnexion!","Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
}
