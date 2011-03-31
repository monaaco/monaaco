public class Track {

	private String author = null;
	private String title = null;
	private Integer tiempo = null;
		
	public Track(){
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	
	public void setTime(Integer tiempo){
		this.tiempo = tiempo;
	}
	
	public String getAuthor(){
		return this.author;
	}
	
	public String getTitle(){
		return title;
	}
	
	public Integer getTiempo(){
		return tiempo;
	}
	
	public String toString(){
		String aux = "El autor es: " + author;
		aux += "\nEl título es: " + title;
		aux += "\nEl tiempo es: " + tiempo;
		aux += "\n";
		return aux;		
	}
	
}

