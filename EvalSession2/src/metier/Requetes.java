package metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.AccesBD;
import model.Activite;
import model.Apprenant;
import model.Region;

public class Requetes {
	
	/**
	 * Méthode pour retourner tous les apprenants dans un tableau
	 */
	public static ArrayList<Apprenant> getAllApprenants() throws ClassNotFoundException, SQLException {
		
		Statement statement = null;
		
		ArrayList<Apprenant>  apprenants = new ArrayList<Apprenant>();
		String requete	= "SELECT * FROM apprenants ORDER BY ID_APPRENANT";
		statement = AccesBD.getConnection().createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		while(resultat.next())
		{
			Apprenant a = Mapping.mapperApprenant(resultat);
			apprenants.add(a);
		}
		return apprenants;
	}
	
	/**
	 * Méthode qui retourne un objet de type Region en fonction de son identifiant
	 */
	public static Region getRegionById(int id) throws ClassNotFoundException, SQLException {
		
		Statement statement = null;
		
		Region region = new Region();
		String requete	= "SELECT * FROM regions WHERE ID_REGION="+id;
		statement = AccesBD.getConnection().createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		resultat.next();
		region.setId(resultat.getInt("ID_REGION"));
		region.setNomRegion(resultat.getString("NOM_REGION"));
		return region;
			
	}
	
	/**
	 * Méthode pour retourner tous les apprenants dans un tableau
	 */
	public static ArrayList<Activite> getAllActivites() throws ClassNotFoundException, SQLException {
		
		Statement statement = null;
		
		ArrayList<Activite>  activites = new ArrayList<>();
		String requete	= "SELECT * FROM activites ORDER BY ID_ACTIVITE";
		statement = AccesBD.getConnection().createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		while(resultat.next())
		{
			Activite a = Mapping.mapperActivite(resultat);
			activites.add(a);
		}
		return activites;
	}
}
