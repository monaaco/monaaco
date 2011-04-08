
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Launcher extends JFrame {

	
	public Launcher(){}
	
	public static void main(String[] args) {
		
		
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
		ImageIcon monkeyLoading1 = new ImageIcon("images/monkeyLoading1.png");	
		ImageIcon monkeyLoading2 = new ImageIcon("images/monkeyLoading2.png");	
		ImageIcon monkeyLoading3 = new ImageIcon("images/monkeyLoading3.png");	
		ImageIcon monkeyLoading4 = new ImageIcon("images/monkeyLoading4.png");	
		ImageIcon monkeyLoading5 = new ImageIcon("images/monkeyLoading5.png");	
		ImageIcon monkeyLoading6 = new ImageIcon("images/monkeyLoading6.png");	
		ImageIcon monkeyLoading7 = new ImageIcon("images/monkeyLoading7.png");	
		ImageIcon monkeyLoading8 = new ImageIcon("images/monkeyLoading8.png");
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
