package IS2011.Interfaz;
import java.util.Map;

import IS2011.bibliotecaXML.Track;


import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class ReproductorListener implements BasicPlayerListener{

	private double bytesLength;
	private InterfazAvanzada player;
	
	public ReproductorListener(InterfazAvanzada p){
		super();
		player=p;
	}
	public void opened(Object arg0, Map arg1) {
		//Nos devuelve el numero de bytes al abrir el archivo
		if (arg1.containsKey("audio.length.bytes")) {
			  bytesLength = Double.parseDouble(arg1.get("audio.length.bytes").toString());
			  player.setBytesArchivo(bytesLength);
			 }
		player.ajustaBarraProgreso(bytesLength);
		player.actualizaBarraProgreso(0);
	}


	public void progress(int bytesread, long microseconds, byte[] pcmdata,  Map properties) {
		 float progressUpdate = (float) (bytesread * 1.0f / bytesLength * 1.0f);
		 int progressNow = (int) (bytesLength * progressUpdate);
		 float porcentaje = (float)(bytesread / bytesLength);
		 long second = microseconds;
		 System.out.print("Waka waka ehhhh ehhhh: eeeeee :D" + porcentaje);
		 int total;
		 if(player != null && player.getListaReproduccion().current() != null){
			 total = player.getListaReproduccion().current().getTotalTime();
			 System.out.print("   TOTAL: " + total);
			 second = (long) (total * (porcentaje));
			 System.out.println(" -&gt; " + progressNow +"    en segs= "+second);
			 player.cambiaSegundos((second/60) +":"+ (second%60));
			 player.actualizaBarraProgreso(progressNow);
		 }
		 else{
			 System.out.println(" -&gt; " + progressNow +"    en segs= "+microseconds/1000);
			 player.cambiaSegundos((microseconds/60000000) +":"+ ((microseconds/1000000)%60));
			 player.actualizaBarraProgreso(progressNow);
		 }
		}


	public void setController(BasicController arg0) {
		System.out.println("Listener, ajustado control.");
	}


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