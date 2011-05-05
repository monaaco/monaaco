package IS2011.Interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import IS2011.bibliotecaXML.Track;

public class BibliotecaInterfaz extends JDialog{

	private JButton propiedades= null;
	private JTable tabla= null;
	private ArrayList biblioteca=null; //Sino es un ArrayList es la propia biblioteca.
	private TableRowSorter<TableModel> elQueOrdena; 
	
	public BibliotecaInterfaz(ArrayList library){
		super();
		biblioteca = library;
		initBibliotecaInterfaz();
	}

	public void initBibliotecaInterfaz()
	{
		this.setLayout(new BorderLayout());
		
		// Instanciamos nuestro modelo de datos, por ejemplo, DefaultTableModel
		// y lo metemos en el JTable
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Titulo");
		modelo.addColumn("Artista");
		modelo.addColumn("Album artista");
		modelo.addColumn("Tiempo total (seg)");
		modelo.addColumn("Bitrate");
		modelo.addColumn("Comentarios");
		tabla = new JTable(modelo);
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
		
		
		JScrollPane scroll = new JScrollPane(tabla);
		this.add(scroll, "NORTH");
		this.add(propiedades, "SOUTH");
	}
	
	public void actualiza(){
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		/*1º Borrar todo lo que tenga la tabla SIN HACER*/
		
		//IMPRESCINDIBLE CREO modelo.getSort().removeFilters();
		
		for(int i= modelo.getRowCount() - 1; i>=0 ;i++){
			 modelo.removeRow(i);
		}
		
		/*2º Meter todo en la tabla*/
		
		Iterator it = biblioteca.iterator();
		Track aux= null;
		while (it.hasNext())
		{
			aux = (Track)it.next();
			((DefaultTableModel) modelo).addRow(new Object[] { aux.getName(), aux.getArtist(), aux.getAlbumArtist(), aux.getTotalTime(), aux.getBitRate(), aux.getComments()});
		}
		
	}
}
