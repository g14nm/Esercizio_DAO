package it.betacom.model;

public class Editore {
	
	private int codiceE;
	private String nome;
	
	//costruttore senza id per l'inserimento nel db
	public Editore(String nome) {
		this.nome = nome;
	}
	
	//costruttore per l'estrazione dal db con id
	public Editore(int codiceE, String nome) {
		this.codiceE = codiceE;
		this.nome = nome;
	}
	
	public int getCodiceE() {
		return codiceE;
	}
	
	public void setCodiceE(int codiceE) {
		this.codiceE = codiceE;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return this.codiceE + " | " + this.nome;
	}
}
