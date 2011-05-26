package IS2011.GestorXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;


import IS2011.biblioteca.Biblioteca;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.*;


public class GestorXML<T extends Object>{

	private InputStream is = null;
	
	private XStream xs = null;
	
	/**
	 * Constructora
	 */
	public GestorXML(Class t){	
		//DomDriver carga mas lento
		xs = new XStream(new DomDriver());
		//StaxDriver genera un xml de una sola linea
		//xs = new XStream(new StaxDriver());
		xs.processAnnotations(t);
	}
	
	
	/**
	 * Carga en objeto el contenido del XML en el fichero "filename"
	 * @param filename
	 * @return objeto cargada
	 * @throws Exception 
	 */
	public T cargar(String filename) throws Exception{
		return cargar(new File(filename));
	}
	
	/**
	 * Carga en objeto el contenido del XML en el fichero File
	 * @param file
	 * @return objeto cargada
	 * @throws Exception 
	 */
	public T cargar(File file) throws Exception {
		T object = (T) new Object();
			is = new FileInputStream(file);
		 	//necesario InputStreamReader para que lea carácteres especiales como tíldes!
			object = (T) xs.fromXML(new InputStreamReader(is));

		 return object;
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
				xs.toXML(objeto, new FileOutputStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,
					    "El fichero xml no ha podido ser guardado.\n" +
					    e.getMessage(),
					    "Error carga de la biblioteca",
					    JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
