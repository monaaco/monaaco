package IS2011.Interfaz;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import IS2011.Configuracion.GestorPreferencias;
import IS2011.FiltrosArchivos.FiltroMP3;
import IS2011.FiltrosArchivos.FiltroOGG;
import IS2011.FiltrosArchivos.FiltroSoportados;
import IS2011.biblioteca.GestorBiblioteca;
import IS2011.biblioteca.Playlist;
import IS2011.biblioteca.Track;

import com.sun.awt.AWTUtilities;

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
	 * @uml.property  name="monkeyIcon"
	 * @uml.associationEnd  
	 */
	private ImageIcon monkeyIcon = null;
	/**
	 * @uml.property  name="carpetaIcon"
	 * @uml.associationEnd  
	 */
	private ImageIcon carpetaIcon = null;
	/**
	 * @uml.property  name="menuIcon"
	 * @uml.associationEnd  
	 */
	private ImageIcon menuIcon = null;
	
	/**
	 * @uml.property  name="fFIcon"
	 * @uml.associationEnd  
	 */
	private ImageIcon FFIcon = null;
	/**
	 * @uml.property  name="wwIcon"
	 * @uml.associationEnd  
	 */
	private ImageIcon wwIcon = null;
	/**
	 * @uml.property  name="playIcon"
	 * @uml.associationEnd  
	 */
	private ImageIcon playIcon = null;
	/**
	 * @uml.property  name="libreriaIcon"
	 * @uml.associationEnd  
	 */
	private ImageIcon libreriaIcon = null;
	/**
	 * @uml.property  name="cerrarIcon"
	 * @uml.associationEnd  
	 */
	private ImageIcon cerrarIcon = null;
	/**
	 * @uml.property  name="pauseIcon"
	 * @uml.associationEnd  
	 */
	private ImageIcon pauseIcon = null;

	/**
	 * @uml.property  name="bgColor"
	 */
	private Color bgColor = null;
	/**
	 * @uml.property  name="fgColor"
	 */
	private Color fgColor = null;
	/**
	 * @uml.property  name="bgColorInterno"
	 */
	private Color bgColorInterno = null;
	/**
	 * @uml.property  name="fgColorInterno"
	 */
	private Color fgColorInterno = null;



	/**
	 * @uml.property  name="cursor"
	 */
	private Cursor cursor = null;

	
	/**
	 * principal: Instancia de InterfazAvanzada para el método singleton
	 * (Es statica y privada)
	 */
	static private InterfazAvanzada principal = null;
	
	/**
	 * @uml.property  name="infoPlaylist"
	 * @uml.associationEnd  inverse="interfazAvanzada:IS2011.Interfaz.SongInterfaz"
	 */
	private SongInterfaz infoPlaylist = null;
	/**
	 * @uml.property  name="infoSong"
	 * @uml.associationEnd  inverse="interfazAvanzada:IS2011.Interfaz.SongInfoInterfaz"
	 */
	private SongInfoInterfaz infoSong = null;
	/**
	 * @uml.property  name="bibliotecaInterfaz"
	 * @uml.associationEnd  inverse="interfazPadre:IS2011.Interfaz.BibliotecaInterfaz"
	 */
	private BibliotecaInterfaz bibliotecaInterfaz = null;

	/**
	 * @uml.property  name="stopButton"
	 * @uml.associationEnd  
	 */
	private BotonAvanzado stopButton = null;
	/**
	 * @uml.property  name="pauseButton"
	 * @uml.associationEnd  
	 */
	private BotonAvanzado pauseButton = null;
	/**
	 * @uml.property  name="playButton"
	 * @uml.associationEnd  
	 */
	private BotonAvanzado playButton = null;
	/**
	 * @uml.property  name="botonBiblioteca"
	 * @uml.associationEnd  
	 */
	private JButton botonBiblioteca = null;
	/**
	 * @uml.property  name="segundero"
	 * @uml.associationEnd  
	 */
	private JLabel segundero = null;
	/**
	 * @uml.property  name="menuLabel"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel menuLabel = null;
	/**
	 * @uml.property  name="barraProgreso"
	 * @uml.associationEnd  
	 */
	private JSlider barraProgreso = null;
	/**
	 * @uml.property  name="salirButton"
	 * @uml.associationEnd  
	 */
	private JButton salirButton = null;
	
	/**
	 * @uml.property  name="main"
	 * @uml.associationEnd  
	 */
	private JPanelRound main = null;
	
	// Barra de menu principal
	/**
	 * @uml.property  name="menuPrincipal"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="ia:IS2011.Interfaz.Menu"
	 */
	private Menu menuPrincipal = null;
	// menu player
	/**
	 * @uml.property  name="playerMenu"
	 * @uml.associationEnd  
	 */
	private JMenu playerMenu = null;
	/**
	 * @uml.property  name="cargarArchivoItem"
	 * @uml.associationEnd  
	 */
	private JMenuItem cargarArchivoItem = null;
	/**
	 * @uml.property  name="salirItem"
	 * @uml.associationEnd  
	 */
	private JMenuItem salirItem = null;
	/**
	 * @uml.property  name="nextItem"
	 * @uml.associationEnd  
	 */
	private JMenuItem nextItem = null;
	/**
	 * @uml.property  name="previousItem"
	 * @uml.associationEnd  
	 */
	private JMenuItem previousItem = null;
	
	/**
	 * @uml.property  name="backGround"
	 * @uml.associationEnd  
	 */
	private JPanelRound backGround = null;
	
	/**
	 * @uml.property  name="infoSongLabel"
	 * @uml.associationEnd  
	 */
	private JLabel infoSongLabel = null;


	

	/**
	 * @uml.property  name="pause"
	 */
	private boolean pause = false;
	/**
	 * @uml.property  name="desplegado"
	 */
	private boolean desplegado = false;
	/**
	 * @uml.property  name="bytesArchivoActual"
	 */
	private double bytesArchivoActual; 
	/**
	 * @uml.property  name="mPlayer"
	 * @uml.associationEnd  
	 */
	private BasicPlayer mPlayer;
	/**
	 * @uml.property  name="reproductorListener"
	 * @uml.associationEnd  inverse="player:IS2011.Interfaz.ReproductorListener"
	 */
	private ReproductorListener reproductorListener;
	/**
	 * @uml.property  name="figura"
	 */
	private Shape figura;
	/**
	 * @uml.property  name="_pista"
	 * @uml.associationEnd  readOnly="true"
	 */
	private Track _pista;
	/**
	 * @uml.property  name="reproduciendo"
	 */
	private boolean reproduciendo = false;
	/**
	 * @uml.property  name="restante"
	 */
	private boolean restante= false;
	
	
	//private TransparentBackground fondo = null;

	//Playlist:
	/**
	 * @uml.property  name="listaReproduccion"
	 * @uml.associationEnd  
	 */
	private Playlist listaReproduccion = null;
	/**
	 * @uml.property  name="caratula"
	 */
	private Image caratula = null;
	/**
	 * @uml.property  name="pantalla"
	 */
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
     // Se obtienen las dimensiones en pixels de la ventana.
    /**
	 * @uml.property  name="ventana"
	 */
    private Dimension ventana = this.getSize();

	/**
	 * @uml.property  name="posTag"
	 */
	private int posTag=0;

	/**
	 * @uml.property  name="rutaIndexada"
	 */
	private String rutaIndexada;
	
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
		
		principal = this;

		//Carga con Xstream las Preferencias
		this.cargarPreferencias();
		

		
		// infoSong.actualiza(null);
		//fondo = new TransparentBackground(this);
		
		menuLabel = new JLabel();
		ImageIcon menuAux =new ImageIcon(menuIcon.getImage().getScaledInstance(120,120, Image.SCALE_SMOOTH)); //Resizamos la imagen
		menuLabel.setIcon(menuAux);
		menuLabel.setBounds(0,0, 120,120);
		menuPrincipal = new Menu(this);
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
		this.setForeground(Color.white);

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
	private JPanelRound getPanel(){
		if( main == null){
			main = new JPanelRound();
			main.setLayout(new GridBagLayout()); // Le ponemos el
		//	main.setUndecorated(true);														// GridBagLayout
			main.setSize(300, 300);
			
			//main.centrarVentana();
			
			pause = false;
			bytesArchivoActual =0;
			
			main.setEnabled(true);						//En la otra principal
	
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
			segundero.setForeground(fgColor);
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
	       	position.anchor = GridBagConstraints.WEST; 
	       	position.fill = GridBagConstraints.NONE;
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
	        position1.fill = GridBagConstraints.HORIZONTAL ;
	        main.add(infoSong, position1);
	        main.setForeground(Color.white);
		}
     return main;    
        
	}
	/**
	 * Creamos una PlayList vacia
	 * 
	 */
	private void setDefaultPalyList() {
		listaReproduccion = GestorBiblioteca.getInstance().getColaReproduccion();
		listaReproduccion.setRepeat(true);	
	}
	/**
	 * Creamos el Jlabel que se mostrará por pantalla con el título de la canción moviendose  a lo largo de la pantall
	 * @return  infoSonfLabel
	 * @uml.property  name="infoSongLabel"
	 */
	private JLabel getInfoSongLabel() {
		if(infoSongLabel == null) {
			infoSongLabel=new JLabel("Monaaaco Player");
			infoSongLabel.setForeground(fgColor);
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
 * Definimos la barra del menú, obsoleto ya no se usa
 * @return
 * @uml.property  name="cargarArchivoItem"
 */
	private JMenuItem getCargarArchivoItem() {
        if (cargarArchivoItem == null) {
        	cargarArchivoItem = new JMenuItem("Cargar Archivo",carpetaIcon);
        	cargarArchivoItem.setBackground(Color.black);
        	cargarArchivoItem.setForeground(fgColor);
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
	 * Metodo que utilizamos para sacar todos los archivos de audio de un directorio
	 * @param file el fichero en el cual buscamos los archivos de audio
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
	 * Nos proporcioan una Jslider con la que iremos siguiendo el transcurso de la canción, nos proporcionará la capacidad de adelantar o atrasar el curso de la misma
	 * @return  barraProgreso
	 * @uml.property  name="barraProgreso"
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
					if(mPlayer!=null)
					try {
						mPlayer.seek((long) resultado);
						barraProgreso.setValue((int)resultado );
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
	 * Botón que nos dotará de la posibilidad de ocultar o mostrar la biblioteca, se implementa mediante in listener de manera que cada vez que se pulse ampliará el JFrame y el BackGround,  mostrando la biblioteca y lo contrario
	 * @return  botonBiblioteca
	 * @uml.property  name="botonBiblioteca"
	 */
	
	public JButton getBotonBiblioteca() {
		if (botonBiblioteca == null){
			botonBiblioteca = new JButton(libreriaIcon);
			botonBiblioteca.setBorderPainted(false);
			botonBiblioteca.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						if (desplegado == false){
						principal.setSize(750,725); //Resizamos el frame Principal (tranparente)
						principal.setLocationRelativeTo(null);
						backGround.setSize(700,675); //Resizamos el backgruond
						bibliotecaInterfaz = new BibliotecaInterfaz(principal); 
						backGround.add(bibliotecaInterfaz);
						bibliotecaInterfaz.setBounds(25,350,650,300);
						desplegado = true;
						repaint();
						}
						else{
							backGround.setSize(700,350);
							principal.setSize(750,400);
							principal.setLocationRelativeTo(null);
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
	 * Accesora JPanelRound backGround. 
	 * @return backGround
	 */
	public JPanelRound getMyBackground(){
		if(backGround == null){
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
			interno.setForeground(Color.white);
			backGround.add(interno);
			interno.setBounds(25,25,650,250);
		}	
		return backGround;
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
	 * Proporciona el boton de play, se implementa mediante un listener, si el reproducotor esta en este momento reproduciendo su funcionalidad cambia a la pause al igual que su imagen
	 * @return  playButton
	 * @uml.property  name="playButton"
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
						infoPlaylist.marcaActual();
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
	 * @return  salirButton
	 * @uml.property  name="salirButton"
	 */
	public JButton getSalirButton() {
		if (salirButton == null){
			salirButton = new JButton(cerrarIcon);
			salirButton.setBorderPainted(false);
			salirButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						salir();
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
	 * @uml.property  name="salirItem"
	 */
	public JMenuItem getSalirItem() {
		
		if (salirItem == null){
			salirItem = new JMenuItem("Salir");
			//salirItem.setBackground(bgcolor);
			salirItem.setBackground(Color.black);
			salirItem.setForeground(fgColor);
			salirItem.addMouseListener(new java.awt.event.MouseAdapter() {
				public synchronized void mouseReleased(java.awt.event.MouseEvent evt) {
					try {
						salir();
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
	 * @return  listaReproduccion
	 * @uml.property  name="listaReproduccion"
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
            mPlayer.play();  //TODO aqui se come formatos no soportados
            infoPlaylist.marcaActual();
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }
   
    /**
	 * Obsoleto ya no se usa
	 * @return  nextItem
	 * @uml.property  name="nextItem"
	 */
    public JMenuItem getNextItem() {
        if (nextItem == null){
            nextItem = new JMenuItem("siguiente");
            //nextItem.setBackground(bgcolor);
            nextItem.setBackground(Color.black);
            nextItem.setForeground(fgColor);
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
	 * @uml.property  name="infoSong"
	 */
    public SongInfoInterfaz getInfoSong(){
    	return infoSong;
    }
    /**
	 * Obsoleto ya no se usa
	 * @return
	 * @uml.property  name="previousItem"
	 */
	public JMenuItem getPreviousItem() {
		if (previousItem == null){
			previousItem = new JMenuItem("anterior");
			//nextItem.setBackground(bgcolor);
			previousItem.setBackground(Color.black);
			previousItem.setForeground(fgColor);
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
	 * Permite comenzar a reproducir el Track pasado por parámetro.
	 * @param track es el track que se va a reproducir.
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

	/**
	 * Modifica el valor actual de la JSlider barraProgreso. 
	 * @param estado nuevo valor de la barraProgreso
	 */
	public void actualizaBarraProgreso(double estado) {
		barraProgreso.setValue((int) estado);
	}
	
	/**
	 * Permite fijar un valor máximo
	 * para la JSlider barraProgreso. 
	 * @param fin pasa a ser el valor máximo de la barra de progreso.
	 */
	public void ajustaBarraProgreso(double fin) {
		barraProgreso.setMaximum((int) fin);
	}
	
	/**
	 * Permite modificar el JLabel segundero que es el encargado
	 * de mostrar en la interfaz el tiempo de la canción
	 * @param texto nuevo valor de segundero.
	 */
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
	 * Actualiza el reloj que marca el transcurso
	 * y mueve por la pantalla el nombre de la 
	 * canción que suena en ese momento (infoSongLabel).
	 * @param int min minutos actuales
	 * @param int segs segundos actuales
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
	/**
	 * Centra la ventana principal del programa.
	 */
	private void centrarVentana() {
		this.setLocationRelativeTo(null);
//        this.setLocation((pantalla.width - ventana.width) / 2,
//                        (((pantalla.height - ventana.height) / 2))-100);
	}
	
	/**
	 * Modificamos el trackActual y reproducimos la posicion pos
	 * de la playlist.
	 * @param pos posicion de la playlist que pasa a ser reproducida.
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
	 * @param bytesLength el nuevo valor de bytesArchivoActual
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
	 * Cambia el elemento actual por el siguiente en la lista.
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
	 * @return  SongInterfaz
	 * @uml.property  name="infoPlaylist"
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
	 * Nos indica si hay alguna canción en reproducción actualmente.
	 * @return boolean
	 */
	public boolean isPlaying(){
		if(mPlayer != null)return (mPlayer.getStatus() == mPlayer.PLAYING);
		else return false;
	}
	
	/**
	 * Inicia la reproducción
	 */
	private void reproducir(){
		try {
			mPlayer.play();
			reproduciendo = true;
			infoSong.actualiza(listaReproduccion.getCurrent());
			infoPlaylist.marcaActual();
			playButton.setIcon(pauseIcon);	
			
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

	/**
	 * carga las preferencias de la clase GestorPreferencias
	 */
	public void cargarPreferencias(){
		this.setRutaIconos(GestorPreferencias.getInstance().getSkin());
		this.setBgColor(GestorPreferencias.getInstance().getBgColor());
		this.setFgColor(GestorPreferencias.getInstance().getFgColor());	
		this.setBgColorInterno(GestorPreferencias.getInstance().getBgColorInterno());
		this.setFgColorInterno(GestorPreferencias.getInstance().getFgColorInterno());	
		this.setRutaIndexada(GestorPreferencias.getInstance().getRutaIndexada());
		//Reproducir la cancion que se estaba rerpoduciendo al apagar
		if(GestorPreferencias.getInstance().getCancionActual() != null){
			this.setCurrentTrack(GestorPreferencias.getInstance().getCancionActual());
			if(GestorPreferencias.getInstance().getPosCancionActual() > 0){
				try {				
					this.reproducir();
					mPlayer.seek((long) GestorPreferencias.getInstance().getPosCancionActual());
					barraProgreso.setValue((int)GestorPreferencias.getInstance().getPosCancionActual() );
				} catch (BasicPlayerException e) {
					e.printStackTrace();
				}
			}
		}
		//TODO más cosas
		
	}
	
	/**
	 * @param  rutaIndexada
	 * @uml.property  name="rutaIndexada"
	 */
	private void setRutaIndexada(String rutaIndexada) {
		this.rutaIndexada = rutaIndexada;
		//Actualizar la biblioteca con la ruta.
	}

	/**
	 * @param  fgColorInterno2
	 * @uml.property  name="fgColorInterno"
	 */
	private void setFgColorInterno(Color fgColorInterno2) {
		this.fgColorInterno = fgColorInterno2;
		this.getPanel().setColorPrimario(fgColorInterno);
	}

	/**
	 * @param  bgColorInterno2
	 * @uml.property  name="bgColorInterno"
	 */
	private void setBgColorInterno(Color bgColorInterno2) {
		this.bgColorInterno = bgColorInterno2;
		this.getPanel().setColorSecundario(bgColorInterno);

	}

	/**
	 * Carga las imagenes de botones e iconos
	 * @param skin los iconos a cargar
	 */
	private void setRutaIconos(String skin) {
		String ruta = GestorPreferencias.getInstance().getSkin();

		monkeyIcon = new ImageIcon(ruta + "/monkeyIcon.jpg");
		carpetaIcon = new ImageIcon(ruta + "/carpetaIcon.jpg");
		menuIcon = new ImageIcon(ruta + "/monkeyIcon2.png");

		FFIcon = new ImageIcon(ruta + "/ff.png");
		wwIcon = new ImageIcon(ruta + "/ww.png");
		playIcon = new ImageIcon(ruta + "/play.png");
		libreriaIcon = new ImageIcon(ruta + "/libreria.png");
		cerrarIcon = new ImageIcon(ruta + "/cerrar.png");
		pauseIcon = new ImageIcon(ruta + "/pause.png");
		
	}

	/**
	 * Mutadora del color principal de la interfaz.
	 * @param fgColor2  nuevo color principal.
	 * @uml.property  name="fgColor"
	 */
	private void setFgColor(Color fgColor2) {
		this.fgColor = fgColor2;
		//this.setBackground(fgColor2);
		this.getMyBackground().setColorPrimario(fgColor2);
	}

	/**
	 * Mutadora del color secundario de la interfaz.
	 * @param bgColor2  nuevo color secundario.
	 * @uml.property  name="bgColor"
	 */
	private void setBgColor(Color bgColor2) {
		this.bgColor = bgColor2;
		this.getMyBackground().setColorSecundario(bgColor2);
	}
	
	
	public void sincronizaBiblioteca(){
		bibliotecaInterfaz.sincroniza(this.rutaIndexada);
	}
	
	public void salir(){
		GestorBiblioteca.getInstance().setColaReproduccion(listaReproduccion);
		GestorBiblioteca.getInstance().guardarXML();
		GestorPreferencias.getInstance().setCancionActual(listaReproduccion.getCurrent());
		if(mPlayer != null) if(mPlayer.getStatus() == mPlayer.PLAYING){
			GestorPreferencias.getInstance().setPosCancionActual((long) barraProgreso.getValue());
		}else GestorPreferencias.getInstance().setPosCancionActual((long) 0);
		GestorPreferencias.getInstance().guardarXML();
		Launcher.tray.remove(Launcher.trayIcon);
		System.exit(0);
	}
}

