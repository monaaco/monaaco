package IS2011.tests;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import IS2011.GestorXML.GestorXML;
import IS2011.biblioteca.Biblioteca;
import IS2011.biblioteca.GestorBiblioteca;
import IS2011.biblioteca.Track;

public class GestorBibliotecaTest extends TestCase{
	
	/**
	 * @uml.property  name="tr"
	 * @uml.associationEnd  
	 */
	Track tr = null;
	/**
	 * @uml.property  name="tr2"
	 * @uml.associationEnd  
	 */
	Track tr2 = null;
	/**
	 * @uml.property  name="trList"
	 */
	List<Track> trList = null;
	/**
	 * @uml.property  name="gestor1"
	 * @uml.associationEnd  
	 */
	GestorXML<Biblioteca> gestor1 = null;
	/**
	 * @uml.property  name="gestor2"
	 * @uml.associationEnd  
	 */
	GestorXML<Biblioteca> gestor2 = null;

	/**
	 * Test de la constructora
	 */
	public GestorBibliotecaTest(){
		super();
		try {
			this.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inicialización de variables para los tests
	 */
	public void setUp(){
		GestorBiblioteca.getInstance();
		tr = new Track("sounds/prueba.mp3");
		tr2 = new Track("sounds/prueba.mp3");
		trList = new LinkedList<Track>();
		trList.add(tr);
		trList.add(tr2);
		Biblioteca b = new Biblioteca();
		b.addAll(trList);
		gestor1 = new GestorXML<Biblioteca>(Biblioteca.class);
		gestor2 = new GestorXML<Biblioteca>(Biblioteca.class);

	}
	
	public void testAddBiblioteca() {
		GestorBiblioteca.getInstance().add(tr);
		GestorBiblioteca.getInstance().add(tr2);
		assertEquals(GestorBiblioteca.getInstance().getArrayList().get(0), tr);
		assertEquals(GestorBiblioteca.getInstance().getArrayList().get(1), tr2);
	}
	

	public void testAddAllBiblioteca() {
		GestorBiblioteca.getInstance().addAll(trList);
		assertEquals(GestorBiblioteca.getInstance().getArrayList().get(0), tr);
		assertEquals(GestorBiblioteca.getInstance().getArrayList().get(1), tr2);
		assertEquals(GestorBiblioteca.getInstance().getArrayList(), trList);
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void testCargarXML() throws Exception{
		//Comprobamos que el guardar y el escribir funcionan correctamente
		//Cargamos una biblioteca, la guardamos y miramos que el archivo obtenido sea igual que el original.
		GestorBiblioteca.getInstance().removeAll();
		GestorBiblioteca.getInstance().addAll(trList);
		GestorBiblioteca.getInstance().guardarXML("xml/test.xml");
		GestorBiblioteca.getInstance().cargarXML("xml/test.xml");
		assertEquals(GestorBiblioteca.getInstance().getBiblioteca(), gestor1.cargar("xml/test.xml"));
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void testGuardarXML() throws Exception{
		GestorBiblioteca.getInstance().removeAll();
		GestorBiblioteca.getInstance().addAll(trList);
		GestorBiblioteca.getInstance().guardarXML("xml/test.xml");
		GestorBiblioteca.getInstance().cargarXML("xml/test.xml");
		assertEquals(GestorBiblioteca.getInstance().getBiblioteca(), gestor1.cargar("xml/test.xml"));
	}
	

}
