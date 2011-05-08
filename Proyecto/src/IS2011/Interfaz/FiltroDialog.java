package IS2011.Interfaz;

import java.awt.GridBagLayout;
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
	
	private FiltroDialog fd= null;
	private TableRowSorter<TableModel> queOrdena= null;
	
	private JLabel labelNombre= null;
	private JTextField nombre= null;
	private JLabel labelArtista= null;
	private JTextField artista=null;
	private JLabel labelAlbumArtista= null;
	private JTextField albumArtista= null;
	private JLabel labelAlbum= null;
	private JTextField album=null;
	private JLabel labelComentario= null;
	private JTextField comentario=null;

	private JButton botonOk = null;
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
	
	public void initPropiedadesUI(){
		this.setSize(400,200);
		this.setLayout(new GridBagLayout());
		labelNombre = new JLabel("Titulo: ");
		labelArtista = new JLabel("Artista: ");
		labelAlbumArtista = new JLabel("Album Artista: ");
		labelAlbum = new JLabel("Album: ");
		labelComentario = new JLabel("Comentario: ");
		nombre = new JTextField("");
		artista = new JTextField("");
		albumArtista = new JTextField("");
		album = new JTextField("");
		comentario = new JTextField("");
		
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
				if(albumArtista.getText()!=""){
					lista.add(RowFilter.regexFilter("(?i)"+albumArtista.getText(),2));
				}
				/*if(album.getText()!=""){
					lista.add(RowFilter.regexFilter("(?i)"+album.getText(),0));
				}*/
				if(comentario.getText()!=""){
					lista.add(RowFilter.regexFilter("(?i)"+comentario.getText(),5));
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
		this.add(labelAlbumArtista);
		this.add(albumArtista);
		this.add(labelAlbum);
		this.add(album);
		this.add(labelComentario);
		this.add(comentario);
		this.add(botonOk);
		this.add(botonCancel);
	}
}
