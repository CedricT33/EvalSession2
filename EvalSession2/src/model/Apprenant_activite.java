package model;

public class Apprenant_activite {
	
	private int idApprenant;
	private int idActivite;
	
	public Apprenant_activite(int idApprenant, int idActivite) {
		this.idApprenant = idApprenant;
		this.idActivite = idActivite;
	}
	
	public Apprenant_activite() {
		
	}

	public int getIdApprenant() {
		return idApprenant;
	}

	public void setIdApprenant(int idApprenant) {
		this.idApprenant = idApprenant;
	}

	public int getIdActivite() {
		return idActivite;
	}

	public void setIdActivite(int idActivite) {
		this.idActivite = idActivite;
	}

	@Override
	public String toString() {
		return "Apprenant_activite idApprenant=" + idApprenant + ", idActivite=" + idActivite;
	}

}
