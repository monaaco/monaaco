package monaaco.bibliotecaXML;

import java.io.File;

public class Biblioteca {

	private File biblioteca = new File("xml/biblioteca.xml");

	public Biblioteca() {
		// TODO Auto-generated constructor stub
	}

	public File getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(File biblioteca) {
		this.biblioteca = biblioteca;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
}
