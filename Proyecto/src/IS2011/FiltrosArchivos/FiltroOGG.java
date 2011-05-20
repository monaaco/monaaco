package IS2011.FiltrosArchivos;
import java.io.File;

import javax.swing.filechooser.FileFilter;


public class FiltroOGG extends FileFilter{

	@Override
	public boolean accept(File f) {
        if (f.getName().toLowerCase().endsWith(".ogg")
        	|| f.isDirectory()) {
            return true;
        }
        return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return ("Archivos OGG.(*.OGG)");
	}

}