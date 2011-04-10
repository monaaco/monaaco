import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class SongInterfaz extends JFrame{
	
	
	private JTextField listado = null;
	
	
	public SongInterfaz(){
		
		super("Listado de Canciones");;
		this.setSize(400, 200);	
		this.centrarVentana();
		this.setEnabled(true);						//En la otra principal
		this.setResizable(false);					//En la otra principal
		this.getContentPane().setLayout(new BorderLayout());
		this.setVisible(true);
		this.getContentPane().setBackground(Color.black);
		
		
		
		listado=getListado();
			
		this.getContentPane().add(listado, BorderLayout.CENTER);
		
	}
	private JTextField getListado(){
		listado = new JTextField();
		listado.setSize(50,50);
		listado.setBackground(null);
		return listado;
		
	}
	/*protected void this_windowOpened(WindowEvent e) {
        centrarVentana();
	}*/
	private void centrarVentana() {
        // Se obtienen las dimensiones en pixels de la pantalla.
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        // Se obtienen las dimensiones en pixels de la ventana.
        Dimension ventana = this.getSize();
        // Una cuenta para situar la ventana en el centro de la pantalla.
        this.setLocation((pantalla.width - ventana.width) / 2,
                        (pantalla.height - ventana.height) / 2);
	}
}
