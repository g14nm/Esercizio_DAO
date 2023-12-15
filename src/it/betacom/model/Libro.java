package it.betacom.model;

public class Libro {
	
	private int codiceL, numPag, anno, codiceA, codiceG, codiceE;
	private String titolo, nomeAutore, cognomeAutore, genere, editore;
	
	public Libro() {};

	//costruttore per inserimento nel db
	public Libro(String titolo, int numPag, int anno, int codiceA, int codiceG, int codiceE) {
		this.titolo = titolo;
		this.numPag = numPag;
		this.anno = anno;
		this.codiceA = codiceA;
		this.codiceG = codiceG;
		this.codiceE = codiceE;
	}
	
	//costruttori per l'estrazione dal db
	public Libro(int codiceL, String titolo, int numPag, int anno, int codiceA, int codiceG, int codiceE) {
		this.codiceL = codiceL;
		this.titolo = titolo;
		this.numPag = numPag;
		this.anno = anno;
		this.codiceA = codiceA;
		this.codiceG = codiceG;
		this.codiceE = codiceE;
	}
	
	public Libro(int codiceL, String titolo, int numPag, int anno, String nomeAutore, String cognomeAutore, String genere, String editore) {
		this.codiceL = codiceL;
		this.titolo = titolo;
		this.numPag = numPag;
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

	public int getNumPag() {
		return numPag;
	}

	public void setNumPag(int numPag) {
		this.numPag = numPag;
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
		return "codiceL : " + this.codiceL + " | titolo : " + this.titolo + " | numero pagine : " + this.numPag + " | anno : " + this.anno + " | autore : " + this.nomeAutore + " " + this.cognomeAutore + " | genere : " + this.genere+ " | editore : " + this.editore;
	}
	
}
