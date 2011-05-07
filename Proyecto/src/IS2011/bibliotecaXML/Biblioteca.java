package IS2011.bibliotecaXML;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Biblioteca {

	
	private ArrayList<Track> b = null;
	
	public Biblioteca()
	{
		b=new ArrayList<Track>();
	}
	public ArrayList<Track> getBiblioteca()
	{
		return b;
	}
	/**
	 * Añade nun Track a la biblioteca
	 * @param tr: Track
	 */
	public void add(Track tr) {
		this.b.add(tr);
	}
	public void addAll(List<Track> trList) {
		Track aux;
		Iterator it = trList.iterator();
		while(it.hasNext())
		{
			aux = (Track)it.next();
			this.b.add(aux);
		}
	}
}

