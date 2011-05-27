package IS2011.Interfaz;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.*;
import javax.swing.*;
import javax.xml.bind.Marshaller.Listener;

//import javazoom.jlgui.basicplayer.BasicPlayerException;
import IS2011.Configuracion.GestorPreferencias;
import IS2011.biblioteca.Track;

import com.sun.awt.AWTUtilities;

/**
 * Nos muestra la playlist en la derecha de la pantalla mediante un JFrame transparente
 */
public class SongInterfaz extends JFrame{
	
	
	
	private static final long serialVersionUID = 1L;
	JPopupMenu menu = new JPopupMenu();
	private JFrame principal = null;
	private DragDropList listado = null;
	private JScrollPane scrollListado = null; 
	private JPanelRound panelInterno;
	private Dimension pantalla = null;
	private Dimension ventana = null;
	private JButton minButton = null;
	private JButton sortButton = null;
	private InterfazAvanzada interfazAvanzada = null;	
	
	private ImageIcon minIcon1 = new ImageIcon("images/skin1/minIcon1.jpg");
	private ImageIcon minIcon2 = new ImageIcon("images/skin1/minIcon2.jpg");
	
	private Color fgColor = new Color(240,240,240);
	private Color bgColor = Color.black;
	
	@SuppressWarnings("restriction")
	public SongInterfaz(String[] temas, InterfazAvanzada interfazAvanzada){
		
		super("Listado de Canciones");
		//this.getContentPane().setLayout(new BorderLayout());
		this.interfazAvanzada = interfazAvanzada;
		pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		ventana = this.getSize();
		principal = this;
		
	
		this.setSize(200, pantalla.height-200);	
		this.centrarVentana();
		this.setResizable(false);					//En la otra principal
		this.setUndecorated(true);
		this.setEnabled(true);	
		this.setAlwaysOnTop(true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/Icono.png")); 

		
		
		minButton = getMinButton();
		minButton.setBounds(105,pantalla.height-225,90,20);
		sortButton = getSortButton();
		sortButton.setBounds(5,pantalla.height-225,90,20);
		
		getPanelInterno(temas).setBounds(0,0,195,pantalla.height-245);

		getListado().setSize(195, pantalla.height-245);

		this.getContentPane().add(minButton);
		this.getContentPane().add(sortButton);
		this.getContentPane().add(getScrollListado(getPanelInterno(temas)));
		this.setVisible(true);
		
	
	
		//JFrame.setDefaultLookAndFeelDecorated(true);
		AWTUtilities.setWindowOpacity(this, (float) 0.3);
		AWTUtilities.setWindowOpaque(this,false);
		
		cargarPreferencias();
		
		
	}
	
	/**
	 * carga las preferencias de la clase GestorPreferencias
	 */
	public void cargarPreferencias(){
		this.setBackground(GestorPreferencias.getInstance().getBgColor());
		this.setForeground(GestorPreferencias.getInstance().getFgColor());
		this.setRutaIconos(GestorPreferencias.getInstance().getSkin());
		//TODO más cosas
	}

	
	/**
	 * Carga las imagenes de botones e iconos
	 * @param skin
	 */
	private void setRutaIconos(String skin) {
		
		String ruta = GestorPreferencias.getInstance().getSkin();

		minIcon1 = new ImageIcon(ruta + "/minIcon1.jpg");
		minIcon2 = new ImageIcon(ruta + "/minIcon2.jpg");
		
	}
	
	
	/**
	 * Crea el panelInterno con la lista temas dentro
	 * @param temas
	 * @return JPanel
	 */
	private JPanel getPanelInterno(String[] temas) {
		if( panelInterno == null ){
			panelInterno = new JPanelRound();
			panelInterno.setLayout(null);
			panelInterno.setColorPrimario(Color.black);
			panelInterno.setColorSecundario(Color.black);
			panelInterno.add(getListado(temas));
			//panelInterno.add(listado);
			
			panelInterno.setSize(190, pantalla.height);
		}
		return panelInterno;
	}

	public InterfazAvanzada getInterfaz(){
		return interfazAvanzada;
	}
	
	public  JFrame getPrincipal(){
		return principal;
	}
	
	public void setScrollListado(JScrollPane scrollListado) {
		this.scrollListado = scrollListado;
	}

	/**
	 * Accesora del scrollPanel 
	 * @return scrollPanel con el JComponent comp dentro
	 */
	public JScrollPane getScrollListado(JComponent comp) {
		if( scrollListado == null ){
			scrollListado = new JScrollPane(comp);	
			scrollListado.setAutoscrolls(true);
			scrollListado.setHorizontalScrollBar(null);
			scrollListado.setEnabled(true);
			scrollListado.setVisible(true);
			scrollListado.setBackground(bgColor);
			scrollListado.setForeground(fgColor);
			scrollListado.getVerticalScrollBar().addMouseListener(new java.awt.event.MouseAdapter() {
				@SuppressWarnings("restriction")
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					AWTUtilities.setWindowOpacity(principal, (float)0.9);
				}
				@SuppressWarnings("restriction")
				public void mouseExited(java.awt.event.MouseEvent evt) {
					AWTUtilities.setWindowOpacity(principal, (float) 0.3);
				}
			});
			
			scrollListado.addMouseListener(new java.awt.event.MouseAdapter() {
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
		return scrollListado;
	}

	/**
	 * Accesora Jlist listado. 
	 * Si no exite el listado genera uno vacío
	 * @return listado
	 */
	public  DragDropList getListado(){
		if( listado == null ){
			String[] temas = {};
			listado = new DragDropList(temas, interfazAvanzada.getListaReproduccion());
			listado.setLayout(new BorderLayout());
			listado.setSize(100, pantalla.height);
			listado.setFont(new java.awt.Font("Helvetica", 1, 12));
			listado.setSelectionBackground(fgColor);
			listado.setSelectionForeground(bgColor);
			listado.setBackground(bgColor);
			listado.setForeground(fgColor);
			listado.setEnabled(true);
			listado.setVisible(true);
			listado.setBounds(5,5,195,pantalla.height-230);
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
	                        marcaActual();
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
						marcaActual();
						break;
					case 10:	// ENTER
						if(listado.getSelectedIndices().length == 1){
							int pos = listado.getSelectedIndex();
							interfazAvanzada.setTrackNumber(pos);
							marcaActual();
						}
						break;
					}
				}
			});
		}
		return listado;
	}
	
	/**
	 * Accesora Jlist listado. 
	 * Si no exite el listado genera uno con la info del array temas
	 * Si existe actualiza el listado con la información del array temas
	 * @return listado
	 */
	public  DragDropList getListado(String[] temas){
		if( listado == null ){
			getListado();
		}
		setListado(temas);
		return listado;
	}

	/**
	 * Pone en mayuscula el track actual
	 * 
	 * 
	 *
	 */
	public void marcaActual(){
		if(interfazAvanzada.isPlaying()){
			String[] lista = interfazAvanzada.getPlaylist().getListado();
			lista[interfazAvanzada.getPlaylist().getCurrentTrack()] = lista[interfazAvanzada.getPlaylist().getCurrentTrack()].toUpperCase();
			listado.removeAll();
			
			listado.setSelectedIndex(interfazAvanzada.getPlaylist().getCurrentTrack());
			//listado.getComponent(interfazAvanzada.getPlaylist().getCurrentTrack()).setBackground(Color.red); //TODO
			//listado.setSelectedIndex( interfazAvanzada.getPlaylist().getCurrentTrack());
			repaint();
			setListado(lista);
		
			
		}
	}
	
	/**
	 * 
	 * @param temas (array con los nombres de las canciones)
	 * @return lista
	 */
	private void  setListado(String[] temas){
		getListado().setListData(temas);		
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
		getListado().setListData(temas);	
		getListado().repaint();
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
	 * @return botón
	 */
	private JButton getSortButton(){
		sortButton = new JButton("desordenar");
		sortButton.setSize(90,20);
	//	minButton.setBackground(Color.black);
		getPopUp();
		sortButton.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent evt) {
		        //if (evt.isPopupTrigger()) {
		        //   menu.show(evt.getComponent(), evt.getX(), evt.getY());
		        //}
		    	int ct = 0;
		    	if( sortButton.getText().equals("desordenar") ){
		    		ct = interfazAvanzada.getPlaylist().desordenar();
		    		sortButton.setText("por Artista");
		    	}else if( sortButton.getText().equals("por Artista") ){
		    		ct = interfazAvanzada.getPlaylist().ordenarPorArtista();
		    		sortButton.setText("desordenar");
		    	}
		    	//actualizar el listado de canciones:
	    		getListado().setSelectedIndex(ct);
				String[] lista = interfazAvanzada.getPlaylist().getListado();
		    	actualizaTemas(interfazAvanzada.getPlaylist().getListado());
		    	marcaActual();

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
	
	/**
	 * Coloca el frame centrado enel borde derecho de la pantalla
	 */
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
