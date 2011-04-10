import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.event.*;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javazoom.jlgui.basicplayer.BasicPlayerException;

import com.sun.awt.AWTUtilities;


public class SongInterfaz extends JFrame{
	
	
	private JList list;
	private Dimension pantalla = null;
	private Dimension ventana = null;
	private JScrollPane scroll=null;
	private BotonAvanzado minButton = null;
	private ImageIcon minIcon1 = new ImageIcon("images/minSongIcon1.jpg");
	private ImageIcon minIcon2 = new ImageIcon("images/minSongIcon3.jpg");
	
	public SongInterfaz(String[] temas){
		
		super("Listado de Canciones");
		pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		ventana = this.getSize();
		this.setSize(200, pantalla.height-200);	
		this.centrarVentana();
		//this.setResizable(false);					//En la otra principal
		this.getContentPane().setLayout(new BorderLayout());
		this.setUndecorated(true);
		this.getContentPane().setBackground(Color.black);
		this.scroll = new JScrollPane();
		this.getContentPane().add(scroll,BorderLayout.EAST);
		this.setEnabled(true);	
		list=getListado(temas);
		this.getContentPane().add(list, BorderLayout.CENTER);
		this.setAlwaysOnTop(true);
		this.minButton = getMinButton();
		this.getContentPane().add(minButton, BorderLayout.SOUTH);
		this.setVisible(true);
		
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
	
	private BotonAvanzado getMinButton(){
		minButton = new BotonAvanzado(minIcon1,minIcon2);
		
		minButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				setExtendedState(JFrame.CROSSHAIR_CURSOR); 
				// TODO
			};
		});
		return minButton;
		
	}
	
	private void centrarVentana() {
        // Se obtienen las dimensiones en pixels de la pantalla.
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        // Se obtienen las dimensiones en pixels de la ventana.
        Dimension ventana = this.getSize();
        // Una cuenta para situar la ventana en el centro de la pantalla.
        this.setLocation((pantalla.width)-200,
                        (pantalla.height - ventana.height) / 2);
	}
	
}
