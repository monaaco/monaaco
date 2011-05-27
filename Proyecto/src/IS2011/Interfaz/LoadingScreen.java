package IS2011.Interfaz;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

import com.sun.awt.AWTUtilities;


public class LoadingScreen extends Thread{
	
	//static Icon icon = new ImageIcon("images/loading.gif");
	/**
	 * @uml.property  name="termina"
	 */
	private boolean termina = false;
	/**
	 * @uml.property  name="x"
	 */
	private int x ;
	/**
	 * @uml.property  name="y"
	 */
	private int y ;
	
	
	public LoadingScreen(int x, int y){
		this.x = x;
		this.y= y;
		
	}
	@SuppressWarnings({ "restriction", "deprecation" })
	public synchronized void run()
	   {
			
	      // Aquí el código pesado que tarda mucho
			JWindow splash = new JWindow();
			 
	        Icon icon = new ImageIcon("images/loading.gif");
	      
	        splash.setSize(icon.getIconWidth(),icon.getIconHeight());
	      
	        JLabel label = new JLabel();
	        label.setIcon(icon);

	        splash.getContentPane().add(label);
	        splash.setSize(icon.getIconWidth(),icon.getIconHeight());
	      //  splash.setLocation(x-splash.getX()/2-150, y-splash.getY()/2-200);
	        splash.setLocation(0,0);
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

