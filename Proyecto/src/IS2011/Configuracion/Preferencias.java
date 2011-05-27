package IS2011.Configuracion;

import java.awt.Color;

import IS2011.biblioteca.Track;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("preferencias")
public class Preferencias {

	
	/**
	 * @uml.property  name="rutaIndexada"
	 */
	private String rutaIndexada;
	
	/**
	 * @uml.property  name="skin"
	 */
	private String skin;
	/**
	 * @uml.property  name="launcher"
	 */
	private String launcher;
	/**
	 * @uml.property  name="sound"
	 */
	private String sound;
	
	/**
	 * @uml.property  name="bgColor"
	 */
	private Color bgColor;
	/**
	 * @uml.property  name="fgColor"
	 */
	private Color fgColor;
	/**
	 * @uml.property  name="bgColorInterno"
	 */
	private Color bgColorInterno;
	/**
	 * @uml.property  name="fgColorInterno"
	 */
	private Color fgColorInterno;
	
	/**
	 * @uml.property  name="cancionActual"
	 * @uml.associationEnd  
	 */
	@XStreamAlias("track")
	private Track cancionActual;
	/**
	 * @uml.property  name="posCancionActual"
	 */
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
	 * @return  the rutaIndexada
	 * @uml.property  name="rutaIndexada"
	 */
	public String getRutaIndexada() {
		return rutaIndexada;
	}

	/**
	 * @param rutaIndexada  the rutaIndexada to set
	 * @uml.property  name="rutaIndexada"
	 */
	public void setRutaIndexada(String rutaIndexada) {
		this.rutaIndexada = rutaIndexada;
	}

	/**
	 * @return  the bgColorInterno
	 * @uml.property  name="bgColorInterno"
	 */
	public Color getBgColorInterno() {
		return bgColorInterno;
	}

	/**
	 * @param bgColorInterno  the bgColorInterno to set
	 * @uml.property  name="bgColorInterno"
	 */
	public void setBgColorInterno(Color bgColorInterno) {
		this.bgColorInterno = bgColorInterno;
	}

	/**
	 * @return  the fgColorInterno
	 * @uml.property  name="fgColorInterno"
	 */
	public Color getFgColorInterno() {
		return fgColorInterno;
	}

	/**
	 * @param fgColorInterno  the fgColorInterno to set
	 * @uml.property  name="fgColorInterno"
	 */
	public void setFgColorInterno(Color fgColorInterno) {
		this.fgColorInterno = fgColorInterno;
	}

	/**
	 * Devuelve el nombre del skin
	 * @return  String
	 * @uml.property  name="skin"
	 */
	public String getSkin() {
		return skin;
	}
	
	/**
	 * Cambia el skin asociado
	 * @param skin  - String
	 * @uml.property  name="skin"
	 */
	public void setSkin(String skin) {
		this.skin = skin;
	}
	
	/**
	 * Devuelve el nombre del launcher
	 * @return  String
	 * @uml.property  name="launcher"
	 */
	public String getLauncher() {
		return launcher;
	}
	
	/**
	 * Cambia el launcher asociado
	 * @param launcher  - String
	 * @uml.property  name="launcher"
	 */
	public void setLauncher(String launcher) {
		this.launcher = launcher;
	}
	
	/**
	 * Devuelve el nombre del sonido asociado
	 * @return
	 * @uml.property  name="sound"
	 */
	public String getSound() {
		return sound;
	}
	
	/**
	 * Cambia el sonido asociado
	 * @param sound  - String
	 * @uml.property  name="sound"
	 */
	public void setSound(String sound) {
		this.sound = sound;
	}
	
	/**
	 * Devuelve el color de fondo
	 * @return  Color
	 * @uml.property  name="bgColor"
	 */
	public Color getBgColor() {
		return bgColor;
	}
	
	/**
	 * Cambia el color de fondo
	 * @param bgColor  - Color
	 * @uml.property  name="bgColor"
	 */
	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}
	
	/**
	 * Devuelve el color frontal
	 * @return  Color
	 * @uml.property  name="fgColor"
	 */
	public Color getFgColor() {
		return fgColor;
	}
	
	/**
	 * Cambia el color frontal
	 * @param fgColor  - Color
	 * @uml.property  name="fgColor"
	 */
	public void setFgColor(Color fgColor) {
		this.fgColor = fgColor;
	}

	/**
	 * @return  the rutaCancionActual
	 * @uml.property  name="cancionActual"
	 */
	public Track getCancionActual() {
		return cancionActual;
	}

	/**
	 * @param track  the rutaCancionActual to set
	 * @uml.property  name="cancionActual"
	 */
	public void setCancionActual(Track track) {
		this.cancionActual = track;
	}

	/**
	 * @return  the posCancionActual
	 * @uml.property  name="posCancionActual"
	 */
	public long getPosCancionActual() {
		return posCancionActual;
	}

	/**
	 * @param posCancionActual  the posCancionActual to set
	 * @uml.property  name="posCancionActual"
	 */
	public void setPosCancionActual(long posCancionActual) {
		this.posCancionActual = posCancionActual;
	}
	
	
	
}
