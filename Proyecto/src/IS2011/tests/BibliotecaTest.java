package IS2011.tests;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import IS2011.biblioteca.GestorBiblioteca;
import IS2011.biblioteca.Track;

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
		GestorBiblioteca.getInstance().removeAll();
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
		GestorBiblioteca.getInstance();
		Track tr = new Track("sounds/prueba.mp3");
		Track tr2 = new Track("sounds/prueba.mp3");
		GestorBiblioteca.getInstance().add(tr);
		GestorBiblioteca.getInstance().add(tr2);
		assertEquals(GestorBiblioteca.getInstance().getArrayList().get(0), tr);
		assertEquals(GestorBiblioteca.getInstance().getArrayList().get(1), tr2);
	}
	
	/**
	 * Testeo de la función addAll:
	 *  - comprobar que se agregan bien las listas de tracks.
	 */	
	public void testAddAllBiblioteca() {
		GestorBiblioteca.getInstance();
		Track tr = new Track("/sounds/prueba.mp3");
		Track tr2 = new Track("/sounds/prueba.mp3");
		List<Track> trList = new LinkedList<Track>();
		trList.add(tr);
		trList.add(tr2);
		int tam = trList.size();
		GestorBiblioteca.getInstance().removeAll();
		GestorBiblioteca.getInstance().addAll(trList);
		assertEquals(GestorBiblioteca.getInstance().getArrayList().get(tam-2), tr);
		assertEquals(GestorBiblioteca.getInstance().getArrayList().get(tam-1), tr2);
	}
	
	

}
