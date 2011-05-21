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


private float tran= 0.8f;

public JPanelTransparente(){

}

@Override
/**
 * Redefinicón del metodo paintComponents para el JPanelTransparente.
 */
protected void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
	g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	AlphaComposite old = (AlphaComposite) g2.getComposite();
	g2.setComposite(AlphaComposite.SrcOver.derive(getTran()));
	super.paintComponent(g);
	g2.setComposite(old);
}

/**
 * Devuelve el porcentaje de transparencia
 * @return float
 */
public float getTran() {
return tran;
}

/**
 * Cambia el porcentaje de transparencia
 * @param tran
 */
public void setTran(float tran) {
this.tran = tran;
}

}