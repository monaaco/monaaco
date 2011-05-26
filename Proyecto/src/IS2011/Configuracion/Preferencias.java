package IS2011.Configuracion;

import java.awt.Color;

import IS2011.biblioteca.Track;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("preferencias")
public class Preferencias {

	
	private String rutaIndexada;
	
	private String skin;
	private String launcher;
	private String sound;
	
	private Color bgColor;
	private Color fgColor;
	private Color bgColorInterno;
	private Color fgColorInterno;
	
	@XStreamAlias("track")
	private Track cancionActual;
	private long posCancionActual;
	
	
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
	public void setDefault() {
		skin = "images/Skin3";
		launcher = "images/Skin3";
		sound = "images/Skin3";
		bgColor = Color.black;
		fgColor = Color.white;	
		bgColorInterno = Color.black;
		fgColorInterno = Color.lightGray;
		rutaIndexada = "sounds";
	}
	
	/**
	 * @return the rutaIndexada
	 */
	public String getRutaIndexada() {
		return rutaIndexada;
	}

	/**
	 * @param rutaIndexada the rutaIndexada to set
	 */
	public void setRutaIndexada(String rutaIndexada) {
		this.rutaIndexada = rutaIndexada;
	}

	/**
	 * @return the bgColorInterno
	 */
	public Color getBgColorInterno() {
		return bgColorInterno;
	}

	/**
	 * @param bgColorInterno the bgColorInterno to set
	 */
	public void setBgColorInterno(Color bgColorInterno) {
		this.bgColorInterno = bgColorInterno;
	}

	/**
	 * @return the fgColorInterno
	 */
	public Color getFgColorInterno() {
		return fgColorInterno;
	}

	/**
	 * @param fgColorInterno the fgColorInterno to set
	 */
	public void setFgColorInterno(Color fgColorInterno) {
		this.fgColorInterno = fgColorInterno;
	}

	/**
	 * Devuelve el nombre del skin
	 * @return String
	 */
	public String getSkin() {
		return skin;
	}
	
	/**
	 * Cambia el skin asociado
	 * @param skin - String
	 */
	public void setSkin(String skin) {
		this.skin = skin;
	}
	
	/**
	 * Devuelve el nombre del launcher
	 * @return String 
	 */
	public String getLauncher() {
		return launcher;
	}
	
	/**
	 * Cambia el launcher asociado
	 * @param launcher - String
	 */
	public void setLauncher(String launcher) {
		this.launcher = launcher;
	}
	
	/**
	 * Devuelve el nombre del sonido asociado
	 * @return
	 */
	public String getSound() {
		return sound;
	}
	
	/**
	 * Cambia el sonido asociado
	 * @param sound - String
	 */
	public void setSound(String sound) {
		this.sound = sound;
	}
	
	/**
	 * Devuelve el color de fondo
	 * @return Color
	 */
	public Color getBgColor() {
		return bgColor;
	}
	
	/**
	 * Cambia el color de fondo
	 * @param bgColor - Color
	 */
	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}
	
	/**
	 * Devuelve el color frontal
	 * @return Color
	 */
	public Color getFgColor() {
		return fgColor;
	}
	
	/**
	 * Cambia el color frontal
	 * @param fgColor - Color
	 */
	public void setFgColor(Color fgColor) {
		this.fgColor = fgColor;
	}

	/**
	 * @return the rutaCancionActual
	 */
	public Track getCancionActual() {
		return cancionActual;
	}

	/**
	 * @param track the rutaCancionActual to set
	 */
	public void setCancionActual(Track track) {
		this.cancionActual = track;
	}

	/**
	 * @return the posCancionActual
	 */
	public long getPosCancionActual() {
		return posCancionActual;
	}

	/**
	 * @param posCancionActual the posCancionActual to set
	 */
	public void setPosCancionActual(long posCancionActual) {
		this.posCancionActual = posCancionActual;
	}
	
	
	
}
