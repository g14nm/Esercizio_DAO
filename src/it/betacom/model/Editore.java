package it.betacom.model;

public class Editore {
	
	private int codiceE;
	private String nome;
	
	public Editore(String nome) {
		this.nome = nome;
	}
	
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
	
}
