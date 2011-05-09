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
	}

	/**
	 * @param ruta válida o falla.
	 * @throws file not found exception?
	 */
	public boolean add(String fileName){
		return lista.add(new Track(fileName));
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
	 * @return
	 */
	public Track getCurrent(){
		if((currentTrack == -1) && (getNumTracks() != 0)){
			currentTrack = 0;
			return getTrack(0);
		}else if(currentTrack == -1){
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
		if( currentTrack ==  (getNumTracks() - 1)){
			if(repeat){
				currentTrack = 0;
				return this.getTrack(currentTrack);
			}else if (random){
				currentTrack = (int)( Math.random() % (getNumTracks() - 1));
				return this.getTrack(currentTrack);
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
		if( currentTrack == 0){
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
	

}