package monaaco.Interfaz;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
//import javax.swing.event.*;
//import javax.swing.filechooser.FileFilter;

import monaaco.FiltrosArchivos.*;
import monaaco.bibliotecaXML.*;

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
	
	private JFrame principal = null;
	private SongInterfaz infoPlaylist = null;
	private SongInfo infoSong = null;

	private BotonAvanzado stopButton = null;
	private BotonAvanzado pauseButton = null;
	private BotonAvanzado playButton = null;
	private JLabel segundero = null;
	private JSlider barraProgreso = null;
	private JButton salirButton = null;
	
	// Barra de menu principal
	private JMenuBar barraMenu = null;
	// menu player
	private JMenu playerMenu = null;
	private JMenuItem cargarArchivoItem = null;
	private JMenuItem salirItem = null;
	private JMenuItem nextItem = null;
	private JMenuItem previousItem = null;
	private JFrame infoFrame = null;
	private JPanel infoPanel = null;
	
	
	private JLabel etiqueta = null;
	
/*	// menu XML  Por si lo usaramos que esta en la otra playerInterface!
	private JMenuItem bibliotecaMenu = null;
	private JMenu leerMenu = null;
	private JMenuItem leerXML = null;
	private JMenu guardarMenu = null;
	private JMenuItem guardarXML = null;
*/
	private boolean pause;
	private double bytesArchivoActual; 
	private BasicPlayer mPlayer;
	private ReproductorListener reproductorListener;
	private String fileName = "sounds/prueba.mp3";
	private Shape figura;
	private Track _pista;
	private Color bgcolor = Color.black;

	//private TransparentBackground fondo = null;

	//Playlist:
	private Playlist listaReproduccion = null;
	private Image caratula = null;
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
     // Se obtienen las dimensiones en pixels de la ventana.
    private Dimension ventana = this.getSize();

	private int posTag=0;
	
	
	
	public InterfazAvanzada() {
		
		super("Monaaaaco"); // El t�tulo
		//fondo = new TransparentBackground(this);
		this.getContentPane().setLayout(new GridBagLayout()); // Le ponemos el
		this.setUndecorated(false);														// GridBagLayout
		this.setSize(400, 200);
		this.centrarVentana();
		
		GridBagConstraints constraints = new GridBagConstraints();
		pause = false;
		bytesArchivoActual =0;
		
		this.setEnabled(true);						//En la otra principal
		this.setResizable(false);					//En la otra principal
		this.setIconImage(monkeyIcon.getImage());	//En la otra principal A�ADIDO POR MI
		this.setTitle("Monaaco Player");			//En la otra principal A�ADIDO POR MI
		this.setJMenuBar(getBarraMenu());
		this.getContentPane().setBackground(bgcolor);
		this.setEnabled(true);
		this.setVisible(true);
	
		
		
		stopButton = getStopButton();
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.fill = GridBagConstraints.EAST;
		this.getContentPane().add(stopButton, constraints);

		playButton = getPlayButton(); // new JButton(playIcon);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.fill = GridBagConstraints.CENTER;
		this.getContentPane().add(playButton, constraints);

		pauseButton = getPauseButton(); // JButton pauseButton = new
		// JButton(pauseIcon);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.fill = GridBagConstraints.WEST;
		this.getContentPane().add(pauseButton, constraints);
		constraints.weightx = 0.0;

		segundero = new JLabel("0:00");
		segundero.setForeground(Color.blue);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.CENTER;
		this.getContentPane().add(segundero, constraints);

		
		barraProgreso = getBarraProgreso();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		this.getContentPane().add(barraProgreso, constraints);
		// constraints.weighty = 0.0; // Restauramos al valor por defecto, para
		// no afectar a los siguientes componentes.
		
		etiqueta=new JLabel("Monaaaco Player");
		etiqueta.setForeground(Color.ORANGE);
		GridBagConstraints position = new GridBagConstraints();
		position.gridx = 0;
		position.gridy = 3;
		position.gridheight = 1;
		position.gridwidth = 3;
		position.fill = GridBagConstraints.HORIZONTAL;
		this.getContentPane().add(etiqueta, position);
		
		
		/*info = getInfo();
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 1.0; 
		this.getContentPane().add(info, constraints);
		constraints.weighty = 0.0;*/
		

		listaReproduccion = new Playlist();
		listaReproduccion.setRepeat(true);
		listaReproduccion.add(fileName); //a�adir archivo
		setCurrentTrack(listaReproduccion.current());
		listaReproduccion.add("sounds/mic_check.mp3"); //a�adir archivo
		
        String[] temas = listaReproduccion.getListado();
        infoPlaylist = new SongInterfaz(temas,this);
	}
	
	private JMenuBar getBarraMenu() {
		// TODO m�s elementos �e iconos?
		if (barraMenu == null) {
			barraMenu = new JMenuBar();
			barraMenu.setBackground(bgcolor);
			barraMenu.setBorderPainted(false);
			playerMenu = new JMenu("Menu");
			//playerMenu.setIcon(monkeyIcon);
			playerMenu.add(getCargarArchivoItem());
			playerMenu.add(getPreviousItem());
			playerMenu.add(getNextItem());
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
                        
                        fc.setMultiSelectionEnabled(true);
                        if(fc.showOpenDialog(principal) == JFileChooser.APPROVE_OPTION) {
                        	File[] array = fc.getSelectedFiles();
                        	for(int i=0; i<array.length; i++){
                        		File f = array[i];
                        		System.out.println(f.getAbsolutePath());
                                listaReproduccion.add(f.getAbsolutePath());

                        	}
                        	setCurrentTrack(listaReproduccion.current());
                            String[] temas = listaReproduccion.getListado();
                            infoPlaylist.actualizaTemas(temas);
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
			barraProgreso.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					double posH = e.getPoint().getX();
					double ancho = barraProgreso.getWidth();
					double MaxValor = barraProgreso.getMaximum();
					double resultado =posH*MaxValor/ancho;
					System.out.println("Posicion clicada: "+ posH);
					System.out.println("Valor anterior: " + barraProgreso.getValue());
					System.out.println("Ancho: " + ancho);
					System.out.println("Maximo valor" + MaxValor);
					System.out.println("Deberiamos apuntar el setValue a: " + resultado);
					try {
						mPlayer.seek((long) resultado);
						barraProgreso.setValue((int)resultado );
						System.out.println("Lo que nos devuelve : " + barraProgreso.getValue());
					} catch (BasicPlayerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			/*barraProgreso.addMouseListener(new java.awt.event.MouseAdapter(){
	            @Override
	            public void mouseReleased(MouseEvent e) {
	                int value = barraProgreso.getValue();
	                value = value - mPlayer.PLAYING;
	                System.out.printf("Eeeeeeeeeey", value);
	                try {
	                	mPlayer.seek(value);
	                } catch (BasicPlayerException e1) {
	                    e1.printStackTrace();
	                }
	            }
	        });*/
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
	

	
	public JMenuItem getNextItem() {
		if (nextItem == null){
			nextItem = new JMenuItem("siguiente");
			//nextItem.setBackground(bgcolor);
			nextItem.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						//neeeeext cambia el currentTrack de la clase palylist y le devuelve playlist
						setCurrentTrack(listaReproduccion.next());
						pause = false;
						mPlayer.stop();
						mPlayer.play();
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		}
		return nextItem;
	}

	public JMenuItem getPreviousItem() {
		if (previousItem == null){
			previousItem = new JMenuItem("anterior");
			//nextItem.setBackground(bgcolor);
			previousItem.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						//previous cambia el currentTrack de la clase palylist y le devuelve playlist
						setCurrentTrack(listaReproduccion.previous());
						pause = false;
						mPlayer.stop();
						mPlayer.play();
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		}
		return previousItem;
	}

	private void setCurrentTrack(Track track) {
		try {
			 File f = new File(track.getLocation());
			 /* TODO 
			  * - poner la info en la interfaz en la interfaz;
			  * - Se podria hacer que la esto se mostrara en una ventana semi trasparente 
			  * similar a la del playlist, de forma que la info de cancion se muestre
			  * durante unos segundos cuando hay un cambio de cancion.
			  * - Resaltar la cancion actual en el playlist (Songinterfaz)
			  *
			 posTag=0-etiqueta.getSize().width;
			 caratula = track.getArtwork();
			 infoFrame = new JFrame("Informaci�n");
			 infoFrame.dispose();
			
			 infoFrame = new JFrame("Informaci�n");
			 infoPanel = new JPanel(new FlowLayout());
			 infoPanel.add(new JLabel((Icon)caratula));
			 infoPanel.add(new JLabel(track.getArtist() + " - " + track.getName()
					 	+ " (" + track.getAlbumArtist() +") "	) );
			 infoPanel.setEnabled(true);
			 infoPanel.setVisible(true);
			 infoFrame.setEnabled(true);
			 infoFrame.setVisible(true);
			 infoFrame.add(infoPanel);
			 infoFrame.setSize(500, 100);
			 infoFrame.setLocation((pantalla.width - ventana.width) / 2,
                     ((pantalla.height - ventana.height) / 2)-110);
			  *
			  * 
			  * 
			  * */
			 
			 
             if (mPlayer != null) {
                     mPlayer.stop();
             }
             mPlayer = new BasicPlayer();
             reproductorListener = new ReproductorListener(this);
             etiqueta.setText(null);
             etiqueta=new JLabel(track.getArtist() + " - " + track.getName()
					 	+ " (" + track.getAlbumArtist() +") "	 );
             etiqueta.setForeground(Color.ORANGE);
            
             GridBagConstraints position = new GridBagConstraints();
              position.gridx = 0;
              position.gridy = 3;
              position.gridheight = 1;
              position.gridwidth = 3;
              position.fill = GridBagConstraints.HORIZONTAL;
              this.getContentPane().add(etiqueta, position);
            
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
		if(posTag == 400){
			posTag=0-etiqueta.getSize().width;
		}
		else 
			posTag++;
			etiqueta.setLocation(posTag,(int) etiqueta.getLocation().getY());
		
	}
	
	private void centrarVentana() {
        
        this.setLocation((pantalla.width - ventana.width) / 2,
                        ((pantalla.height - ventana.height) / 2));
	}
	public void setTrackNumber(int number){
		try{
		setCurrentTrack(listaReproduccion.getTrack(number));
		pause = false;
		mPlayer.stop();
		mPlayer.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setBytesArchivo(double bytesLength) {
		this.bytesArchivoActual = bytesLength;
	}

}
