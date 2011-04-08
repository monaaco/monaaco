import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class InterfazAvanzada extends JFrame {

	private static final long serialVersionUID = 1L;
	// Imagenes:
	ImageIcon monkeyIcon = new ImageIcon("images/monkeyIcon.jpg");
	ImageIcon carpetaIcon = new ImageIcon("images/carpetaIcon.jpg");
	ImageIcon playIcon = new ImageIcon("images/playIcon.jpg");
	ImageIcon stopIcon = new ImageIcon("images/stopIcon.jpg");
	ImageIcon pauseIcon = new ImageIcon("images/pauseIcon.jpg");

	JButton stopButton = null;
	JButton pauseButton = null;
	JButton playButton = null;
	JLabel segundero = null;
	JSlider barraProgreso = null;
	// Barra de menu principal
	private JMenuBar barraMenu = null;
	// menu player
	private JMenu playerMenu = null;
	private JMenuItem cargarArchivo = null;
	
/*	// menu XML  Por si lo usaramos que esta en la otra playerInterface!
	private JMenuItem bibliotecaMenu = null;
	private JMenu leerMenu = null;
	private JMenuItem leerXML = null;
	private JMenu guardarMenu = null;
	private JMenuItem guardarXML = null;
*/
	boolean pause;
	BasicPlayer mPlayer;
	ReproductorListener reproductorListener;
	String fileName = "sounds/prueba.mp3";

	public InterfazAvanzada() {
		super("Monaaaaco"); // El título
		this.getContentPane().setLayout(new GridBagLayout()); // Le ponemos el
																// GridBagLayout
		this.setSize(400, 200);						//En la otra principal
		GridBagConstraints constraints = new GridBagConstraints();
		pause = false;
		this.setEnabled(true);						//En la otra principal
		this.setResizable(false);					//En la otra principal
		this.setIconImage(monkeyIcon.getImage());	//En la otra principal AÑADIDO POR MI
		this.setTitle("Monaaco Player");			//En la otra principal AÑADIDO POR MI
		this.setJMenuBar(getBarraMenu());

		stopButton = getStopButton();
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(stopButton, constraints);

		segundero = new JLabel("0:00");
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(segundero, constraints);

		pauseButton = getPauseButton(); // JButton pauseButton = new
										// JButton(pauseIcon);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(pauseButton, constraints);

		playButton = getPlayButton(); // new JButton(playIcon);
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(playButton, constraints);

		barraProgreso = new JSlider();
		constraints.gridx = 2;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		// constraints.weighty = 1.0; // La fila 0 debe estirarse, le ponemos un
		// 1.0
		this.getContentPane().add(barraProgreso, constraints);
		// constraints.weighty = 0.0; // Restauramos al valor por defecto, para
		// no afectar a los siguientes componentes.

		crearMPlayer(fileName);
	}
	
	private JMenuBar getBarraMenu() {
		// TODO Action events ...
		if (barraMenu == null) {
			barraMenu = new JMenuBar();
			playerMenu = new JMenu("Menu");
			getCargarArchivoItem();
			// cargarArchivo = new JMenuItem("Cargar archivo", carpetaIcon);
			// TODO
			playerMenu.add(cargarArchivo);
			barraMenu.add(playerMenu);
		}
		return barraMenu;
	}
	
	private JMenuItem getCargarArchivoItem() {
		if (cargarArchivo == null) {
			cargarArchivo = new JMenuItem();
			cargarArchivo.setText("Cargar Archivo");
			cargarArchivo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						/*
						 * setMultiSelectionEnabled(booleano) este metodo de
						 * JFileChooser nos permite seleccionar varios ficheros
						 * al mismo tiempo Tambien esta setFileSelectionMode()
						 * que nos permite elegir si seleccionar ficheros
						 * directorios o ambos Info:
						 * http://soporteti.net/programacion
						 * -2/dialogo-de-seleccion
						 * -de-ficheros-jfilechooser-java/
						 */
						JFileChooser fc = new JFileChooser();
						FileFilter ff = new FileFilter() {
							@Override
							public String getDescription() {
								return ("MP3 Soportados .mp3");
							}

							@Override
							public boolean accept(File f) {
								if (f.getName().toLowerCase().endsWith(".mp3")) {
									return true;
								}
								return false;
							}
						};
						fc.setFileFilter(ff);
						fc.setMultiSelectionEnabled(true);
						if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)/*PETA!*/
						{
							File f = fc.getSelectedFile();
							crearMPlayer(f.getAbsolutePath());
							System.out.println(f.getAbsolutePath());
							mPlayer.play();
						}
							
/*						FileDialog fd = new FileDialog(new JFrame(), "Abrir",
								FileDialog.LOAD);
						fd.setVisible(true);*/
					} catch (/* BasicPlayer */Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return cargarArchivo;
	}


	public JButton getStopButton() {
		JButton stopButton = new JButton(stopIcon);
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
		return stopButton;
	}

	public JButton getPlayButton() {
		JButton playButton = new JButton(playIcon);
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
				// TODO
			};
		});
		return playButton;
	}

	public JButton getPauseButton() {
		JButton pauseButton = new JButton(pauseIcon);
		pauseButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
				try {
					if (pause == false) {
						pause = true;
						mPlayer.pause();
					} else {
						pause = false;
						mPlayer.resume();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		});
		return pauseButton;
	}

	private void crearMPlayer(String fileName) {
		try {
			File f = new File(fileName);
			if (mPlayer != null) {
				mPlayer.stop();
				// TODO
			}
			// reproductorListener = new ReproductorListener(this);
			mPlayer = new BasicPlayer();
			reproductorListener = new ReproductorListener(this);
			mPlayer.addBasicPlayerListener(reproductorListener);
			mPlayer.open(f);

		} catch (Exception e) {
			System.err.printf("%s\n", e.getMessage());
		}
	}

	public void actualizaBarraProgreso(double estado) {
		barraProgreso.setValue((int) estado);
	}

	public void ajustaBarraProgreso(double fin) {
		barraProgreso.setMaximum((int) fin);
	}

	public void cambiaSegundos(String texto) {
		segundero.setText(texto);
	}

}
