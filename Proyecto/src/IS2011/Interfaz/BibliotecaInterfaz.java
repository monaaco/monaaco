package IS2011.Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JWindow;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import IS2011.FiltrosArchivos.FiltroMP3;
import IS2011.FiltrosArchivos.FiltroOGG;
import IS2011.FiltrosArchivos.FiltroSoportados;
import IS2011.bibliotecaXML.Biblioteca;
import IS2011.bibliotecaXML.Track;

public class BibliotecaInterfaz extends JFrame{

	private JFrame frame= null;
	
	private JMenuBar menuBI=null;
	private JMenu menuArchivo=null;
	private JMenuItem anadirArchivos=null;
	private JButton propiedades= null;
	private JTable tabla= null;
	private Biblioteca biblioteca=null; //Sino es un ArrayList es la propia biblioteca.
	private TableRowSorter<TableModel> elQueOrdena; 
	
	public BibliotecaInterfaz(Biblioteca library){
		super("Biblioteca");
		biblioteca = library;
		initBibliotecaInterfaz();
		frame = this;
	}

	public void initBibliotecaInterfaz()
	{
		this.setSize(600, 300);
		this.setLayout(new BorderLayout());
		this.setJMenuBar(getBarraMenu());
		
		
		// Instanciamos nuestro modelo de datos, por ejemplo, DefaultTableModel
		// y lo metemos en el JTable
		MyDefaultTableModel modelo = new MyDefaultTableModel();
		modelo.addColumn("Titulo");
		modelo.addColumn("Artista");
		modelo.addColumn("Album artista");
		modelo.addColumn("Tiempo total (seg)");
		modelo.addColumn("Bitrate");
		modelo.addColumn("Comentarios");
		tabla = new JTable(modelo);
		tabla.setSize(500,250);
		actualiza();
		// Instanciamos el TableRowSorter y lo añadimos al JTable
		elQueOrdena = new TableRowSorter<TableModel>(modelo);
		tabla.setRowSorter(elQueOrdena);
		
		/*para el filtro general
		elQueOrdena.setRowFilter(RowFilter.regexFilter(".*foo.*"));
		*/
		
		
		/*
		Para poder filtrar -> la expresion regular en este caso es "2" y la columna es la 1. 
		modeloOrdenado.setRowFilter(RowFilter.regexFilter("2", 1));
		Para poder filtrar por varias a la vez 
		LinkedList<RowFilter> lista = new LinkedList<RowFilter>();
		lista.add(RowFilter.dateFilter(....));
		lista.add(RowFilter.regexFilter(....));
				
		RowFilter filtroAnd = RowFilter.andFilter(lista);
		*/
		
		
		/*tabla.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e) 
		      {
		    	if(e.getClickCount()==2){
		         int fila = tabla.rowAtPoint(e.getPoint());
		         if ((fila > -1))
		         {
		        	 //pasamos de la fila recibida a la fila sin filtrar
		        	 //Anade a la listaReproduccion
		         }
		    	}
		      }
		   });*/

		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setSize(500,250);
		this.add(scroll);
	}
	
	private JMenuBar getBarraMenu() {
		// TODO más elementos ¿e iconos?
		if (menuBI == null) {
			menuBI = new JMenuBar();
			menuBI.setBorderPainted(false);
			menuArchivo = new JMenu("Biblioteca");
			menuArchivo.add(getAnadirArchivos());
			menuBI.add(menuArchivo);
		}
		return menuBI;
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
                    
                    fc.setMultiSelectionEnabled(true);
                    if(fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    	File[] array = fc.getSelectedFiles();
                    	for(int i=0; i<array.length; i++){
                    		File f = array[i];
                    		Track aux = new Track(f.getAbsolutePath());
                    		biblioteca.add(aux);
                    	}
                    	actualiza();
                    }
				};
			});
		}
		return anadirArchivos;
	}

	public void actualiza(){
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		/*1º Borrar todo lo que tenga la tabla SIN HACER*/
		
		//IMPRESCINDIBLE CREO modelo.getSort().removeFilters();
		
		for(int i= modelo.getRowCount() - 1; i>=0 ;i++){
			 modelo.removeRow(i);
		}
		
		/*2º Meter todo en la tabla*/
		
		Iterator it = biblioteca.getArray().iterator();
		Track aux= null;
		while (it.hasNext())
		{
			aux = (Track)it.next();
			((DefaultTableModel) modelo).addRow(new Object[] { aux.getName(), aux.getArtist(), aux.getAlbumArtist(), aux.getTotalTime(), aux.getBitRate(), aux.getComments()});
		}
		
		repaint();
	}
}
