package IS2011.tests;

import java.io.File;
import java.util.*;

import javax.swing.ImageIcon;

import IS2011.Interfaz.BotonAvanzado;
import IS2011.bibliotecaXML.Biblioteca;
import IS2011.bibliotecaXML.Track;

import junit.framework.TestCase;

public class BibliotecaTest extends TestCase {
	
	public void testGetBiblioteca() {
		Biblioteca b = new Biblioteca();
		Track tr = new Track("/sounds/prueba.mp3");
		Track tr2 = new Track("/sounds/prueba.mp3");
		List<Track> trList = new LinkedList<Track>();
		trList.add(tr);
		trList.add(tr2);
		b.addAll(trList);
		assertEquals(b.getBiblioteca().get(0), tr);
		assertEquals(b.getBiblioteca().get(1), tr2);
		assertEquals(b.getBiblioteca(), trList);
	}
		
	public void testAddBiblioteca() {
		Biblioteca b = new Biblioteca();
		Track tr = new Track("/sounds/prueba.mp3");
		Track tr2 = new Track("/sounds/prueba.mp3");
		b.add(tr);
		b.add(tr2);
		assertEquals(b.getBiblioteca().get(0), tr);
		assertEquals(b.getBiblioteca().get(1), tr2);
	}
	
	public void testAddAllBiblioteca() {
		Biblioteca b = new Biblioteca();
		Track tr = new Track("/sounds/prueba.mp3");
		Track tr2 = new Track("/sounds/prueba.mp3");
		List<Track> trList = new LinkedList<Track>();
		trList.add(tr);
		trList.add(tr2);
		b.addAll(trList);
		assertEquals(b.getBiblioteca().get(0), tr);
		assertEquals(b.getBiblioteca().get(1), tr2);
		assertEquals(b.getBiblioteca(), trList);
	}
	
	
	/* Este test en texto
	public void testCargaTrack(){ 
	}
	*/
}
