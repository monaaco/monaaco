package IS2011.tests;

import java.io.File;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import junit.framework.TestCase;

public class BasicPlayerTest extends TestCase {
	
	@SuppressWarnings("static-access")
	public void testOpenFile() throws BasicPlayerException{
		BasicPlayer player = new BasicPlayer();
		File f = new File("sounds/prueba.mp3");
		player.open(f);
		assertEquals(player.getStatus(), player.OPENED);
	}
	
	@SuppressWarnings("static-access")
	public void testStop() throws BasicPlayerException{
		BasicPlayer player = new BasicPlayer();
		File f = new File("sounds/prueba.mp3");
		player.open(f);
		player.play();
		player.stop();
		assertEquals(player.getStatus(), player.STOPPED);
	}
	
	@SuppressWarnings("static-access")
	public void testPlay() throws BasicPlayerException{
		BasicPlayer player = new BasicPlayer();
		File f = new File("sounds/prueba.mp3");
		player.open(f);
		player.play();
		assertEquals(player.getStatus(), player.PLAYING);
	}
	
	@SuppressWarnings("static-access")
	public void testPause() throws BasicPlayerException{
		BasicPlayer player = new BasicPlayer();
		File f = new File("sounds/prueba.mp3");
		player.open(f);
		player.play();
		player.pause();
		assertEquals(player.getStatus(), player.PAUSED);
	}
}
