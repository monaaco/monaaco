package IS2011.biblioteca;

import java.io.File;
import java.util.*;

import javax.swing.JOptionPane;

import IS2011.GestorXML.GestorXML;


/**
 * Adapter de Biblioteca
 * @author usuario_local
 *
 */
public class GestorBiblioteca {

	
	private GestorXML<Biblioteca> gestorXML = null;
	
	/**
	 * Ruta de la biblioteca por defecto 
	 */	
	private final String rutaBiblioteca = "xml/biblioteca.xml";
			
	private Biblioteca biblioteca = null;
	
	/**
	 * Instancia de GestorBiblioteca (Patr�n singleton)
	 */
	private static GestorBiblioteca instance = null;
	
	
	/**
	 * Patr�n singleton.
	 * Devuelve la �nica instancia de la clase GestorBiblioteca
	 */
	public static GestorBiblioteca getInstance() {
		if(instance == null){
			instance = new GestorBiblioteca();
		}
		return instance;
	}
	
	/**
	 * Crea una biblioteca a partir de el archivo XML por defecto  (Patr�n singleton)
	 * @param b
	 */
	private GestorBiblioteca() {
		gestorXML = new GestorXML<Biblioteca>(Biblioteca.class);
		cargarXML();
	}

	/**
	 * Esta funci�n devuelve la variable biblioteca
	 */
	private Biblioteca getBiblioteca(){
		return biblioteca;
	}
	
	/**
	 * Mutadora de la variable biblioteca
	 * @param biblioteca
	 */
	private void setBiblioteca(Biblioteca biblioteca){
		this.biblioteca = biblioteca;
	}

	/**
	 * Esta funci�n simplemente llama a la de biblioteca
	 * @param trList
	 */
	public void addAll(List<Track> trList) {
		this.biblioteca.addAll(trList);
	}

	/**
	 * Esta funci�n adapta la de biblioteca
	 * @param tr
	 */
	public void add(Track tr) {
		this.biblioteca.add(tr);
	}	
	
	/** 
	 * Esta funci�n adapta la de biblioteca
	 */
	public ArrayList<Track> getArrayList(){
		return biblioteca.getBiblioteca();
	}
	
	/** 
	 * Esta funci�n adapta la de biblioteca
	 */
	public void remove(Track track){
		//TODO
	}
	
	/** 
	 * Vac�a la biblioteca
	 */
	public void removeAll(){
		getBiblioteca().removeAll();
	}
	
	/**
	 * Esta funci�n simplemente llama a la de biblioteca
	 * @return Hashmap de la biblioteca
	 */
	public HashMap<String,Boolean> getHashMap(){
		return biblioteca.getHashMap();
	}
	
	/**
	 * Guarda la biblioteca en XML en la ruta por defecto
	 */
	public void guardarXML(){
		gestorXML.guardar(getBiblioteca(), rutaBiblioteca);
	}
	
	/**
	 * Guarda la biblioteca en XML en la ruta indicada
	 * @param ruta
	 */
	public void guardarXML(String ruta){
		gestorXML.guardar(getBiblioteca(), ruta);
	}
	
	/**
	 * Guarda la biblioteca en XML en el archivo indicado
	 * @param file
	 */
	public void guardarXML(File file){
		gestorXML.guardar(getBiblioteca(), file);
	}

	/**
	 * Carga el archivo xml
	 */
	public void cargarXML() {
			cargarXML(rutaBiblioteca);
	}
	
	/**
	 * Carga el archivo xml desde la ruta indicada
	 * @param ruta
	 */
	public void cargarXML(String ruta) {
		cargarXML(new File(ruta));
	}
	
	/**
	 * Carga el archivo xml indicado
	 * @param file
	 */
	public void cargarXML(File file) {
			setBiblioteca(gestorXML.cargar(file));
	}
	
	

}