package metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Apprenant;
import model.Region;

public class Requetes {
	
	private static Statement statement = null;
	
	/**
	 * Méthode pour retourner tous les apprenants dans un tableau
	 */
	public static ArrayList<Apprenant> getAllApprenants() throws ClassNotFoundException, SQLException {
		
		ArrayList<Apprenant>  apprenants = new ArrayList<>();
		String requete	= "SELECT * FROM apprenants ORDER BY ID_APPRENANT";
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
	public static Region getRegionById(int id) throws ClassNotFoundException, SQLException

	{
		Region region = new Region();
		String requete	= "SELECT * FROM region WHERE ID_REGION="+id;
		ResultSet resultat = statement.executeQuery(requete);
		resultat.next();
		region.setId(resultat.getInt("ID_REGION"));
		region.setNomRegion(resultat.getString("NOM_REGION"));
		return region;
			
		}
}
