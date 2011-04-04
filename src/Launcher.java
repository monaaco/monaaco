import javax.swing.JFrame;


public class Launcher extends JFrame {

	
	public Launcher(){}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Launcher l = new Launcher();
		
		//Cargamos la biblioteca
		
		//Cargamos las opciones
		
		PlayerInterface pi = new PlayerInterface();
		//TODO pasar la biblioteca, opciones y demas a la constructora
		l.setContentPane(pi);
	    l.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}

}
