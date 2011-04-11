package bibliotecaXML;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.Image;
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
                        id++;
                        setId(id);              
                        setTotalTime(ah.getTrackLength());
                        setArtworkList(tag.getArtworkList());
                        
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
                                return artworkList.get(1).getImage();
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                System.out.println("Error al obtener la carátula. \n" + e.getMessage());
                        }
                }
                return null;
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
                String aux = "Artist: " + getArtist();
                aux += "\n Name: " + name;
                aux += "\n Time: " + totalTime.toString();
                aux += "\n";
                return aux;             
        }
}
