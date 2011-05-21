package IS2011.Interfaz;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JMenuItem;

public class ItemsMenu extends JMenuItem {
	 private Color colorPrimario = new Color(0x666f7f);
	   
	    private float tran= 0.5f;
	   /**
	    * Clase que extiende a JPanel modificando sus métodos para que lo pinte con bordes redondos 
	    * y con degradado de dos colores de fondo
	    */
	    public ItemsMenu(String datos) {
	        super();
	        this.setText(datos);
	        //this.setColorPrimario(Color.black);
			//this.setColorPrimario(Color.white);
	        setOpaque(false);
	        this.setBackground(Color.black);
	        this.setForeground(Color.white);
	        this.setBorderPainted(false);
	        
	       
	    }
	   
	   /**
	    * Sobreescirtura del métodos paintComponent que es el que nos pinta el JPanel
	    * 
	    */
	    @Override
	    protected void paintComponent(Graphics g) {

		    Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			AlphaComposite old = (AlphaComposite) g2.getComposite();
			g2.setComposite(AlphaComposite.SrcOver.derive(getTran()));
			super.paintComponent(g);
			g2.setComposite(old);
		        
	    }

	    public float getTran() {
	    	return tran;
	    	}

	    public void setTran(float tran) {
	    	this.tran = tran;
	    }


}
