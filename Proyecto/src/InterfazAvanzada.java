
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

import javazoom.jlgui.basicplayer.BasicPlayer;


public class InterfazAvanzada extends JFrame{
		
	ImageIcon monkeyIcon = new ImageIcon("images/monkeyIcon.jpg");	
  	ImageIcon carpetaIcon = new ImageIcon("images/carpetaIcon.jpg");		
  	ImageIcon playIcon = new ImageIcon("images/playIcon.jpg");
  	ImageIcon stopIcon = new ImageIcon("images/stopIcon.jpg");
  	ImageIcon pauseIcon = new ImageIcon("images/pauseIcon.jpg");
  	
  	JButton stopButton = null;
  	JButton pauseButton = null;
  	JButton playButton = null;
  	JLabel segundero = null;
  
  	
  	boolean pause;
  	BasicPlayer mPlayer;
	
	 public InterfazAvanzada()
	   {
	      super ("Monaaaaco");        // El título
	      this.getContentPane().setLayout (new GridBagLayout()); // Le ponemos el GridBagLayout
	      this.setSize(400,200);
	      GridBagConstraints constraints = new GridBagConstraints();
	      
	      stopButton = getStopButton();
	      constraints.gridx = 3;
	      constraints.gridy = 1;
	      constraints.gridwidth = 1;
	      constraints.gridheight = 1;
	      this.getContentPane().add (stopButton, constraints);
	      
	      segundero = new JLabel("0:00");
	      constraints.gridx = 2;
	      constraints.gridy = 1;
	      constraints.gridwidth = 1;
	      constraints.gridheight = 1;
	      this.getContentPane().add (segundero, constraints);
	      
	     // pauseButton = getPauseButton(); //JButton pauseButton = new JButton(pauseIcon);
	      constraints.gridx = 1;
	      constraints.gridy = 1;
	      constraints.gridwidth = 1;
	      constraints.gridheight = 1;
	      this.getContentPane().add (pauseButton, constraints);
	      	
	     // playButton = getPlayButton; // new JButton(playIcon);
	      constraints.gridx = 2;
	      constraints.gridy = 0;
	      constraints.gridwidth = 1;
	      constraints.gridheight = 1;
	      this.getContentPane().add (playButton, constraints);
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	JSlider barraProgreso = new JSlider();
	    	constraints.gridx = 2;
	    	constraints.gridy = 5;
	    	constraints.gridwidth = 1;
	    	constraints.gridheight = 3;
	    	//constraints.weighty = 1.0; // La fila 0 debe estirarse, le ponemos un 1.0
	    	this.getContentPane().add (barraProgreso, constraints);
	    	//constraints.weighty = 0.0; // Restauramos al valor por defecto, para no afectar a los siguientes componentes.
	    	
	   }
	    	public JButton getStopButton(){
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
	    	
	    	public void cambiaSegundos(String texto) {
	    		segundero.setText(texto);
	    	}
	     
	  
	

}
