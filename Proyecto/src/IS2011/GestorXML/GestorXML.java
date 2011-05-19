package IS2011.GestorXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GestorXML<T>{


	private File fichero = null;
	private T objeto = null;

	private InputStream is=null;
	

	/**
	 * 	
	 * @param objeto
	 * @param ruta
	 */
	public GestorXML(T objeto, String ruta) {
		this.objeto = objeto;
		fichero = new File(ruta);
	}
	
	/**
	 * 
	 * @param ruta
	 */
	public GestorXML(String ruta){
		fichero = new File(ruta);
	}
	
	/**
	 * 
	 * @param f
	 */
	public GestorXML(File f){
		this.fichero = f;
		
	}

	/**
	 * 	
	 * @return
	 */
	public File getFichero() {
		return fichero;
	}

	/**
	 * 
	 * @return
	 */
	public T getObjeto() {
		return objeto;
	}

	/**
	 * Carga en objeto el contenido del XML en el fichero pasado a la constructora
	 */
	public void cargar(){
		cargar(fichero);
	}
	
	/**
	 * Carga en objeto el contenido del XML en el fichero "filename"
	 * @param filename
	 */
	public void cargar(String filename){
		cargar(new File(filename));
	}
	
	/**
	 * Carga en objeto el contenido del XML en el fichero File
	 * @param file
	 */
	public void cargar(File file){
		try {
			 XStream xs = new XStream();
			 is = new FileInputStream(file);
			 objeto  = (T) new Object();
			 xs.processAnnotations(objeto.getClass());
			 //necesario InputStreamReader para que lea car�cteres especiales como t�ldes!
			 objeto = (T) xs.fromXML(new InputStreamReader(is));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				    "Problema en la carga, el formato es err�neo.\n" +
				    e.getMessage(),
				    "Error carga de la biblioteca",
				    JOptionPane.WARNING_MESSAGE);
			//TODO crear un nuevo xml vac�o
			e.printStackTrace();
		}

	}

	/**
	 * Guarda en el XML todo el contenido actual de la objeto objeto
	 */
	public void guardar(){
		//tarea correspondiente a la escritura del XML (Miguel, Jachu)
		// tener como entrada el ArrayList de la Biblioteca funcionXML(biblioteca)?�
		guardar(fichero);
	}
	
	/**
	 * Metodo para guardar la biblioteca a un archivo especificado mediante un String
	 * @param filename
	 */
	public void guardar(String filename){
		guardar(new File(filename));
	}
	
	/**
	 * Metodo para guardar la biblioteca a un archivo especificado
	 * @param filename
	 */
	public void guardar(File file){
		if( objeto != null){
			try {
				XStream xstream = new XStream();
				// Esta linea es para que haga caso de las anotaciones del tipo @XStream*
				xstream.processAnnotations(objeto.getClass());
				xstream.toXML(objeto, new FileOutputStream(file));
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,
					    "El fichero xml no ha podido ser guardado.\n" +
					    e.getMessage(),
					    "Error carga de la biblioteca",
					    JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
			}
		}
	}
}
