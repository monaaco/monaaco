package IS2011.Interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;


/**
 * Clase que extiende a JPanel modificando sus m�todos para que lo pinte con bordes redondos 
 * y con degradado de dos colores de fondo, los atributos arcw y arch nos miden el �ngulo de
 * las esquinas
 * @extends JPanel
 */
public class JPanelRound extends JPanel{

    /**
	 * @uml.property  name="colorPrimario"
	 */
    private Color colorPrimario = new Color(0x666f7f);
    /**
	 * @uml.property  name="colorSecundario"
	 */
    private Color colorSecundario = new Color(0x262d3d);
    /**
	 * @uml.property  name="colorContorno"
	 */
    private Color colorContorno = new Color(0x262d3d);
    /**
	 * @uml.property  name="arcw"
	 */
    private int arcw=20;
    /**
	 * @uml.property  name="arch"
	 */
    private int arch=20;
   /**
    * Clase que extiende a JPanel modificando sus m�todos para que lo pinte con bordes redondos 
    * y con degradado de dos colores de fondo
    */
    public JPanelRound() {
        super();
        setOpaque(false);
    }
   
   /**
    * Sobreescirtura del m�todos paintComponent que es el que nos pinta el JPanel
    * @param g Graphics usado para la interfaz.
    */
     @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
       
       
         Paint oldPaint = g2.getPaint();
          RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(
                    0,0,getWidth(),getHeight()-1,getArcw(),getArch());
            g2.clip(r2d);
            g2.setPaint(new GradientPaint(0.0f, 0.0f,getColorPrimario().darker(),
                    0.0f, getHeight(),getColorSecundario().darker()));
            g2.fillRect(0,0,getWidth(),getHeight());
           
            g2.setStroke(new BasicStroke(4f));
            g2.setPaint(new GradientPaint(0.0f, 0.0f,getColorContorno(),
                    0.0f, getHeight(), getColorContorno()));
            g2.drawRoundRect(0, 0, getWidth()-2 , getHeight() -2, 18, 18);
       
        g2.setPaint(oldPaint);
        super.paintComponent(g);
    }

    /**
	 * Devuelve el color primario
	 * @return  nos da el color primario
	 * @uml.property  name="colorPrimario"
	 */
    public Color getColorPrimario() {
        return colorPrimario;
    }

    /**
	 * Cambia el color primario
	 * @param colorPrimario  nuevo color primario
	 * @uml.property  name="colorPrimario"
	 */
    public void setColorPrimario(Color colorPrimario) {
        this.colorPrimario = colorPrimario;
    }

    /**
	 * Devuelve el color secundario
	 * @return  nos devuelve el color secundario
	 * @uml.property  name="colorSecundario"
	 */
    public Color getColorSecundario() {
        return colorSecundario;
    }


    /**
	 * Cambia el color secundario
	 * @param colorSecundario  Nuevo color secundario
	 * @uml.property  name="colorSecundario"
	 */
    public void setColorSecundario(Color colorSecundario) {
        this.colorSecundario = colorSecundario;
    }

    /**
	 * Devuelve el color del contorno
	 * @return  Color contorno
	 * @uml.property  name="colorContorno"
	 */
    public Color getColorContorno() {
        return colorContorno;
    }

    /**
	 * Cambia el color del contorno
	 * @param colorContorno  Nuevo color del contorno
	 * @uml.property  name="colorContorno"
	 */
    public void setColorContorno(Color colorContorno) {
        this.colorContorno = colorContorno;
    }

    /**
	 * @return
	 * @uml.property  name="arcw"
	 */
    public int getArcw() {
        return arcw;
    }

    /**
	 * @param arcw
	 * @uml.property  name="arcw"
	 */
    public void setArcw(int arcw) {
        this.arcw = arcw;
    }

    /**
	 * @return
	 * @uml.property  name="arch"
	 */
    public int getArch() {
        return arch;
    }

    /**
	 * @param arch
	 * @uml.property  name="arch"
	 */
    public void setArch(int arch) {
        this.arch = arch;
    }
}