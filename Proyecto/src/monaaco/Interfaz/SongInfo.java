package monaaco.Interfaz;
import java.awt.*;

import javax.swing.event.*;
import javax.swing.*;

import monaaco.bibliotecaXML.Track;

//import javazoom.jlgui.basicplayer.BasicPlayerException;
import com.sun.awt.AWTUtilities;


public class SongInfo extends JFrame {
	
	private static final long serialVersionUID = 1L;

	
	private Track track = null;
	
	/**
	 *  @param track de la que hay que mostrar la info 
	 */
	public SongInfo(Track track){
		this.track = track;
	}

}
