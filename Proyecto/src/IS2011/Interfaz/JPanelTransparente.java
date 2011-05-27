package IS2011.Interfaz;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Redefinidmos el la clase ya creada JPanelRound para conseguir transparancias
 * 
 *
 */
public class JPanelTransparente extends JPanelRound{


/**
 * @uml.property  name="tran"
 */
private float tran= 0.8f;

public JPanelTransparente(){

}

@Override
/**
 * Redefinicón del metodo paintComponents para el JPanelTransparente, utilizamos el patron "Composite"
 * redefinimos la clase JPanel unificando la JPanleRound y los métodos de esta nueva
 */
protected void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
	g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	AlphaComposite old = (AlphaComposite) g2.getComposite();
	g2.setComposite(AlphaComposite.SrcOver.derive(getTran()));
	super.paintComponent(g);
	g2.setComposite(old);  //Patron composite
}

/**
 * Devuelve el porcentaje de transparencia
 * @return  valor del porcentaje de transparencia
 * @uml.property  name="tran"
 */
public float getTran() {
return tran;
}

/**
 * Cambia el porcentaje de transparencia
 * @param tran  nuevo porcentaje de transparencia
 * @uml.property  name="tran"
 */
public void setTran(float tran) {
this.tran = tran;
}

}