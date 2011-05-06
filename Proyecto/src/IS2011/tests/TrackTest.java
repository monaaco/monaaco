package IS2011.tests;

import java.io.File;

import java.util.*;

import IS2011.bibliotecaXML.*;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import junit.framework.TestCase;

public class TrackTest extends TestCase {

	public void testToString(){
		String ruta = "sounds/prueba.mp3";
		Track track = new Track(ruta);
		String cadena = "Artist: Rafael Lechowski\n Name: Donde duele inspira\n Time: 216\n";
		assertEquals(cadena, track.toString());		
	}
	
	public void testArtist() throws BasicPlayerException {
		Playlist listaReproduccion = new Playlist();
		listaReproduccion.setRepeat(true);
		listaReproduccion.add("sounds/prueba.mp3"); 

		assertEquals(listaReproduccion.current().getArtist(),"Rafael Lechowski");
	}
	public void testAlbum() throws BasicPlayerException {
		Playlist listaReproduccion = new Playlist();
		listaReproduccion.setRepeat(true);
		listaReproduccion.add("sounds/prueba.mp3"); 
		System.out.println(listaReproduccion.current().getName());
		assertEquals(listaReproduccion.current().getName(),"Donde duele inspira");
	}
	

}
