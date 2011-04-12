import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.*;

import javax.swing.event.*;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javazoom.jlgui.basicplayer.BasicPlayerException;

import bibliotecaXML.Track;

import com.sun.awt.AWTUtilities;


public class SongInterfaz extends JFrame{
	
	
	private JFrame principal;
	private JList listado;
	private Dimension pantalla = null;
	private Dimension ventana = null;
	private JScrollPane scroll=null;
	private BotonAvanzado minButton = null;
	private ImageIcon minIcon1 = new ImageIcon("images/skin1/minIcon1.jpg");
	private ImageIcon minIcon2 = new ImageIcon("images/skin1/minIcon2.jpg");
	
	
	public SongInterfaz(String[] temas){
		
		super("Listado de Canciones");
		pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		ventana = this.getSize();
		principal = this;
		this.setSize(200, pantalla.height-200);	
		this.centrarVentana();
		//this.setResizable(false);					//En la otra principal
		this.getContentPane().setLayout(new BorderLayout());
		this.setUndecorated(true);
		this.getContentPane().setBackground(Color.black);
		this.scroll = new JScrollPane();
		this.getContentPane().add(scroll,BorderLayout.EAST);
		this.setEnabled(true);	
		listado=getListado(temas);
		this.getContentPane().add(listado, BorderLayout.CENTER);
		this.setAlwaysOnTop(true);
		this.minButton = getMinButton();
		this.getContentPane().add(minButton, BorderLayout.SOUTH);
		this.setVisible(true);
		
		//JFrame.setDefaultLookAndFeelDecorated(true);
		AWTUtilities.setWindowOpacity(this, (float) 0.3);
		
	}
		
	private JList  getListado(String[] temas){
		listado = new JList(temas);
		listado.setSize(100, pantalla.height);
		listado.setBackground(Color.black);
		listado.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				AWTUtilities.setWindowOpacity(principal, (float)0.9);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				AWTUtilities.setWindowOpacity(principal, (float) 0.3);
			}
		
		});
		
		/*list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				if (e.getSource() == list){
					if (list.getSelectedIndex() == -1)
						btnFire.setEnabled(false);
				}
				
			}
		})*/
				
		return listado;
		
	}
	/*protected void this_windowOpened(WindowEvent e) {
        centrarVentana();
	}*/
	public void actualizaTemas(String[] temas){
		listado.setListData(temas);
				
	}
	
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
