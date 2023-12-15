package it.betacom.service;

public interface PrintService<T> {

	public void saveListAsPdf();
	
	public void saveListAsCsv();
	
	public void saveListAsTxt();
	
	public void saveAsPdf(T t);
	
	public void saveAsCsv(T t);
	
	public void saveAsTxt(T t);
	
}
