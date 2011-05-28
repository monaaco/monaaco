package IS2011.tests;

import javazoom.jlgui.basicplayer.BasicPlayerException;
import junit.framework.TestCase;
import IS2011.biblioteca.Playlist;
import IS2011.biblioteca.Track;

public class TrackTest extends TestCase {
	
	/**
	 * @uml.property  name="track"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Track track = null;
	/**
	 * @uml.property  name="track2"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Track track2 = null;
	/**
	 * @uml.property  name="cadena"
	 */
	private String cadena;
	/**
	 * @uml.property  name="listaReproduccion"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Playlist listaReproduccion;
	
	/**
	 * Test de la constructora
	 */
	public TrackTest(){
		super();
		try {
			this.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inicialización de variables para los tests
	 */
	public void setUp(){
		listaReproduccion = new Playlist();
		listaReproduccion.setRepeat(true);
		listaReproduccion.add("sounds/prueba.mp3"); 
		listaReproduccion.add("sounds/prueba2.ogg");
		
		String ruta = "sounds/prueba.mp3";
		String ruta2 = "sounds/prueba2.ogg";
		track = new Track(ruta);
		track2 = new Track(ruta2);
		cadena = "Artist: Flowklorikos\n Name: Donde duele inspira\n";
	}
	
	/**
	 * test de toString()
	 */
	public void testToString(){
		assertEquals(cadena, track.toString());		
	}
	
	/**
	 * test de getCurrent()
	 * @throws BasicPlayerException
	 */
	public void testGetCurrent() throws BasicPlayerException {
		assertEquals(listaReproduccion.getCurrent(),track);
	}
	
	/**
	 * test de next()
	 * @throws BasicPlayerException
	 */
	public void testNext() {
		assertEquals(listaReproduccion.next(),track2);
		assertEquals(listaReproduccion.next(),track);
		listaReproduccion.setRepeat(false);
		assertEquals(listaReproduccion.next(),track2);
		assertNull(listaReproduccion.next());
		listaReproduccion.setRepeat(true);
	}
	
	/**
	 * test de previous() (y setRepeat())
	 * @throws BasicPlayerException
	 */
	public void testPrevious() throws BasicPlayerException {
		assertEquals(listaReproduccion.previous(),track2);
		listaReproduccion.setRepeat(false);
		assertEquals(listaReproduccion.previous(),track);
		assertNull(listaReproduccion.previous());
		listaReproduccion.setRepeat(true);
		assertEquals(listaReproduccion.previous(),track2);
		assertEquals(listaReproduccion.previous(),track);
	}
	
	/**
	 * test de getArtist()
	 * @throws BasicPlayerException
	 */
	public void testGetArtist() throws BasicPlayerException {
		assertEquals(listaReproduccion.getCurrent().getArtist(),"Flowklorikos");
	}
	
	/**
	 * test de getTotalTime()
	 */
	public void testGetTotalTime(){
		assertEquals(listaReproduccion.getCurrent().getTotalTime(),(Integer)Integer.parseInt("216"));
	}
	
	/**
	 * test de getName()
	 * @throws BasicPlayerException
	 */
	public void testGetAlbum() throws BasicPlayerException {
		assertEquals(listaReproduccion.getCurrent().getName(),"Donde duele inspira");
	}
	
	/**
	 * test de getAlbumArtist()
	 */
	public void testGetAlmbumArtist(){
		assertEquals(listaReproduccion.getCurrent().getAlbumArtist(),"");
	}
	
	/**
	 *  test de getGenre()
	 */
	public void testGetGener(){
		assertEquals(listaReproduccion.getCurrent().getGenre(),"Hip-Hop");
		
	}
	
	/**
	 * test de getKind()
	 */
	public void testGetKind(){
		assertEquals(listaReproduccion.getCurrent().getKind(),"MPEG-1 Layer 3");
	}
	
	/**
	 * test de getComments()
	 * @throws BasicPlayerException
	 */
	public void testGetComments()throws BasicPlayerException {
		assertEquals(listaReproduccion.getCurrent().getComments(),"www.HHGroups.com");
	}
	
	/**
	 *  test de getBitRate()
	 */
	public void testGetBitrate(){
		assertEquals(listaReproduccion.getCurrent().getBitRate(),(Integer)Integer.parseInt("320"));
	}
	
}