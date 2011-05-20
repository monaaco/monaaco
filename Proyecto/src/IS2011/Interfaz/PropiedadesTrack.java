package IS2011.Interfaz;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JWindow;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import IS2011.biblioteca.Track;

/*Nuevo Frame
En este frame mostramos la info de la linea seleccionada en la biblioteca 
para modificar su info*/


public class PropiedadesTrack extends JDialog //o JWindow, probar ambas.
{
	private Track track= null;
	
	private PropiedadesTrack pt= null;
	
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
	private JLabel labelGenero= null;
	private JTextField genero=null;
	/*private artworkList ; pensarlo bien, realmente la interfaz muestra un label con un imgIcon y el artworkList es el mismo, salvo que se cambie. Lo mismo no interesa de primeras cambiar la imagen.
	*/
	private JButton botonOk = null;
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