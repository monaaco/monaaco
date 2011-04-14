package IS2011.Interfaz;
import java.awt.*;

import javax.swing.event.*;
import javax.swing.*;


//import javazoom.jlgui.basicplayer.BasicPlayerException;
import IS2011.bibliotecaXML.Track;

import com.sun.awt.AWTUtilities;


public class SongInfoInterfaz extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JFrame principal = null;
	private InterfazAvanzada interfazAvanzada;
	private Dimension pantalla = null;
	private Dimension ventana = null;

	private JPanel caratulaPanel = null;
	private JPanel infoPanel = null;

	private Track track = null;

	
	/**
	 *  @param track de la que hay que mostrar la info 
	 *  @throws InterruptedException 
	 */
	public SongInfoInterfaz(Track track) throws InterruptedException{
		super("Reproduciendo");
		this.track = track;
		this.interfazAvanzada = interfazAvanzada;
		principal = this;
		
		pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		ventana = this.getSize();
		principal = this;
		principal.setSize(pantalla.width,50);	
		this.colocarVentana();
		
		principal.getContentPane().setLayout(new BorderLayout());
		principal.setUndecorated(true);
		principal.getContentPane().setBackground(Color.black);
		

		principal.getContentPane().add(getCaratulaPanel(),BorderLayout.WEST);
		principal.getContentPane().add(getInfoPanel(), BorderLayout.CENTER);

		principal.setEnabled(true);	
		principal.setAlwaysOnTop(true);
		principal.setResizable(false);
		
		principal.setVisible(true);
		//cerrar a los 5 min
		
	}
	
	private JPanel getInfoPanel() {
		if(infoPanel == null){
			infoPanel = new JPanel(new FlowLayout());
			infoPanel.add(new JLabel(" Artist: " +track.getArtist()
										+ " Title: " + track.getName()));
		}
		return infoPanel;
	}

	private JPanel getCaratulaPanel() {
		if(caratulaPanel == null){
			caratulaPanel = new JPanel();
			principal.getGraphics().drawImage(track.getArtwork(), 0, 0, null);
		}
		return caratulaPanel;
	}

	private void colocarVentana() {
        // Se obtienen las dimensiones en pixels de la pantalla.
        //Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        // Se obtienen las dimensiones en pixels de la ventana.
        //Dimension ventana = principal.getSize();
        // Una cuenta para situar la ventana en el centro de la pantalla.
        principal.setLocation(0, 0);
	}
	
	public Track getTrack(){
		return track;
	}
	
	public JFrame getPrincipal(){
		return principal;
	}
	
}
