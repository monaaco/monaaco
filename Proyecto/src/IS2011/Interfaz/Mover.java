package IS2011.Interfaz;


import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Clase que nos va a proporcionar la fincionalidad de mover los componentes con el click y arrastre del rat�n
 * redefiniendo los m�todos necesario, luego solamente habr� que a�adirsela a listener de algun componente
 * @implements MouseMotionListener
 * @implements MouseListener
 * 
 *
 */
public class Mover implements MouseMotionListener, MouseListener{
	/**
	 * @uml.property  name="target"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	JComponent target;
    /**
	 * @uml.property  name="start_drag"
	 */
    Point start_drag;
    /**
	 * @uml.property  name="start_loc"
	 */
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
     * @param e Entra por par�metro un mouseEvent para conocer la posici�n del rat�n.
     * @return el punto de la pantalla donde est� el rat�n.
     */
    Point getScreenLocation(MouseEvent e) {
	    Point cursor = e.getPoint();
	    Point target_location = this.target.getLocationOnScreen();
	    return new Point((int) (target_location.getX() + cursor.getX()),
	        (int) (target_location.getY() + cursor.getY()));
    }
    
    /**
     * Movemos el component al sitio donde soltamos el rat�n
     * @param e Entra por par�metro un mouseEvent para saber la posici�n del rat�n.
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
