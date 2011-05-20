package IS2011.tests;

import java.util.*;

import IS2011.biblioteca.*;
import junit.framework.TestCase;

public class BibliotecaTest extends TestCase {
	
	/**
	 * Testeo de la función getBiblioteca:
	 *  - comprobar que get biblioteca devuelve bien la biblioteca, y en consecuencia
	 *  que se agregan bien las tracks.
	 */
	public void testGetBiblioteca() {
		GestorBiblioteca.getInstance();
		Track tr = new Track("sounds/prueba.mp3");
		Track tr2 = new Track("sounds/prueba.mp3");
		List<Track> trList = new LinkedList<Track>();
		trList.add(tr);
		trList.add(tr2);
		GestorBiblioteca.getInstance().addAll(trList);
		assertEquals(GestorBiblioteca.getInstance().getArrayList().get(0), tr);
		assertEquals(GestorBiblioteca.getInstance().getArrayList().get(1), tr2);
		assertEquals(GestorBiblioteca.getInstance().getArrayList(), trList);
	}
	
	/**
	 * Testeo de la función add:
	 *  - comprobar que se agregan bien las tracks.
	 */	
	public void testAddBiblioteca() {
		Biblioteca b = new Biblioteca();
		Track tr = new Track("sounds/prueba.mp3");
		Track tr2 = new Track("sounds/prueba.mp3");
		b.add(tr);
		b.add(tr2);
		assertEquals(b.getArrayList().get(0), tr);
		assertEquals(b.getArrayList().get(1), tr2);
	}
	
	/**
	 * Testeo de la función addAll:
	 *  - comprobar que se agregan bien las listas de tracks.
	 */	
	public void testAddAllBiblioteca() {
		Biblioteca b = new Biblioteca();
		Track tr = new Track("/sounds/prueba.mp3");
		Track tr2 = new Track("/sounds/prueba.mp3");
		List<Track> trList = new LinkedList<Track>();
		trList.add(tr);
		trList.add(tr2);
		b.addAll(trList);
		assertEquals(b.getArrayList().get(0), tr);
		assertEquals(b.getArrayList().get(1), tr2);
		assertEquals(b.getArrayList(), trList);
	}
	
	

}
