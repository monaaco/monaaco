import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import javazoom.jl.player.Player;
import javazoom.jl.player.PlayerApplet;
import javazoom.jl.decoder.*;

public class Principal {
	private String filename;
	private Player player;

	/**
	 * MP3 constructora
	 */
	public Principal(String filename) {
		this.filename = filename;
	}

	/**
	 * Crea un nuevo Player
	 */
	public void play() {
		try {
			FileInputStream fis = new FileInputStream(this.filename);
			BufferedInputStream bis = new BufferedInputStream(fis);
			if(this.player!=null){
				this.player.close();
			}
			this.player = new Player(bis);
		} catch (Exception e) {
			System.err.printf("%s\n", e.getMessage());
		}

		new Thread() {
			@Override
			public void run() {
				try {
					player.play();					
					
				} catch (Exception e) {
					System.err.printf("%s\n", e.getMessage());
				}
			}
		}.start();
	}

	public void stop() {
		if (this.player != null) {
			player.close();
		}
	}

	public void fast_forward() {
		int ms = player.getPosition();
		System.out.println("Current frame: " + ms);
		try {
			if(this.player!=null)
			{
				player.play(26*ms);
				player.close();
				
			}
		} catch (JavaLayerException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public String damePos(){
		if(player!=null)
		{ 	int ms = player.getPosition();
			return Integer.toString(ms/60000) +":"+ Integer.toString((ms/1000)%60);
		}
		else 
			return "0:00";
	}
}
