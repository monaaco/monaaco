package IS2011.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("biblioteca")
public class Biblioteca {

	/**
	 * @uml.property  name="b"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="IS2011.biblioteca.Track"
	 */
	@XStreamAlias("tracks")
	private ArrayList<Track> b = null;
	
	/**
	 * @uml.property  name="colaReproduccion"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	@XStreamAlias("trackQueue")
	private Playlist colaReproduccion = null;
	

	/**
	 * Constructora por defecto de la biblioteca
	 */
	public Biblioteca()	{
		b = new ArrayList<Track>();
		colaReproduccion = new Playlist();
	}
	
	/**
	 * Devuelve un ArrayList con el contenido de la biblioteca 
	 * @return ArrayList<Track>
	 */
	public ArrayList<Track> getBiblioteca()	{
		return b;
	}
	
	/**
	 * @return  the colaReproduccion
	 * @uml.property  name="colaReproduccion"
	 */
	public Playlist getColaReproduccion() {
		return colaReproduccion;
	}

	/**
	 * @param colaReproduccion  the colaReproduccion to set
	 * @uml.property  name="colaReproduccion"
	 */
	public void setColaReproduccion(Playlist colaReproduccion) {
		this.colaReproduccion = colaReproduccion;
	}

	/**
	 * Añade un Track a la biblioteca
	 * @param tr: Track
	 */
	public void add(Track tr) {
		this.b.add(tr);
	}
	
	/**
	 * Añade una lista de tracks a la biblioteca
	 * @param trList
	 */
	public void addAll(List<Track> trList) {
		Track aux;
		Iterator<Track> it = trList.iterator();
		while(it.hasNext())
		{
			aux = (Track)it.next();
			this.b.add(aux);
		}
	}
	
	/**
	 * Nos proporciona un HashMap de la biblioteca, en la clave devuelve la ruta 
	 * del track y en el contenido TRUE
	 * @return HashMap <String, Boolean>
	 */
	public HashMap<String,Boolean> getHashMap(){
		HashMap<String,Boolean> resultado = new HashMap<String,Boolean>();
		Iterator<Track> it = getBiblioteca().iterator();
		String s;
		while (it.hasNext()){
			s = ((Track)it.next()).getLocation();
			resultado.put(s, Boolean.TRUE);
		}
		return resultado;
	}

	/**
	 * Vacia la biblioteca
	 */
	public void removeAll() {
		b.clear();				
	} 
}

