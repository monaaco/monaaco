package IS2011.bibliotecaXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

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
	 * Devuelve todos los tracks de la biblioteca
	 * @return ArrayList<Track>
	 */
/*	public Biblioteca getBiblioteca() {
		return biblioteca;
	}*/

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
	public void cargar(){
		XStream xs=new XStream(new DomDriver());
		
	
		try {
			 is = new FileInputStream(rutaBiblioteca);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//xs.fromXML
		//String xml = xs.toXML(is);
		//String aux=(String)xs.fromXML(xml);
		biblioteca=new Biblioteca();
		biblioteca=(Biblioteca)xs.fromXML(is);
		
		System.out.println("holaaa");

		//Iterator it=(Iterator)tracks.listIterator();
		
		//while(it.hasNext()){
			//Track t=(Track)it.next();
		//	System.out.println(t.getName());
		//}		
	}
	

	/**
	 * Guarda en el XML todo el contenido actual de la biblioteca
	 */
	public void guardar(){
		//TODO codigo perteneciente a la escritura del XML (Jorge, Jachu)
		// Deberiais tener como entrada el ArrayList de la Biblioteca funcionXML(biblioteca)
		XStream xstream = new XStream(new DomDriver());
			try {
				xstream.toXML(biblioteca, new FileOutputStream(rutaBiblioteca));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
		
		/*PrintWriter writer;
		try {
				writer = new PrintWriter(rutaBiblioteca);
		
				writer.print(tracksXML);
				writer.close(); 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}*/

	}

	
	public Biblioteca getArray(){
		return biblioteca;
	}
	
	
	
}
