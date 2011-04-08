import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.*;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class PlayerInterface extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	BasicPlayer mPlayer;
	ReproductorListener reproductorListener;
	boolean pause;
	
	//String fileName = "sounds/prueba.mp3";

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
		private JSlider barraProgreso = null;
		private JLabel segundero = null;
		

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
		principal.setSize(700, 110);
		principal.setResizable(false);
		principal.setVisible(true);
		principal.setEnabled(true);
		principal.setLocationRelativeTo(null);
		//crearMPlayer(fileName);
	}

	private JPanel getBusquedaPanel() {
		// TODO Action events .. 
		if(panelPrincipal == null){
			panelPrincipal = new JPanel(new FlowLayout()); 
			playButton = new JButton(playIcon);
			playButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						pause = false;
						mPlayer.stop();
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
						cambiaSegundos("0:00");
						mPlayer.stop();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			});
			barraProgreso = new JSlider();
			barraProgreso.setSize(300, 30);
			barraProgreso.addMouseListener(new java.awt.event.MouseAdapter(){
				@Override
				public void mouseReleased(MouseEvent e) {
					int value = barraProgreso.getValue();
					//System.out.println(value);
					int maxValue = barraProgreso.getMaximum();
					System.out.println(maxValue);//En bytes
					int maxSeg = mPlayer.getLineCurrentBufferSize();
					System.out.println(maxSeg);
				}
			});
			segundero = new JLabel("0:00");
			
			panelPrincipal.add(playButton);
			panelPrincipal.add(pauseButton);
			panelPrincipal.add(stopButton);
			panelPrincipal.add(barraProgreso);
			panelPrincipal.add(segundero);
		}
		return panelPrincipal;
	}

	private JMenuBar getBarraMenu() {
		// TODO Action events ...
		if(barraMenu == null){
			barraMenu = new JMenuBar();
			playerMenu = new JMenu("Menu");
			getCargarArchivoItem();
			//cargarArchivo = new JMenuItem("Cargar archivo", carpetaIcon);
			//TODO
			playerMenu.add(cargarArchivo);
			barraMenu.add(playerMenu);
		}
		return barraMenu;
	}
	
		private JMenuItem getCargarArchivoItem(){
		if(cargarArchivo == null){
			cargarArchivo = new JMenuItem();
			cargarArchivo.setText("Cargar Archivo");
			cargarArchivo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try {
						FileDialog fd = new FileDialog(new JFrame(), "Abrir", FileDialog.LOAD);
						fd.setVisible(true);
						File f = new File(fd.getDirectory(), fd.getFile());
						crearMPlayer(f.toString());
						mPlayer.play();
					} catch (BasicPlayerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return cargarArchivo;
	}

	private void crearMPlayer(String fileName) {
		try {
			File f = new File(fileName);
			if(mPlayer != null){
				mPlayer.stop();
				//TODO
			}
			reproductorListener = new ReproductorListener(this); 
			mPlayer = new BasicPlayer();
			mPlayer.addBasicPlayerListener(reproductorListener);
			mPlayer.open(f);
		
		} catch (Exception e) {
			System.err.printf("%s\n", e.getMessage());
		}
	}


	public void cambiaSegundos(String texto) {
		segundero.setText(texto);
	}
	
	public void ajustaBarraProgreso(double fin ){
		barraProgreso.setMaximum((int) fin);
	}
	
	public void actualizaBarraProgreso(double estado){
		barraProgreso.setValue((int)estado);
	}
	
	
	
	


}
