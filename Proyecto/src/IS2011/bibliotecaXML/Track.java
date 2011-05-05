package IS2011.bibliotecaXML;

import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;

import org.jaudiotagger.audio.*;
import org.jaudiotagger.tag.*;

import org.jaudiotagger.tag.datatype.Artwork;


public class Track {
        
        static long sid = 0;
        
        private long id;
        private String name = null;
        private String artist = null;
        private String albumArtist = null;
        private String album = null;
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
        private List<Artwork> artworkList = null; 
        
        
        //private Tag tag = null;
        

        
        public Track(String ruta){
                try {
                        File f = new File(ruta);
                        AudioFile af = AudioFileIO.read(f);
                        Tag tag = af.getTag();
                        AudioHeader ah = af.getAudioHeader();
                        
                        setName(tag.getFirst(FieldKey.TITLE));
                        setArtist(tag.getFirst(FieldKey.ARTIST));
                        setAlbumArtist(tag.getFirst(FieldKey.ALBUM_ARTIST));
                        setBitRate(Integer.valueOf(ah.getBitRate()));
                        setComments(tag.getFirst(FieldKey.COMMENT));
                        setArtist(tag.getFirst(FieldKey.ARTIST));
                        setAlbum(tag.getFirst(FieldKey.ALBUM));
                        setGenre(tag.getFirst(FieldKey.GENRE));
                        // de momento el id creo que no nos es útil dejarlo 
                        // hasta que veamos si lo utilizamos en la biblioteca.
                        id++;
                        setId(id);              
                        setTotalTime(ah.getTrackLength());
                        setArtworkList(tag.getArtworkList());
                        setLocation(ruta);
                        
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }
                
        }
        

        
        public List<Artwork> getArtworkList() {
                return artworkList;
        }

        public BufferedImage getArtwork() {
                if(artworkList.size() > 0){
                        try {
                                return artworkList.get(0).getImage();
                        } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("Error al obtener la carátula. \n" + e.getMessage());
                        }
                }
                return null;
        }
        
        public int getNumCaratulas() {
        	if(artworkList == null) return 0;
            return artworkList.size();
    }
        public void setArtworkList(List<Artwork> artworkList) {
                this.artworkList = artworkList;
        }

        


        public long getId() {
                return id;
        }



        public void setId(long id) {
                this.id = id;
        }

        
        public String getAlbum() {
			return album;
		}



		public void setAlbum(String album) {
			this.album = album;
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
                this.artist = artist;
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


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((location == null) ? 0 : location.hashCode());
			return result;
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Track other = (Track) obj;
			if (location == null) {
				if (other.location != null)
					return false;
			} else if (!location.equals(other.location))
				return false;
			return true;
		}



		public String toString(){
                String aux = "Artist: " + getArtist();
                aux += "\n Name: " + name;
                aux += "\n Time: " + totalTime.toString();
                aux += "\n";
                return aux;             
        }
}
