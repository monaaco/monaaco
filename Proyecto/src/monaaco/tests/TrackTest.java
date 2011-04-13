package monaaco.tests;

import java.io.File;

import javax.sound.midi.Track;
import monaaco.bibliotecaXML.*;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import junit.framework.TestCase;

public class TrackTest extends TestCase {

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
