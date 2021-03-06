package IS2011.Interfaz;

import java.io.File;

import javax.swing.JFileChooser;

import IS2011.FiltrosArchivos.FiltroMP3;
import IS2011.FiltrosArchivos.FiltroOGG;
import IS2011.FiltrosArchivos.FiltroSoportados;

public class HiloCargar extends Thread {
	
	/**
	 * @uml.property  name="main"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private BibliotecaInterfaz main = null;
	/**
	 * @uml.property  name="interfazPadre"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private InterfazAvanzada interfazPadre = null;
	
		
	
	public HiloCargar(BibliotecaInterfaz Bi,InterfazAvanzada interfazPadre){
		
		main = Bi;
		this.interfazPadre =interfazPadre;
		
	}
	@SuppressWarnings("deprecation")
	public synchronized void run(){
			
		 JFileChooser fc = new JFileChooser();
		 fc.setFileFilter(new FiltroMP3());
         fc.setFileFilter(new FiltroOGG());
         fc.setFileFilter(new FiltroSoportados());
         fc.setFileSelectionMode(fc.FILES_AND_DIRECTORIES);
         fc.setMultiSelectionEnabled(true);
       /*  LoadingScreen elHilo = new LoadingScreen(interfazPadre.getX()+interfazPadre.getWidth()/2,
        		 									interfazPadre.getY()+interfazPadre.getHeight()/2);*/
         LoadingScreen elHilo = new LoadingScreen(0,0);
		  if( fc.showOpenDialog(interfazPadre) == JFileChooser.APPROVE_OPTION) {
              /* LoadingScreen ls = new LoadingScreen(interfazPadre);
               ls.setVisible(true);*/
			 
			  elHilo.start();
			  File[] array = fc.getSelectedFiles();
               for(int i=0; i<array.length; i++){
            	   main.getAudioFiles(array[i]);
                }
	      
		  }
		  main.actualiza();
		  elHilo.termina();
	

}
}