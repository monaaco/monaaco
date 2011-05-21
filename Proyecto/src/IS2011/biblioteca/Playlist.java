package IS2011.biblioteca;

import java.util.*;

import IS2011.biblioteca.comparators.*;

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
	 * @return posicion en el playlist de la canción añadida
	 */
	public int setCurrentTrack(Track track){
		return currentTrack = getIndex(track);
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
	 * @return tamaño de la lista
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
	 * Borramos los elementos de la lista y devolvemos si alguno de los borrados era el que se estaba reproduciendo
	 */
	public boolean borraTrack(int[] listaTracks){
		boolean b = false;
		for(int i = listaTracks.length-1;i >= 0 ; i--){
			if(getCurrentNumber() == listaTracks[i])b = true;
			if(currentTrack > listaTracks[i])setCurrentTrack(currentTrack-1);
			else if(currentTrack >= lista.size())setCurrentTrack(lista.size()-1);
			lista.remove(listaTracks[i]);
		}
		if(currentTrack >= lista.size())setCurrentTrack(lista.size()-1);
		return b;
	}
	
	/**
	 * Desordena la playlist actualizando el currentrack
	 * @return nueva posicion en el playlist de la canción actual
	 */
	public int desordenar(){
		
		//obtener la cancion actual en reproducción
		Track aux = getCurrent();
		
		//desordenar la lista
		Collections.shuffle(lista);
		
		//actualizar currentrac con la nueva posición de la cancion actual en reproducción
		return setCurrentTrack(aux);
	}
	
	/**
	 * Ordena la playlist por artista actualizando el currentrack
	 * @return nueva posicion en el playlist de la canción actual
	 */
	public int ordenarPorArtista(){
		
		//obtener la cancion actual en reproducción
		Track aux = getCurrent();
		
		//desordenar la lista
		Collections.sort(lista, new ComparatorArtista());
		
		//actualizar currentrac con la nueva posición de la cancion actual en reproducción
		return setCurrentTrack(aux);
	}
	
	/**
	 * Ordena la playlist por titulo actualizando el currentrack
	 * @return nueva posicion en el playlist de la canción actual
	 */
	public int ordenarPorTitulo(){
		
		//obtener la cancion actual en reproducción
		Track aux = getCurrent();
		
		//desordenar la lista
		Collections.sort(lista, new ComparatorTitulo());
		
		//actualizar currentrac con la nueva posición de la cancion actual en reproducción
		return setCurrentTrack(aux);
	}

	/**
	 * Mueve, intercala una cancion dentro de la playList
	 * @param inicio - int posición origen
	 * @param fin - int posición destino
	 */
	public void mueve(int inicio, int fin) {
		Track aux = getTrack(inicio);
		if(fin != lista.size())
		{
			lista.remove(inicio);
			lista.add(fin,aux);
		}
		else
		{
			lista.remove(inicio);
			lista.add(aux);
		}
	}
	
}