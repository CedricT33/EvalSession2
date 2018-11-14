package metier;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Apprenant;


public class Mapping {
	
	/**
	 * Méthode pour instancier un objet Apprenant
	 * @throws ClassNotFoundException 
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
}
