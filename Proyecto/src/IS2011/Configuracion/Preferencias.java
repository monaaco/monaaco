package IS2011.Configuracion;

import java.awt.Color;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("preferencias")
public class Preferencias {

	
	private String skin;
	private String launcher;
	private String sound;
	
	private Color bgColor;
	private Color fgColor;
	
	/**
	 * Constructora
	 */
	public Preferencias() {
		//Asigna valores por defecto
		setDefault();		
	}

	/**
	 * Asigna valores por defecto
	 */
	private void setDefault() {
		skin = "data/skin3";
		launcher = "data/skin3";
		sound = "data/skin3";
		bgColor = Color.black;
		fgColor = Color.white;		
	}

	/**
	 * 
	 * @return nombre del skin
	 */
	public String getSkin() {
		return skin;
	}
	
	/**
	 * 
	 * @param skin
	 */
	public void setSkin(String skin) {
		this.skin = skin;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLauncher() {
		return launcher;
	}
	
	/**
	 * 
	 * @param launcher
	 */
	public void setLauncher(String launcher) {
		this.launcher = launcher;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSound() {
		return sound;
	}
	
	/**
	 * 
	 * @param sound
	 */
	public void setSound(String sound) {
		this.sound = sound;
	}
	
	/**
	 * 
	 * @return
	 */
	public Color getBgColor() {
		return bgColor;
	}
	
	/**
	 * 
	 * @param bgColor
	 */
	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}
	
	/**
	 * 
	 * @return
	 */
	public Color getFgColor() {
		return fgColor;
	}
	
	/**
	 * 
	 * @param fgColor
	 */
	public void setFgColor(Color fgColor) {
		this.fgColor = fgColor;
	}
	
	
	
}
