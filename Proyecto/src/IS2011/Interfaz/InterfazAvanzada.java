package IS2011.Interfaz;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;


import IS2011.FiltrosArchivos.*;
import IS2011.biblioteca.*;

import com.sun.awt.AWTUtilities;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * Clase que nos va a proporcionar una interfaz de usuario para la reproducción musical
 * tambien implementaremos aquí las funciones de reproducción,
 *  *
 */

@SuppressWarnings("restriction")
public class InterfazAvanzada extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Imágenes de la interfaz
	 */
	private ImageIcon monkeyIcon = new ImageIcon("images/Skin3/monkeyIcon.jpg");
	private ImageIcon carpetaIcon = new ImageIcon("images/Skin3/carpetaIcon.jpg");
	private ImageIcon menuIcon = new ImageIcon("images/Skin3/monkeyIcon2.png");
	
	private ImageIcon FFIcon = new ImageIcon("images/Skin3/ff.png");
	private ImageIcon wwIcon = new ImageIcon("images/Skin3/ww.png");
	private ImageIcon playIcon = new ImageIcon("images/Skin3/play.png");
	private ImageIcon libreriaIcon = new ImageIcon("images/Skin3/libreria.png");
	private ImageIcon cerrarIcon = new ImageIcon("images/Skin3/cerrar.png");
	private ImageIcon pauseIcon = new ImageIcon("images/Skin3/pause.png");
	
	private ImageIcon playedIcon = new ImageIcon("images/skin1/playIcon3.jpg");
	private ImageIcon stopIcon = new ImageIcon("images/skin1/stopIcon1.jpg");
	private ImageIcon stopedIcon = new ImageIcon("images/skin1/stopIcon3.jpg");
	private ImageIcon pausedIcon = new ImageIcon("images/skin1/pauseIcon3.jpg");
	private Cursor cursor = null;
	
	/**
	 * principal: Instancia de InterfazAvanzada para el método singleton
	 * (Es statica y privada)
	 */
	static private InterfazAvanzada principal = null;
	
	private SongInterfaz infoPlaylist = null;
	private SongInfoInterfaz infoSong = null;
	private BibliotecaInterfaz bibliotecaInterfaz = null;

	private BotonAvanzado stopButton = null;
	private BotonAvanzado pauseButton = null;
	private BotonAvanzado playButton = null;
	private JButton botonBiblioteca = null;
	private JLabel segundero = null;
	private JLabel menuLabel = null;
	private JSlider barraProgreso = null;
	private JButton salirButton = null;
	
	
	// Barra de menu principal
	private Menu menuPrincipal = null;
	// menu player
	private JMenu playerMenu = null;
	private JMenuItem cargarArchivoItem = null;
	private JMenuItem salirItem = null;
	private JMenuItem nextItem = null;
	private JMenuItem previousItem = null;
	private JPanelRound backGround = null;
	
	private JLabel infoSongLabel = null;


	

	private boolean pause = false;
	private boolean desplegado = false;
	private double bytesArchivoActual; 
	private BasicPlayer mPlayer;
	private ReproductorListener reproductorListener;
	private Shape figura;
	private Track _pista;
	private Color bgcolor = Color.black;
	private Color fgcolor = new Color(240,240,240);
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
	
	/**
	 * Método getSingleton() para la implementacion del patrón Singleton
	 * @return principal
	 */
	static public InterfazAvanzada getSingleton() {
		if (principal == null) {
			principal = new InterfazAvanzada();
		}
		return principal;
	}
	
	/**
	 * Constructora privada para la implementacion del patrón Singleton,
	 * define un Frame principal el cual será transparente, su contentPane será 
	 * un JLayeredPane que nor permite agregar elemento en capas, así conseguimos el efecto del
	 * icono del menu, luego se le añade un JPanelRound que será el fondo de la aplicación
	 * y a este úñtimo se le añade otro JPanel que nos facilita la opciones para la reproducción
	 * y la información de la canción
	 */
	private InterfazAvanzada() {
		
		super("Monaaaaco"); // El título
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
		backGround.setBounds(50,50, 700,350); //movemos el background 50 pixels en ambos ejes para dar paso al icono de Menu
		JPanel interno = getPanel();
		backGround.add(interno);
		interno.setBounds(25,25,650,250);
		
		menuLabel = new JLabel();
		ImageIcon menuAux =new ImageIcon(menuIcon.getImage().getScaledInstance(120,120, Image.SCALE_SMOOTH)); //Resizamos la imagen
		menuLabel.setIcon(menuAux);
		menuLabel.setBounds(0,0, 120,120);
		menuPrincipal = new Menu();
		menuLabel.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent evt) {
		          menuPrincipal.show(evt.getComponent(), backGround.getX(), backGround.getY());
		    }
		   
		});
		
	//	JMenuBar JMenuAux = getBarraMenu();
		//backGround.add(JMenuAux);
		//JMenuAux.setBounds(25, 5, 40, 20);
		
		JButton cerrarBoton = getSalirButton();
		cerrarBoton.setBounds(585,290,90,45);
		backGround.add(cerrarBoton);
		
		this.setSize(750,400); //hacemos la vrntana 50 pixels mas grandes
		this.setContentPane(new JLayeredPane()); //nos permite crear capas
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);	
		this.setVisible(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/Icono.png")); 
		this.getContentPane().add(backGround, new Integer(1));
		this.getContentPane().add(menuLabel,new Integer(2));
		AWTUtilities.setWindowOpaque(this, false); //hacemos transparente el JFrame
		restante= false;
		
		JButton bibliotecaIcono= this.getBotonBiblioteca();
		bibliotecaIcono.setBounds(470,290,90,45);
		backGround.add(bibliotecaIcono);
		
		this.setBiblioteca(this);
		//this.changeCursor();
		
		
		
		/*biliotecaInterfaz = new BibliotecaInterfaz(b,this); 
		aux.add(biliotecaInterfaz);
		biliotecaInterfaz.setBounds(25,300, 650, 200);*/
		
		
		//this.setJMenuBar(getBarraMenu());
		
		//aux.setVisible(true);
	//	aux.add(getPanel());// GridBagLayout
		
		this.centrarVentana();
	}
	/**
	 * Panel donde se mostrará la información de la canción así como los controles básicos, usa un GridBagLayout,
	 * muy complicado de usar se recomienda leer tutorial, la variable constraits indica los detalles de posición
	 * de cada elemento en el panel
	 * @return panelPrincipal
	 */
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
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.0;
		constraints.anchor = GridBagConstraints.SOUTH; 
		constraints.fill = GridBagConstraints.NONE;
		main.add(getPlayButton(), constraints);


		segundero = new JLabel("0:00");
		segundero.setForeground(fgcolor);
		segundero.setFont(new java.awt.Font("Helvetica", 1, 12));
		segundero.addMouseListener(new MouseAdapter() 
		{
            @Override
            public void mouseReleased(MouseEvent e) 
			{
				restante=!restante;
			}
		});
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER; 
		constraints.fill = GridBagConstraints.CENTER;
		main.add(segundero, constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER; 
		constraints.fill = GridBagConstraints.CENTER;
		main.add(getFfButton(), constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER; 
		constraints.fill = GridBagConstraints.CENTER;
		main.add(getWwButton(), constraints);


		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST; 
		constraints.fill = GridBagConstraints.HORIZONTAL;
		main.add(getBarraProgreso(), constraints);
		// constraints.weighty = 0.0; // Restauramos al valor por defecto, para
		// no afectar a los siguientes componentes.

		GridBagConstraints position = new GridBagConstraints();
       	position.gridx = 0;
       	position.gridy = 2;
       	position.gridheight = 1;
       	position.gridwidth = 4;
       	constraints.anchor = GridBagConstraints.WEST; 
		constraints.fill = GridBagConstraints.NONE;
      	main.add(getInfoSongLabel(), position);
						
      	setDefaultPalyList();
        String[] temas = listaReproduccion.getListado();
        infoPlaylist = new SongInterfaz(temas,this);
		infoSong = new SongInfoInterfaz();
		       
        
		GridBagConstraints position1 = new GridBagConstraints();
       	position1.gridx = 3;
       	position1.gridy = 0;
       	position1.gridheight = 2;
       	position1.gridwidth = 1;
        position1.weightx = 1.0;
        position1.anchor = GridBagConstraints.CENTER; 
        position1.insets= new Insets(0,0,0,20);
        position1.fill = GridBagConstraints.BOTH ;
        main.add(infoSong, position1);
      	
     return main;    
        
	}
	/**
	 * Creamos una PlayList vacia
	 * 
	 */
	private void setDefaultPalyList() {
		listaReproduccion = new Playlist();
		listaReproduccion.setRepeat(true);		
	}
	/**
	 * Creamos el Jlabel que se mostrará por pantalla con el título de la canción moviendose 
	 * a lo largo de la pantall
	 * @return infoSonfLabel
	 */
	private JLabel getInfoSongLabel() {
		if(infoSongLabel == null) {
			infoSongLabel=new JLabel("Monaaaco Player");
			infoSongLabel.setForeground(fgcolor);
			segundero.setFont(new java.awt.Font("Helvetica", 1, 12));
		}
		return infoSongLabel;
	}
	/** 
	 * Definimos la barra del menú, obsoleto ya no se usa
	 * @return
	 */
	/*private JMenuBar getBarraMenu() {
		 TODO más elementos ¿e iconos?
		if (/*barraMenu == null) {
			barraMenu = new JMenuBar();
			barraMenu.setBackground(bgcolor);
			
			barraMenu.setBorderPainted(false);
			playerMenu = new JMenu("Menu");
			//playerMenu.setIcon(monkeyIcon);
			playerMenu.add(getCargarArchivoItem());
		    playerMenu.setBackground(Color.black);
			playerMenu.setForeground(fgcolor);
			playerMenu.add(getPreviousItem());
			playerMenu.add(getNextItem());
			playerMenu.add(getSalirItem());
			barraMenu.add(playerMenu);
			
			JLabel movimiento = new JLabel("");
			movimiento.setBackground(Color.black);
			movimiento.setForeground(fgcolor);
	      		      	
	     	try{
				//@SuppressWarnings("rawtypes")
				//Class clazz= Class.forName("com.sun.awt.AWTUtilities");
				//@SuppressWarnings("unchecked")
				//Method method = clazz.getMethod("setWindowOpaque",java.awt.Window.class, Boolean.TYPE);
				//method.invoke(clazz, this,false);
			}catch (Exception e){}
			Mover mml = new Mover(movimiento);
			barraMenu.addMouseListener(mml);
			barraMenu.addMouseMotionListener(mml);
	    	barraMenu.add(movimiento);
		}
		return barraMenu;
	}*/
	
/**
 * Méotod para cargar un archivo, obsoleto ya no se usa
 * @return
 */
	private JMenuItem getCargarArchivoItem() {
        if (cargarArchivoItem == null) {
        	cargarArchivoItem = new JMenuItem("Cargar Archivo",carpetaIcon);
        	cargarArchivoItem.setBackground(Color.black);
        	cargarArchivoItem.setForeground(fgcolor);
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
                        	setCurrentTrack(listaReproduccion.getCurrent());
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
	/**
	 * Metodo que utilizamos para sacar todos los archivos de audia de un directorio
	 * @param file
	 */
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

	/**
	 * Nos proporcioan una Jslider con la que iremos siguiendo el transcurso de la canción, nos proporcionará la capacidad de adelantar
	 * o atrasar el curso de la misma
	 * @return barraProgreso
	 */
	public JSlider getBarraProgreso(){
		if(barraProgreso == null){
			barraProgreso = new JSlider();
			CustomSlider sliderUI = new CustomSlider(barraProgreso);
			//barraProgreso.setCursor(cursor);
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
					if(mPlayer!=null)
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
	/**
	 * Metodo que nos crea el nuevo cursor de la Slider, ojo que la path del cursor va a capón
	 * tambié puede usarse para modificar el cursor del ratón, mirar setCursor()
	 * @return cursor
	 */
	public void changeCursor(){ //TODO
		
		//Get the default toolkit  
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		  
		//Load an image for the cursor  
		Image image = toolkit.getImage("images/cursorBlanco.png");  //OJO con esto
		//Create the hotspot for the cursor  
		Point hotSpot = new Point(0,0);  
		//Create the custom cursor  
		cursor = toolkit.createCustomCursor(image, hotSpot, "Oro");  
		setCursor(cursor);
		
	}
	
	
	
	
	/**
	 * Botón que nos dotará de la posibilidad de ocultar o mostrar la biblioteca, se implementa mediante in listener de manera que
	 * cada vez que se pulse ampliará el JFrame y el BackGround,  mostrando la biblioteca y lo contrario
	 * @return botonBiblioteca
	 */
	
	public JButton getBotonBiblioteca() {
		if (botonBiblioteca == null){
			botonBiblioteca = new JButton(libreriaIcon);
			botonBiblioteca.setBorderPainted(false);
			botonBiblioteca.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						if (desplegado == false){
						principal.setSize(750,700); //Resizamos el frame Principal (tranparente)
						backGround.setSize(700,650); //Resizamos el backgruond
						bibliotecaInterfaz = new BibliotecaInterfaz(principal); 
						backGround.add(bibliotecaInterfaz);
						bibliotecaInterfaz.setBounds(25,350, 650, 200);
						desplegado = true;
						repaint();
						}
						else{
							backGround.setSize(700,350);
							principal.setSize(750,400);
							bibliotecaInterfaz=null;
							desplegado = false;
							
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		}
		return botonBiblioteca;
	}
	
	
	
	
	/**
	 * Proporciona el boton de avance, el nombre del botón no corresponde con su función
	 * @return stopButton
	 */
	//TODO
	public BotonAvanzado getFfButton() {
		if (stopButton == null){
			stopButton = new BotonAvanzado(FFIcon,FFIcon);
			stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					reproducirSiguiente();
				};
			});
		}
		return stopButton;
	}
	/**
	 * Proporciona el boton de play, se implementa mediante un listener, si el reproducotor esta en este momento reproduciendo
	 * su funcionalidad cambia a la pause al igual que su imagen
	 * @return playButton
	 */
	public BotonAvanzado getPlayButton() {
		if (playButton == null){
			playButton = new BotonAvanzado(playIcon,pauseIcon);
			playButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						if((reproduciendo == false) && listaReproduccion.getCurrentNumber()==-1)
						{
							//TODO lo que queriamos meter la 1º
							listaReproduccion.reset();
							if(listaReproduccion.getCurrentNumber()!=-1)
							{
								setTrackNumber(0);
								infoSong.actualiza(listaReproduccion.getCurrent());
								mPlayer.play();
								infoPlaylist.marcaActual();
							}
						}
						else if(reproduciendo == false){
							playButton.setIcon(pauseIcon);	
							mPlayer.play();
							infoPlaylist.marcaActual();
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
	/**
	 * Misma función que el botón de avance, su función no se corresponde con el nombre
	 * @return pauseButton
	 */
	public BotonAvanzado getWwButton() {
		if (pauseButton == null){
			pauseButton = new BotonAvanzado(wwIcon,wwIcon);
			pauseButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						reproducirAnterior();
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		}
		return pauseButton;
	}
	/**
	 * Botón para cerrar la aplicación
	 * @return salirButton
	 */
	public JButton getSalirButton() {
		if (salirButton == null){
			salirButton = new JButton(cerrarIcon);
			salirButton.setBorderPainted(false);
			salirButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						GestorBiblioteca.getInstance().guardarXML();
						System.exit(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		}
		return salirButton;
	}
	/**
	 * Obseloto ya no se usa
	 * @return
	 */
	public JMenuItem getSalirItem() {
		
		if (salirItem == null){
			salirItem = new JMenuItem("Salir");
			//salirItem.setBackground(bgcolor);
			salirItem.setBackground(Color.black);
			salirItem.setForeground(fgcolor);
			salirItem.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						GestorBiblioteca.getInstance().guardarXML();
						System.exit(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		}
		return salirItem;
	}
	/**
	 * Devuelve la lista de reproducción
	 * @return listaReproduccion
	 */
	public Playlist getListaReproduccion(){
		return listaReproduccion;
	}

	/**
	 * Pasa a reproducir la siguiente canción
	 */
    public void reproducirSiguiente(){
        setCurrentTrack(listaReproduccion.next());
        try {
            pause = false;
            mPlayer.stop();
            infoSong.actualiza(listaReproduccion.getCurrent());
            mPlayer.play();
            infoPlaylist.marcaActual();
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
       
    }
    /**
     * Pasa a reproducir la canción anterior
     */
    public void reproducirAnterior(){
        setCurrentTrack(listaReproduccion.previous());
        try {
            pause = false;
            mPlayer.stop();
            infoSong.actualiza(listaReproduccion.getCurrent());
            mPlayer.play();
            infoPlaylist.marcaActual();
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }
   
    /**
     * Obsoleto ya no se usa
     * @return nextItem
     */
    public JMenuItem getNextItem() {
        if (nextItem == null){
            nextItem = new JMenuItem("siguiente");
            //nextItem.setBackground(bgcolor);
            nextItem.setBackground(Color.black);
            nextItem.setForeground(fgcolor);
            nextItem.addMouseListener(new java.awt.event.MouseAdapter() {
                public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
                    reproducirSiguiente();
                };
            });
        }
        return nextItem;
    }
    /**
     * devuelve la info de la canción que suena
     * @return
     */
    public SongInfoInterfaz getInfoSong(){
    	return infoSong;
    }
    /**
     * Obsoleto ya no se usa
     * @return
     */
	public JMenuItem getPreviousItem() {
		if (previousItem == null){
			previousItem = new JMenuItem("anterior");
			//nextItem.setBackground(bgcolor);
			previousItem.setBackground(Color.black);
			previousItem.setForeground(fgcolor);
			previousItem.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						//previous cambia el currentTrack de la clase palylist y le devuelve playlist
						reproducirAnterior();
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
			 
             if (mPlayer != null) {
                     mPlayer.stop();
                                
             }
             
             mPlayer = new BasicPlayer();
             reproductorListener = new ReproductorListener(this);
             mPlayer.addBasicPlayerListener(reproductorListener);
             mPlayer.open(f);          
             posTag = 0-infoSongLabel.getSize().width;
             
             //TODO quitar
             infoSongLabel.setText(null);
             infoSongLabel.setText(track.getArtist() + " - " + track.getName()
					 	+ " (" + track.getAlbumArtist() +") "	 );
             
             
             infoSong.actualiza(track);

     } catch (Exception e) {
    	 System.err.println("Error la libreria no soporta el formato especificado.");
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
	/**
	 * Actualiza el reloj que marca el transcurso y mueve por la pantalla el nombre de la 
	 * canción que suena en ese momento (infoSongLabel).
	 */
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
		this.setLocationRelativeTo(null);
//        this.setLocation((pantalla.width - ventana.width) / 2,
//                        (((pantalla.height - ventana.height) / 2))-100);
	}
	
	/**
	 * Modificamos el trackActual y reproducimos pos
	 * @param pos indica la posición de la playlist que vamos a reproducir
	 */
	public void setTrackNumber(int pos){
		try{
			listaReproduccion.setCurrentTrack(pos);
			setCurrentTrack(listaReproduccion.getTrack(pos));
			pause = false;
			mPlayer.stop();
			mPlayer.play();
			if(!reproduciendo){
				reproduciendo = true;
				playButton.setIcon(pauseIcon);
			}else{
				playButton.setIcon(pauseIcon);
			}

		} catch (Exception e) {
			System.out.println("ALGO");
			e.printStackTrace();
		}
	}

	
	/**
	 * Mutadora de bytesArchivoActual
	 * 
	 */
	public void setBytesArchivo(double bytesLength) {
		this.bytesArchivoActual = bytesLength;
	}
	
	
	/**
	 * Asocia una biblioteca a nuestra interfaz de reproduccion
	 * @param ia Referencia a la interfaz avanzada de la que procede
	 */
	public void setBiblioteca(InterfazAvanzada ia){
		bibliotecaInterfaz = new BibliotecaInterfaz(ia);
		bibliotecaInterfaz.setVisible(true);
	}

	/**
	 * Cambia el elemento actual por el que corresponda
	 * 
	 */
	public void borradoElemActualPlaylist(){
		if(pause == false || reproduciendo == false){
			try {
				mPlayer.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(listaReproduccion.getNumTracks() != 0){
				try {
					setCurrentTrack(listaReproduccion.getCurrent());
					mPlayer.play();
					//TODO FALTAAAAA!!!!
					//TODO Si no quedan canciones en la playlist se quita de la interfaz la ultima
				} catch (BasicPlayerException e) {
					System.out.println("ALGO");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else{
			setCurrentTrack(listaReproduccion.getCurrent());
			reproduciendo = false;
		}
	}
	
	/**
	 * Accesora a infoPlaylist
	 * @return SongInterfaz
	 */
	public SongInterfaz getInfoPlaylist(){
		return infoPlaylist;
	}
	
	
	/**
	 * Accesora a listaReproduccion
	 * @return PlayList
	 */
	public Playlist getPlaylist(){
		if( listaReproduccion == null ){
			setDefaultPalyList();
		}
		return listaReproduccion;
	}
	
	/**
	 * Nos indica si hay alguna canción en reproducción en mPlayer.
	 * @return boolean
	 */
	public boolean isPlaying(){
		if(mPlayer != null)return (mPlayer.getStatus() == mPlayer.PLAYING);
		else return false;
	}
	private void reproducir(){
		try {
			mPlayer.play();
		} catch (BasicPlayerException e) {
			int[] lista = new int[0];
			lista[0] = listaReproduccion.getCurrentNumber();
			boolean b = listaReproduccion.borraTrack(lista);
			if(b){
				borradoElemActualPlaylist();
			}
			infoPlaylist.marcaActual();
			e.printStackTrace();
		}
	}
}

