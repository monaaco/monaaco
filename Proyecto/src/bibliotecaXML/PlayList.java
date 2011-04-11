package bibliotecaXML;

import java.net.URL;
import java.util.*;

public class Playlist {
	
	private List lista;

	private Track currentTrack;

	private boolean repeat;

	private boolean random;


	public Playlist(){
		lista = new LinkedList();
		reset();
	}

	/**
	 * 
	 * @param ruta v�lida o falla.
	 * @throws file not found exception?
	 */
	public void add(String fileName){
		
	}
	
	public LinkedList<Track> getEntries(){
		return (LinkedList<Track>) lista;
	}

	public int getIndex(Track track){
		return lista.indexOf(track);
	}

	// Devuelve la siguiente canci�n del playList
	public Track next(){
		
		Track nextTrack = getNext();

		if (nextTrack == null && repeat){
			if (!lista.isEmpty()){
				nextTrack = (Track) lista.get(0);
			}
		}

		if (nextTrack != null){
			selectTrack(false);
			currentTrack = nextTrack;
			selectTrack(true);
		}

		return nextTrack;
	}

	public Track current(){
		return currentTrack;
	}


	public Track previous(){
		Track prevTrack = getPrevious();

		if (prevTrack == null && repeat){
			if (!lista.isEmpty()){
				prevTrack = (Track) lista.get(lista.size() > 0 ? lista.size() - 1 : 0);
			}
		}

		if (prevTrack != null){
			selectTrack(false);
			currentTrack = prevTrack;
			selectTrack(true);
		}

		return prevTrack;
	}


	public void setCurrent(Track track){
		selectTrack(false);
		currentTrack = track;
		selectTrack(true);
	}

	public void reset(){
		// iterator = list.listIterator();
		if (lista.isEmpty())	{
			currentTrack = null;
		}
		else{
			currentTrack = (Track) lista.get(0);
		}
	}


	public void clear()
	{
		for (Iterator it = lista.iterator(); it.hasNext();){
			Track track = (Track) it.next();
			//fireEntryRemovedEvent(track);
		}

		lista.clear();
		reset();
	}


	private Track getNext()	{
		Track track = null;

		if (hasNext()) {
			track = (Track) lista.get(lista.indexOf(currentTrack) + 1);
		}

		return track;
	}

	private Track getPrevious()	{
		Track track = null;

		if (hasPrevious()) {
			track = (Track) lista.get(lista.indexOf(currentTrack) - 1);
		}

		return track;
	}

	private boolean hasNext() {
		if (currentTrack != null) {
			if (lista.indexOf(currentTrack) + 1 < lista.size()){
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	private boolean hasPrevious() {
		if (currentTrack != null) {
			if (lista.indexOf(currentTrack) - 1 >= 0){
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	private void selectTrack(boolean select){
		/*if (currentTrack != null){
			currentTrack.setRead(select);
			fireEntryChangedEvent(currentTrack);
		}*/
	}	

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean b) {
		repeat = b;
	}
	public boolean isRandom() {
		return random;
	}

	public void setRandom(boolean b) {
		random = b;
	}

}