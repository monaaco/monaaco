import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import com.sun.awt.AWTUtilities;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class InterfazAvanzada extends JFrame {

	private static final long serialVersionUID = 1L;
	// Imagenes:
	private ImageIcon monkeyIcon = new ImageIcon("images/monkeyIcon.jpg");
	private ImageIcon carpetaIcon = new ImageIcon("images/carpetaIcon.jpg");
	
	private ImageIcon playIcon = new ImageIcon("images/skin1/playIcon1.jpg");
	private ImageIcon playedIcon = new ImageIcon("images/skin1/playIcon3.jpg");
	private ImageIcon stopIcon = new ImageIcon("images/skin1/stopIcon1.jpg");
	private ImageIcon stopedIcon = new ImageIcon("images/skin1/stopIcon3.jpg");
	private ImageIcon pauseIcon = new ImageIcon("images/skin1/pauseIcon1.jpg");
	private ImageIcon pausedIcon = new ImageIcon("images/skin1/pauseIcon3.jpg");
	
	private BotonAvanzado stopButton = null;
	private BotonAvanzado pauseButton = null;
	private BotonAvanzado playButton = null;
	private JLabel segundero = null;
	private JSlider barraProgreso = null;
	private SongInterfaz info = null;
	
	// Barra de menu principal
	private JMenuBar barraMenu = null;
	// menu player
	private JMenu playerMenu = null;
	private JMenuItem cargarArchivoItem = null;
	private JMenuItem salirItem = null;
	private JButton salirButton = null;
	
	
/*	// menu XML  Por si lo usaramos que esta en la otra playerInterface!
	private JMenuItem bibliotecaMenu = null;
	private JMenu leerMenu = null;
	private JMenuItem leerXML = null;
	private JMenu guardarMenu = null;
	private JMenuItem guardarXML = null;
*/
	private JFrame principal;
	private JFrame infoSongPanel;
	private boolean pause;
	private BasicPlayer mPlayer;
	private ReproductorListener reproductorListener;
	private String fileName = "sounds/prueba.mp3";
	private Shape figura;
	
	private Color bgcolor = Color.black;
	
	//private TransparentBackground fondo = null;

	@SuppressWarnings("restriction")
	public InterfazAvanzada() {
		
			
		super("Monaaaaco"); // El título
		//fondo = new TransparentBackground(this);
		this.getContentPane().setLayout(new GridBagLayout()); // Le ponemos el
		this.setUndecorated(true);														// GridBagLayout
		this.setSize(400, 200);
		this.centrarVentana();
		GridBagConstraints constraints = new GridBagConstraints();
		pause = false;
		this.setEnabled(true);						//En la otra principal
		this.setResizable(false);					//En la otra principal
		this.setIconImage(monkeyIcon.getImage());	//En la otra principal AÑADIDO POR MI
		this.setTitle("Monaaco Player");			//En la otra principal AÑADIDO POR MI
		this.setJMenuBar(getBarraMenu());
		this.getContentPane().setBackground(bgcolor);
		this.setVisible(true);
		String[] temas= {"1-Probando","1-Probando","1-Probando","1-Probando","1-Probando","1-Probando"};
		info = new SongInterfaz(temas);
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		AWTUtilities.setWindowOpacity(info, (float) 0.6);
		
		
		
		stopButton = getStopButton();
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(stopButton, constraints);

		segundero = new JLabel("0:00");
		segundero.setForeground(Color.blue);
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(segundero, constraints);

		pauseButton = getPauseButton(); // JButton pauseButton = new
										// JButton(pauseIcon);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(pauseButton, constraints);

		playButton = getPlayButton(); // new JButton(playIcon);
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(playButton, constraints);

		barraProgreso = getBarraProgreso();
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		// constraints.weighty = 1.0; // La fila 0 debe estirarse, le ponemos un
		// 1.0
		// constraints.fill = GridBagConstraints.BOTH;
		this.getContentPane().add(barraProgreso, constraints);
		// constraints.weighty = 0.0; // Restauramos al valor por defecto, para
		// no afectar a los siguientes componentes.
		
		
		/*info = getInfo();
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 1.0; 
		this.getContentPane().add(info, constraints);
		constraints.weighty = 0.0;*/
		
		crearMPlayer(fileName);//archivo por defecto
	}
	
	private JMenuBar getBarraMenu() {
		// TODO más elementos ¿e iconos?
		if (barraMenu == null) {
			barraMenu = new JMenuBar();
			playerMenu = new JMenu("Menu");
			//playerMenu.setIcon(monkeyIcon);
			playerMenu.add(getCargarArchivoItem());
			playerMenu.add(getSalirItem());
			barraMenu.add(playerMenu);
		}
		return barraMenu;
	}
	

	private JMenuItem getCargarArchivoItem() {
        if (cargarArchivoItem == null) {
        	cargarArchivoItem = new JMenuItem("Cargar Archivo",carpetaIcon);
        	cargarArchivoItem.addActionListener(new ActionListener() {
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
                        fc.setFileFilter(new FiltroMP3());
                        fc.setFileFilter(new FiltroOGG());
                        fc.setFileFilter(new FiltroSoportados());
                        fc.setMultiSelectionEnabled(true);//TODO esto como va?
                        if(fc.showOpenDialog(principal) == JFileChooser.APPROVE_OPTION)
                        	/*TODO PETA!?*/
                        {
                            File f = fc.getSelectedFile();
                            crearMPlayer(f.getAbsolutePath());
                            System.out.println(f.getAbsolutePath());
                            mPlayer.play();
                            /* TODO
                             * -crear un objeto Track con la ruta del archivo cargado
                             * -añadir ese objeto a un traclist or something
                             * 
                             * 
                             */
                        }
                    } catch (/* BasicPlayer */Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return cargarArchivoItem;
    }

	public JSlider getBarraProgreso(){
		if(barraProgreso == null){
			barraProgreso = new JSlider();
			barraProgreso.setBackground(bgcolor);
	        barraProgreso.addMouseListener(new java.awt.event.MouseAdapter(){
	            @Override
	            public void mouseReleased(MouseEvent e) {
	                int value = barraProgreso.getValue();
	                value = value - mPlayer.PLAYING;
	                System.out.printf("Eeeeeeeeeey", value);
	                try {
	                    mPlayer.seek(value);
	                } catch (BasicPlayerException e1) {
	                    // TODO Auto-generated catch block
	                    e1.printStackTrace();
	                }
	            }
	        });
		}
	    return barraProgreso;
	}
	public BotonAvanzado getStopButton() {
		if (stopButton == null){
			stopButton = new BotonAvanzado(stopIcon,stopedIcon);
			stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						pause = false;
						cambiaSegundos("0:00");
						mPlayer.stop();
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		}
		return stopButton;
	}

	public BotonAvanzado getPlayButton() {
		if (playButton == null){
			playButton = new BotonAvanzado(playIcon,playedIcon);
			playButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						pause = false;
						mPlayer.stop();
						mPlayer.play();
					} catch (BasicPlayerException e) {
						e.printStackTrace();
					}
				};
			});
		}
		return playButton;
	}

	public BotonAvanzado getPauseButton() {
		if (pauseButton == null){
			pauseButton = new BotonAvanzado(pauseIcon,pausedIcon);
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
		}
		return pauseButton;
	}
	
	public JButton getSalirButton() {
		if (salirButton == null){
			salirButton = new JButton("salir");
			salirButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						System.exit(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		}
		return salirButton;
	}

	public JMenuItem getSalirItem() {
		if (salirItem == null){
			salirItem = new JMenuItem("Salir");
			//salirItem.setBackground(bgcolor);
			salirItem.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						System.exit(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		}
		return salirItem;
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
	
	private void centrarVentana() {
        // Se obtienen las dimensiones en pixels de la pantalla.
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        // Se obtienen las dimensiones en pixels de la ventana.
        Dimension ventana = this.getSize();
        // Una cuenta para situar la ventana en el centro de la pantalla.
        this.setLocation((pantalla.width - ventana.width) / 2,
                        ((pantalla.height - ventana.height) / 2));
	}

}
