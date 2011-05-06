package IS2011.Interfaz;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import IS2011.bibliotecaXML.Track;

/*Nuevo Frame
En este frame mostramos la info de la linea seleccionada en la biblioteca 
para modificar su info*/


public class PropiedadesTrack extends JDialog //o JWindow, probar ambas.
{
	private String ruta=null;
	private Track track= null;
	
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
	/*private artworkList ; pensarlo bien, realmente la interfaz muestra un label con un imgIcon y el artworkList es el mismo, salvo que se cambie. Lo mismo no interesa de primeras cambiar la imagen.
	*/
	private JButton botonOk = null;
	private JButton botonCancel = null;

	/**
	 * Construye la interfaz de la ventana de propiedades del Track
	 * @param rutaArchivo: String
	 * @param track: Track
	 */
	public PropiedadesTrack(String rutaArchivo, Track track)
	{
		super();
		ruta = rutaArchivo; 
		this.track = track;
		this.setTitle("Propiedades del archivo:");
		initPropiedadesUI();
	}	
	
	/**
	 * Inicializa la ventana
	 */
	public void initPropiedadesUI(){
		this.setLayout(new GroupLayout(this));
		labelNombre = new JLabel("Titulo: ");
		labelArtista = new JLabel("Artista: ");
		labelAlbumArtista = new JLabel("Album Artista: ");
		labelAlbum = new JLabel("Album: ");
		labelComentario = new JLabel("Comentario: ");
		nombre = new JTextField(track.getName());
		artista = new JTextField(track.getArtist());
		albumArtista = new JTextField(track.getAlbumArtist());
		album = new JTextField(track.getAlbum());
		comentario = new JTextField(track.getComments());
		
		botonOk = new JButton("OK");
		botonOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				/*1º en este metodo deberiamos modificar el Track que se nos ha pasado al metodo,
				si no se le ha hecho ningun new entre medias este Track se encuentra dentro del ArrayList de bliblioteca y/o de mostradas en biblioteca con lo cual bastaría con guardar la biblioteca entera
				al modificar el Track.*/
				track.setName(nombre.getText());
				track.setArtist(artista.getText());
				track.setAlbumArtist(albumArtista.getText());
				track.setAlbum(album.getText());
				track.setComments(comentario.getText());
				
				/*2º Cambiar los Tags en el archivo original
				 Deberiamos probar que pasa si se modifica un archivo en reproducción, si falla:
				 SOLUCION, pasar a la constructora una referencia a Interfaz avanzada, en ella creamos un get
				 de lo que se está reproduciendo y preguntamos por el. Si es el mismo archivo le pedimos 
				 getPosition(bytes), stop, close(f), GUARDAMOS (codigo comúm puesto a continuación), y hacemos un 
				 open(f), seek(bytes) y play.
				*/
				
				//EL GUARDAR la metaInformación en el archivo.
				File file = new File(ruta);
				try{
					AudioFile f = AudioFileIO.read(file);
					Tag tag = f.getTag();
					tag.setField(FieldKey.TITLE, nombre.getText());
					tag.setField(FieldKey.ARTIST, artista.getText());
					tag.setField(FieldKey.ALBUM_ARTIST, albumArtista.getText());
					tag.setField(FieldKey.ALBUM, album.getText());
					tag.setField(FieldKey.COMMENT, comentario.getText());
					f.commit();
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error en la modificación del archivo");
				}
				/*3º Cerrar al igual que se hace en el de cancelar
				 */
				//TODO
			}
		});
		
		botonCancel = new JButton("Cancel");
		botonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO que se cierre
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
	}
}