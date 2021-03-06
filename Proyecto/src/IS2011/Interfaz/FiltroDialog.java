package IS2011.Interfaz;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class FiltroDialog extends JDialog{
	
	/**
	 * @uml.property  name="fd"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private FiltroDialog fd= null;
	/**
	 * @uml.property  name="queOrdena"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private TableRowSorter<TableModel> queOrdena= null;
	
	/**
	 * @uml.property  name="labelNombre"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel labelNombre= null;
	/**
	 * @uml.property  name="nombre"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JTextField nombre= null;
	/**
	 * @uml.property  name="labelArtista"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel labelArtista= null;
	/**
	 * @uml.property  name="artista"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JTextField artista=null;
	/**
	 * @uml.property  name="labelAlbum"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel labelAlbum= null;
	/**
	 * @uml.property  name="album"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JTextField album=null;
	/**
	 * @uml.property  name="labelGenero"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel labelGenero= null;
	/**
	 * @uml.property  name="genero"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JTextField genero=null;

	/**
	 * @uml.property  name="botonOk"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JButton botonOk = null;
	/**
	 * @uml.property  name="botonCancel"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JButton botonCancel = null;

	/**
	 * Construye la interfaz de la ventana de filtro avanzado
	 * @param comp: JFrame del que hereda el dialog que creamos
	 * @param modal: Booleana de modal
	 */
	public FiltroDialog(JFrame comp,boolean modal, TableRowSorter<TableModel> ordena)
	{
		super(comp, modal);
		this.setTitle("Filtrar por");
		fd = this;
		queOrdena = ordena;
		initPropiedadesUI();
	}
	
	/**
	 * Inicializa la interfaz del FiltroDialog.
	 */
	public void initPropiedadesUI(){
		this.setSize(380,200);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		labelNombre = new JLabel("Titulo:  ");
		labelArtista = new JLabel("Artista: ");
		labelAlbum = new JLabel("Album:   ");
		labelGenero =  new JLabel("Genero:  ");
		nombre = new JTextField("",25);
		artista = new JTextField("",25);
		album = new JTextField("",25);
		genero = new JTextField("",25);
		
		botonOk = new JButton("OK");
		botonOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {		
				/* Recogemos y vamos creando los filtros*/
				
				/*
					Para poder filtrar -> la expresion regular en este caso es "2" y la columna es la 1. 
					modeloOrdenado.setRowFilter(RowFilter.regexFilter("2", 1));
					Para poder filtrar por varias a la vez 
					lista.add(RowFilter.dateFilter(....));
				*/

				List<RowFilter<Object,Object>> lista =  new ArrayList<RowFilter<Object,Object>>();
				if(nombre.getText()!=""){
					lista.add(RowFilter.regexFilter("(?i)"+nombre.getText(),0));
				}
				if(artista.getText()!=""){
					lista.add(RowFilter.regexFilter("(?i)"+artista.getText(),1));
				}
				if(album.getText()!=""){
					lista.add(RowFilter.regexFilter("(?i)"+album.getText(),2));
				}
				if(genero.getText()!=""){
					lista.add(RowFilter.regexFilter("(?i)"+genero.getText(),4));
				}
				
				RowFilter filtroAnd = RowFilter.andFilter(lista);
				queOrdena.setRowFilter(filtroAnd);
				
				/* Cerrar al igual que se hace en el de cancelar */
				fd.dispose();
			}
		});
		
		botonCancel = new JButton("Cancel");
		botonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				fd.dispose();
			}
		});
		
		this.add(labelNombre);
		this.add(nombre);
		this.add(labelArtista);
		this.add(artista);
		this.add(labelAlbum);
		this.add(album);
		this.add(labelGenero);
		this.add(genero);
		this.add(botonOk);
		this.add(botonCancel);
	}
}
