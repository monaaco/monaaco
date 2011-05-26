package IS2011.Interfaz;

import java.awt.*;
import java.util.LinkedList;
import java.util.Iterator;
import javax.swing.*;

import com.sun.awt.AWTUtilities;


public class LoadingScreen extends Thread{
	
	//static Icon icon = new ImageIcon("images/loading.gif");
	private boolean termina = false;
	private int x ;
	private int y ;
	
	
	public LoadingScreen(int x, int y){
		this.x = x;
		this.y= y;
		
	}
	@SuppressWarnings({ "restriction", "deprecation" })
	public synchronized void run()
	   {
			
	      // Aqu� el c�digo pesado que tarda mucho
			JWindow splash = new JWindow();
			 
	        Icon icon = new ImageIcon("images/loading.gif");
	        splash.setSize(icon.getIconWidth(),icon.getIconHeight());
	      
	        JLabel label = new JLabel();
	        label.setIcon(icon);

	        splash.getContentPane().add(label);
	        splash.setSize(icon.getIconWidth(),icon.getIconHeight());
	        splash.setLocation(x-splash.getX()/2-150, y-splash.getY()/2-200);
	        splash.setAlwaysOnTop(true);
	        splash.setVisible(true);
	    	AWTUtilities.setWindowOpaque(splash, false);
	    	while(termina==false){}
	    	splash.setVisible(false);
	    	label=null;
	    	splash.disable();
	   } 
	public void termina(){
		this.termina = true;
		
	}
	
}

