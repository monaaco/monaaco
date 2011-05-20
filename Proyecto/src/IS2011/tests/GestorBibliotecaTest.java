package IS2011.tests;

import java.util.*;

import java.io.File;

import IS2011.GestorXML.GestorXML;
import IS2011.biblioteca.*;
import junit.framework.TestCase;

public class GestorBibliotecaTest extends TestCase{
	
	Track tr = null;
	Track tr2 = null;
	List<Track> trList = null;
	GestorXML<Biblioteca> gestor1 = null;
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
		
		gestor1 = new GestorXML<Biblioteca>();
		gestor2 = new GestorXML<Biblioteca>();

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
	
	public void testCargarXML(){
		//Comprobamos que el guardar y el escribir funcionan correctamente
		//Cargamos una biblioteca, la guardamos y miramos que el archivo obtenido sea igual que el original.
		GestorBiblioteca.getInstance().removeAll();
		GestorBiblioteca.getInstance().addAll(trList);
		GestorBiblioteca.getInstance().guardarXML("xml/test.xml");

		assertEquals(gestor1.cargarXML("xml/test.xml"), gestor1.getBiblioteca());
	}
	
	public void testGuardarXML(){
		GestorBiblioteca.getInstance().removeAll();
		GestorBiblioteca.getInstance().addAll(trList);
		GestorBiblioteca.getInstance().guardarXML();
		
		assertEquals(gestor1.getBiblioteca(), gestor2.getBiblioteca());
	}
	
	public void testConstructoras(){	//Comprobamos que si usamos la misma biblioteca las dos constructoras son iguales
		BibliotecaAdapter gestor1 = new BibliotecaAdapter();
		gestor1.cargar();
		B gestor2 = new BibliotecaAdapter(gestor1.getClaseBiblioteca());
		assertEquals(gestor1, gestor2);
	}
}
