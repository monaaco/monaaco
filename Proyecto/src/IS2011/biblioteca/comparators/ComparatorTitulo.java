package IS2011.biblioteca.comparators;

import java.util.Comparator;

import IS2011.biblioteca.Track;

public class ComparatorTitulo implements Comparator<Track> {


	/**
	 * Compara por album
	 */
	public int compare(Track o1, Track o2) {
		return o1.getAlbum().compareTo(o2.getAlbum());
	}

}
