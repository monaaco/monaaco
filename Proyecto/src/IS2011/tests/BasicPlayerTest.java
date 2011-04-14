package IS2011.tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import IS2011.FiltrosArchivos.FiltroMP3;
import IS2011.FiltrosArchivos.FiltroOGG;
import IS2011.FiltrosArchivos.FiltroSoportados;
import IS2011.Interfaz.BotonAvanzado;
import IS2011.Interfaz.InterfazAvanzada;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import junit.framework.TestCase;

public class BasicPlayerTest extends TestCase {
	
	public void testOpenFile() throws BasicPlayerException{
		BasicPlayer player = new BasicPlayer();
		File f = new File("sounds/prueba.mp3");
		player.open(f);
		assertEquals(player.getStatus(), player.OPENED);
	}
	
	public void testStop() throws BasicPlayerException{
		BasicPlayer player = new BasicPlayer();
		File f = new File("sounds/prueba.mp3");
		player.open(f);
		player.play();
		player.stop();
		assertEquals(player.getStatus(), player.STOPPED);
	}
	
	public void testPlay() throws BasicPlayerException{
		BasicPlayer player = new BasicPlayer();
		File f = new File("sounds/prueba.mp3");
		player.open(f);
		player.play();
		assertEquals(player.getStatus(), player.PLAYING);
	}
	
	public void testPause() throws BasicPlayerException{
		BasicPlayer player = new BasicPlayer();
		File f = new File("sounds/prueba.mp3");
		player.open(f);
		player.play();
		player.pause();
		assertEquals(player.getStatus(), player.PAUSED);
	}
}
