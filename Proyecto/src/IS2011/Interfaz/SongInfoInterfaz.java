package IS2011.Interfaz;
import java.awt.*;

import javax.swing.event.*;
import javax.swing.*;


//import javazoom.jlgui.basicplayer.BasicPlayerException;
import IS2011.bibliotecaXML.Track;

import com.sun.awt.AWTUtilities;


public class SongInfoInterfaz extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private SongInfoInterfaz principal = null;
	private InterfazAvanzada interfazAvanzada;
	
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
		super();
		principal = this;
		principal.setLayout(new BorderLayout());
		principal.setBackground(Color.white);
		principal.setEnabled(true);	
		principal.setSize(100,200);
		//principal.setAlwaysOnTop(true);
		//principal.setResizable(false);
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
		
		principal.add(getCaratulaPanel(),BorderLayout.WEST);
		principal.add(getInfoPanel(), BorderLayout.CENTER);

		
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
	

	
	
	public Track getTrack(){
		return track;
	}
	
	public SongInfoInterfaz getPrincipal(){
		return principal;
	}
	
}
