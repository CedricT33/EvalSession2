package metier;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Activite;
import model.Apprenant;


public class Mapping {
	
	/**
	 * Méthode pour instancier un objet Apprenant
	 */
	public static Apprenant mapperApprenant(ResultSet resultat) throws SQLException, ClassNotFoundException {
		
		Apprenant apprenant = new Apprenant();
		apprenant.setId(resultat.getInt("ID_APPRENANT"));
		apprenant.setNom(resultat.getString("NOM"));
		apprenant.setPrenom(resultat.getString("PRENOM"));
		apprenant.setBirthdate(resultat.getDate("BIRTHDATE"));
		apprenant.setEmail(resultat.getString("EMAIL"));
		apprenant.setPhoto(resultat.getString("PHOTO"));
		apprenant.setRegion(Requetes.getRegionById(resultat.getInt("ID_REGION")));
		
		return apprenant;
	
	}
	
	/**
	 * Méthode pour instancier un objet Activite
	 */
	public static Activite mapperActivite(ResultSet resultat) throws SQLException, ClassNotFoundException {
		
		Activite activite = new Activite();
		activite.setId(resultat.getInt("ID_ACTIVITE"));
		activite.setActivite(resultat.getString("ACTIVITE"));
		
		return activite;
	
	}
}
