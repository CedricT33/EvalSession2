package metier;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Activite;
import model.Apprenant;
import model.Apprenant_activite;


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
	
	/**
	 * Méthode pour instancier un objet Apprenant_activite
	 */
	public static Apprenant_activite mapperApprenant_activite(ResultSet resultat) throws SQLException, ClassNotFoundException {
		
		Apprenant_activite app_act = new Apprenant_activite();
		app_act.setIdApprenant(resultat.getInt("ID_APPRENANT"));
		app_act.setIdActivite(resultat.getInt("ID_ACTIVITE"));
		
		return app_act;
	
	}
}
