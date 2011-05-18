package IS2011.Interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.*;
import javax.swing.*;

//import javazoom.jlgui.basicplayer.BasicPlayerException;
import IS2011.bibliotecaXML.Track;

import com.sun.awt.AWTUtilities;

/**
 * Nos muestra la playlist en la derecha de la pantalla mediante un JFrame transparente
 */
public class SongInterfaz extends JFrame{
	
	
	
	private static final long serialVersionUID = 1L;
	JPopupMenu menu = new JPopupMenu();
	private JFrame principal;
	private JList listado;
	private JPanelRound panelInterno;
	private Dimension pantalla = null;
	private Dimension ventana = null;
	private Scrollbar scroll=null;
	private JButton minButton = null;
	private JButton sortButton = null;
	private ImageIcon minIcon1 = new ImageIcon("images/skin1/minIcon1.jpg");
	private ImageIcon minIcon2 = new ImageIcon("images/skin1/minIcon2.jpg");
	private InterfazAvanzada interfazAvanzada= null;
	private Color c= new Color(240,240,240);
	
	@SuppressWarnings("restriction")
	public SongInterfaz(String[] temas, InterfazAvanzada interfazAvanzada){
		
		super("Listado de Canciones");
		//this.getContentPane().setLayout(new BorderLayout());
		this.interfazAvanzada = interfazAvanzada;
		pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		ventana = this.getSize();
		principal = this;
		panelInterno = new JPanelRound();
		panelInterno.setLayout(new BorderLayout());
		
		this.setSize(200, pantalla.height-200);	
		this.centrarVentana();
		this.setResizable(false);					//En la otra principal
		this.setUndecorated(true);
		this.setBackground(Color.black);
		
		this.setEnabled(true);	
		//scroll = new Scrollbar();
		//panelInterno.add(scroll,BorderLayout.EAST);
		
		listado = getListado(temas);
		//listado.add(scroll,BorderLayout.EAST);
		listado.setSize(195, pantalla.height-245);
		

		panelInterno.add(listado,BorderLayout.CENTER);
		panelInterno.add(listado);
		this.setAlwaysOnTop(true);
		
		panelInterno.setSize(190, pantalla.height-245);	
		panelInterno.setBounds(0,0,195,pantalla.height-245);
		minButton = getMinButton();
		minButton.setBounds(105,pantalla.height-225,90,20);
		sortButton = getSortButton();
		sortButton.setBounds(5,pantalla.height-225,90,20);
		
		this.getContentPane().add(minButton);
		this.getContentPane().add(sortButton);
		this.getContentPane().add(panelInterno);
		this.setVisible(true);
		
	
	
		//JFrame.setDefaultLookAndFeelDecorated(true);
		AWTUtilities.setWindowOpacity(this, (float) 0.3);
		
		principal.addMouseListener(new java.awt.event.MouseAdapter() {
			@SuppressWarnings("restriction")
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				AWTUtilities.setWindowOpacity(principal, (float)0.9);
			}
			@SuppressWarnings("restriction")
			public void mouseExited(java.awt.event.MouseEvent evt) {
				AWTUtilities.setWindowOpacity(principal, (float) 0.3);
			}
		
		});
		
	}
	
	public InterfazAvanzada getInterfaz(){
		return interfazAvanzada;
	}
	
	public  JFrame getPrincipal(){
		return principal;
	}
	
	public  JList getListado(){
		return listado;
	}

	private JList  getListado(String[] temas){
		
		listado = new JList(temas);
		listado.setLayout(new BorderLayout());
		listado.setSize(100, pantalla.height);
		listado.setBackground(Color.black);
		listado.setFont(new java.awt.Font("Helvetica", 1, 12));

		listado.setForeground(c);
		listado.addMouseListener(new java.awt.event.MouseAdapter() {
			@SuppressWarnings("restriction")
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				AWTUtilities.setWindowOpacity(principal, (float)0.9);
			}
			@SuppressWarnings("restriction")
			public void mouseExited(java.awt.event.MouseEvent evt) {
				AWTUtilities.setWindowOpacity(principal, (float) 0.3);
			}
		
		});
		 
		/*listado.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		        if (evt.getValueIsAdjusting())
		        	 interfazAvanzada.setTrackNumber(listado.getSelectedIndex());
		      }
		});*/
		
		listado.addMouseListener(new java.awt.event.MouseAdapter(){ 
			public void mouseClicked(MouseEvent e) 
		    {
				if (e.getClickCount() == 2)
		        {
					if(listado.getMaximumSize().getHeight() >= e.getPoint().getY()){
                        interfazAvanzada.setTrackNumber(listado.locationToIndex(e.getPoint()));
                    }
		        }
			}
		});

		listado.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyReleased(KeyEvent k){
				int c = k.getKeyCode();
				switch(c){	// Elegimos las posibles teclas
				case 127:	// suprimir
					borrarSeleccionados();
					break;
				case 10:	// ENTER
					if(listado.getSelectedIndices().length == 1){
						int pos = listado.getSelectedIndex();
						interfazAvanzada.setTrackNumber(pos);
					}
					break;
				}
			}
		});
		return listado;
	}
	/*protected void this_windowOpened(WindowEvent e) {
        centrarVentana();
	}*/
	
	/**
	 * 
	 * Borramos los elementos de la lista
	 */
	public void borrarSeleccionados(){
		int[] listaIndices = listado.getSelectedIndices();
		boolean b = interfazAvanzada.getListaReproduccion().borraTrack(listaIndices);
		if(b){
			interfazAvanzada.borradoElemActualPlaylist();
		}
		actualizaTemas(interfazAvanzada.getListaReproduccion().getListado());
	}
	
	public void actualizaTemas(String[] temas){
		listado.setListData(temas);		
	}
	
	private JButton getMinButton(){
		minButton = new JButton("min");
		minButton.setSize(90,20);
	//	minButton.setBackground(Color.black);
		minButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				setExtendedState(JFrame.CROSSHAIR_CURSOR); 
				// TODO
			};
		});
		return minButton;
		
	}
	/**
	 * Método que nos proporcioan un PopUpMenu para elegir que tipo de ordenación queremos,
	 * pulsar boton izquierdo y sin soltarlo elegir la opción, modificar el listener si se quiere
	 * dejar permanente hasta elgir uan opción.
	 * @return
	 */
	private JButton getSortButton(){
		sortButton = new JButton("ord");
		sortButton.setSize(90,20);
	//	minButton.setBackground(Color.black);
		getPopUp();
		sortButton.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent evt) {
		       // if (evt.isPopupTrigger()) {
		            menu.show(evt.getComponent(), evt.getX(), evt.getY());
		        //}
		    }
		  /*  public void mouseReleased(MouseEvent evt) {
		        if (evt.isPopupTrigger()) {
		            menu.show(evt.getComponent(), evt.getX(), evt.getY());
		        }
		    }*/
		});
		return sortButton;
		
	}
	/**
	 * Nos devuelve el menu PopUp del Boton sortButton
	 * @return
	 */
	private JPopupMenu getPopUp(){
		
		menu = new JPopupMenu();
		menu.setBackground(Color.black);
		menu.setBorderPainted(false);
		
		JMenuItem album = new JMenuItem("Album");
		album.setBackground(Color.black);
		/*album.addActionListener(new ActionListener() {
		 
        public void actionPerformed(ActionEvent e)
        {
        	
    		
        }
    });*/
		menu.add(album);
		JMenuItem artista = new JMenuItem("Autor");
		artista.setBackground(Color.black);
		/*artista/*album.addActionListener(new ActionListener() {
		 
        public void actionPerformed(ActionEvent e)
        {
        	
    		
        }
    });*/
		menu.add(artista);
		return menu;
		
	}
	private void centrarVentana() {
        // Se obtienen las dimensiones en pixels de la pantalla.
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        // Se obtienen las dimensiones en pixels de la ventana.
        Dimension ventana = principal.getSize();
        // Una cuenta para situar la ventana en el centro de la pantalla.
        principal.setLocation((pantalla.width)-200,
                        (pantalla.height - ventana.height) / 2);
	}
	
}
