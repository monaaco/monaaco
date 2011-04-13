package monaaco.old;







/*import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;


public class MPlayer extends AdvancedPlayer implements Runnable {
	
	private String filename;
	private int frameActual;
	
    public MPlayer(InputStream is) throws JavaLayerException
	 {
		super(is);
		frameActual = 0;
	}
    
   	@Override
	public void run() {
		// TODO Auto-generated method stub
   		try {
			play();
			
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   	
   	public synchronized void stop(){
   		super.stop();
   	}
   	
	public boolean play(int frames) throws JavaLayerException
	{
		boolean ret = true;
			
		while (frames-- > 0 && ret)
		{
			ret = decodeFrame();
			frameActual = Integer.MAX_VALUE - frames;
		}
		return ret;
	}

   	public void pause() throws InterruptedException{
   		this.wait();
   	}
   	
}*/
