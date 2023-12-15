package it.betacom.model;

public class Libro {
	
	private int codiceL, numPag, anno, codiceA, codiceG, codiceE;
	private String titolo;

	public Libro(String titolo, int numPag, int anno, int codiceA, int codiceG, int codiceE) {
		this.titolo = titolo;
		this.numPag = numPag;
		this.anno = anno;
		this.codiceA = codiceA;
		this.codiceG = codiceG;
		this.codiceE = codiceE;
	}
	
	public Libro(int codiceL, String titolo, int numPag, int anno, int codiceA, int codiceG, int codiceE) {
		this.codiceL = codiceL;
		this.titolo = titolo;
		this.numPag = numPag;
		this.anno = anno;
		this.codiceA = codiceA;
		this.codiceG = codiceG;
		this.codiceE = codiceE;
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

	@Override
	public String toString() {
		return "codiceL : " + this.codiceL + " | titolo : " + this.titolo + " | numero pagine : " + this.numPag + " | anno : " + this.anno + " | codice autore : " + this.codiceA + " " + " | codice genere : " + this.codiceG + " | codice editore : " + this.codiceE;
	}
	
}
