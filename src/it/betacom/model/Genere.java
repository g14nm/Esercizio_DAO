package it.betacom.model;

public class Genere {
	
	private int codiceG;
	private String descrizione;
	
	public Genere(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public Genere(int codiceG, String descrizione) {
		this.codiceG = codiceG;
		this.descrizione = descrizione;
	}
	
	public int getCodiceG() {
		return codiceG;
	}
	
	public void setCodiceG(int codiceG) {
		this.codiceG = codiceG;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
}
