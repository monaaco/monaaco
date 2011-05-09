package IS2011.tests;

import java.util.*;

import java.io.File;

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
	
	public void testBiblioteca(){	//Comprobamos que el guardar y el escribir funcionan correctamente
									//Cargamos una biblioteca, la guardamos y miramos que el archivo obtenido sea igual que el original.
		GestorXML gestor = new GestorXML();
		gestor.cargar();
		gestor.guardar();
		GestorXML gestor1 = new GestorXML();
		gestor1.cargar();
		assertEquals(gestor.getBiblioteca(), gestor1.getBiblioteca());
	}
	
	public void testGuardar(){	//Comprobamos que el guardar() y el guardar(File file)
								//hacen lo mismo si trabajan sobre el mismo file
		GestorXML gestor1 = new GestorXML();
		gestor1.cargar();		//Cargamos para guardar algo en ambos guardar
		gestor1.guardar();
		File file = new File("xml/biblioteca.xml");
		GestorXML gestor2 = new GestorXML();
		gestor2.cargar();		//Cargamos para guardar algo en ambos guardar
		gestor2.guardar(file);
		assertEquals(gestor1.getBiblioteca(), gestor2.getBiblioteca());
	}
	
	public void testConstructoras(){	//Comprobamos que si usamos la misma biblioteca las dos constructoras son iguales
		GestorXML gestor1 = new GestorXML();
		gestor1.cargar();
		GestorXML gestor2 = new GestorXML(gestor1.getClaseBiblioteca());
		assertEquals(gestor1, gestor2);
	}
}
