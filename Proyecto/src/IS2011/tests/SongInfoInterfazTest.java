package IS2011.tests;

import junit.framework.TestCase;
import IS2011.Interfaz.SongInfoInterfaz;
import IS2011.biblioteca.Track;

public class SongInfoInterfazTest extends TestCase {
	
	public void testConstructor(){
		String ruta = "sounds/prueba.mp3";
		Track track = new Track(ruta);
		SongInfoInterfaz songInfo;
		songInfo = new SongInfoInterfaz();
		songInfo.actualiza(track);
		assertEquals(track, songInfo.getTrack());
		assertEquals(songInfo, songInfo.getPrincipal());
	}
}
