import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.UIManager;

import javazoom.jlgui.basicplayer.BasicPlayer;

public class Launcher extends JWindow {

	  BorderLayout borderLayout1 = new BorderLayout();
	  JLabel imageLabel = new JLabel();
	  JPanel southPanel = new JPanel();
	  
	ImageIcon monkeyLoading1 = new ImageIcon("images/launcher/monkeyLoading1.png");	
	ImageIcon monkeyLoading2 = new ImageIcon("images/launcher/monkeyLoading2.png");	
	ImageIcon monkeyLoading3 = new ImageIcon("images/launcher/monkeyLoading3.png");	
	ImageIcon monkeyLoading4 = new ImageIcon("images/launcher/monkeyLoading4.png");	
	ImageIcon monkeyLoading5 = new ImageIcon("images/launcher/monkeyLoading5.png");	
	ImageIcon monkeyLoading6 = new ImageIcon("images/launcher/monkeyLoading6.png");	
	ImageIcon monkeyLoading7 = new ImageIcon("images/launcher/monkeyLoading7.png");	
	ImageIcon monkeyLoading8 = new ImageIcon("images/launcher/monkeyLoading8.png");

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
		   Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		   Dimension ventana = this.getSize();
		   this.setLocation((pantalla.width - ventana.width) / 2,(pantalla.height - ventana.height) / 2);
		   
		   this.setVisible(true);
		  }
	  
	  void cambiaImagen(ImageIcon im){
		  imageLabel.setIcon(im);
		  this.repaint();
	  }
	  
	public static void main(String[] args) throws NullPointerException, IllegalStateException, MalformedURLException, IOException {
		
		
		try
		{
		   //Correcion hecha por Chuster Boy ;)
			
		   UIManager.setLookAndFeel("net.sourceforge.napkinlaf.NapkinLookAndFeel");

		}
		catch (Exception e)
		{
		   e.printStackTrace();
		}

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
			while (i< Integer.MAX_VALUE/10){
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
		
		JFrame interfaz;
		interfaz = new InterfazAvanzada();
		
		//interfaz = new PlayerInterface(); 
		
		//TODO pasar la biblioteca, opciones y demas a la constructora
		
		interfaz.setVisible(true);
		interfaz.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		l.dispose();
	}

}
