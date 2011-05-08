package IS2011.tests;

import java.util.*;
import IS2011.bibliotecaXML.*;
import junit.framework.TestCase;

public class GestorXMLTest extends TestCase{
	

	public void testAddBiblioteca() {
		Biblioteca b = new Biblioteca();
		GestorXML gb = new GestorXML(b);
		Track tr = new Track("sounds/prueba.mp3");
		Track tr2 = new Track("sounds/prueba.mp3");
		gb.add(tr);
		gb.add(tr2);
		assertEquals(gb.getBiblioteca().get(0), tr);
		assertEquals(gb.getBiblioteca().get(1), tr2);
		assertEquals(b.getBiblioteca().get(0), tr);
		assertEquals(b.getBiblioteca().get(1), tr2);
	}
	
	public void testAddAllBiblioteca() {
		Biblioteca b = new Biblioteca();
		GestorXML gb = new GestorXML(b);
		Track tr = new Track("sounds/prueba.mp3");
		Track tr2 = new Track("sounds/prueba.mp3");
		List<Track> trList = new LinkedList<Track>();
		trList.add(tr);
		trList.add(tr2);
		b.addAll(trList);
		assertEquals(gb.getBiblioteca().get(0), tr);
		assertEquals(gb.getBiblioteca().get(1), tr2);
		assertEquals(gb.getBiblioteca(), trList);
		assertEquals(b.getBiblioteca().get(0), tr);
		assertEquals(b.getBiblioteca().get(1), tr2);
		assertEquals(b.getBiblioteca(), trList);

	}
}
