package IS2011.bibliotecaXML;

import java.io.File;
import java.util.*;

public class Biblioteca {

	private File rutaBiblioteca = new File("xml/biblioteca.xml");
	private ArrayList<Track> biblioteca = null;
	
	/**
	 * Crea una biblioteca a partir de el archivo XML por defecto
	 */
	public Biblioteca() {
		biblioteca = new ArrayList<Track>();
		carga();
	}
	
	/**
	 * Crea una biblioteca a partir de un archivo XML
	 * @param f: Archivo XML de entrada
	 */
	public Biblioteca(File f){
		rutaBiblioteca = f;
		biblioteca = new ArrayList<Track>();
		carga();
	}


	/**
	 * Devuelve todos los tracks de la biblioteca
	 * @return ArrayList<Track>
	 */
	public ArrayList<Track> getBiblioteca() {
		return biblioteca;
	}

	/**
	 * Añade toda una lista de tracks a la biblioteca
	 * @param trList
	 */
	public void addAll(List<Track> trList) {
		Track aux;
		Iterator it = trList.iterator();
		while(it.hasNext())
		{
			aux = (Track)it.next();
			this.biblioteca.add(aux);
		}
	}

	/**
	 * Añade nun Track a la biblioteca
	 * @param tr: Track
	 */
	public void add(Track tr) {
		this.biblioteca.add(tr);
	}

	/**
	 * Carga en la biblioteca el contenido del XML
	 */
	public void carga(){
		//TODO codigo perteneciente a la lectura del XML (bea y jorge)
		// necesitamos un ArrayList lo guardamos en: biblioteca = resultado;
	}
	
	/**
	 * Guarda en el XML todo el contenido actual de la biblioteca
	 */
	public void guarda(){
		//TODO codigo perteneciente a la escritura del XML (Jorge, Jachu)
		// Deberiais tener como entrada el ArrayList de la Biblioteca funcionXML(biblioteca)
	}
	
	public ArrayList<Track> getArray(){
		return biblioteca;
	}
}
