/**
 * Classe de connexion à la BDD.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AccesBD {
	
	private static String pilote = "com.mysql.cj.jdbc.Driver";
	private static String utilisateur="root";
	private static String motDePasse="root";
	private static String url = "jdbc:mysql://localhost:3306/bd-apprenants";
	private static String urlSuite = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	private static Connection connexion = null;
	
	
	
	//Appel du pilote.
	static
	{
		try
		{
			Class.forName(pilote).newInstance();
		}
		catch (Exception e)
		{

			System.out.println(e);
			JOptionPane.showMessageDialog(null,"Pilote non valide ou introuvable !","AccesBD",JOptionPane.WARNING_MESSAGE);

		}
	}
	
	/**
	 * Methode qui retourne un objet de type Connection
	 * @return Connection
	 */
	public static Connection getConnection() {
		try
		{
			if (connexion==null) connexion = DriverManager.getConnection(url+urlSuite, utilisateur, motDePasse);


		}catch (Exception e)
			{
				System.out.println(e);
				JOptionPane.showMessageDialog(null,e.getMessage(),"Connexion a MySQL",JOptionPane.WARNING_MESSAGE);
			}

		return connexion;
	}
	
	/**
	 * methode de fermeture d'un objet de type connection
	 */
	public static void fermerConnection()
	{
		if (connexion!=null)
		{
			try
			{
				connexion.close();
			}

			catch(Exception e)
			{
				System.out.println("Fermeture de la Connexion Impossible !");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * méthode de fermeture d'un objet de type Statement
	 */

	public static void fermerStatement(Statement statement)
	{
		if (statement!=null)
		{
			try
			{
				statement.close();
			}

			catch(Exception e)
			{
				System.out.println("Fermeture du Statement Impossible !");
				e.printStackTrace();
			}
		}
	}

	/**
	 * méthode de fermeture d'un objet de type Statement
	 */

	public static void fermerResultSet(ResultSet resultSet)
	{
		if (resultSet!=null)
		{
			try
			{
				resultSet.close();
			}

			catch(Exception e)
			{
				System.out.println("Fermeture du ResultSet Impossible !");
				e.printStackTrace();
			}
		}
	}
	
}
