package monaaco.tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import monaaco.FiltrosArchivos.FiltroMP3;
import monaaco.FiltrosArchivos.FiltroOGG;
import monaaco.FiltrosArchivos.FiltroSoportados;
import monaaco.Interfaz.BotonAvanzado;
import monaaco.Interfaz.InterfazAvanzada;
import junit.framework.TestCase;

public class BasicPlayerTest extends TestCase {
	public void testStopButton() throws BasicPlayerException{
		BasicPlayer player = new BasicPlayer();
		File f = new File("sounds/prueba.mp3");
		player.open(f);
		player.stop();
		assertEquals(player.getStatus(), player.STOPPED);
	}
}
