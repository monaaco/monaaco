package IS2011.Interfaz;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import IS2011.biblioteca.Track;

/*Nuevo Frame
En este frame mostramos la info de la linea seleccionada en la biblioteca 
para modificar su info*/


public class PropiedadesTrack extends JDialog //o JWindow, probar ambas.
{
	/**
	 * @uml.property  name="track"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Track track= null;
	
	/**
	 * @uml.property  name="pt"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private PropiedadesTrack pt= null;
	
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
	 * @uml.property  name="labelAlbumArtista"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel labelAlbumArtista= null;
	/**
	 * @uml.property  name="albumArtista"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JTextField albumArtista= null;
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
	 * @uml.property  name="labelComentario"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel labelComentario= null;
	/**
	 * @uml.property  name="comentario"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JTextField comentario=null;
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
	/*private artworkList ; pensarlo bien, realmente la interfaz muestra un label con un imgIcon y el artworkList es el mismo, salvo que se cambie. Lo mismo no interesa de primeras cambiar la imagen.
	*/
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
	 * Construye la interfaz de la ventana de propiedades del Track
	 * @param comp: JFrame del que hereda el dialog que creamos
	 * @param modal: Booleana de modal
	 * @param track: Track track del que mostramos información
	 */
	public PropiedadesTrack(JFrame comp,boolean modal ,Track track)
	{
		super(comp, modal);
		this.track = track;
		this.setTitle("Propiedades del archivo: "+ track.getLocation());
		pt = this;
		initPropiedadesUI();
	}	
	
	/**
	 * Inicializa la ventana
	 */
	public void initPropiedadesUI(){
		this.setSize(380,250);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		labelNombre =  new JLabel("Titulo:   ");
		labelArtista = new JLabel("Artista:  ");
		labelAlbumArtista = new JLabel("Album Artista:     ");
		labelAlbum =   new JLabel("Album:    ");
		labelComentario = new JLabel("Comentario: ");
		labelGenero = new JLabel("Genero:   ");
		nombre = new JTextField(track.getName(),25);
		artista = new JTextField(track.getArtist(),25);
		albumArtista = new JTextField(track.getAlbumArtist(),20);
		album = new JTextField(track.getAlbum(),25);
		comentario = new JTextField(track.getComments(),25);
		genero = new JTextField(track.getGenre(),25);
		
		botonOk = new JButton("OK");
		botonOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				/*1º en este metodo deberiamos modificar el Track que se nos ha pasado al metodo,
				si no se le ha hecho ningun new entre medias este Track se encuentra dentro del ArrayList
				 de bliblioteca y/o de mostradas en biblioteca con lo cual bastaría con guardar la biblioteca 
				 entera al modificar el Track.*/
				/*2º Cambiar los Tags en el archivo original
				 Deberiamos probar que pasa si se modifica un archivo en reproducción, si falla:
				 SOLUCION, pasar a la constructora una referencia a Interfaz avanzada, en ella creamos un get
				 de lo que se está reproduciendo y preguntamos por el. Si es el mismo archivo le pedimos 
				 getPosition(bytes), stop, close(f), GUARDAMOS (codigo comúm puesto a continuación), y hacemos un 
				 open(f), seek(bytes) y play.
				*/
				try{
					if((nombre.getText() != null) && (nombre.getText() != "")) track.setName(nombre.getText());
					if((artista.getText() != null) && (artista.getText() != "")) track.setArtist(artista.getText());
					if((albumArtista.getText() != null) && (albumArtista.getText() != "")) track.setAlbumArtist(albumArtista.getText());
					if((album.getText() != null) && (album.getText() != "")) track.setAlbum(album.getText());
					if((genero.getText() != null)&& (album.getText() != "")) track.setGenre(genero.getText());
					/*if((comentario.getText() != null) && (comentario.getText() != ""))*/ track.setComments(comentario.getText());
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null,
							"Error en la modificación del archivo"
							);
				}
				
				/*3º Cerrar al igual que se hace en el de cancelar */

				pt.dispose();
			}
		});
		
		botonCancel = new JButton("Cancel");
		botonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				pt.dispose();
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
		this.add(labelGenero);
		this.add(genero);
		this.add(botonOk);
		this.add(botonCancel);
	}
}