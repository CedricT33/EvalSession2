package main;

import java.sql.SQLException;
import java.util.ArrayList;

import metier.Requetes;
import model.Activite;
import model.Apprenant;

/**
 * 
 * @author Thomas et Cédric
 * Evaluation Session 2 Simplon.
 *
 */
public class Application {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String nom = "Tressous";
		Apprenant apprenant1 = Requetes.getApprenantByName(nom);
		ArrayList<Activite> listActivite = Requetes.getActivitesByApprenant(apprenant1);
		System.out.println(apprenant1.getPrenom() + " a comme activité(s) : \n");
		for (Activite activite : listActivite) {
			System.out.println(activite.getActivite());
		}
		
//		ArrayList<Apprenant> apprenants = Requetes.getAllApprenants("ID_REGION");
//		
//		for (Apprenant apprenant : apprenants) {
//			System.out.println(apprenant.afficherNomPrenomRegion());
//		}
		
//		ArrayList<Apprenant> apprenants = Requetes.getAllApprenants("ID_APPRENANT");
//		
//		for (Apprenant apprenant : apprenants) {
//			System.out.println(apprenant.afficherNomPrenom());
//		}
	}
}
