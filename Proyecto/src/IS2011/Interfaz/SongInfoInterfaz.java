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
	private JTextArea  etiqueta = null;
	private JLabel etiquetaCaratula = null;
	private ImageIcon caratula = null;

	private Track track = null;

	private Color c= new Color(240,240,240);
	/**
	 *  @param track de la que hay que mostrar la info 
	 *  @throws InterruptedException 
	 */
	public SongInfoInterfaz(){
		super("Reproduciendo");
		principal = this;

		pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		ventana = this.getSize();
		principal = this;
		principal.setUndecorated(true);

		principal.getContentPane().setLayout(new BorderLayout());
		principal.getContentPane().setBackground(Color.black);
		this.colocarVentana();
		principal.setEnabled(true);	
		//principal.setAlwaysOnTop(true);
		principal.setResizable(false);
		principal.setVisible(true);

		//cerrar a los 5 min
		
	}
	
	private JPanel getInfoPanel() {
		if(infoPanel == null){
			infoPanel = new JPanel(new GridLayout());
			infoPanel.setBackground(Color.black);
			infoPanel.setSize(100, 100);
			infoPanel.setForeground(Color.white);
			etiqueta = new JTextArea ();
			etiqueta.setFont(new java.awt.Font("Helvetica", 1, 12));
			etiqueta.setBackground(Color.black);			
			etiqueta.setForeground(c);			
			
			infoPanel.add(etiqueta);
		}
		etiqueta.setText(" Artist: " + track.getArtist()
				+ "\n Title: " + track.getName() 
				+ "\n Album: " + track.getAlbum());
		return infoPanel;
	}
	
	public void actualiza(Track pista){
		this.track = pista;
		
		principal.getContentPane().add(getCaratulaPanel(),BorderLayout.WEST);
		principal.getContentPane().add(getInfoPanel(), BorderLayout.CENTER);

		
	}
	private JPanel getCaratulaPanel() {
		if(caratulaPanel == null){
			caratulaPanel = new JPanel();
			caratulaPanel.setBackground(Color.black);
			//caratulaPanel.setForeground(Color.c);	
			etiquetaCaratula = new JLabel();
			caratulaPanel.add(etiquetaCaratula);
			/*if(track.getArtwork() != null)
				caratulaPanel.getGraphics().drawImage((Image)track.getArtwork(), 0, 0, null);*/
		}
		if(track.getNumCaratulas() > 0 && track.getArtwork() != null){
			caratula = new ImageIcon(track.getArtwork().getScaledInstance(100,100,Image.SCALE_SMOOTH));
		}else 	caratula = new ImageIcon(new ImageIcon("images/monkeyIcon.jpg").getImage()
											.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		etiquetaCaratula.setIcon(caratula);

		return caratulaPanel;
	}
	

	private void colocarVentana() {
        // Se obtienen las dimensiones en pixels de la pantalla.
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        // Se obtienen las dimensiones en pixels de la ventana.
        //Dimension ventana = principal.getSize();
        // Una cuenta para situar la ventana en el centro de la pantalla.
        
         		principal.setSize(400,120);	
				principal.setLocation((pantalla.width - ventana.width) / 2,
                (((pantalla.height - ventana.height) / 2))+100);
		
		/*principal.setSize(pantalla.width,100);	
		principal.setLocation(0,0);*/
	}
	
	public Track getTrack(){
		return track;
	}
	
	public JFrame getPrincipal(){
		return principal;
	}
	
}
