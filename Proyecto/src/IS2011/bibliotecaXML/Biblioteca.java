package IS2011.bibliotecaXML;

import java.io.File;
import java.util.*;

public class Biblioteca {

	private File rutaBiblioteca = new File("xml/biblioteca.xml");
	private ArrayList<Track> biblioteca = null;
	
	public Biblioteca() {
		// TODO Auto-generated constructor stub
		biblioteca = new ArrayList<Track>();
	}

	public ArrayList<Track> getBiblioteca() {
		return biblioteca;
	}

	/**
	 * 
	 * @param trList
	 */
	public void addAll(List<Track> trList) {
		this.biblioteca.addAll(trList);
		
	}

	public void add(Track tr) {
		this.biblioteca.add(tr);
		
	}

	
	
	
}
