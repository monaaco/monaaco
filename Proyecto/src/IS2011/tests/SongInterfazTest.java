package IS2011.tests;

import junit.framework.TestCase;
import IS2011.Interfaz.InterfazAvanzada;
import IS2011.Interfaz.SongInterfaz;

public class SongInterfazTest extends TestCase {
	public void testConstructor(){
		InterfazAvanzada interfazAvanzada = InterfazAvanzada.getSingleton();
		String[] temas = null;
	
		SongInterfaz songInterfaz = new SongInterfaz(temas, interfazAvanzada);
		assertEquals(songInterfaz.getInterfaz(), interfazAvanzada);	//Tira
		assertEquals(songInterfaz, songInterfaz.getPrincipal());	//Tira
	}

	public void testActualizaTemas(SongInterfaz song){
		String[] temas = new String[2];
		temas[0] = "Tema 0";
		temas[1] = "Tema 1";
		temas[2] = "Tema 2";
		song.actualizaTemas(temas);
		assertEquals(temas, song.getListado());
	}
}
