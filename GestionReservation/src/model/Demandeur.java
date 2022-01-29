
package model;
public class Demandeur {

	private int code;	
	private String nom;
	private String adresse;



	public Demandeur(String nom, String adresse, int code) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.code = code;
	}
	public Demandeur() {
		super(); 
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Demandeur [nom=" + nom + ", adresse=" + adresse + ", code=" + code + "]";
	}


}
