import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerInterface extends JFrame {
	
	MPlayer mPlayer = null;
	
	String fileName = "sounds/prueba.mp3";

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
			playButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						mPlayer.play();
					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			});
			pauseButton = new JButton(pauseIcon);
			pauseButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						mPlayer.pause();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			});
			stopButton = new JButton(stopIcon);
			stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						mPlayer.stop();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			});
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

	private void crearMPlayer(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			if(mPlayer != null){
				mPlayer.close();
			}
			mPlayer = new MPlayer(bis);
		} catch (Exception e) {
			System.err.printf("%s\n", e.getMessage());
		}
	}


	
	
	
	
	
	


}
