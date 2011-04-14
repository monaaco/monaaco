package IS2011.tests;

import IS2011.Interfaz.SongInfoInterfaz;
import IS2011.bibliotecaXML.Track;
import junit.framework.TestCase;

public class SongInfoInterfazTest extends TestCase {
	
	public void testConstructor(){
		String ruta = "sounds/prueba.mp3";
		Track track = new Track(ruta);
		SongInfoInterfaz songInfo;
		try {
			songInfo = new SongInfoInterfaz(track);
			assertEquals(track, songInfo.getTrack());
			assertEquals(songInfo, songInfo.getPrincipal());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
