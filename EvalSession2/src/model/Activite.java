package model;

public class Activite {
	private int id;
	private String activite;
	
	public Activite(int id, String activite) {
		this.id = id;
		this.activite = activite;
	}
	
	public Activite() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	@Override
	public String toString() {
		return "Activite id=" + id + ", activite=" + activite;
	}
	
	
	
	
}
