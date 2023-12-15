package it.betacom.model;

public class Autore {
	
	private String nome, cognome, nazione;
	private int codiceA, annoNascita;
	private Integer annoMorte;
	private char sesso;
	
	public Autore(String nome, String cognome, int annoNascita, char sesso, String nazione) {
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
		this.annoMorte = null;
		this.sesso = sesso;
		this.nazione = nazione;
	}
	
	public Autore(String nome, String cognome, int annoNascita, int annoMorte, char sesso, String nazione) {
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
		this.annoMorte = annoMorte;
		this.sesso = sesso;
		this.nazione = nazione;
	}
	
	public Autore(int codiceA, String nome, String cognome, int annoNascita, char sesso, String nazione) {
		this.codiceA = codiceA;
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
		this.annoMorte = null;
		this.sesso = sesso;
		this.nazione = nazione;
	}
	
	public Autore(int codiceA, String nome, String cognome, int annoNascita, int annoMorte, char sesso, String nazione) {
		this.codiceA = codiceA;
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
		this.annoMorte = annoMorte;
		this.sesso = sesso;
		this.nazione = nazione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public int getCodiceA() {
		return codiceA;
	}

	public void setCodiceA(int codiceA) {
		this.codiceA = codiceA;
	}

	public int getAnnoNascita() {
		return annoNascita;
	}

	public void setAnnoNascita(int annoNascita) {
		this.annoNascita = annoNascita;
	}

	public Integer getAnnoMorte() {
		return annoMorte;
	}

	public void setAnnoMorte(int annoMorte) {
		this.annoMorte = annoMorte;
	}

	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
	}
	
	@Override
	public String toString() {
		return "codiceA : " + this.codiceA + " | nome : " + this.nome + " | cognome : " + this.cognome + " | annoN : " + this.annoNascita + " | annoM : " + this.annoMorte + " | sesso : " + this.sesso + " | nazione : " + this.nazione;
	}
	
}
