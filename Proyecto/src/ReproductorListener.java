import java.util.Map;

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
	@Override
	public void opened(Object arg0, Map arg1) {
		//Nos devuelve el numero de bytes al abrir el archivo
		if (arg1.containsKey("audio.length.bytes")) {
			  bytesLength = Double.parseDouble(arg1.get("audio.length.bytes").toString());
			 }
		player.ajustaBarraProgreso(bytesLength);
		player.actualizaBarraProgreso(0);
	}

	@Override
	public void progress(int bytesread, long microseconds, byte[] pcmdata,  Map properties) {
		 float progressUpdate = (float) (bytesread * 1.0f / bytesLength * 1.0f);
		 int progressNow = (int) (bytesLength * progressUpdate);
		 System.out.println(" -&gt; " + progressNow +"    en segs= "+microseconds/1000);
		 player.cambiaSegundos((microseconds/60000000) +":"+ ((microseconds/1000000)%60));
		 player.actualizaBarraProgreso(progressNow);
		}

	@Override
	public void setController(BasicController arg0) {
		System.out.println("Listener, ajustado control.");
	}

	@Override
	public void stateUpdated(BasicPlayerEvent arg0) {
		System.out.println("Listener, cambio de estado.");
		if(arg0.getCode()== BasicPlayerEvent.STOPPED){
			player.actualizaBarraProgreso(0);
			player.cambiaSegundos("0:00");
		}
	}
	
}
