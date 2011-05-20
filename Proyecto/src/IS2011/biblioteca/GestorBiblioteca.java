package IS2011.biblioteca;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.mapper.CannotResolveClassException;

import IS2011.GestorXML.GestorXML;


/**
 * Adapter de Biblioteca
 * @author usuario_local
 *
 */
public class GestorBiblioteca {

	
	private GestorXML<Biblioteca> gestorXML = null;
	
	/**
	 * Fichero por defecto
	 */
	private File fileBiblioteca = new File("xml/biblioteca.xml");
	
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
		gestorXML = new GestorXML<Biblioteca>();
		cargarXML();
	}

	/**
	 * Esta función devuelve la variable biblioteca
	 */
	public Biblioteca getBiblioteca(){
		return biblioteca;
	}
	
	/**
	 * Mutadora de la variable biblioteca
	 * @param biblioteca
	 */
	public void setBiblioteca(Biblioteca biblioteca){
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
	 * Esta función simplemente llama a la de biblioteca
	 * @param tr
	 */
	public void add(Track tr) {
		this.biblioteca.add(tr);
	}	
	
	/** 
	 * Esta función simplemente llama a la de biblioteca
	 */
	public ArrayList<Track> getArrayList(){
		return biblioteca.getBiblioteca();
	}
	
	/** 
	 * Esta función simplemente llama a la de biblioteca
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
		gestorXML.guardar(getBiblioteca(), fileBiblioteca);
	}
	
	/**
	 * Guarda la biblioteca en XML en la ruta indicada
	 * @param file
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
		try {
			setBiblioteca(gestorXML.cargar(fileBiblioteca));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				    "Problema en la carga, el formato es erróneo.\n" +
				    "Se procederá a crear una biblioteca vacía\n" +
				    e.getMessage(),
				    "Error carga de la biblioteca",
				    JOptionPane.WARNING_MESSAGE);
					//crear y cargar un nuevo xml vacío
					gestorXML.guardar(new Biblioteca(), fileBiblioteca);
			e.printStackTrace();
		} finally {
			try {
				setBiblioteca(gestorXML.cargar(fileBiblioteca));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
					    "Problema en la carga, el formato es erróneo.\n" +
					    e.getMessage(),
					    "Error carga de la biblioteca",
					    JOptionPane.WARNING_MESSAGE);
			}
		}
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
	
		try {
			setBiblioteca(gestorXML.cargar(file));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				    "Problema en la carga, el formato es erróneo.\n" +
				    "Se procederá a crear una biblioteca vacía\n" +
				    e.getMessage(),
				    "Error carga de la biblioteca",
				    JOptionPane.WARNING_MESSAGE);
					//crear y cargar un nuevo xml vacío
					gestorXML.guardar(new Biblioteca(), fileBiblioteca);
			e.printStackTrace();
		} finally {
			try {
				setBiblioteca(gestorXML.cargar(fileBiblioteca));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
					    "Problema en la carga, el formato es erróneo.\n" +
					    e.getMessage(),
					    "Error carga de la biblioteca",
					    JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	

}
