package IS2011.Interfaz;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;


import java.awt.event.*; 


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.UIManager;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import IS2011.GestorXML.GestorXML;
import IS2011.biblioteca.Biblioteca;
import IS2011.biblioteca.GestorBiblioteca;
import IS2011.biblioteca.Track;

import javazoom.jlgui.basicplayer.BasicPlayer;

public class Launcher extends JWindow {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static java.awt.TrayIcon trayIcon; 
	static java.awt.SystemTray tray;  
	
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel imageLabel = new JLabel();
	JPanel southPanel = new JPanel();
	static InterfazAvanzada interfaz;
	
	static ImageIcon menuBarIcon = new ImageIcon("images/barIcon.png");
	static ImageIcon icono = new ImageIcon("images/Icono.png");
	  
	ImageIcon monkeyLoading1 = new ImageIcon("images/Skin3/launcher/monkeyLoading1.png");	
	ImageIcon monkeyLoading2 = new ImageIcon("images/Skin3/launcher/monkeyLoading2.png");	
	ImageIcon monkeyLoading3 = new ImageIcon("images/Skin3/launcher/monkeyLoading3.png");	
	ImageIcon monkeyLoading4 = new ImageIcon("images/Skin3/launcher/monkeyLoading4.png");	
	ImageIcon monkeyLoading5 = new ImageIcon("images/Skin3/launcher/monkeyLoading5.png");	
	ImageIcon monkeyLoading6 = new ImageIcon("images/Skin3/launcher/monkeyLoading6.png");	
	ImageIcon monkeyLoading7 = new ImageIcon("images/Skin3/launcher/monkeyLoading7.png");	
	ImageIcon monkeyLoading8 = new ImageIcon("images/Skin3/launcher/monkeyLoading8.png");

	BasicPlayer cargaInicio;
	
	public Launcher(){
	    try {
	      jbInit();
	    }
	    catch(Exception ex) {
	      ex.printStackTrace();
	    }
	}
	
	
	  void jbInit() throws Exception {
		   cargaInicio = new BasicPlayer();
		   cargaInicio.open(new File("sounds/mic_check.mp3"));
		   cargaInicio.play();
		    imageLabel.setIcon(monkeyLoading1);
		    this.getContentPane().setLayout(borderLayout1);
		    this.getContentPane().add(imageLabel, BorderLayout.CENTER);
		    this.pack();
		    
		    //Carga de la biblioteca
		    GestorBiblioteca.getInstance();
		   
		    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		   Dimension ventana = this.getSize();
		   this.setLocation((pantalla.width - ventana.width) / 2,(pantalla.height - ventana.height) / 2);
		   
		   this.setVisible(true);
		  }
	  
	  void cambiaImagen(ImageIcon im){
		  imageLabel.setIcon(im);
		  this.repaint();
	  }
	  
	  /**
	   * Tray icon
	   */
	  public static void IconTray(){
          /*Se verifica si el sistema soporta los try icons*/
          if (SystemTray.isSupported()) {

              tray = SystemTray.getSystemTray();

              //Se asigna la imagen que del tray icon
              ImageIcon im = menuBarIcon;
                        
              //Este listener permite salir de la aplicacion
              ActionListener salirListener = new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      System.out.println("Cerrando...");
                      System.exit(0);
                  }
              };
              /*creamos un action listener para pasar a la siguiente cancion*/
              ActionListener siguienteListener = new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      interfaz. reproducirSiguiente();
                  }
              };
              /*Creamos un action Listener para volver a la anterior canci�n"
                      */
              ActionListener anteriorListener = new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      interfaz. reproducirAnterior();;
                  }
              };
              /*Creamos un acction listener que se ejecuta cuando le damos
              doble click al try icon*/
              ActionListener abrirListener = new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      trayIconActionPerformed(e);
                  }
              };

              //Aqu� se crea un popup menu
              PopupMenu popup = new PopupMenu();
              //Se agrega la opci�n de salir
              MenuItem salirItem = new MenuItem("Salir");   
              MenuItem siguienteItem = new MenuItem("Siguiente");
              MenuItem anteriorItem = new MenuItem("Anterior");

              //Se le asigna al item del popup el listener para salir de la app
              siguienteItem.addActionListener(siguienteListener);
              salirItem.addActionListener(salirListener); 
              anteriorItem.addActionListener(anteriorListener);

              popup.add(salirItem);    
              popup.add(siguienteItem);   
              popup.add(anteriorItem);  

              /*Aqui creamos una instancia del tray icon y asignamos
              La imagen, el nombre del tray icon y el popup*/
              trayIcon = new TrayIcon(im.getImage(), "Monaaco Player", popup);           

              trayIcon.setImageAutoSize(true);
              trayIcon.addActionListener(abrirListener);

              try {
                  tray.add(trayIcon);

              } catch (AWTException ex) {
                  ex.printStackTrace();
              }

          } else {
              System.err.println("System tray is currently not supported.");
          }
      } 
	   
	    private static void trayIconActionPerformed(java.awt.event.ActionEvent evt){ 
	    	interfaz.setVisible(true); 
	    	interfaz.toFront(); 
	       // tray.remove(trayIcon); 
	    }  
	  
	public static void main(String[] args) throws NullPointerException, IllegalStateException, MalformedURLException, IOException {

		// TODO Auto-generated method stub
		Launcher l = new Launcher();
        
        
        
        
		/*l.setSize(600, 400);
		JPanel p = new JPanel(null);
		p.setSize(600, 400);
		l.add(p);
		JLabel fondo = new JLabel(monkeyLoading1);
		fondo.setBounds(-4,-14,monkeyLoading1.getIconWidth(),monkeyLoading1.getIconHeight());
		l.getLayeredPane().add(fondo,JLayeredPane.FRAME_CONTENT_LAYER);
		l.setVisible(true);
		*/
		int i;
		int a= 0;
		while(a<9){
			i = 0;
			while (i< Integer.MAX_VALUE/16){
				i++;
			}
			if(a==1)
				l.cambiaImagen(l.monkeyLoading2);
			else if(a==2)
				l.cambiaImagen(l.monkeyLoading3);
			else if(a==3)
				l.cambiaImagen(l.monkeyLoading4);
			else if(a==4)
				l.cambiaImagen(l.monkeyLoading5);
			else if(a==5)
				l.cambiaImagen(l.monkeyLoading6);
			else if(a==6)
				l.cambiaImagen(l.monkeyLoading7);
			else if(a==7)
				l.cambiaImagen(l.monkeyLoading8);
			
			a++;
		}
		
		//Cargamos la biblioteca
		
		
		//Cargamos las opciones
		
		
		interfaz = InterfazAvanzada.getSingleton();
		
		interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfaz.setVisible(true);
		
		IconTray();

		
		
		
		//interfaz = new PlayerInterface(); 
		
		//TODO pasar la biblioteca, opciones y demas a la constructora
		
		interfaz.setVisible(true);
		interfaz.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		l.dispose();
	}

}
