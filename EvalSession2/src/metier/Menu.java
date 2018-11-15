package metier;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Activite;
import model.Apprenant;
import model.Region;

public class Menu {
	
	private static Scanner saisieUtilisateur = new Scanner(System.in);
	private static String saisie;
	private static boolean premierTourBoucle = true;
	
	public static void afficherMenu() throws ClassNotFoundException, SQLException {
		slowWriting("-------------------------------------------------------------\n                        EVAL SESSION 2 \n-------------------------------------------------------------\n\n");
				
		do {
			if (!premierTourBoucle) {
				slowWriting("\n------------------------------------------------------------------------\n");
			}
			System.out.println("Veuillez choisir votre requete :\n");
			System.out.println("1 - Affichez les noms et prénoms de tous les apprenant(e)s.");
			System.out.println("2 - Affichez la liste des apprenants pour chaque région.");
			System.out.println("3 - Recherchez les activités pratiquées par un apprenant.");
			System.out.println("4 - Recherchez les apprenants qui pratiquent une activité spécifique.");
			System.out.println("5 - Ajouter un(e) nouvel(le) apprenant(e).");
			System.out.println("6 - Affecter une activité à un apprenant.");
			System.out.println("7 - Liste des activités non pratiquées.");
			System.out.println("8 - Modifier le nom d’un(e) apprenant(e).");
			System.out.println("9 - Supprimer un apprenant.");
			System.out.println("10 - Quitter.\n");
			System.out.print("Choix : ");
			saisie = saisieUtilisateur.nextLine();
			System.out.println("\n------------------------------------------------------------------------\n");
			
			premierTourBoucle = false;
			
			switch (saisie) {
				case "1" : nomPrenomAllApprenant(); break;
				case "2" : listeApprenantByRegion(); break;
				case "3" : rechercheActivitesByApprenant(); break;
				case "4" : rechercheApprenantsByActivite(); break;
				case "5" : ajouterNouvelApprenant(); break;
				case "6" : affecterActiviteApprenant(); break;
				case "7" : listeActiviteNonPratiques(); break;
				case "8" : modifierNomApprenant(); break;
				case "9" : supprimerUnApprenant(); break;
				case "10" : break;
				default : System.out.println("\nVeuillez taper un chiffre dans la liste svp !");
			}
		} while (!saisie.equals("10"));	
		
		System.out.println("Au revoir ! :)");
	}
	
	public static void nomPrenomAllApprenant() throws ClassNotFoundException, SQLException {
		ArrayList<Apprenant> apprenants = Requetes.getAllApprenants("ID_APPRENANT");
		
		for (Apprenant apprenant : apprenants) {
			System.out.println(apprenant.afficherNomPrenom());
		}
	}
	
	public static void nomAllApprenant() throws ClassNotFoundException, SQLException {
		ArrayList<Apprenant> apprenants = Requetes.getAllApprenants("ID_APPRENANT");
		
		for (Apprenant apprenant : apprenants) {
			System.out.println(apprenant.getNom());
		}
	}
	
	public static void AllActivites() throws ClassNotFoundException, SQLException {
		ArrayList<Activite> activites = Requetes.getAllActivites();
		
		for (Activite activite : activites) {
			System.out.println(activite.getActivite());
		}
	}
	
	public static void listeApprenantByRegion() throws ClassNotFoundException, SQLException {
		ArrayList<Apprenant> apprenants = Requetes.getAllApprenants("ID_REGION");
		
		for (Apprenant apprenant : apprenants) {
			System.out.println(apprenant.afficherNomPrenomRegion());
			
		}
	}
	
	public static void rechercheActivitesByApprenant() throws ClassNotFoundException, SQLException {
		System.out.println("Veuillez entrer un nom d'apprenant parmi : \n");
		nomAllApprenant();
		System.out.print("\nChoix : ");
		String nom = saisieUtilisateur.nextLine();
		
		Apprenant apprenant1 = Requetes.getApprenantByName(nom);
		ArrayList<Activite> listActivite = Requetes.getActivitesByApprenant(apprenant1);
		
		if (listActivite.isEmpty()) {
			System.out.println("\n" + apprenant1.getPrenom() + " n'a pas d'activité enregistrée...");
		}
		else {
			System.out.println("\n" + apprenant1.getPrenom() + " a comme activité(s) : \n");
			
			for (Activite activite : listActivite) {
				System.out.println(activite.getActivite());	
			}
		}
	}
	
	public static void rechercheApprenantsByActivite() throws ClassNotFoundException, SQLException {
		System.out.println("Veuillez entrer le nom d'une activité parmi : \n");
		AllActivites();
		System.out.print("\nChoix : ");
		String activiteRecherche = saisieUtilisateur.nextLine();
		Activite activite1 = Requetes.getActiviteByActivite(activiteRecherche);
		ArrayList<Apprenant> listApprenant = Requetes.getApprenantByActivite(activite1);
		if (activite1.getActivite() == null) {
			System.out.println("\nCette activité n'existe pas !");
		}
		else {
			if (listApprenant.isEmpty()) {
				System.out.println("\nL'activité : \"" + activite1.getActivite() + "\" n'a pas d'apprenant enregistré...");
			}
			else {
				System.out.println("\nL'activité : \"" + activite1.getActivite() + "\" a comme apprenant(s) : \n");
				
				for (Apprenant apprenant : listApprenant) {
					System.out.println(apprenant.getPrenom() + " " + apprenant.getNom());	
				}
			}
		}
	}
	
	public static void ajouterNouvelApprenant() throws SQLException {
		System.out.print("Entrez le NOM du nouvel apprenant : ");
		String nom = saisieUtilisateur.nextLine();
		System.out.print("Entrez le PRENOM du nouvel apprenant : ");
		String prenom = saisieUtilisateur.nextLine();
		System.out.print("Entrez la date de naissance du nouvel apprenant (AAAA-MM-JJ) : ");
		String date = saisieUtilisateur.nextLine();
		System.out.print("Entrez l'EMAIL du nouvel apprenant : ");
		String mail = saisieUtilisateur.nextLine();
		System.out.println("Entrez la région du nouvel apprenant");
		System.out.print("1-Ile de france\n2-Pays de Loire\n3-Aquitaine\nChoix : ");
		String choixRegion = saisieUtilisateur.nextLine();
		Region region = new Region(1, "Ile de France");
		
		switch (choixRegion) {
		case "1" : region = new Region(1, "Ile de France"); break;
		case "2" : region = new Region(2, "Pays de Loire"); break;
		case "3" : region = new Region(3, "Aquitaine"); break;
		default : System.out.println("\nMauvais choix de région, l'apprenant aura la région par défaut (Ile de France) !"); break;
		}
		
		Apprenant toto = new Apprenant(99, nom, prenom, Date.valueOf(date), mail, prenom + ".jpg", region);
		Requetes.ajouterApprenant(toto);
	}
	
	public static void affecterActiviteApprenant() throws ClassNotFoundException, SQLException {
		System.out.println("Choisissez un apprenant parmi : \n");
		nomPrenomAllApprenant();
		System.out.print("\nChoix : ");
		int choixApprenant = Integer.parseInt(saisieUtilisateur.nextLine());
		Apprenant apprenantChoisi = Requetes.getApprenantById(choixApprenant);
		System.out.println("\nChoisissez une activité parmi : \n");
		ArrayList<Activite> listeActivite = Requetes.getAllActivites();
		for (Activite activite : listeActivite) {
			System.out.println("Activité n°" + activite.getId() + " " + activite.getActivite());
		}
		System.out.print("\nChoix : ");
		int choixActivite= Integer.parseInt(saisieUtilisateur.nextLine());
		Activite activiteChoisie = Requetes.getActiviteById(choixActivite);
		Requetes.ajouterActiviteApprenant(apprenantChoisi, activiteChoisie);
	}
	
	public static void listeActiviteNonPratiques() throws ClassNotFoundException, SQLException {
		ArrayList<Activite> listActiviteNonPratiques = Requetes.ActivitesNonPratiques();
		System.out.println("Les activités non pratiqués sont : \n");
		for (Activite activite : listActiviteNonPratiques) {
			System.out.println("-" + activite.getActivite());
		}
	}
	
	public static void modifierNomApprenant() throws ClassNotFoundException, SQLException {
		System.out.println("Choisissez un apprenant parmi : \n");
		nomPrenomAllApprenant();
		System.out.print("\nChoix : ");
		int choixApprenant = Integer.parseInt(saisieUtilisateur.nextLine());
		Apprenant apprennantChoisi = Requetes.getApprenantById(choixApprenant);
		System.out.print("Entrez un nouveau nom : ");
		String nouveauNom = saisieUtilisateur.nextLine();
		Requetes.modifierNomApprenant(apprennantChoisi, nouveauNom);
	}
	
	public static void supprimerUnApprenant() throws ClassNotFoundException, SQLException {
		System.out.println("Choisissez un apprenant à supprimer parmi : \n");
		nomPrenomAllApprenant();
		System.out.print("\nChoix : ");
		int choixApprenant = Integer.parseInt(saisieUtilisateur.nextLine());
		Apprenant apprennantChoisi = Requetes.getApprenantById(choixApprenant);
		Requetes.supprimerApprenant(apprennantChoisi);
	}
	
	  // Method for writing letter by letter slowly.
	  static void slowWriting(String word) {
	    for (int i = 0; i < word.length(); i++) {
	      System.out.print(word.charAt(i));
	      try {
	        Thread.sleep(20); // 20 milliseconds pause between each letter display.
	      } catch (InterruptedException ie) {
	        ie.printStackTrace();
	      }
	    }
	  }
}
