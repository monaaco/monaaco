package IS2011.Interfaz;
import java.util.Map;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class ReproductorListener implements BasicPlayerListener{

	/**
	 * @uml.property  name="bytesLength"
	 */
	private double bytesLength;
	/**
	 * @uml.property  name="player"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="reproductorListener:IS2011.Interfaz.InterfazAvanzada"
	 */
	private InterfazAvanzada player;
	
	/**
	 * Constructora del Reproductor Listene
	 * @param p InterfazAvanzada a la que esta asociada
	 */
	public ReproductorListener(InterfazAvanzada p){
		super();
		player=p;
	}
	
	/**
	 * Actualiza el player y la barra de progerso 
	 * al abrir una nueva pista de audio
	 * @param arg0
	 * @param arg1
	 */
	public void opened(Object arg0, Map arg1) {
		//Nos devuelve el numero de bytes al abrir el archivo
		if (arg1.containsKey("audio.length.bytes")) {
			  bytesLength = Double.parseDouble(arg1.get("audio.length.bytes").toString());
			  player.setBytesArchivo(bytesLength);
			 }
		player.ajustaBarraProgreso(bytesLength);
		player.actualizaBarraProgreso(0);
	}

	/**
	 * Este metodo se va ejecutando a medida que avanza la canción.
	 * Actualiza barra y tiempo en la interfaz.
	 * @param bytesread bytes leidos hasta el momento.
	 * @param microseconds 
	 * @param pcmdata
	 * @param properties
	 */
	public void progress(int bytesread, long microseconds, byte[] pcmdata,  Map properties) {
		 float progressUpdate = (float) (bytesread * 1.0f / bytesLength * 1.0f);
		 int progressNow = (int) (bytesLength * progressUpdate);
		 float porcentaje = (float)(bytesread / bytesLength);
		 long microsecond=0;
	     if(properties.get("mp3.position.microseconds")!=null)
		 {
	    	 microsecond = (long)Double.parseDouble(properties.get("mp3.position.microseconds").toString());
		 }
	     else 
	     {
	    	 microsecond = microseconds;
	     }
		// System.out.println(" -&gt; " + progressNow +"    en segs= "+microseconds/1000);
		// System.out.println(" -&gt; " + progressNow +"    en segs= "+microseconds/1000);
         player.cambiaSegundos((int)(microsecond/60000000),((int)(microsecond/1000000)%60));
			 player.actualizaBarraProgreso(progressNow);
		}


	/**
	 * Ajusta los controles sobre el reproductor
	 * @param arg0
	 */
	public void setController(BasicController arg0) {
		System.out.println("Listener, ajustado control.");
	}

	/**
	 * Actualiza el estado del reproductor.
	 * Actualiza barra progreso y segundero, tambien cambia de canción al 
	 * acabar la pista actual.
	 * @param arg0
	 */
	public void stateUpdated(BasicPlayerEvent arg0) {
		System.out.println("Listener, cambio de estado.");
		if(arg0.getCode()== BasicPlayerEvent.STOPPED){
			player.actualizaBarraProgreso(0);
			player.cambiaSegundos("0:00");
		}
		
		else if(arg0.getCode()== BasicPlayerEvent.EOM){
			player.reproducirSiguiente();
		}
	}
	
}
