package IS2011.FiltrosArchivos;
import java.io.File;

import javax.swing.filechooser.FileFilter;


public class FiltroSoportados extends FileFilter{

	@Override
	public boolean accept(File f) {
        if (f.getName().toLowerCase().endsWith(".OGG")
            || f.getName().toLowerCase().endsWith(".MP3")
            || f.getName().toLowerCase().endsWith(".ogg")
            || f.getName().toLowerCase().endsWith(".mp3")
        	|| f.isDirectory()) {
            return true;
        }
        return false;
	}

	@Override
	public String getDescription() {
        return ("Archivos de audio soportados.(*.MP3; *.OGG)");
	}

}
