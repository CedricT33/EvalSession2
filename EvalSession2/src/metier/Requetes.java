package metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.AccesBD;
import model.Activite;
import model.Apprenant;
import model.Apprenant_activite;
import model.Region;

public class Requetes {
	
	/**
	 * Méthode ajouter un nouvel apprenant
	 */
	public static void ajouterApprenant(Apprenant apprenant) {
		try {
			PreparedStatement prepareStatement = AccesBD.getConnection().prepareStatement("INSERT INTO apprenants (NOM, PRENOM, BIRTHDATE, EMAIL, PHOTO, ID_REGION) VALUES( ? , ? , ? , ?, ?, ?)");
			prepareStatement.setString(1,apprenant.getNom());
			prepareStatement.setString(2,apprenant.getPrenom());
			prepareStatement.setDate(3,apprenant.getBirthdate());
			prepareStatement.setString(4,apprenant.getEmail());
			prepareStatement.setString(5,apprenant.getPhoto());
			prepareStatement.setInt(6,apprenant.getRegion().getId());
			prepareStatement.executeUpdate();
			System.out.println("L'apprenant a bien été ajouté !");
		}
		catch(SQLException e) {
			System.out.println("Erreur lors de l'ajout de l'apprenant !");
		}
	}
	
	/**
	 * Méthode supprimer un apprenant
	 */
	public static void supprimerApprenant(Apprenant apprenant) throws SQLException
	{
		Statement statement = null;

		try {
			statement = AccesBD.getConnection().createStatement();
			String sql = "DELETE FROM apprenant_activite WHERE ID_APPRENANT = "+ apprenant.getId();//Suppression dans la table avec les foreign Key avant.
			String sql2 = "DELETE FROM apprenants WHERE ID_APPRENANT = "+ apprenant.getId();
			statement.executeUpdate(sql);
			statement.executeUpdate(sql2);
			System.out.println("Suppression de l'apprenant "+ apprenant + " effectuée");
		}
		catch(SQLException e){
			System.out.println(e);
			System.out.println("Erreur lors de la suppression de l'apprenant !");
		}
	}
	
	/**
	 * Méthode pour modifier le nom d'un apprenant.
	 */
	public static void modifierNomApprenant(Apprenant apprenant, String nom) {
		try {
			PreparedStatement prepareStatement = AccesBD.getConnection().prepareStatement("UPDATE apprenants SET NOM = ?  WHERE ID_APPRENANT = ? ");
			prepareStatement.setString(1,nom);
			prepareStatement.setInt(2,apprenant.getId());
			prepareStatement.executeUpdate();
			System.out.println("Modification effectuee pour l'apprenant : "+ apprenant);

		}
		catch(SQLException e){
			System.out.println("Erreur lors de la modification !");
		}
	}
	
	/**
	 * Méthode pour ajouter une activité à un apprenant
	 */
	public static void ajouterActiviteApprenant(Apprenant apprenant, Activite activite) {
		try {
			int id_apprenant = apprenant.getId();
			int id_activite = activite.getId();
			
			PreparedStatement prepareStatement = AccesBD.getConnection().prepareStatement("INSERT INTO apprenant_activite VALUES( ? , ? )");
			prepareStatement.setInt(1,id_activite);
			prepareStatement.setInt(2,id_apprenant);
			prepareStatement.executeUpdate();
			System.out.println("L'ajout d'activité a réussi !");
		}
		catch(SQLException e){
			System.out.println("L'ajout d'activité a échoué !");
		}
	}
	
	/**
	 * Méthode pour retourner une liste d'activites non pratiqués par les apprenants.
	 */
	public static ArrayList<Activite> ActivitesNonPratiques() throws SQLException, ClassNotFoundException {
		
		Statement statement = null;
		String requete	= "select * from activites left join apprenant_activite on activites.ID_ACTIVITE = apprenant_activite.ID_ACTIVITE where apprenant_activite.ID_ACTIVITE IS NULL";
		statement = AccesBD.getConnection().createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		ArrayList<Activite> listActiviteNonPratiques = new ArrayList<>();
		while(resultat.next())
		{
			Activite activite = Mapping.mapperActivite(resultat);
			listActiviteNonPratiques.add(activite);
		}
		
		return listActiviteNonPratiques;
	}
	
	/**
	 * Méthode pour retourner tous les apprenants rangés par @param dans un tableau
	 */
	public static ArrayList<Apprenant> getAllApprenants(String orderBy) throws ClassNotFoundException, SQLException {
		
		Statement statement = null;
		
		ArrayList<Apprenant>  apprenants = new ArrayList<Apprenant>();
		String requete	= "SELECT * FROM apprenants ORDER BY "+ orderBy;
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
	 * Méthode pour retourner un apprenant par son nom
	 */
	public static Apprenant getApprenantByName(String nom) throws ClassNotFoundException, SQLException {
		
		Statement statement = null;
		Apprenant apprenant = new Apprenant();
		String requete	= "SELECT * FROM apprenants WHERE NOM = '" + nom + "'";
		statement = AccesBD.getConnection().createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		
		while(resultat.next())
		{
			apprenant = Mapping.mapperApprenant(resultat);
		}

		return apprenant;
	}
	
	/**
	 * Méthode pour retourner un apprenant par son nom
	 */
	public static ArrayList<Apprenant> getApprenantByActivite(Activite activite) throws ClassNotFoundException, SQLException {
		
        int id_activite = activite.getId();
		
		Statement statement = null;
		ArrayList<Apprenant_activite> listApp_act = new ArrayList<>();
		String requete	= "SELECT * FROM apprenant_activite WHERE ID_ACTIVITE = " + id_activite;
		statement = AccesBD.getConnection().createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		
		while(resultat.next())
		{
			Apprenant_activite app_act = Mapping.mapperApprenant_activite(resultat);
			listApp_act.add(app_act);
		}
		
		ArrayList<Apprenant> listApprenants = new ArrayList<>();
		
		for (Apprenant_activite apprenant_activite : listApp_act) {
			int id_apprenant = apprenant_activite.getIdApprenant();
			Apprenant apprenant = getApprenantById(id_apprenant);
			listApprenants.add(apprenant);
		}
		
		return listApprenants;
	}
	
	//creer getApprenantById(int id_apprenant)
	
	/**
	 * Méthode pour retourner les activités de l'apprenant
	 */
	public static ArrayList<Activite> getActivitesByApprenant(Apprenant apprenant) throws ClassNotFoundException, SQLException {
		
		int id_apprenant = apprenant.getId();
		
		Statement statement = null;
		ArrayList<Apprenant_activite> listApp_act = new ArrayList<>();
		String requete	= "SELECT * FROM apprenant_activite WHERE ID_APPRENANT = " + id_apprenant;
		statement = AccesBD.getConnection().createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		
		while(resultat.next())
		{
			Apprenant_activite app_act = Mapping.mapperApprenant_activite(resultat);
			listApp_act.add(app_act);
		}
		
		ArrayList<Activite> listActivite = new ArrayList<>();
		
		for (Apprenant_activite apprenant_activite : listApp_act) {
			int id_activite = apprenant_activite.getIdActivite();
			Activite activite = getActiviteById(id_activite);
			listActivite.add(activite);
		}
		
		return listActivite;
	}
	
	/**
	 * Méthode pour retourner une activité avec l'Id_Activite
	 */
	public static Activite getActiviteById(int id_activite) throws ClassNotFoundException, SQLException {
		
		Statement statement = null;
		Activite activite = new Activite();
		String requete	= "SELECT * FROM activites WHERE ID_ACTIVITE = " + id_activite;
		statement = AccesBD.getConnection().createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		while(resultat.next())
		{
			activite = Mapping.mapperActivite(resultat);
		}
		
		return activite;
	}
	
	/**
	 * Méthode pour retourner une activité avec l'Id_Activite
	 */
	public static Apprenant getApprenantById(int id_apprenant) throws ClassNotFoundException, SQLException {
		
		Statement statement = null;
		Apprenant apprenant = new Apprenant();
		String requete	= "SELECT * FROM apprenants WHERE ID_Apprenant = " + id_apprenant;
		statement = AccesBD.getConnection().createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		while(resultat.next())
		{
			apprenant = Mapping.mapperApprenant(resultat);
		}
		
		return apprenant;
	}

	/**
	 * Méthode pour retourner une activité avec l'Id_Activite
	 */
	public static Activite getActiviteByActivite(String activiteRecherche) throws ClassNotFoundException, SQLException {
		
		Statement statement = null;
		Activite activite = new Activite();
		String requete	= "SELECT * FROM activites WHERE ACTIVITE = '" + activiteRecherche + "'";
		statement = AccesBD.getConnection().createStatement();
		ResultSet resultat = statement.executeQuery(requete);
		while(resultat.next())
		{
			activite = Mapping.mapperActivite(resultat);
		}
		
		return activite;
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
