import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.event.*;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.awt.AWTUtilities;


public class SongInterfaz extends JFrame{
	
	
	private JList list;
	private Dimension pantalla = null;
	private Dimension ventana = null;
	private JScrollPane scroll=null;
	
	
	public SongInterfaz(String[] temas){
		
		super("Listado de Canciones");
		pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		ventana = this.getSize();
		this.setSize(300, pantalla.height);	
		this.centrarVentana();
		//this.setResizable(false);					//En la otra principal
		this.getContentPane().setLayout(new BorderLayout());
		this.setVisible(true);
		this.getContentPane().setBackground(Color.black);
		this.scroll = new JScrollPane();
		this.getContentPane().add(scroll,BorderLayout.EAST);
		this.setEnabled(true);	
		
		list=getListado(temas);
			
		this.getContentPane().add(list, BorderLayout.CENTER);
		
	}
	private JList  getListado(String[] temas){
		list = new JList(temas);
		list.setSize(300, pantalla.height);
		list.setBackground(null);
		/*list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				if (e.getSource() == list){
					if (list.getSelectedIndex() == -1)
						btnFire.setEnabled(false);
				}
				
			}
		})*/
		
		return list;
		
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
        this.setLocation((pantalla.width)-300,
                        (pantalla.height - ventana.height) / 2);
	}
	
}
