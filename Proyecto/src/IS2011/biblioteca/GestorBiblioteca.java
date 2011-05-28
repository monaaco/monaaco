package IS2011.biblioteca;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import IS2011.GestorXML.GestorXML;


/**
 * Adapter de Biblioteca
 * @author usuario_local
 *
 */
public class GestorBiblioteca {

	
	/**
	 * @uml.property  name="gestorXML"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private GestorXML<Biblioteca> gestorXML = null;
	
	/**
	 * Ruta de la biblioteca por defecto
	 * @uml.property  name="rutaBiblioteca"
	 */	
	private final String rutaBiblioteca = "xml/biblioteca.xml";
			
	/**
	 * @uml.property  name="biblioteca"
	 * @uml.associationEnd  
	 */
	private Biblioteca biblioteca = null;
	
	/**
	 * Instancia de GestorBiblioteca (Patrón singleton)
	 */
	private static GestorBiblioteca instance = null;
	
	
	/**
	 * Patrón singleton.
	 * Devuelve la única instancia de la clase GestorBiblioteca
	 */
	public static GestorBiblioteca getInstance() {
		if(instance == null){
			instance = new GestorBiblioteca();
		}
		return instance;
	}
	
	/**
	 * Crea una biblioteca a partir de el archivo XML por defecto  (Patrón singleton)
	 * @param b
	 */
	private GestorBiblioteca() {
		gestorXML = new GestorXML<Biblioteca>(Biblioteca.class);
		cargarXML();
	}

	/**
	 * Esta función devuelve la variable biblioteca
	 * @uml.property  name="biblioteca"
	 */
	public Biblioteca getBiblioteca(){
		return biblioteca;
	}
	
	/**
	 * Mutadora de la variable biblioteca
	 * @param  biblioteca
	 * @uml.property  name="biblioteca"
	 */
	private void setBiblioteca(Biblioteca biblioteca){
		this.biblioteca = biblioteca;
	}

	/**
	 * Esta función simplemente llama a la de biblioteca
	 * @param trList
	 */
	public void addAll(List<Track> trList) {
		this.biblioteca.addAll(trList);
	}

	/**
	 * Esta función adapta la de biblioteca
	 * @param tr
	 */
	public void add(Track tr) {
		this.biblioteca.add(tr);
	}	
	
	/** 
	 * Esta función adapta la de biblioteca
	 */
	public ArrayList<Track> getArrayList(){
		return getBiblioteca().getBiblioteca();
	}
	
	
	/**
	 * @return the colaReproduccion
	 */
	public Playlist getColaReproduccion() {
		return getBiblioteca().getColaReproduccion();
	}

	/**
	 * @param colaReproduccion the colaReproduccion to set
	 */
	public void setColaReproduccion(Playlist colaReproduccion) {
		getBiblioteca().setColaReproduccion(colaReproduccion);
		}
	
	/** 
	 * Esta función adapta la de biblioteca
	 */
	public void remove(Track track){
		//TODO
	}
	
	/** 
	 * Vacía la biblioteca
	 */
	public void removeAll(){
		getBiblioteca().removeAll();
	}
	
	/**
	 * Esta función simplemente llama a la de biblioteca
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
		try{
			setBiblioteca(gestorXML.cargar(file));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				    "Problema en la carga de las biblioteca, el formato es erróneo.\n" +
				    "Se creará una biblioteca vacía.\n" +
				    e.getMessage(),
				    "Error carga del xml",
			JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			//crear xml vacío y cargarlo
			setBiblioteca(new Biblioteca());
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			guardarXML(file);
		}
	}
	
	

}
