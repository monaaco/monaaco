package IS2011.bibliotecaXML;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("biblioteca")
public class Biblioteca {

	@XStreamAlias("trackList")
	private ArrayList<Track> b = null;

	//TODO aqui podriamos tener los playlists
	
	public Biblioteca()
	{
		b=new ArrayList<Track>();
	}
	
	public ArrayList<Track> getBiblioteca()
	{
		return b;
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
		Iterator it = trList.iterator();
		while(it.hasNext())
		{
			aux = (Track)it.next();
			this.b.add(aux);
		}
	}
	
	public HashMap<String,Boolean> creaHashMap(){
		HashMap<String,Boolean> resultado = new HashMap<String,Boolean>();
		Iterator it = getBiblioteca().iterator();
		String s;
		while (it.hasNext()){
			s = ((Track)it.next()).getLocation();
			resultado.put(s, Boolean.TRUE);
		}
		return resultado;
	} 
}

