package IS2011.Interfaz;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class BotonAvanzado extends JButton {
	
	private Color colorFondo, colorPresionado;
	private Shape figura;
	private ImageIcon iCon;
	private ImageIcon iConS;
	
	/**
	 * Clase que nos va a generar un botón redondo con la posibilidad de cambiar su imagen
	 * cuando se pulse o se pase el ratón opr encima
	 * @param imagen
	 * @param imagenS
	 */
	public BotonAvanzado(ImageIcon imagen,ImageIcon imagenS)
	{
		super(imagen);
		iCon = imagen;
		
		iConS = imagenS;
		//colorFondo=fon;
		//colorPresionado=pre;
		setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
	}
	
	
	//Los siguientes dos getters son necesarios para los test
	
	/**
	 * Devuelve el icono principal
	 * return ImageIcon
	 * */
	public ImageIcon getICon(){
		return iCon;
	}
	
	/**
	 * Devuelve el icono secundario
	 * return ImageIcon
	 */
	public ImageIcon getIConS(){
		return iConS;
	}
	/**
	 * Método que pinta el componente y lo rellena de color si es necesario
	 */
	protected void paintComponent( Graphics g )
	{
		if(getModel().isArmed()){
				g.setColor(Color.black);
				//this.setIcon(iConS);
		}
		
		g.fillOval(21,3,getSize().width-42,getSize().height-7);
		super.paintComponent(g);
	}
	/**
	 * Define el color del contorno
	 */
	protected void paintBorder( Graphics g )
	{
		g.setColor(Color.black);
		//g.drawOval(17,3,getSize().width-38,getSize().height-7);
	}
	/** 
	 * Define el are que será sensible al clik del ratón, usamos la clase Ellipse2d para dibujar in
	 * círculo con los parametros que se le pasa
	 * @param x
	 * @param y
	 */
	public boolean contains(int x,int y)
	{
			
		if(figura==null || !figura.getBounds().equals(getBounds()))
				figura = new Ellipse2D.Float(21,3,getSize().width-42,getSize().height-7);
		return (figura.contains(x,y));
	}

}
