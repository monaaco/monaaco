package monaaco.tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JList;
import javax.swing.JScrollPane;

import com.sun.awt.AWTUtilities;

import monaaco.Interfaz.InterfazAvanzada;
import monaaco.Interfaz.SongInterfaz;
import junit.framework.TestCase;

public class SongInterfazTest extends TestCase {
	public void testConstructor(){
		InterfazAvanzada interfazAvanzada = new InterfazAvanzada();
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
