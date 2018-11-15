package main;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import metier.Requetes;
import model.Activite;
import model.Apprenant;
import model.Region;

/**
 * 
 * @author Thomas et Cédric
 * Evaluation Session 2 Simplon.
 *
 */
public class Application {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ArrayList<Activite> listActiviteNonPratiques = Requetes.ActivitesNonPratiques();
		System.out.println("Les activités non pratiqués sont : \n");
		for (Activite activite : listActiviteNonPratiques) {
			System.out.println(activite.getActivite());
		}
		
//		Region region1 = new Region(3, "Aquitaine");
//		Apprenant toto = new Apprenant(19, "Toto", "Titi", Date.valueOf("2987-12-12"), "toto.titi@free.fr", "titi.jpg", region1);
//		Requetes.ajouterApprenant(toto);
		
//		Apprenant toto = Requetes.getApprenantByName("Toto");
//		Activite activite1 = Requetes.getActiviteById(9);
//		Activite activite2 = Requetes.getActiviteById(8);
//		Requetes.ajouterActiviteApprenant(toto, activite1);
//		Requetes.ajouterActiviteApprenant(toto, activite2);
//		Requetes.supprimerApprenant(toto);
		
		
//		String activiteRecherche = "Rien faire";
//		Activite activite1 = Requetes.getActiviteByActivite(activiteRecherche);
//		ArrayList<Apprenant> listApprenant = Requetes.getApprenantByActivite(activite1);
//		if (listApprenant.isEmpty()) {
//			System.out.println("L'activité : \"" + activite1.getActivite() + "\" n'a pas d'apprenant enregistré...");
//		}
//		else {
//			System.out.println("L'activité : \"" + activite1.getActivite() + "\" a comme apprenant(s) : \n");
//			
//			for (Apprenant apprenant : listApprenant) {
//				System.out.println(apprenant.getPrenom() + " " + apprenant.getNom());	
//			}
//		}
		
		
//		String nom = "Zébutruc";
//		Apprenant apprenant1 = Requetes.getApprenantByName(nom);
//		ArrayList<Activite> listActivite = Requetes.getActivitesByApprenant(apprenant1);
//		
//		if (listActivite.isEmpty()) {
//			System.out.println(apprenant1.getPrenom() + " n'a pas d'activité enregistrée...");
//		}
//		else {
//			System.out.println(apprenant1.getPrenom() + " a comme activité(s) : \n");
//			
//			for (Activite activite : listActivite) {
//				System.out.println(activite.getActivite());	
//			}
//		}
		
		
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
