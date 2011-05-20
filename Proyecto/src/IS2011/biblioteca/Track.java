package IS2011.biblioteca;

import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jaudiotagger.audio.*;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.*;

import org.jaudiotagger.tag.datatype.Artwork;

import com.thoughtworks.xstream.annotations.*;


@XStreamAlias("track")
public class Track {
        
		@XStreamOmitField
        static long sid = 0;
		
	   	@XStreamAsAttribute
        private long id;
        
        private String name = null;
        
        private String artist = null;
        
        private String albumArtist = null;
        
        private String album = null;
        
		private String genre = null;
		
        private String kind = null;
        
        private long size = 0;
        
        private Integer totalTime = null;
        
        private Integer year = null;
        
        private Date dateModified = null;
        
        private Date dateAdded = null;
        
        private Integer bitRate = null;
        
        private Integer sampleRate = null;
        
        private String comments = null;
        
        private String trackType = null;
        
        private String location = null;
        
       
        
        //private Tag tag = null;
 

        public Track(String ruta){
            try {
                File f = new File(ruta);
                AudioFile af = AudioFileIO.read(f);
                Tag tag = af.getTagOrCreateAndSetDefault();
                AudioHeader ah = af.getAudioHeader();
                
                this.location = ruta;
                
                if(tag != null )
                {
                	this.name = tag.getFirst(FieldKey.TITLE);
                	if(name ==""){
                		name = "No title";
                	}
                }
                if(tag != null)
                {
                	this.artist = tag.getFirst(FieldKey.ARTIST);
                	if(artist == ""){
                		artist = "No Artist";
                	}
                }
                if(tag != null && !tag.hasField(FieldKey.ALBUM_ARTIST.name()))
                {
                	this.albumArtist = tag.getFirst(FieldKey.ALBUM_ARTIST);
                }
                else
                {
                	this.albumArtist ="";
                }
                this.bitRate = Integer.valueOf(ah.getBitRate());
                if(tag != null && !tag.hasField(FieldKey.COMMENT.name()))
                {
                	this.comments = tag.getFirst(FieldKey.COMMENT);
                }
                else
                {
                	this.comments = "";
                }
                if(tag != null && !tag.hasField(FieldKey.ALBUM.name()))
                {
                	this.album = tag.getFirst(FieldKey.ALBUM);
                }
                else
                {
                	this.album = "";
                }
                if(tag != null && !tag.hasField(FieldKey.GENRE.name()))
                {
                	this.genre = tag.getFirst(FieldKey.GENRE);
                }
                else
                {
                	this.genre= "";
                }
                this.kind = ah.getFormat();
 
	
                setId(sid++);    
                
                this.totalTime = ah.getTrackLength();
                
                this.size = f.getTotalSpace();
                
            } catch (Exception e) {
            		//TODO SHOW DIALOG
                    System.out.println(e.getMessage());
            }
                
        }
        

        
        public List<Artwork> getArtworkList() {
			try {
	        	File f = new File(getLocation());
	            AudioFile af;
				af = AudioFileIO.read(f);
				Tag tag = af.getTag();
				return tag.getArtworkList();
			} catch (CannotReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TagException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ReadOnlyFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAudioFrameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
        }
        
        /**
         * Accesora para la carátula del archivo
         * Abre el archivo y la obtiene sin tener que tenerla 
         * cargada durante toda la ejecución del programa
         * 
         * @return Primera carátula del archivo
         */
        public BufferedImage getArtwork() {
                        try {
                        		File f = new File(getLocation());
                        		AudioFile af = AudioFileIO.read(f);
                        		Tag tag = af.getTag();
                        		if(getArtworkList().size() <= 0) return null;
                                return tag.getArtworkList().get(0).getImage();
                        } catch (IOException e) {
                                e.printStackTrace();
                    			JOptionPane.showMessageDialog(new JFrame(),
                    				    "Error al obtener la carátula.",
                    				    "Error",
                    				    JOptionPane.WARNING_MESSAGE);
                    	} catch (CannotReadException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TagException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ReadOnlyFileException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvalidAudioFrameException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                return null;
        }
        
        /**
         * Accesora para el número de carátulas
         * 
         * @return número carátula de carátulas
         */
        public int getNumCaratulas() {
            try {
        		File f = new File(getLocation());
        		AudioFile af = AudioFileIO.read(f);
        		Tag tag = af.getTag();
        		if(tag!=null){
        			return tag.getArtworkList().size();
        		}
        		else 
        			return 0;
	        } catch (IOException e) {
	                e.printStackTrace();
	    			JOptionPane.showMessageDialog(new JFrame(),
	    				    "Error al obtener la carátula.",
	    				    "Error",
	    				    JOptionPane.WARNING_MESSAGE);
	    	} catch (CannotReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TagException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ReadOnlyFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAudioFrameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
        }
        
        /**
         * Mutadora de las carátulas del archivo
         * 
         * @return Primera carátula del archivo
         * @param artworkList Lista de carátulas
         */
        public void setArtworkList(List<Artwork> artworkList) {
        	//TODO
        }

       

        /**
         * Accesora para la id de la canción
         * 
         * @return Primera carátula del archivo
         */
        public long getId() {
                return id;
        }

        /**
         * Mutadora de la id de canción
         * Es privada, ya que sólo se debe modificar al crearse la canción en la constructora
         * 
         * @param id 
         */
        private void setId(long id) {
                this.id = id;
        }

        /**
         * Accesora del albúm de la canción
         * 
         * @return album  
         */
        public String getAlbum() {
			return album;
		}

        /**
         * Mutadora  del albúm de la canción
         * Es privada, ya que sólo se debe modificar al crearse la canción en la constructora
         * 
         * @param id 
         * @throws InvalidAudioFrameException 
         * @throws ReadOnlyFileException 
         * @throws TagException 
         * @throws IOException 
         * @throws CannotReadException 
         * @throws CannotWriteException 
         */
		public void setAlbum(String album) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
			this.album = album;
			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
			Tag tag = f.getTagOrCreateAndSetDefault();
			tag.setField(FieldKey.ALBUM, album);
			f.commit();
		}

		/**
		 * 
		 * @return nombre del artista de la canción
		 */
        public String getName() {
                return name;
        }


        /**
         * 
         * @param name
         * @throws InvalidAudioFrameException 
         * @throws ReadOnlyFileException 
         * @throws TagException 
         * @throws IOException 
         * @throws CannotReadException 
         * @throws CannotWriteException 
         */
        public void setName(String name) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
            this.name = name;
			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
			Tag tag = f.getTagOrCreateAndSetDefault();
			tag.setField(FieldKey.TITLE, name);
			f.commit();
    	}


        /**
         * 
         * @return Artista de la canción
         */
        public String getArtist() {
                return artist;
        }


        /**
         * 
         * @param artist
         * @throws InvalidAudioFrameException 
         * @throws ReadOnlyFileException 
         * @throws TagException 
         * @throws IOException 
         * @throws CannotReadException 
         * @throws CannotWriteException 
         */
        public void setArtist(String artist) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
            this.artist = artist;
			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
			Tag tag = f.getTagOrCreateAndSetDefault();
			tag.setField(FieldKey.ARTIST, artist);
			f.commit();
    	}


        /**
         * 
         * @return Artista del album de la cancón
         */
        public String getAlbumArtist() {
            return albumArtist;
    	}


        /**
         * 
         * @param albumArtist
         * @throws KeyNotFoundException 
         * @throws InvalidAudioFrameException 
         * @throws ReadOnlyFileException 
         * @throws TagException 
         * @throws IOException 
         * @throws CannotReadException 
         * @throws CannotWriteException 
         */
        public void setAlbumArtist(String albumArtist) throws KeyNotFoundException, CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
            this.albumArtist = albumArtist;
			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
			Tag tag = f.getTagOrCreateAndSetDefault();
			tag.setField(FieldKey.ALBUM_ARTIST, albumArtist);
			f.commit();
		}


        /**
         * 
         * @return Género de la canción
         */
        public String getGenre() {
                return genre;
        }


        /**
         * 
         * @param genre
         * @throws InvalidAudioFrameException 
         * @throws ReadOnlyFileException 
         * @throws TagException 
         * @throws IOException 
         * @throws CannotReadException 
         * @throws CannotWriteException 
         */
        public void setGenre(String genre) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
            this.genre = genre;
			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
			Tag tag = f.getTagOrCreateAndSetDefault();
			tag.setField(FieldKey.GENRE, genre);
			f.commit();
		}

        /**
         * 
         * @return tipo de archivo de audio
         */
        public String getKind() {
                return kind;
        }

        /**
         * 
         * @return Tamaño del archivo
         */
        public long getSize() {
                return size;
        }

        /**
         * 
         * @return Tiempo total de la canción
         */
        public Integer getTotalTime() {
                return totalTime;
        }

        /**
         * 
         * @return Año de la canción
         */
        public Integer getYear() {
                return year;
        }

        /**
         *  Mutadora, cambia el tag year de la canción
         * @param year
         */
        public void setYear(Integer year) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
            this.year = year;
			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
			Tag tag = f.getTagOrCreateAndSetDefault();
			tag.setField(FieldKey.YEAR, String.valueOf(year));
			f.commit();
        }

        /**
         * 
         * @return fecha de modificación
         */
        public Date getDateModified() {
                return dateModified;
    			//TODO
        }
        
        /**
         * 
         * @param dateModified
         */
        public void setDateModified(Date dateModified) {
                this.dateModified = dateModified;
        }

        /**
         * 
         * @return fecha de adición
         */
        public Date getDateAdded() {
                return dateAdded;
        }

        /**
         * 
         * @param dateAdded
         */
        public void setDateAdded(Date dateAdded) {
                this.dateAdded = dateAdded;
        }

        /**
         * 
         * @return bitrate de la canción
         */
        public Integer getBitRate() {
                return bitRate;
        }

        /**
         * 
         * @return bitrate de una muestra de la canción
         */
        public Integer getSampleRate() {
                return sampleRate;
        }

        /**
         * 
         * @return comentarios del tag de  la canción
         */
        public String getComments() {
                return comments;
        }

        /**
         * 
         * @param comments
         * @throws InvalidAudioFrameException 
         * @throws ReadOnlyFileException 
         * @throws TagException 
         * @throws IOException 
         * @throws CannotReadException 
         * @throws CannotWriteException 
         */
        public void setComments(String comments) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
                this.comments = comments;
    			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
    			Tag tag = f.getTagOrCreateAndSetDefault();
    			tag.setField(FieldKey.COMMENT, comments);
    			f.commit();
        }


        /**
         * 
         * @return Tipo de track
         */
        public String getTrackType() {
                return trackType;
        }

        /**
         * 
         * @return location del archivo
         */
        public String getLocation() {
                return location;
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

		
		/**
		 *  Devuelve la información principal de la canción
		 *  @return string con info del artusta y título
		 */
		public String toString(){
                String aux = "Artist: " + getArtist();
                aux += "\n Name: " + name + "\n";
                return aux;             
        }
}
