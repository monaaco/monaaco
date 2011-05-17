package IS2011.bibliotecaXML;

import java.util.*;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("playlist")
public class Playlist {
	
	@XStreamAlias("trackList")
	private ArrayList<Track> lista;

	private int currentTrack;

	private boolean repeat;

	private boolean random;

	/**
	 * Constructora
	 */
	public Playlist(){
		lista = new ArrayList<Track>();
		currentTrack = -1;
		reset();
		setRandom(false);
		setRepeat(false);
	}

	/**
	 * @param ruta válida o falla.
	 * @throws file not found exception
	 */
	public boolean add(String fileName){
		return lista.add(new Track(fileName));
	}
	
	/**
	 * @param rutas válidas o falla.
	 * @throws file not found exception
	 */
	public void addAll(List<String> listaRutas){
		//TODO
		/*while
		return lista.add(new Track(fileName));*/
	}	
	
	/**
	 *
	 * @return la lista de reproducción en un arrayList
	 */
	public ArrayList<Track> getEntries(){
		return (ArrayList<Track>) lista;
	}

	/**
	 * 
	 * @param track
	 * @return posición del track
	 */
	public int getIndex(Track track){
		return lista.indexOf(track);
	}

	/**
	 * 
	 * @param index
	 * @return posición del track
	 */
	public Track getTrack(int index){
		return (Track) lista.get(index);
	}
	
	/**
	 * 
	 * @return array con la info de los tracks
	 */
	public String[] getListado(){
		int tamaño =this.getNumTracks();
		String[] array = new String[tamaño];
		Track aux = null;
		int pos=0;
		Iterator<Track> it = lista.iterator();
		while (it.hasNext()){
			aux = (Track)it.next();
			String nombre = aux.getArtist()+(" - ")+aux.getName();
			array[pos] = nombre;
			pos++;
		}
		return array;
	}
	
	
	/**
	 * 
	 * @return Track actual
	 */
	public Track getCurrent(){
		if(getCurrentTrack() == -1){
			return null;
		}
		return getTrack(currentTrack);
	}

	/**
	 * 
	 * @param track
	 */
	public void setCurrentTrack(Track track){
		currentTrack = getIndex(track);
	}
	
	/**
	 * 
	 * @param track
	 */
	public void setCurrentTrack(int pos){
		currentTrack = pos;
	}
	
	/**
	 * 
	 * @return int con la posición de la cancion actual
	 */
	public int getCurrentTrack(){
		if((currentTrack == -1) && (!lista.isEmpty())){
			currentTrack = 0;
			return currentTrack;
		}else if(currentTrack == -1){
			return -1;
		}
		return currentTrack;
	}
	
	/**
	 * resetea el current track
	 */
	public void reset(){
		if (lista.isEmpty()){
			currentTrack = -1;
		}
		else{
			currentTrack = 0;
		}
	}

	/**
	 * Vacia la lista de reproducción
	 */
	public void clear()
	{
		lista.clear();
		reset();
	}

	/**
	 * Devuelve la siguiente canción marcándola como currentTrack
	 * @return siguiente track
	 */
	public Track next(){
		//Comprobar si no hay canciones
		if(getCurrentTrack() == -1) return null;
		//Comprobar si es la última canción
		if(getCurrentTrack() == (getNumTracks() - 1)){
			//si esta en modo repeat devolver la primera
			if(repeat){
				currentTrack = 0;
				return this.getTrack(currentTrack);
			//si esta en random devolver cualquiera
			}else if (random){
				currentTrack = (int)( Math.random() % (getNumTracks() - 1));
				return this.getTrack(currentTrack);
			// si no, no hay siguiente
			}else return null;
		}
		currentTrack++;
		return this.getTrack(currentTrack);
	}
	
	/**
	 * 
	 * @return track anterior
	 */
	public Track previous()	{
		if((getCurrentTrack() == -1)) return null;
		if( getCurrentTrack() == 0){
			if(isRepeat()){
				currentTrack = getNumTracks() - 1;
				return this.getTrack(currentTrack);
			}else if (isRandom()){
				currentTrack = (int)( Math.random() % (getNumTracks() - 1));
				return this.getTrack(currentTrack);
			}else return null;
		}
		currentTrack--;
		return this.getTrack(currentTrack);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumTracks(){
		return lista.size();
	}
	
	/**
	 * 
	 * @return repeat
	 */
	public boolean isRepeat() {
		return repeat;
	}
	
	/**
	 * 
	 * @param b
	 */
	public void setRepeat(boolean b) {
		repeat = b;
	}
	
	/**
	 * 
	 * @return random
	 */
	public boolean isRandom() {
		return random;
	}

	/**
	 * 
	 * @param b
	 */
	public void setRandom(boolean b) {
		random = b;
	}

	/**
	 * 
	 * @return
	 */
	public int getCurrentNumber() {
		return currentTrack;
	}
	
	/**
	 * 
	 * Borramos los elementos de la lista
	 */
	public void borraTrack(int[] listaTracks){
		for(int i = listaTracks.length-1;i >= 0 ; i--){
			lista.remove(listaTracks[i]);
		}
	}
}