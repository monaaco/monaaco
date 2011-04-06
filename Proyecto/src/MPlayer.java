import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;


public class MPlayer extends Player implements Runnable {
	
	private String filename;

    public MPlayer(InputStream is) throws JavaLayerException
	 {
		super(is);
		
	}
    
   	@Override
	public void run() {
		// TODO Auto-generated method stub
   		
	}
}
