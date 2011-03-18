import javax.media.*;
import java.io.File;
import java.awt.*;

public class TrivialJMFPLayer extends Frame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static void main (String[] args) {
        try {
            Frame f = new TrivialJMFPLayer();
            f.pack();
            f.setVisible (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public TrivialJMFPLayer()
        throws java.io.IOException,
               java.net.MalformedURLException,
               javax.media.MediaException {
        FileDialog fd = new FileDialog
            (this, "TrivialJMFPlayer", FileDialog.LOAD);
        fd.setVisible(true);
        File f = new File (fd.getDirectory(), fd.getFile());
        Player p = Manager.createRealizedPlayer
            (f.toURI().toURL());
        //Component c = p.getVisualComponent();
        //add (c);
        p.start();
    }
}