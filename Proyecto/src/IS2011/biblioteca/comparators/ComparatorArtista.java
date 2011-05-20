package IS2011.biblioteca.comparators;

import java.util.Comparator;

import IS2011.biblioteca.Track;

public class ComparatorArtista implements Comparator<Track>{

	/**
	 * Compara por artista
	 */
	public int compare(Track o1, Track o2) {
		return o1.getArtist().compareTo(o2.getArtist());
	}

	
}
