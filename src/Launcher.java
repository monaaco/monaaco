import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Launcher extends JFrame {

	
	public Launcher(){}
	
	public static void main(String[] args) {
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
		
		PlayerInterface pi = new PlayerInterface();
		//TODO pasar la biblioteca, opciones y demas a la constructora
		l.setContentPane(pi);
	    l.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}

}
