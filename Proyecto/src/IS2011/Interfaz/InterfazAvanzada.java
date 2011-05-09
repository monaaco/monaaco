package IS2011.Interfaz;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.lang.reflect.Method;

import javax.swing.*;
//import javax.swing.event.*;
//import javax.swing.filechooser.FileFilter;


import IS2011.FiltrosArchivos.*;
import IS2011.bibliotecaXML.*;

import com.sun.awt.AWTUtilities;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;


@SuppressWarnings("restriction")
public class InterfazAvanzada extends JFrame {

	private static final long serialVersionUID = 1L;
	// Imagenes:
	private ImageIcon monkeyIcon = new ImageIcon("images/monkeyIcon.jpg");
	private ImageIcon carpetaIcon = new ImageIcon("images/carpetaIcon.jpg");
	
	private ImageIcon playIcon = new ImageIcon("images/Skin3/play.png");
	private ImageIcon libreriaIcon = new ImageIcon("images/Skin3/libreria.png");
	private ImageIcon cerrarIcon = new ImageIcon("images/Skin3/cerrar.png");
	private ImageIcon playedIcon = new ImageIcon("images/skin1/playIcon3.jpg");
	private ImageIcon stopIcon = new ImageIcon("images/skin1/stopIcon1.jpg");
	private ImageIcon stopedIcon = new ImageIcon("images/skin1/stopIcon3.jpg");
	private ImageIcon pauseIcon = new ImageIcon("images/Skin3/pause.png");
	private ImageIcon pausedIcon = new ImageIcon("images/skin1/pauseIcon3.jpg");
	
	private InterfazAvanzada principal = null;
	private SongInterfaz infoPlaylist = null;
	private SongInfoInterfaz infoSong = null;
	private JPanel infoSong2 = null;
	private BibliotecaInterfaz biliotecaInterfaz = null;
	private GestorXML b = null;

	private BotonAvanzado stopButton = null;
	private BotonAvanzado pauseButton = null;
	private BotonAvanzado playButton = null;
	private JButton botonBiblioteca = null;
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
	private JPanelRound backGround = null;
	
	private Color c= new Color(240,240,240);
	private JLabel infoSongLabel = null;

	
/*	// menu XML  Por si lo usaramos que esta en la otra playerInterface!
	private JMenuItem bibliotecaMenu = null;
	private JMenu leerMenu = null;
	private JMenuItem leerXML = null;
	private JMenu guardarMenu = null;
	private JMenuItem guardarXML = null;
*/
	private boolean pause = false;
	private double bytesArchivoActual; 
	private BasicPlayer mPlayer;
	private ReproductorListener reproductorListener;
	private Shape figura;
	private Track _pista;
	private Color bgcolor = Color.black;
	private boolean reproduciendo = false;
	private boolean restante= false;
	
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
		// infoSong.actualiza(null);
		//fondo = new TransparentBackground(this);
		principal = this;
		backGround = new JPanelRound();	
		//backGround.setSize(700,700);
		//aux.setBackground(Color.BLUE);
		backGround.setOpaque(false);
		backGround.setLayout(null);
		backGround.setColorPrimario(Color.black);
		Mover mml = new Mover(backGround);
		backGround.addMouseListener(mml);
		backGround.addMouseMotionListener(mml);
		backGround.setColorSecundario(Color.white);
		JPanel interno = getPanel();
		backGround.add(interno);
		interno.setBounds(25,25,650,250);
		
		JMenuBar JMenuAux = getBarraMenu();
		backGround.add(JMenuAux);
		JMenuAux.setBounds(25, 5, 40, 20);
		
		JButton cerrarBoton = getSalirButton();
		cerrarBoton.setBounds(585,290,90,45);
		backGround.add(cerrarBoton);
		
		this.setSize(700,350);
		this.setContentPane(backGround);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);	
		this.setVisible(true);
		
		restante= false;
		
		JButton bibliotecaIcono= this.getBotonBiblioteca();
		bibliotecaIcono.setBounds(470,290,90,45);
		backGround.add(bibliotecaIcono);
		
		b = new GestorXML();
		b.cargar();
		this.setBiblioteca(b,this);
		
		/*biliotecaInterfaz = new BibliotecaInterfaz(b,this); 
		aux.add(biliotecaInterfaz);
		biliotecaInterfaz.setBounds(25,300, 650, 200);*/
		
		
		//this.setJMenuBar(getBarraMenu());
		
		//aux.setVisible(true);
	//	aux.add(getPanel());// GridBagLayout
		
		this.centrarVentana();
	}
	private JPanel getPanel(){
		JPanelRound main = new JPanelRound();
		main.setColorPrimario(bgcolor);
		main.setLayout(new GridBagLayout()); // Le ponemos el
	//	main.setUndecorated(true);														// GridBagLayout
		main.setSize(300, 300);
		
		//main.centrarVentana();
		
		pause = false;
		bytesArchivoActual =0;
		
		main.setEnabled(true);						//En la otra principal
		//main.setResizable(false);		*/			//En la otra principal
		//main.setIconImage(monkeyIcon.getImage());	//En la otra principal A�ADIDO POR MI
		//main.setTitle("Monaaco Player");			//En la otra principal A�ADIDO POR MI
		//main.setJMenuBar(getBarraMenu());
		main.setBackground(bgcolor);
		main.setEnabled(true);
		main.setVisible(true);
	
		GridBagConstraints constraints = new GridBagConstraints();
	/*	constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.fill = GridBagConstraints.EAST;
	//	main.add(getStopButton(), constraints);*/

		/*constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.fill = GridBagConstraints.WEST;
	//	main.add(getPauseButton(), constraints);
		constraints.weightx = 0.0;*/

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.0;
		constraints.fill = GridBagConstraints.WEST;
		main.add(getPlayButton(), constraints);


		segundero = new JLabel("0:00");
		segundero.setForeground(c);
		segundero.setFont(new java.awt.Font("Helvetica", 1, 12));
		segundero.addMouseListener(new MouseAdapter() 
		{
            @Override
            public void mouseReleased(MouseEvent e) 
			{
				restante=!restante;
			}
		});
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.CENTER;
		main.add(segundero, constraints);

		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		main.add(getBarraProgreso(), constraints);
		// constraints.weighty = 0.0; // Restauramos al valor por defecto, para
		// no afectar a los siguientes componentes.

		GridBagConstraints position = new GridBagConstraints();
       	position.gridx = 0;
       	position.gridy = 2;
       	position.gridheight = 1;
       	position.gridwidth = 2;
       	position.fill = GridBagConstraints.HORIZONTAL;
      	main.add(getInfoSongLabel(), position);
						
      	getDefaultPalyList();
        String[] temas = listaReproduccion.getListado();
        infoPlaylist = new SongInterfaz(temas,this);
		infoSong = new SongInfoInterfaz();
		       
        
		GridBagConstraints position1 = new GridBagConstraints();
       	position1.gridx = 1;
       	position1.gridy = 0;
       	position1.gridheight = 1;
       	position1.gridwidth = 1;
        position1.weightx = 1.0;
        position1.fill = GridBagConstraints.CENTER ;
        main.add(infoSong, position1);
      	
     return main;    
        
	}
	
	private void getDefaultPalyList() {
		listaReproduccion = new Playlist();
		listaReproduccion.setRepeat(true);
		listaReproduccion.add("sounds/prueba2.ogg"); 
		listaReproduccion.add("sounds/prueba.mp3"); 
		setCurrentTrack(listaReproduccion.current());

		
	}

	private JLabel getInfoSongLabel() {
		if(infoSongLabel == null) {
			infoSongLabel=new JLabel("Monaaaco Player");
			infoSongLabel.setForeground(c);
			segundero.setFont(new java.awt.Font("Helvetica", 1, 12));
		}
		return infoSongLabel;
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
		    playerMenu.setBackground(Color.black);
			playerMenu.setForeground(c);
			playerMenu.add(getPreviousItem());
			playerMenu.add(getNextItem());
			playerMenu.add(getSalirItem());
			barraMenu.add(playerMenu);
			
			JLabel movimiento = new JLabel("");
			movimiento.setBackground(Color.black);
			movimiento.setForeground(c);
	      		      	
	      /*	try{
				//@SuppressWarnings("rawtypes")
				//Class clazz= Class.forName("com.sun.awt.AWTUtilities");
				//@SuppressWarnings("unchecked")
				//Method method = clazz.getMethod("setWindowOpaque",java.awt.Window.class, Boolean.TYPE);
				//method.invoke(clazz, this,false);
			}catch (Exception e){}*/
			Mover mml = new Mover(movimiento);
			barraMenu.addMouseListener(mml);
			barraMenu.addMouseMotionListener(mml);
	    	barraMenu.add(movimiento);
		}
		return barraMenu;
	}
	

	private JMenuItem getCargarArchivoItem() {
        if (cargarArchivoItem == null) {
        	cargarArchivoItem = new JMenuItem("Cargar Archivo",carpetaIcon);
        	cargarArchivoItem.setBackground(Color.black);
        	cargarArchivoItem.setForeground(c);
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
                        fc.setFileSelectionMode(fc.FILES_AND_DIRECTORIES);
                        if(fc.showOpenDialog(principal) == JFileChooser.APPROVE_OPTION) {
                        	File[] array = fc.getSelectedFiles();
                        	for(int i=0; i<array.length; i++){
                        		getAudioFiles(array[i]);
                        		/*File f = array[i];
                                listaReproduccion.add(f.getAbsolutePath());*/
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
	
	private void getAudioFiles(File file){
		if(file.isDirectory()){
			File[] array = file.listFiles();
			int n = array.length;
			int i;
			for(i = 0; i < n; i++){
				getAudioFiles(array[i]);
			}
		}
		else if(file.getName().toLowerCase().endsWith(".mp3") || file.getName().toLowerCase().endsWith(".ogg")){
			listaReproduccion.add(file.getAbsolutePath());
		}
	}


	public JSlider getBarraProgreso(){
		if(barraProgreso == null){
			barraProgreso = new JSlider();
			barraProgreso.setOpaque(false);
			barraProgreso.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
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
	
	
	
	

	
	public JButton getBotonBiblioteca() {
		if (botonBiblioteca == null){
			botonBiblioteca = new JButton(libreriaIcon);
			botonBiblioteca.setBorderPainted(false);
			botonBiblioteca.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						principal.setSize(700,600);
										
						biliotecaInterfaz = new BibliotecaInterfaz(b,principal); 
						backGround.add(biliotecaInterfaz);
						biliotecaInterfaz.setBounds(25,350, 650, 200);
						repaint();
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		}
		return botonBiblioteca;
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
			playButton = new BotonAvanzado(playIcon,pauseIcon);
			playButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						if(reproduciendo == false){
							playButton.setIcon(pauseIcon);	
							mPlayer.play();
							reproduciendo = true;
						
						}
						else{
							if (pause == false) {
								pause = true;
								mPlayer.pause();
								playButton.setIcon(playIcon);	
							}else {
								pause = false;
								mPlayer.resume();
								playButton.setIcon(pauseIcon);
								
							}
							
						}
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
			salirButton = new JButton(cerrarIcon);
			salirButton.setBorderPainted(false);
			salirButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						biblioteca.guardar();
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
			salirItem.setBackground(Color.black);
			salirItem.setForeground(c);
			salirItem.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						biblioteca.guardar();
						System.exit(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		}
		return salirItem;
	}

	public Playlist getListaReproduccion(){
		return listaReproduccion;
	}

	
    public void reproducirSiguiente(){
        setCurrentTrack(listaReproduccion.next());
        try {
            pause = false;
            mPlayer.stop();
            infoSong.actualiza(listaReproduccion.current());
            mPlayer.play();
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }
   
    public JMenuItem getNextItem() {
    	
    	
        if (nextItem == null){
            nextItem = new JMenuItem("siguiente");
            //nextItem.setBackground(bgcolor);
            nextItem.setBackground(Color.black);
            nextItem.setForeground(c);
            nextItem.addMouseListener(new java.awt.event.MouseAdapter() {
                public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
                    reproducirSiguiente();
                };
            });
        }
        return nextItem;
    }
    
    public SongInfoInterfaz getInfoSong(){
    	return infoSong;
    }

	public JMenuItem getPreviousItem() {
		if (previousItem == null){
			previousItem = new JMenuItem("anterior");
			//nextItem.setBackground(bgcolor);
			previousItem.setBackground(Color.black);
			previousItem.setForeground(c);
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

	
	/**
	 * 
	 * @param track con la que se crea el BasicPlayer
	 */
	public void setCurrentTrack(Track track) {
		try {
			 File f = new File(track.getLocation());
			 /* TODO 
			  * - poner la info en la interfaz en la interfaz;
			  * - Se podria hacer que la esto se mostrara en una ventana semi trasparente 
			  * similar a la del playlist, de forma que la info de cancion se muestre
			  * durante unos segundos cuando hay un cambio de cancion.
			  * - Resaltar la cancion actual en el playlist (Songinterfaz)
			  *
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
             mPlayer.addBasicPlayerListener(reproductorListener);
             mPlayer.open(f); 
                  
             
             posTag = 0-infoSongLabel.getSize().width;
             infoSongLabel.setText(null);
             infoSongLabel.setText(track.getArtist() + " - " + track.getName()
					 	+ " (" + track.getAlbumArtist() +") "	 );
             
             
             infoSong.actualiza(track);

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
			posTag=0-infoSongLabel.getSize().width;
		}
		else 
			posTag++;
			infoSongLabel.setLocation(posTag,(int) infoSongLabel.getLocation().getY());
		
	}
	
	public void cambiaSegundos(int min, int segs) {
		String texto;
		texto= "";
		if(restante)
		{
			//TODO obtenemos los restantes en segundos como en el Track.
			
			int segTotales;
			int resultado;
			
			segTotales = infoSong.getTrack().getTotalTime();
			//deberia estar cargado ya por ahi no estar cargandolo siempre.
			resultado = (segTotales - ((60*min)+segs));
			min = (resultado / 60);
			segs = resultado % 60;
			texto= "-";
		}
		if(segs>=10)
		{
			texto+= min +":"+ segs;
		}
		else
		{
			texto+= min +":0"+ segs;
		}
        segundero.setText(texto);
        if(posTag == 400){
                posTag=0-infoSongLabel.getSize().width;
        }
        else
                posTag++;
                infoSongLabel.setLocation(posTag,(int) infoSongLabel.getLocation().getY());
}
	
	private void centrarVentana() {
        
        this.setLocation((pantalla.width - ventana.width) / 2,
                        (((pantalla.height - ventana.height) / 2))-100);
	}
	public void setTrackNumber(int number){
		try{
		setCurrentTrack(listaReproduccion.getTrack(number));
		pause = false;
		mPlayer.stop();
		mPlayer.play();
		  reproduciendo = true;
          playButton.setIcon(pauseIcon);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setBytesArchivo(double bytesLength) {
		this.bytesArchivoActual = bytesLength;
	}
	
	
	/**
	 * Asocia una biblioteca a nuestra interfaz de reproduccion
	 * @param b  Biblioteca asociada
	 * @param ia Referencia a la interfaz avanzada de la que procede
	 */
	public void setBiblioteca(GestorXML b, InterfazAvanzada ia){
		biblioteca = b;
		bInterfaz = new BibliotecaInterfaz(b, ia);
		bInterfaz.setVisible(true);
	}
	
	public SongInterfaz getInfoPlaylist(){
		return infoPlaylist;
	}
	
	private GestorXML biblioteca= null;
	private BibliotecaInterfaz bInterfaz = null;

}

