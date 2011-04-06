import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;


public class MPlayer extends Player implements Runnable {
	
	private Player player;
	
	public MPlayer(InputStream is) throws JavaLayerException {
		// TODO Auto-generated constructor stub
		super(is);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
