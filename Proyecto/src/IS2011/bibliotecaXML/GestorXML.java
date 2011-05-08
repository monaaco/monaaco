package IS2011.bibliotecaXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GestorXML {

	//private File rutaBiblioteca = new File("xml/biblioteca.xml");
	//private ArrayList<Track> biblioteca = null;
	
	private File rutaBiblioteca ;
	private Biblioteca biblioteca;
	private Biblioteca carga;
	private InputStream is=null;
	
	/**
	 * Crea una biblioteca a partir de el archivo XML por defecto
	 * @param b
	 */
	public GestorXML(Biblioteca b) {
	//	biblioteca = new ArrayList<Track>();
	//	carga();
		biblioteca = b;
		rutaBiblioteca= new File("xml/biblioteca.xml");
	}
	
	public GestorXML(){
		rutaBiblioteca= new File("xml/biblioteca.xml");
		
	}
	
	/**
	 * Crea una biblioteca a partir de un archivo XML
	 * @param f: Archivo XML de entrada
	 */
	public GestorXML(File f){
		rutaBiblioteca = f;
	//	biblioteca = new ArrayList<Track>();
	//	carga();
		
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
	 * Carga en la biblioteca el contenido del XML
	 */
	public void cargar(){
		try {
			 XStream xs=new XStream(new DomDriver());
			 is = new FileInputStream(rutaBiblioteca);
			 biblioteca = new Biblioteca();
			 xs.processAnnotations(Biblioteca.class);
			 biblioteca = (Biblioteca)xs.fromXML(is);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "La biblioteca no ha podido ser cargada, el formato es erróneo.",
				    "Error cargando la biblioteca",
				    JOptionPane.WARNING_MESSAGE);
			//TODO crear un nuevo biblioteca.xml 
			e.printStackTrace();
		}
		//xs.fromXML
		//String xml = xs.toXML(is);
		//String aux=(String)xs.fromXML(xml);
		//Iterator it=(Iterator)tracks.listIterator();
		
		//while(it.hasNext()){
			//Track t=(Track)it.next();
		//	System.out.println(t.getName());
		//}		
	}
	

	/**
	 * Guarda en el XML todo el contenido actual de la biblioteca b
	 */
	public void guardar(){
		//TODO codigo perteneciente a la escritura del XML (Miguel, Jachu)
		// tener como entrada el ArrayList de la Biblioteca funcionXML(biblioteca)?¿
		
		if( biblioteca != null){
				try {
					XStream xstream = new XStream(new DomDriver());
					// Esta linea es para que haga caso de las anotaciones del tipo @XStream*
					xstream.processAnnotations(Biblioteca.class);
					xstream.toXML(biblioteca, new FileOutputStream(rutaBiblioteca));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		}
				/*	
				PrintWriter writer;
				try {
						writer = new PrintWriter(rutaBiblioteca);
				
						writer.print(tracksXML);
						writer.close(); 
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				 */

	}

	
	public Biblioteca getArray(){
		return biblioteca;
	}
	
	
	
}
