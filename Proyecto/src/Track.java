import java.util.Date;


public class Track {
	
	private Integer id = null;
	private String name = null;
	private String artist = null;
	private String albumArtist = null;
	private String genre = null;
	private String kind = null;
	private Integer size = null;
	private Integer totalTime = null;
	private Integer year = null;
	private Date dateModified = null;
	private Date dateAdded = null;
	private Integer bitRate = null;
	private Integer sampleRate = null;
	private String comments = null;
	private String trackType = null;
	private String location = null;
	
	
	
	public Track(){
	}
	

	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getArtist() {
		return artist;
	}



	public void setArtist(String artist) {
		artist = artist;
	}



	public String getAlbumArtist() {
		return albumArtist;
	}



	public void setAlbumArtist(String albumArtist) {
		this.albumArtist = albumArtist;
	}



	public String getGenre() {
		return genre;
	}



	public void setGenre(String genre) {
		this.genre = genre;
	}



	public String getKind() {
		return kind;
	}



	public void setKind(String kind) {
		this.kind = kind;
	}



	public Integer getSize() {
		return size;
	}



	public void setSize(Integer size) {
		this.size = size;
	}



	public Integer getTotalTime() {
		return totalTime;
	}



	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}



	public Integer getYear() {
		return year;
	}



	public void setYear(Integer year) {
		this.year = year;
	}



	public Date getDateModified() {
		return dateModified;
	}



	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}



	public Date getDateAdded() {
		return dateAdded;
	}



	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}



	public Integer getBitRate() {
		return bitRate;
	}



	public void setBitRate(Integer bitRate) {
		this.bitRate = bitRate;
	}



	public Integer getSampleRate() {
		return sampleRate;
	}



	public void setSampleRate(Integer sampleRate) {
		this.sampleRate = sampleRate;
	}



	public String getComments() {
		return comments;
	}



	public void setComments(String comments) {
		this.comments = comments;
	}



	public String getTrackType() {
		return trackType;
	}



	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String toString(){
		String aux = "El autor es: " + artist;
		aux += "\nEl título es: " + name;
		aux += "\nEl tiempo es: " + totalTime.toString();
		aux += "\n";
		return aux;		
	}
}
