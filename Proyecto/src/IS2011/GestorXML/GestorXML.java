package IS2011.GestorXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XomDriver;

public class GestorXML<T extends Object>{


	private InputStream is = null;
	private XStream xs = null;
	
	/**
	 * Constructora
	 */
	public GestorXML(){	
		//DomDriver carga mas lento
		 //xs = new XStream();
		xs = new XStream(new DomDriver());
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
	public T cargar(File file){
		T objeto = (T) new Object();
		try {
			 is = new FileInputStream(file);
			 xs.processAnnotations(objeto.getClass());
			 //necesario InputStreamReader para que lea carácteres especiales como tíldes!
			 objeto = (T) xs.fromXML(new InputStreamReader(is));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				    "Problema en la carga, el formato es erróneo.\n" +
				    e.getMessage(),
				    "Error carga de la biblioteca",
				    JOptionPane.WARNING_MESSAGE);
			//TODO crear un nuevo xml vacío
			e.printStackTrace();
		}
		return objeto;
	}

	
	/**
	 * Metodo para guardar la biblioteca a un archivo especificado mediante un String
	 * @param filename
	 */
	public void guardar(T objeto, String filename){
		guardar(objeto, new File(filename));
	}
	
	/**
	 * Metodo para guardar la biblioteca a un archivo especificado
	 * @param filename
	 */
	public void guardar(T objeto, File file){
		if( objeto  != null){
			try {
				// Esta linea es para que haga caso de las anotaciones del tipo @XStream*
				xs.processAnnotations(objeto.getClass());
				xs.toXML(objeto, new FileOutputStream(file));
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
