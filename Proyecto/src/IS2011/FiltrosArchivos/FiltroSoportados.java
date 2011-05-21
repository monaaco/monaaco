package IS2011.FiltrosArchivos;
import java.io.File;

import javax.swing.filechooser.FileFilter;


public class FiltroSoportados extends FileFilter{

	@Override	
	/**
	 * Redefinción del metedo accept. Acepta archivos .wav .ogg .mp3 y directorios.
	 * @param File f 
	 */
	public boolean accept(File f) {
        if (f.getName().toLowerCase().endsWith(".wav")
            || f.getName().toLowerCase().endsWith(".ogg")
            || f.getName().toLowerCase().endsWith(".mp3")
        	|| f.isDirectory()) {
            return true;
        }
        return false;
	}

	@Override
	/**
	 * Devuelve la descripción del filtro
	 * returns String
	 */
	public String getDescription() {
        return ("Archivos de audio soportados.(*.WAV; *.MP3; *.OGG)");
	}

}
