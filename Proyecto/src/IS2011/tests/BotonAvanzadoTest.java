package IS2011.tests;

import java.io.File;

import javax.swing.ImageIcon;

import IS2011.Interfaz.BotonAvanzado;

import junit.framework.TestCase;

public class BotonAvanzadoTest extends TestCase {
	
	public void testConstructor(){
		File f = new File("images/skin1/playIcon1.jpg");
		File fS = new File("images/skin1/playIcon3.jpg");
		ImageIcon imagen = new ImageIcon(f.toString());
		ImageIcon imagenS = new ImageIcon(f.toString());
		BotonAvanzado boton = new BotonAvanzado(imagen, imagenS);
		assertEquals(imagen, boton.getICon());
		assertEquals(imagenS, boton.getIConS());
	}
}
