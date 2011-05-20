package IS2011.Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;


import IS2011.FiltrosArchivos.*;
import IS2011.biblioteca.GestorBiblioteca;
import IS2011.biblioteca.Playlist;
import IS2011.biblioteca.Track;

public class BibliotecaInterfaz extends JPanelRound{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private BibliotecaInterfaz frame= null;
	
	private InterfazAvanzada interfazPadre= null;
	private JMenuBar menuBI=null;
	
	private JPopupMenu menu = null;
	private JMenuItem anadirArchivos=null;
	private JMenuItem editarPropiedades=null;
	private JMenuItem filtroAvanzado=null;
	private JMenuItem elimina= null;
	private JMenuItem anadirAPlayList=null;
	private JTextField busquedaRapida= null;
	JFrame popup = null;
	private ZebraJTable tabla= null;
	
	
	private TableRowSorter<TableModel> elQueOrdena=null; 
	private MyDefaultTableModel modelo= null;
	private String escrito;
	private long mili;
	
	public BibliotecaInterfaz(InterfazAvanzada ia){
		//super("library");
		interfazPadre = ia;
		initBibliotecaInterfaz();
		frame = this;
		escrito = "";
		mili = System.currentTimeMillis();
		//this.setTran(0.5f);
	}

	public void initBibliotecaInterfaz()
	{
		this.setSize(600, 300);
		this.setLayout(new BorderLayout());
		JMenuBar JMenuAux = getBarraMenu();
		//JMenuAux.setBounds(5, 10, 590, 10)
	
		this.add(JMenuAux,BorderLayout.SOUTH);
		
		
		
		// Instanciamos nuestro modelo de datos, por ejemplo, DefaultTableModel
		// y lo metemos en el JTable
		modelo = new MyDefaultTableModel();
		modelo.addColumn("Titulo");
		modelo.addColumn("Artista");
		modelo.addColumn("Album");
		modelo.addColumn("Tiempo total (seg)");
		modelo.addColumn("Genero");
	
		
		tabla = new ZebraJTable(modelo);
		/*tabla.setBackground( Color.darkGray );
		tabla.setForeground( Color.white );
		tabla.setSelectionBackground( Color.yellow );
		tabla.setSelectionForeground( Color.black );*/
		tabla.setSize(500,250);
		
		getPopUp();

		// Instanciamos el TableRowSorter y lo añadimos al JTable
		elQueOrdena = new TableRowSorter<TableModel>(modelo);
		tabla.setRowSorter(elQueOrdena);
		
		actualiza();
			
		tabla.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e) 
		      {
		    	if(e.getClickCount()==2){
		    		int[] fila = new int[1];
		    		fila[0] = tabla.rowAtPoint(e.getPoint());
		    		meterEnPlayList(fila);
		    		repaint();
		    		//TODO CODIGO MOVIDO A meterEnPlayList
		    	}
		      }
		   });
		
		tabla.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent evt) {
		        if (evt.isPopupTrigger()) {
		            menu.show(evt.getComponent(), evt.getX(), evt.getY());
		        }
		    }
		    public void mouseReleased(MouseEvent evt) {
		        if (evt.isPopupTrigger()) {
		            menu.show(evt.getComponent(), evt.getX(), evt.getY());
		            
		        }
		    }
		});
		tabla.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyReleased(KeyEvent k){
				int c = k.getKeyCode();
				if(((65 <= c) && (c <= 90)) || c == 32){ 
						//Si es una letra o un espacio
					acumulaLetra(k.getKeyChar());
				}
				else{	//Si no...
					escrito = "";
					switch(c){	// Elegimos las posibles teclas
					case 127:	// suprimir
						borraElemBiblioteca();
						break;
					case 10:	// ENTER
						meterEnPlayList(tabla.getSelectedRows());
					}
				}
			}
		});

		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setSize(500,250);
		this.add(scroll,BorderLayout.CENTER);
		
		busquedaRapida = new JTextField("");
		busquedaRapida.setSize(100,10);
		Document d = busquedaRapida.getDocument();
		d.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				filtraRapido();
				repaint();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				filtraRapido();
				repaint();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				filtraRapido();
				repaint();
			}
		});
		menuBI.add(busquedaRapida);
	}
	/*
	 * Crea y define los listener del menu PopUp que borra y agrega canciones
	 */
	private JPopupMenu getPopUp(){
		
		menu = new JPopupMenu();
		
		menu.setBorderPainted(false);
		
		JMenuItem eliminar = new JMenuItem("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			 
	            public void actionPerformed(ActionEvent e)
	            {
	            	 borraElemBiblioteca();
		    		
	            }
	        });

		
		menu.add(eliminar);
		JMenuItem Agregar = new JMenuItem("Agregar");
		Agregar.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	int[] seleccionadas = tabla.getSelectedRows();
	    		meterEnPlayList(seleccionadas);
	    		
            }
        });
		menu.add(Agregar);
		return menu;
		
	}
	private JMenuBar getBarraMenu() {
		
		
		
		// TODO más elementos ¿e iconos?
		if (menuBI == null) {
			menuBI = new JMenuBar();
			menuBI.setBorderPainted(false);
			
			JMenu menuArchivo = new JMenu("Añadir archivo");
			menuArchivo.add(getAnadirArchivos());
			menuArchivo.add(getAnadirAPlayList());
			JMenu menuFiltro = new JMenu("Filtro");
			menuFiltro.add(getFiltroAvanzado());
			JMenu menuPropiedades = new JMenu("Propiedades");
			menuPropiedades.add(getEditarPropiedades());
			JMenu menuEliminar = new JMenu("Eliminar");
			menuEliminar.add(getElimina());
			menuBI.add(menuArchivo);
			menuBI.add(menuFiltro);
			menuBI.add(menuPropiedades);
			menuBI.add(menuEliminar);
		}
		return menuBI;
	}

	
	private JMenuItem getAnadirAPlayList() {
		if (anadirAPlayList == null) {
			anadirAPlayList = new JMenuItem("Añadir seleccionados a la PlayList");
			anadirAPlayList.addMouseListener(new java.awt.event.MouseAdapter() {
				public  void mouseReleased(java.awt.event.MouseEvent evt) {		
						int[] seleccionadas = tabla.getSelectedRows();
						if(seleccionadas.length != 0)
						{
							int fila;
							Track aux;
							Playlist listaRepr = interfazPadre.getListaReproduccion();
							for(int i= 0; i<seleccionadas.length ;i++)
							{
								fila = seleccionadas[i];
								fila = tabla.convertRowIndexToModel (fila);
								aux = GestorBiblioteca.getInstance().getArrayList().get(fila);
					        	listaRepr.add(aux.getLocation());
							}
							String[] temas = listaRepr.getListado();
		                    interfazPadre.getInfoPlaylist().actualizaTemas(temas);
		                    repaint();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Selecciona las filas para pasar a la PlayList");
						}
                    }
				});
		}
		return anadirAPlayList;
	}

	private JMenuItem getElimina() {
		if (elimina == null) {
			elimina = new JMenuItem("Eliminar seleccionados de la biblioteca");
			elimina.addMouseListener(new java.awt.event.MouseAdapter() {
				public  void mouseReleased(java.awt.event.MouseEvent evt) {		
						borraElemBiblioteca();
						repaint();
						//He movido el codigo a este metodo para no duplicarlo con las key
                    }
				});
		}
		return elimina;
	}

	private JMenuItem getAnadirArchivos() {
		if (anadirArchivos == null) {
			anadirArchivos = new JMenuItem("Añadir archivos a la biblioteca");
			anadirArchivos.addMouseListener(new java.awt.event.MouseAdapter() {
				public  void mouseReleased(java.awt.event.MouseEvent evt) {
					JFileChooser fc = new JFileChooser();
                    fc.setFileFilter(new FiltroMP3());
                    fc.setFileFilter(new FiltroOGG());
                    fc.setFileFilter(new FiltroSoportados());
                    fc.setFileSelectionMode(fc.FILES_AND_DIRECTORIES);
                    fc.setMultiSelectionEnabled(true);
                    if(fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    	File[] array = fc.getSelectedFiles();
                    	
                    	for(int i=0; i<array.length; i++){
                    		getAudioFiles(array[i]);
                    	}
                    
                    	actualiza();
                    	busquedaRapida.setText("");
                    	repaint();
                    }
				};
			});
		}
		return anadirArchivos;
	}
	
	
	private void getAudioFiles(File file){
		if(file.isDirectory()){
			File[] array = file.listFiles();
			int n = array.length;
			int i;
			for(i = 0; i < n; i++){
				getAudioFiles(array[i]);
			}
		}
		else if(file.getName().toLowerCase().endsWith(".mp3") || file.getName().toLowerCase().endsWith(".ogg") || file.getName().toLowerCase().endsWith(".wav"))
		{
			HashMap<String,Boolean> hash = GestorBiblioteca.getInstance().getHashMap();
			if(!hash.containsKey(file.getAbsolutePath()))
			{
				Track aux = new Track(file.getAbsolutePath());
				GestorBiblioteca.getInstance().add(aux);
				hash.put(file.getAbsolutePath(), Boolean.TRUE);
			}
		}
	}
	
	public JMenuItem getEditarPropiedades(){
		if(editarPropiedades==null){
			editarPropiedades= new JMenuItem("Editar propiedades");
			editarPropiedades.addMouseListener(new java.awt.event.MouseAdapter() {
				public  void mouseReleased(java.awt.event.MouseEvent evt) {
					//TODO Mostrar el PropiedadesTrack	
					int[] seleccionadas = tabla.getSelectedRows();
					int fila;
					if(seleccionadas.length > 0){
						for(int i =0;i< seleccionadas.length ;i++){
							fila = seleccionadas[i];
							fila = tabla.convertRowIndexToModel (fila);
							Track track = GestorBiblioteca.getInstance().getArrayList().get(fila);
							PropiedadesTrack pt = new PropiedadesTrack(interfazPadre, true ,track);
							pt.setVisible(true);
						}
						actualiza();
						repaint();
					}
				}
			});
		}
		return editarPropiedades;
	}

	public JMenuItem getFiltroAvanzado(){
		if(filtroAvanzado==null){
			filtroAvanzado= new JMenuItem("Busqueda avanzada");
			filtroAvanzado.addMouseListener(new java.awt.event.MouseAdapter() {
				public  void mouseReleased(java.awt.event.MouseEvent evt) {
					//TODO Mostrar un panel con los campos a buscar		
					FiltroDialog fd = new FiltroDialog(interfazPadre, true,elQueOrdena);
					fd.setVisible(true);
					repaint();
				}
			});
		}
		return filtroAvanzado;
	}
	
	public void actualiza(){
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		/*1º Borrar todo lo que tenga la tabla*/
		elQueOrdena.setRowFilter(javax.swing.RowFilter.regexFilter(""));
		
		for(int i= modelo.getRowCount() - 1; i>=0 ;i--){
			 modelo.removeRow(i);
		}
		
		
		/*2º Meter todo en la tabla*/
		Iterator<Track> it = GestorBiblioteca.getInstance().getArrayList().iterator();
		Track aux= null;
		while (it.hasNext())
		{
			aux = (Track)it.next();
			((DefaultTableModel) modelo).addRow(new Object[] { aux.getName(), aux.getArtist(), aux.getAlbum(), aux.getTotalTime(), aux.getGenre()});
		}
		
		repaint();
	}
	
	public void filtraRapido(){
		elQueOrdena.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)"+busquedaRapida.getText()));
		repaint();
	}
	
	public void meterEnPlayList(int[] fila){
        for(int i = 0;i<=fila.length-1;i++){
			if ((fila[i] > -1))
	        {
		       	fila[i] = tabla.convertRowIndexToModel(fila[i]);
		       	Track aux = GestorBiblioteca.getInstance().getArrayList().get(fila[i]);
		       	Playlist listaRepr = interfazPadre.getListaReproduccion();
		       	listaRepr.add(aux.getLocation());
		       	//interfazPadre.setCurrentTrack(listaRepr.current());
		        String[] temas = listaRepr.getListado();
		        interfazPadre.getInfoPlaylist().actualizaTemas(temas);
		       	//pasamos de la fila recibida a la fila sin filtrar
		       	//Anade a la listaReproduccion
		        interfazPadre.getInfoPlaylist().marcaActual();
		        repaint();
	        }
        }
	}
	
	public void borraElemBiblioteca(){
		int[] seleccionadas = tabla.getSelectedRows();
		if(seleccionadas.length != 0)
		{
			//Nos devuelve un vecotor con las filas a borrar ordenadas
			
			//Lo pasamos al global para evitar el filtro y lo borramos
			// en el mismo paso gracias al orden
			int fila;
			for(int i= seleccionadas.length - 1; i>=0 ;i--)
			{
				fila = seleccionadas[i];
				fila = tabla.convertRowIndexToModel (fila);
				GestorBiblioteca.getInstance().getArrayList().remove(fila);
			}
        	actualiza();
        	busquedaRapida.setText("");
        	repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "Selecciona las filas antes de borrar.");
		}
	}
	
	public void acumulaLetra(char c){
		if(System.currentTimeMillis() - mili > 1000){
			escrito = "";
		}
		mili = System.currentTimeMillis();
		escrito += c;
		buscaNombre(escrito);
		System.out.println(escrito);
	}
	
	public void buscaNombre(String nombre){
		Iterator<Track> it = GestorBiblioteca.getInstance().getArrayList().iterator();
		Track tr;
		int i = 0;
		while(it.hasNext()){
			tr = (Track)it.next();
			if(tr.getAlbum().toLowerCase().contains(nombre.toLowerCase()) || tr.getArtist().toLowerCase().contains(nombre.toLowerCase()) ||
			tr.getName().toLowerCase().contains(nombre.toLowerCase()) || tr.getGenre().toLowerCase().contains(nombre.toLowerCase())){
				tabla.setRowSelectionInterval(i, i);
				return;
			}
			i++;
		}
		repaint();
	}
}
