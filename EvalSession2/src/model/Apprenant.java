package model;

import java.sql.Date;

public class Apprenant {
	
	private int id;
	private String nom;
	private String prenom;
	private Date birthdate;
	private String email;
	private String photo;
	private Region region;
	
	public Apprenant(int id, String nom, String prenom, Date birthdate, String email, String photo, Region region) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.birthdate = birthdate;
		this.email = email;
		this.photo = photo;
		this.region = region;
	}
	
	public Apprenant() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region ;
	}

	@Override
	public String toString() {
		return "Apprenant id=" + id + ",\t\tnom=" + nom + ",\t\tprenom=" + prenom + ",\t\tbirthdate=" + birthdate + ",\t\temail="
				+ email + ",\t\tphoto=" + photo + ",\t\t\tregion=" + region;
	}

	public String afficherNomPrenom() {
		if (nom.length() > 10) {
			return "Apprenant "+ id + ",\t\tnom=" + nom + ",\tprenom=" + prenom;
		}
		else {
			return "Apprenant "+ id + ",\t\tnom=" + nom + ",\t\tprenom=" + prenom;
		}
		
	}
	
	public String afficherNomPrenomRegion() {
		return region.getNomRegion() + "\t:\t" + nom + " " + prenom;
	}
	
}
