package model;

public class Region {
	private int id;
	private String nomRegion;
	
	public Region(int id, String nomRegion) {
		this.id = id;
		this.nomRegion = nomRegion;
	}
	
	public Region() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomRegion() {
		return nomRegion;
	}

	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}

	@Override
	public String toString() {
		return "Region id=" + id + ", nomRegion=" + nomRegion;
	}
	
	
	
}
