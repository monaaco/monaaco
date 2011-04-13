package monaaco.Interfaz;
import java.awt.*;

import javax.swing.event.*;
import javax.swing.*;

import monaaco.bibliotecaXML.Track;

//import javazoom.jlgui.basicplayer.BasicPlayerException;
import com.sun.awt.AWTUtilities;


public class SongInfoInterfaz extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JFrame principal = null;
	private InterfazAvanzada interfazAvanzada;
	private Dimension pantalla = null;
	private Dimension ventana = null;

	
	private Track track = null;

	
	/**
	 *  @param track de la que hay que mostrar la info 
	 */
	public SongInfoInterfaz(Track track){
		super("Reproduciendo");
		this.track = track;
		this.interfazAvanzada = interfazAvanzada;
		principal = this;
		
		pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		ventana = this.getSize();
		principal = this;
		principal.setSize(200, pantalla.height-200);	
		this.colocarVentana();
		
		principal.getContentPane().setLayout(new BorderLayout());
		principal.setUndecorated(true);
		principal.getContentPane().setBackground(Color.black);
		/*
		this.scroll = new JScrollPane();
		principal.getContentPane().add(scroll,BorderLayout.EAST);
		principal.setEnabled(true);	
		listado = getListado(temas);
		principal.getContentPane().add(listado, BorderLayout.CENTER);
		principal.setAlwaysOnTop(true);
		this.minButton = getMinButton();
		principal.getContentPane().add(minButton, BorderLayout.SOUTH);
		*/
		principal.setVisible(true);
		principal.setResizable(false);
		
		//cerrar a los 5 min

	}
	
	private void colocarVentana() {
        // Se obtienen las dimensiones en pixels de la pantalla.
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        // Se obtienen las dimensiones en pixels de la ventana.
        Dimension ventana = principal.getSize();
        // Una cuenta para situar la ventana en el centro de la pantalla.
        principal.setLocation(pantalla.width / 2, 0);
	}
	
}
