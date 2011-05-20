package IS2011.tests;

import java.util.*;

import java.io.File;

import IS2011.biblioteca.*;
import junit.framework.TestCase;

public class GestorBibliotecaTest extends TestCase{
	

	//TODO setup
	
	public void testAddBiblioteca() {
		GestorBiblioteca.getInstance();
		Track tr = new Track("sounds/prueba.mp3");
		Track tr2 = new Track("sounds/prueba.mp3");
		gb.add(tr);
		gb.add(tr2);
		assertEquals(gb.getBiblioteca().get(0), tr);
		assertEquals(gb.getBiblioteca().get(1), tr2);
		assertEquals(b.getArrayList().get(0), tr);
		assertEquals(b.getArrayList().get(1), tr2);
	}
	
	public void testAddAllBiblioteca() {
		GestorBiblioteca.getInstance();
		Track tr = new Track("sounds/prueba.mp3");
		Track tr2 = new Track("sounds/prueba.mp3");
		List<Track> trList = new LinkedList<Track>();
		trList.add(tr);
		trList.add(tr2);
		GestorBiblioteca.addAll(trList);
		assertEquals(gb.getBiblioteca().get(0), tr);
		assertEquals(gb.getBiblioteca().get(1), tr2);
		assertEquals(gb.getBiblioteca(), trList);
		assertEquals(GestorBiblioteca.getInstance().getArrayList().get(0), tr);
		assertEquals(GestorBiblioteca.getInstance().getArrayList().get(1), tr2);
		assertEquals(GestorBiblioteca.getInstance().getArrayList(), trList);
	}
	
	public void testBiblioteca(){	//Comprobamos que el guardar y el escribir funcionan correctamente
									//Cargamos una biblioteca, la guardamos y miramos que el archivo obtenido sea igual que el original.
		BibliotecaAdapter gestor = new BibliotecaAdapter();
		gestor.cargar();
		gestor.guardar();
		BibliotecaAdapter gestor1 = new BibliotecaAdapter();
		gestor1.cargar();
		assertEquals(gestor.getBiblioteca(), gestor1.getBiblioteca());
	}
	
	public void testGuardar(){	//Comprobamos que el guardar() y el guardar(File file)
								//hacen lo mismo si trabajan sobre el mismo file
		BibliotecaAdapter gestor1 = new BibliotecaAdapter();
		gestor1.cargar();		//Cargamos para guardar algo en ambos guardar
		gestor1.guardar();
		File file = new File("xml/biblioteca.xml");
		BibliotecaAdapter gestor2 = new BibliotecaAdapter();
		gestor2.cargar();		//Cargamos para guardar algo en ambos guardar
		gestor2.guardar(file);
		assertEquals(gestor1.getBiblioteca(), gestor2.getBiblioteca());
	}
	
	public void testConstructoras(){	//Comprobamos que si usamos la misma biblioteca las dos constructoras son iguales
		BibliotecaAdapter gestor1 = new BibliotecaAdapter();
		gestor1.cargar();
		BibliotecaAdapter gestor2 = new BibliotecaAdapter(gestor1.getClaseBiblioteca());
		assertEquals(gestor1, gestor2);
	}
}
