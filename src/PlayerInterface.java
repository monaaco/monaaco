import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;

import javax.swing.*;

public class PlayerInterface extends JFrame {
	private JFrame principal = null;
	
	//Barra de menu principal
	private JMenuBar barraMenu = null;
		//menu player
		private JMenu playerMenu = null;
			private JMenuItem cargarArchivo = null;
		//menu XML
		private JMenuItem bibliotecaMenu = null;
			private JMenu leerMenu = null;
			private JMenuItem leerXML = null;
			private JMenu guardarMenu = null;
			private JMenuItem guardarXML = null;

	//panel central
	private JPanel panelPrincipal = null;
		private JButton playButton = null;
		private JButton pauseButton = null;
		private JButton stopButton = null;
		

	//Imágenes e Iconos
	ImageIcon monkeyIcon = new ImageIcon("images/monkeyIcon.jpg");	
	ImageIcon carpetaIcon = new ImageIcon("images/carpetaIcon.jpg");		
	ImageIcon playIcon = new ImageIcon("images/playIcon.jpg");
	ImageIcon stopIcon = new ImageIcon("images/stopIcon.jpg");
	ImageIcon pauseIcon = new ImageIcon("images/pauseIcon.jpg");

	
	public PlayerInterface() {
		// TODO autogenerado
		principal = this;
		principal.setIconImage(monkeyIcon.getImage());
		principal.setJMenuBar(getBarraMenu());
		principal.setContentPane(getBusquedaPanel());
		//principal.setSize(getPreferredSize());
		principal.setSize(400, 107);
		principal.setResizable(false);
		principal.setVisible(true);
		principal.setEnabled(true);
		principal.setLocationRelativeTo(null);
	}

	private JPanel getBusquedaPanel() {
		// TODO Action events .. 
		if(panelPrincipal == null){
			panelPrincipal = new JPanel(new FlowLayout()); 
			playButton = new JButton(playIcon);
			pauseButton = new JButton(pauseIcon);
			stopButton = new JButton(stopIcon);
			panelPrincipal.add(playButton);
			panelPrincipal.add(pauseButton);
			panelPrincipal.add(stopButton);
		}
		return panelPrincipal;
	}

	private JMenuBar getBarraMenu() {
		// TODO Action events ...
		if(barraMenu == null){
			barraMenu = new JMenuBar();
			playerMenu = new JMenu("Menu");
			cargarArchivo = new JMenuItem("Cargar archivo", carpetaIcon);
			playerMenu.add(cargarArchivo);
			barraMenu.add(playerMenu);
		}
		return barraMenu;
	}



	
	
	
	
	
	


}
