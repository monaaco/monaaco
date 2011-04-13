package monaaco.tests;

import monaaco.Interfaz.SongInfoInterfaz;
import monaaco.bibliotecaXML.Track;
import junit.framework.TestCase;

public class SongInfoInterfazTest extends TestCase {
	
	public void testConstructor(){
		String ruta = "sounds/prueba.mp3";
		Track track = new Track(ruta);
		SongInfoInterfaz songInfo = new SongInfoInterfaz(track);
		assertEquals(track, songInfo.getTrack());
		assertEquals(songInfo, songInfo.getPrincipal());
	}
}
