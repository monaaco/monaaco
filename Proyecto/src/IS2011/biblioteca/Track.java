package IS2011.biblioteca;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("track")
public class Track {
        
		@XStreamOmitField
        static long sid = 0;
		
	   	/**
		 * @uml.property  name="id"
		 */
	   	@XStreamAsAttribute
        private long id;
        
        /**
		 * @uml.property  name="name"
		 */
        private String name = null;
        
        /**
		 * @uml.property  name="artist"
		 */
        private String artist = null;
        
        /**
		 * @uml.property  name="albumArtist"
		 */
        private String albumArtist = null;
        
        /**
		 * @uml.property  name="album"
		 */
        private String album = null;
        
		/**
		 * @uml.property  name="genre"
		 */
		private String genre = null;
		
        /**
		 * @uml.property  name="kind"
		 */
        private String kind = null;
        
        /**
		 * @uml.property  name="size"
		 */
        private long size = 0;
        
        /**
		 * @uml.property  name="totalTime"
		 */
        private Integer totalTime = null;
        
        /**
		 * @uml.property  name="year"
		 */
        private Integer year = null;
        
        /**
		 * @uml.property  name="dateModified"
		 */
        private Date dateModified = null;
        
        /**
		 * @uml.property  name="dateAdded"
		 */
        private Date dateAdded = null;
        
        /**
		 * @uml.property  name="bitRate"
		 */
        private Integer bitRate = null;
        
        /**
		 * @uml.property  name="sampleRate"
		 */
        private Integer sampleRate = null;
        
        /**
		 * @uml.property  name="comments"
		 */
        private String comments = null;
        
        /**
		 * @uml.property  name="trackType"
		 */
        private String trackType = null;
        
        /**
		 * @uml.property  name="location"
		 */
        private String location = null;
        
       
        
        //private Tag tag = null;
 
        /**
         * Constructora de Track a partir de la ruta de un archivo de audio.
         */
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
        

        /**
         * Nos devuelve las caratulas del track
         * @return List<Artwork>
         */
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
         * Accesora para la car�tula del archivo
         * Abre el archivo y la obtiene sin tener que tenerla 
         * cargada durante toda la ejecuci�n del programa
         * 
         * @return Primera car�tula del archivo
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
                    				    "Error al obtener la car�tula.",
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
         * Accesora para el n�mero de car�tulas
         * 
         * @return n�mero car�tula de car�tulas
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
	    				    "Error al obtener la car�tula.",
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
         * Mutadora de las car�tulas del archivo
         * 
         * @return Primera car�tula del archivo
         * @param artworkList Lista de car�tulas
         */
        public void setArtworkList(List<Artwork> artworkList) {
        	//TODO
        }

       

        /**
		 * Accesora para la id de la canci�n
		 * @return  Primera car�tula del archivo
		 * @uml.property  name="id"
		 */
        public long getId() {
                return id;
        }

        /**
		 * Mutadora de la id de canci�n Es privada, ya que s�lo se debe modificar al crearse la canci�n en la constructora
		 * @param  id
		 * @uml.property  name="id"
		 */
        private void setId(long id) {
                this.id = id;
        }

        /**
		 * Accesora del alb�m de la canci�n
		 * @return  album
		 * @uml.property  name="album"
		 */
        public String getAlbum() {
			return album;
		}

        /**
		 * Mutadora  del alb�m de la canci�n Es privada, ya que s�lo se debe modificar al crearse la canci�n en la constructora
		 * @param id  
		 * @throws InvalidAudioFrameException  
		 * @throws ReadOnlyFileException  
		 * @throws TagException  
		 * @throws IOException  
		 * @throws CannotReadException  
		 * @throws CannotWriteException
		 * @uml.property  name="album"
		 */
		public void setAlbum(String album) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
			this.album = album;
			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
			Tag tag = f.getTagOrCreateAndSetDefault();
			tag.setField(FieldKey.ALBUM, album);
			f.commit();
		}

		/**
		 * @return  nombre del artista de la canci�n
		 * @uml.property  name="name"
		 */
        public String getName() {
                return name;
        }


        /**
		 * @param name
		 * @throws InvalidAudioFrameException  
		 * @throws ReadOnlyFileException  
		 * @throws TagException  
		 * @throws IOException  
		 * @throws CannotReadException  
		 * @throws CannotWriteException
		 * @uml.property  name="name"
		 */
        public void setName(String name) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
            this.name = name;
			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
			Tag tag = f.getTagOrCreateAndSetDefault();
			tag.setField(FieldKey.TITLE, name);
			f.commit();
    	}


        /**
		 * @return  Artista de la canci�n
		 * @uml.property  name="artist"
		 */
        public String getArtist() {
                return artist;
        }


        /**
		 * @param artist
		 * @throws InvalidAudioFrameException  
		 * @throws ReadOnlyFileException  
		 * @throws TagException  
		 * @throws IOException  
		 * @throws CannotReadException  
		 * @throws CannotWriteException
		 * @uml.property  name="artist"
		 */
        public void setArtist(String artist) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
            this.artist = artist;
			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
			Tag tag = f.getTagOrCreateAndSetDefault();
			tag.setField(FieldKey.ARTIST, artist);
			f.commit();
    	}


        /**
		 * @return  Artista del album de la canc�n
		 * @uml.property  name="albumArtist"
		 */
        public String getAlbumArtist() {
            return albumArtist;
    	}


        /**
		 * @param albumArtist
		 * @throws KeyNotFoundException  
		 * @throws InvalidAudioFrameException  
		 * @throws ReadOnlyFileException  
		 * @throws TagException  
		 * @throws IOException  
		 * @throws CannotReadException  
		 * @throws CannotWriteException
		 * @uml.property  name="albumArtist"
		 */
        public void setAlbumArtist(String albumArtist) throws KeyNotFoundException, CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
            this.albumArtist = albumArtist;
			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
			Tag tag = f.getTagOrCreateAndSetDefault();
			tag.setField(FieldKey.ALBUM_ARTIST, albumArtist);
			f.commit();
		}


        /**
		 * @return  G�nero de la canci�n
		 * @uml.property  name="genre"
		 */
        public String getGenre() {
                return genre;
        }


        /**
		 * @param genre
		 * @throws InvalidAudioFrameException  
		 * @throws ReadOnlyFileException  
		 * @throws TagException  
		 * @throws IOException  
		 * @throws CannotReadException  
		 * @throws CannotWriteException
		 * @uml.property  name="genre"
		 */
        public void setGenre(String genre) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
            this.genre = genre;
			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
			Tag tag = f.getTagOrCreateAndSetDefault();
			tag.setField(FieldKey.GENRE, genre);
			f.commit();
		}

        /**
		 * @return  tipo de archivo de audio
		 * @uml.property  name="kind"
		 */
        public String getKind() {
                return kind;
        }

        /**
		 * @return  Tama�o del archivo
		 * @uml.property  name="size"
		 */
        public long getSize() {
                return size;
        }

        /**
		 * @return  Tiempo total de la canci�n
		 * @uml.property  name="totalTime"
		 */
        public Integer getTotalTime() {
                return totalTime;
        }

        /**
		 * @return  A�o de la canci�n
		 * @uml.property  name="year"
		 */
        public Integer getYear() {
                return year;
        }

        /**
		 * Mutadora, cambia el tag year de la canci�n
		 * @param  year
		 * @uml.property  name="year"
		 */
        public void setYear(Integer year) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
            this.year = year;
			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
			Tag tag = f.getTagOrCreateAndSetDefault();
			tag.setField(FieldKey.YEAR, String.valueOf(year));
			f.commit();
        }

        /**
		 * @return  fecha de modificaci�n
		 * @uml.property  name="dateModified"
		 */
        public Date getDateModified() {
                return dateModified;
    			//TODO
        }
        
        /**
		 * @param  dateModified
		 * @uml.property  name="dateModified"
		 */
        public void setDateModified(Date dateModified) {
                this.dateModified = dateModified;
        }

        /**
		 * @return  fecha de adici�n
		 * @uml.property  name="dateAdded"
		 */
        public Date getDateAdded() {
                return dateAdded;
        }

        /**
		 * @param  dateAdded
		 * @uml.property  name="dateAdded"
		 */
        public void setDateAdded(Date dateAdded) {
                this.dateAdded = dateAdded;
        }

        /**
		 * @return  bitrate de la canci�n
		 * @uml.property  name="bitRate"
		 */
        public Integer getBitRate() {
                return bitRate;
        }

        /**
		 * @return  bitrate de una muestra de la canci�n
		 * @uml.property  name="sampleRate"
		 */
        public Integer getSampleRate() {
                return sampleRate;
        }

        /**
		 * @return  comentarios del tag de  la canci�n
		 * @uml.property  name="comments"
		 */
        public String getComments() {
                return comments;
        }

        /**
		 * @param comments
		 * @throws InvalidAudioFrameException  
		 * @throws ReadOnlyFileException  
		 * @throws TagException  
		 * @throws IOException  
		 * @throws CannotReadException  
		 * @throws CannotWriteException
		 * @uml.property  name="comments"
		 */
        public void setComments(String comments) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
                this.comments = comments;
    			AudioFile f = AudioFileIO.read(new File(this.getLocation()));
    			Tag tag = f.getTagOrCreateAndSetDefault();
    			tag.setField(FieldKey.COMMENT, comments);
    			f.commit();
        }


        /**
		 * @return  Tipo de track
		 * @uml.property  name="trackType"
		 */
        public String getTrackType() {
                return trackType;
        }

        /**
		 * @return  location del archivo
		 * @uml.property  name="location"
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


		/**
		 * Redefinici�n del meteodo equals, compara solo por ubicaci�n del archivo
		 * asociado al Track
		 */
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
		 *  Devuelve la informaci�n principal de la canci�n
		 *  @return string con info del artusta y t�tulo
		 */
		public String toString(){
                String aux = "Artist: " + getArtist();
                aux += "\n Name: " + name + "\n";
                return aux;             
        }
}
