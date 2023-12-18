package it.betacom.model;

public class Autore {
	
	private String nome, cognome, nazione;
	private int codiceA, annoNascita;
	private Integer annoMorte;
	private char sesso;

	//costruttore senza id per inserimento nel db
	public Autore(String nome, String cognome, int annoNascita, int annoMorte, char sesso, String nazione) {
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
		this.annoMorte = annoMorte;
		this.sesso = sesso;
		this.nazione = nazione;
	}

	//costruttore senza id e senza anno di morte per inserimento nel db
	public Autore(String nome, String cognome, int annoNascita, char sesso, String nazione) {
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
		this.annoMorte = null;
		this.sesso = sesso;
		this.nazione = nazione;
	}
	
	//costruttore per l'estrazione dal db con id
	public Autore(int codiceA, String nome, String cognome, int annoNascita, int annoMorte, char sesso, String nazione) {
		this.codiceA = codiceA;
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
		this.annoMorte = annoMorte;
		this.sesso = sesso;
		this.nazione = nazione;
	}
	
	//costruttore per l'estrazione dal db con id e senza anno di morte
	public Autore(int codiceA, String nome, String cognome, int annoNascita, char sesso, String nazione) {
		this.codiceA = codiceA;
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
		this.annoMorte = null;
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
		return
				this.codiceA + " | "
				+ this.nome + " " + this.cognome
				+ " (" + this.annoNascita + " - " + ((this.annoMorte == null) ? "in vita" : this.annoMorte) + ") "
//				+ "[" + this.sesso + "] "
				+ "[" + this.nazione + "]";
	}
	
	public String details() {
		StringBuilder sb = new StringBuilder();
		sb.append("codiceA: ").append(this.codiceA).append("\n");
		sb.append("Nome: ").append(this.nome).append("\n");
		sb.append("Cognome: ").append(this.cognome).append("\n");
		sb.append("Anno di nascita: ").append(this.annoNascita).append("\n");
		sb.append("Anno di morte: ").append((this.annoMorte == null) ? "in vita" : this.annoMorte).append("\n");
		sb.append("Sesso: ").append(this.sesso).append("\n");
		sb.append("Nazione: ").append(this.nazione);
		return sb.toString();
	}
	
}
