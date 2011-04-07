import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class PlayerInterface extends JFrame {
	
	BasicPlayer mPlayer;
	boolean pause;
	
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
		pause = false;
		principal = this;
		principal.setIconImage(monkeyIcon.getImage());
		principal.setTitle("Monaaco Player");
		principal.setJMenuBar(getBarraMenu());
		principal.setContentPane(getBusquedaPanel());
		//principal.setSize(getPreferredSize());
		principal.setSize(400, 107);
		principal.setResizable(false);
		principal.setVisible(true);
		principal.setEnabled(true);
		principal.setLocationRelativeTo(null);
		crearMPlayer(fileName);
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
					} catch (BasicPlayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//TODO
				};
			});
			pauseButton = new JButton(pauseIcon);
			pauseButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try{
						if(pause == false){
							pause = true;
								mPlayer.pause();
						}
						else {
							pause = false;
							mPlayer.resume();
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}
				};
			});
			stopButton = new JButton(stopIcon);
			stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						pause = false;
						mPlayer.seek(1000000);
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
			//cargarArchivo.addMouseListener(new MouseListener())
			//TODO
			playerMenu.add(cargarArchivo);
			barraMenu.add(playerMenu);
		}
		return barraMenu;
	}

	private void crearMPlayer(String fileName) {
		try {
			File f = new File(fileName);
			if(mPlayer != null){
				//mPlayer.close();
				//TODO
			}
			mPlayer = new BasicPlayer();
			mPlayer.open(f);
		} catch (Exception e) {
			System.err.printf("%s\n", e.getMessage());
		}
	}


	
	
	
	
	
	


}
