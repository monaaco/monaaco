package IS2011.Configuracion;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

import IS2011.GestorXML.GestorXML;


/**
 * Adapter de Preferencias
 * @author usuario_local
 *
 */
public class GestorPreferencias {

	
	private GestorXML<Preferencias> gestorXML = null;
	
	/**
	 * Ruta de las Preferencias por defecto 
	 */	
	private final String rutaPreferencias = "xml/preferencias.xml";
			
	private Preferencias preferencias = null;
	
	/**
	 * Instancia de GestorPreferencias (Patrón singleton)
	 */
	private static GestorPreferencias instance = null;
	
	
	/**
	 * Patrón singleton.
	 * Devuelve la única instancia de la clase GestorPreferencias
	 */
	public static GestorPreferencias getInstance() {
		if(instance == null){
			instance = new GestorPreferencias();
		}
		return instance;
	}
	
	/**
	 * Crea unas Preferencias a partir de el archivo XML por defecto  (Patrón singleton)
	 * @param b
	 */
	private GestorPreferencias() {
		gestorXML = new GestorXML<Preferencias>(Preferencias.class);
		cargarXML();
	}

	/**
	 * Esta función devuelve la variable preferencias
	 */
	public Preferencias getPreferencias(){
		return preferencias;
	}
	
	/**
	 * Mutadora de la variable preferencias
	 * @param biblioteca
	 */
	private void setPreferencias(Preferencias preferencias){
		if(preferencias != null) this.preferencias = preferencias;
	}

	
	/**
	 * carga las Preferencias por defecto
	 */
	public void setDefault(){
		preferencias.setDefault();
	}
	
	/**
	 * Devuelve el nombre del skin
	 * @return String
	 */
	public String getSkin() {
		return getPreferencias().getSkin();
	}
	
	/**
	 * Cambia el skin asociado
	 * @param skin - String
	 */
	public void setSkin(String skin) {
		getPreferencias().setSkin(skin);
	}
	
	/**
	 * Devuelve el nombre del launcher
	 * @return String 
	 */
	public String getLauncher() {
		return 	getPreferencias().getLauncher();
	}
	
	/**
	 * Cambia el launcher asociado
	 * @param launcher - String
	 */
	public void setLauncher(String launcher) {
		getPreferencias().setLauncher(launcher);
	}
	
	/**
	 * Devuelve el nombre del sonido asociado
	 * @return
	 */
	public String getSound() {
		return getPreferencias().getSound();
	}
	
	/**
	 * Cambia el sonido asociado
	 * @param sound - String
	 */
	public void setSound(String sound) {
		getPreferencias().setSound(sound);
	}
	
	/**
	 * Devuelve el color de fondo
	 * @return Color
	 */
	public Color getBgColor() {
		return getPreferencias().getBgColor();
	}
	
	/**
	 * Cambia el color de fondo
	 * @param bgColor - Color
	 */
	public void setBgColor(Color bgColor) {
		getPreferencias().setBgColor(bgColor);
	}
	
	/**
	 * Devuelve el color frontal
	 * @return Color
	 */
	public Color getFgColor() {
		return getPreferencias().getFgColor();
	}
	
	/**
	 * Cambia el color frontal
	 * @param fgColor - Color
	 */
	public void setFgColor(Color fgColor) {
		getPreferencias().setFgColor(fgColor);
	}
	
	
	/**
	 * Guarda las Preferencias en XML en la ruta por defecto
	 */
	public void guardarXML(){
		gestorXML.guardar(getPreferencias(), rutaPreferencias);
	}
	
	/**
	 * Guarda las Preferencias en XML en la ruta indicada
	 * @param ruta
	 */
	public void guardarXML(String ruta){
		gestorXML.guardar(getPreferencias(), ruta);
	}
	
	/**
	 * Guarda la biblioteca en XML en el archivo indicado
	 * @param file
	 */
	public void guardarXML(File file){
		gestorXML.guardar(getPreferencias(), file);
	}

	/**
	 * Carga el archivo xml
	 */
	public void cargarXML() {
			cargarXML(rutaPreferencias);
	}
	
	/**
	 * Carga el archivo xml desde la ruta indicada
	 * @param ruta
	 */
	public void cargarXML(String ruta) {
		cargarXML(new File(ruta));
	}
	
	/**
	 * Carga el archivo xml indicado
	 * @param file
	 */
	public void cargarXML(File file) {
			try {
				setPreferencias(gestorXML.cargar(file));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
					    "Problema en la carga de las preferencias, el formato es erróneo.\n" +
					    "Se cargarán las preferencias por defecto.\n" +
					    e.getMessage(),
					    "Error carga del xml",
				JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
				//crear xml vacío y cargarlo
				setPreferencias(new Preferencias());
				try {
					file.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				guardarXML(file);
			}
	}

	/**
	 * 
	 * @return Color
	 */
	public Color getBgColorInterno() {
		return getPreferencias().getBgColorInterno();
	}
	
	/**
	 * 
	 * @return Color
	 */
	public Color getFgColorInterno() {
		return getPreferencias().getFgColorInterno();
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getRutaIndexada() {
		return getPreferencias().getRutaIndexada();
	}
	
	/**
	 * 
	 * @param ruta
	 */
	public void setRutaIndexada(String ruta) {
		getPreferencias().setRutaIndexada(ruta);
	}

	/**
	 * 
	 * @param bgColor
	 */
	public void setBgColorInterno(Color bgColor) {
		getPreferencias().setBgColorInterno(bgColor);
		
	}

	/**
	 * 
	 * @param fgColor
	 */
	public void setFgColorInterno(Color fgColor) {
		getPreferencias().setFgColorInterno(fgColor);
		
	}
		

}
