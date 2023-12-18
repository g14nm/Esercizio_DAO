package it.betacom.model;

public class Libro {
	
	private int codiceL, numeroPagine, anno, codiceA, codiceG, codiceE;
	private String titolo, nomeAutore, cognomeAutore, genere, editore;

	//costruttore senza id per inserimento nel db e con l'inserimento degli id di autore, genere ed editore
	public Libro(String titolo, int numPag, int anno, int codiceA, int codiceG, int codiceE) {
		this.titolo = titolo;
		this.numeroPagine = numPag;
		this.anno = anno;
		this.codiceA = codiceA;
		this.codiceG = codiceG;
		this.codiceE = codiceE;
	}

	//costruttore per l'estrazione dal db con id del libro e gli id di autore, genere ed editore
	public Libro(int codiceL, String titolo, int numPag, int anno, int codiceA, int codiceG, int codiceE) {
		this.codiceL = codiceL;
		this.titolo = titolo;
		this.numeroPagine = numPag;
		this.anno = anno;
		this.codiceA = codiceA;
		this.codiceG = codiceG;
		this.codiceE = codiceE;
	}
	
	//costruttore per l'estrazione dal db con id e dettagli di autore, genere ed editore
	public Libro(int codiceL, String titolo, int numPag, int anno, String nomeAutore, String cognomeAutore, String genere, String editore) {
		this.codiceL = codiceL;
		this.titolo = titolo;
		this.numeroPagine = numPag;
		this.anno = anno;
		this.nomeAutore = nomeAutore;
		this.cognomeAutore = cognomeAutore;
		this.genere = genere;
		this.editore = editore;
	}

	public int getCodiceL() {
		return codiceL;
	}

	public void setCodiceL(int codiceL) {
		this.codiceL = codiceL;
	}

	public int getNumeroPagine() {
		return numeroPagine;
	}

	public void setNumeroPagine(int numeroPagine) {
		this.numeroPagine = numeroPagine;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getCodiceA() {
		return codiceA;
	}

	public void setCodiceA(int codiceA) {
		this.codiceA = codiceA;
	}

	public int getCodiceG() {
		return codiceG;
	}

	public void setCodiceG(int codiceG) {
		this.codiceG = codiceG;
	}

	public int getCodiceE() {
		return codiceE;
	}

	public void setCodiceE(int codiceE) {
		this.codiceE = codiceE;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getNomeAutore() {
		return nomeAutore;
	}

	public void setNomeAutore(String nomeAutore) {
		this.nomeAutore = nomeAutore;
	}

	public String getCognomeAutore() {
		return cognomeAutore;
	}

	public void setCognomeAutore(String cognomeAutore) {
		this.cognomeAutore = cognomeAutore;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getEditore() {
		return editore;
	}

	public void setEditore(String editore) {
		this.editore = editore;
	}

	@Override
	public String toString() {
		return
				this.codiceL + " | "
				+ this.titolo + " | "
				+ this.anno + " | "
				+ this.genere + " | "
				+ this.nomeAutore + " " + this.cognomeAutore + " | "
				+ this.numeroPagine + " pagine | "
				+ this.editore;
	}
	
	public String details() {
		StringBuilder sb = new StringBuilder();
		sb.append("codiceL: ").append(this.codiceL).append("\n");
		sb.append("Titolo: ").append(this.titolo).append("\n");
		sb.append("Numero pagine: ").append(this.numeroPagine).append("\n");
		sb.append("Anno: ").append(this.anno).append("\n");
		sb.append("Autore: ").append(this.nomeAutore).append(" ").append(this.cognomeAutore).append("\n");
		sb.append("Genere: ").append(this.genere).append("\n");
		sb.append("Editore: ").append(this.editore);
		return sb.toString();
	}
	
}
