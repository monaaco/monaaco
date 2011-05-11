package IS2011.Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
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

import com.sun.imageio.plugins.png.RowFilter;

import IS2011.FiltrosArchivos.FiltroMP3;
import IS2011.FiltrosArchivos.FiltroOGG;
import IS2011.FiltrosArchivos.FiltroSoportados;
import IS2011.bibliotecaXML.GestorXML;
import IS2011.bibliotecaXML.Playlist;
import IS2011.bibliotecaXML.Track;

public class BibliotecaInterfaz extends JPanelRound{

	private BibliotecaInterfaz frame= null;
	
	private InterfazAvanzada interfazPadre= null;
	private JMenuBar menuBI=null;
	
	private JMenuItem anadirArchivos=null;
	private JMenuItem editarPropiedades=null;
	private JMenuItem filtroAvanzado=null;
	private JMenuItem elimina= null;
	private JMenuItem anadirAPlayList=null;
	private JTextField busquedaRapida= null;
	JFrame popup = null;
	private JTable tabla= null;
	private GestorXML biblioteca=null; //Sino es un ArrayList es la propia biblioteca.
	private TableRowSorter<TableModel> elQueOrdena=null; 
	private MyDefaultTableModel modelo= null;
	
	public BibliotecaInterfaz(GestorXML library, InterfazAvanzada ia){
		//super("Biblioteca");
		interfazPadre = ia;
		biblioteca = library;
		initBibliotecaInterfaz();
		frame = this;
		
		//this.setTran(0.5f);
	}

	public void initBibliotecaInterfaz()
	{
		this.setSize(600, 300);
		this.setLayout(new BorderLayout());
		JMenuBar JMenuAux = getBarraMenu();
		this.add(JMenuAux,BorderLayout.SOUTH);
		
		
		
		// Instanciamos nuestro modelo de datos, por ejemplo, DefaultTableModel
		// y lo metemos en el JTable
		modelo = new MyDefaultTableModel();
		modelo.addColumn("Titulo");
		modelo.addColumn("Artista");
		modelo.addColumn("Album");
		modelo.addColumn("Tiempo total (seg)");
		modelo.addColumn("Genero");
		tabla = new JTable(modelo);
		tabla.setSize(500,250);

		// Instanciamos el TableRowSorter y lo a�adimos al JTable
		elQueOrdena = new TableRowSorter<TableModel>(modelo);
		tabla.setRowSorter(elQueOrdena);
		
		actualiza();
			
		tabla.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e) 
		      {
		    	if(e.getClickCount()==2){
		         int fila = tabla.rowAtPoint(e.getPoint());
		         if ((fila > -1))
		         {
		        	 fila = tabla.convertRowIndexToModel (fila);
		        	 Track aux = biblioteca.getBiblioteca().get(fila);
		        	 Playlist listaRepr = interfazPadre.getListaReproduccion();
		        	 listaRepr.add(aux.getLocation());
		        	 //interfazPadre.setCurrentTrack(listaRepr.current());
                     String[] temas = listaRepr.getListado();
                     interfazPadre.getInfoPlaylist().actualizaTemas(temas);
		        	 //pasamos de la fila recibida a la fila sin filtrar
		        	 //Anade a la listaReproduccion
		         }
		    	}
		      }
		   });

		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setSize(500,250);
		this.add(scroll);
		
		busquedaRapida = new JTextField("");
		busquedaRapida.setSize(100,10);
		Document d = busquedaRapida.getDocument();
		d.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				filtraRapido();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				filtraRapido();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				filtraRapido();
			}
		});
		menuBI.add(busquedaRapida);
	}
	
	private JMenuBar getBarraMenu() {
		
		
		
		// TODO m�s elementos �e iconos?
		if (menuBI == null) {
			menuBI = new JMenuBar();
			menuBI.setBorderPainted(false);
			
			JMenu menuArchivo = new JMenu("A�adir archivo");
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
			anadirAPlayList = new JMenuItem("A�adir seleccionados a la PlayList");
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
								aux = biblioteca.getBiblioteca().get(fila);
					        	listaRepr.add(aux.getLocation());
							}
							String[] temas = listaRepr.getListado();
		                    interfazPadre.getInfoPlaylist().actualizaTemas(temas);
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
								biblioteca.getBiblioteca().remove(fila);
							}
	                    	actualiza();
	                    	busquedaRapida.setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Selecciona las filas antes de borrar.");
						}
                    }
				});
		}
		return elimina;
	}

	private JMenuItem getAnadirArchivos() {
		if (anadirArchivos == null) {
			anadirArchivos = new JMenuItem("A�adir archivos a la biblioteca");
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
                    	remove(popup);
                    	actualiza();
                    	busquedaRapida.setText("");
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
		else if(file.getName().toLowerCase().endsWith(".mp3") || file.getName().toLowerCase().endsWith(".ogg")){
			HashMap<String,Boolean> hash = biblioteca.crearHashMap();
			if(!hash.containsKey(file.getAbsolutePath()))
			{
				Track aux = new Track(file.getAbsolutePath());
				biblioteca.add(aux);
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
							Track track = biblioteca.getBiblioteca().get(fila);
							PropiedadesTrack pt = new PropiedadesTrack(interfazPadre, true ,track);
							pt.setVisible(true);
						}
						actualiza();
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
				}
			});
		}
		return filtroAvanzado;
	}
	
	public void actualiza(){
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		/*1� Borrar todo lo que tenga la tabla*/
		elQueOrdena.setRowFilter(javax.swing.RowFilter.regexFilter(""));
		
		for(int i= modelo.getRowCount() - 1; i>=0 ;i--){
			 modelo.removeRow(i);
		}
		
		
		/*2� Meter todo en la tabla*/
		Iterator it = biblioteca.getBiblioteca().iterator();
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
	}
}
