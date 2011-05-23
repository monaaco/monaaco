package IS2011.Interfaz;


import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Clase que nos va a proporcionar la fincionalidad de mover los componentes con el click y arrastre del ratón
 * redefiniendo los métodos necesario, luego solamente habrá que añadirsela a listener de algun componente
 * @implements MouseMotionListener
 * @implements MouseListener
 * 
 *
 */
public class Mover implements MouseMotionListener, MouseListener{
	JComponent target;
    Point start_drag;
    Point start_loc;
    
    public Mover(JComponent target) {
        this.target = target;
    }
    public static JFrame getFrame(Container target) {
	    if (target instanceof JFrame) {
	      return (JFrame) target;
	    }
	    return getFrame(target.getParent());
    }

    /**
     * Obtenemos el lugar donde se pincho de la pantalla
     * @param e Entra por parámetro un mouseEvent para conocer la posición del ratón.
     * @return el punto de la pantalla donde está el ratón.
     */
    Point getScreenLocation(MouseEvent e) {
	    Point cursor = e.getPoint();
	    Point target_location = this.target.getLocationOnScreen();
	    return new Point((int) (target_location.getX() + cursor.getX()),
	        (int) (target_location.getY() + cursor.getY()));
    }
    
    /**
     * Movemos el component al sitio donde soltamos el ratón
     * @param e Entra por parámetro un mouseEvent para saber la posición del ratón.
     */
    public void mouseDragged(MouseEvent e) {
        Point current = this.getScreenLocation(e);
        Point offset = new Point((int) current.getX() - (int) start_drag.getX(),(int) current.getY() - (int) start_drag.getY());
        JFrame frame = getFrame(target);
        Point new_location = new Point((int) (this.start_loc.getX() + offset.getX()), (int) (this.start_loc.getY() + offset.getY()));
        frame.setLocation(new_location);
    }


    public void mouseMoved(MouseEvent e) {
    }


    public void mouseClicked(MouseEvent e) {
    }


    public void mousePressed(MouseEvent e) {
        this.start_drag = this.getScreenLocation(e);
        this.start_loc = getFrame(this.target).getLocation();
    }


    public void mouseReleased(MouseEvent e) {
    }


    public void mouseEntered(MouseEvent e) {
    }


    public void mouseExited(MouseEvent e) {
    }


}
