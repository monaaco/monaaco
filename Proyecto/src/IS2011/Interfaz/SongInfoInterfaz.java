package IS2011.Interfaz;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import IS2011.Configuracion.GestorPreferencias;
import IS2011.biblioteca.Track;


public class SongInfoInterfaz extends JPanelTransparente {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="principal"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private SongInfoInterfaz principal = null;
	/**
	 * @uml.property  name="interfazAvanzada"
	 * @uml.associationEnd  readOnly="true" inverse="infoSong:IS2011.Interfaz.InterfazAvanzada"
	 */
	private InterfazAvanzada interfazAvanzada;
	
	/**
	 * @uml.property  name="caratulaPanel"
	 * @uml.associationEnd  
	 */
	private JPanel caratulaPanel = null;
	/**
	 * @uml.property  name="infoPanel"
	 * @uml.associationEnd  
	 */
	private JPanelTransparente infoPanel = null;
	/**
	 * @uml.property  name="etiqueta"
	 * @uml.associationEnd  
	 */
	private JLabel  etiqueta = null;
	/**
	 * @uml.property  name="etiquetaCaratula"
	 * @uml.associationEnd  
	 */
	private JLabel etiquetaCaratula = null;
	/**
	 * @uml.property  name="caratula"
	 * @uml.associationEnd  
	 */
	private ImageIcon caratula = null;

	/**
	 * @uml.property  name="track"
	 * @uml.associationEnd  
	 */
	private Track track = null;

	/**
	 * @uml.property  name="c"
	 */
	private Color c= new Color(240,240,240);
	/**
	 * 	Constructora
	 *  @param track de la que hay que mostrar la info 
	 *  @throws InterruptedException 
	 */
	public SongInfoInterfaz(){
		super();
		principal = this;
		//principal.setLayout(new GridBagLayout());
		//principal.setBackground(Color.white);
		principal.setEnabled(true);	
		principal.setSize(500,150);
		//principal.setAlwaysOnTop(true);
		//principal.setResizable(false);
		principal.setVisible(true);
		//principal.setArcw(0); 
		//principal.setArch(0); 
		principal.setColorPrimario(Color.white);
		//principal.setColorSecundario(Color.black);
		cargarPreferencias();
		principal.setTran(0.4f);
		principal.setVisible(true);
		principal.setLayout(new GridBagLayout());

		//cerrar a los 5 min
		
	}
	
	/**
	 * Inicializa el JPanelTransparenet infoPanel en caso de que no exista 
	 * @return  JPanelTransparente
	 * @uml.property  name="infoPanel"
	 */
	private JPanelTransparente getInfoPanel() {
		if(infoPanel == null){
			infoPanel = new JPanelTransparente();
			infoPanel.setTran(0);
			infoPanel.setLayout(new GridLayout());
			//infoPanel.setBackground(Color.black);
			infoPanel.setSize(300,120);
			infoPanel.setArcw(0); 
			infoPanel.setArch(0); 
			//infoPanel.setColorPrimario(Color.white);
			//infoPanel.setColorSecundario(Color.white);
			//infoPanel.setForeground(Color.white);
			etiqueta = new JLabel();
			etiqueta.setFont(new java.awt.Font("Helvetica", 1, 12));
			//etiqueta.setBackground(null);			
			etiqueta.setForeground(c);	
			
			
			infoPanel.add(etiqueta);
		}
		etiqueta.setText("<html> Artist: " + track.getArtist()
				+"<html><br/> Title: " + track.getName() 
				+ "<html><br/> Album: " + track.getAlbum());
		return infoPanel;
	}
	
	/**
	 * Modificamos el Track mostrado actualmente por el que pasamos por parametro
	 *  @param pista indica la pista a mostrar
	 */
	public void actualiza(Track pista){
		this.track = pista;
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.0;
		constraints.insets= new Insets(10,10,10,10);
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST; 
		principal.add(getCaratulaPanel(),constraints);
	/*	JPanel caratula = getCaratulaPanel();
		caratula.setBounds(20,20, 110, 110);
		principal.add(getCaratulaPanel());*/
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.CENTER; 
		constraints.fill = GridBagConstraints.NONE;
		/*JPanelTransparente info = getInfoPanel();
		info.setBounds(160,20,110,400);
		principal.add(getInfoPanel());
		principal.setSize(600,150);*/
		principal.add(getInfoPanel(),constraints);	
	}
	
	/**
	 * Inicializa el JPanel caratulaPanel en caso de que no exista 
	 * @return  JPanel
	 * @uml.property  name="caratulaPanel"
	 */
	private JPanel getCaratulaPanel() {
		if(caratulaPanel == null){
			caratulaPanel = new JPanel();
			caratulaPanel.setSize(120,120);
			caratulaPanel.setBackground(Color.black);
			//caratulaPanel.setForeground(Color.c);	
			etiquetaCaratula = new JLabel();
			caratulaPanel.add(etiquetaCaratula);
			/*if(track.getArtwork() != null)
				caratulaPanel.getGraphics().drawImage((Image)track.getArtwork(), 0, 0, null);*/
		}
		if(track.getNumCaratulas() > 0 && track.getArtwork() != null){
			caratula = new ImageIcon(track.getArtwork().getScaledInstance(120,120,Image.SCALE_SMOOTH));
		}else 	caratula = new ImageIcon(new ImageIcon("images/skin3/monkeyIcon2.png").getImage()
											.getScaledInstance(120,120, Image.SCALE_SMOOTH));
		etiquetaCaratula.setIcon(caratula);
		this.repaint();
		return caratulaPanel;
	}
	

	
	/**
	 * Accesora para Track
	 * @return  Track
	 * @uml.property  name="track"
	 */
	public Track getTrack(){
		return track;
	}
	
	/**
	 * Accesora para principal
	 * @return  SongInfoInterfaz
	 * @uml.property  name="principal"
	 */
	public SongInfoInterfaz getPrincipal(){
		return principal;
	}
	
	/**
	 * carga las preferencias de la clase GestorPreferencias
	 */
	public void cargarPreferencias(){
		//this.setBgColorInterno(GestorPreferencias.getInstance().getBgColorInterno());
		this.setFgColorInterno(GestorPreferencias.getInstance().getBgColorInterno());	
		
	}

	/**
	 * 
	 * @param fgColorInterno
	 */
	private void setFgColorInterno(Color bgColorInterno) {
		this.setColorSecundario(bgColorInterno);
	}

	/**
	 * 
	 * @param bgColorInterno
	 */
	private void setBgColorInterno(Color bgColorInterno) {
		this.setColorSecundario(bgColorInterno);		
	}
	
}
