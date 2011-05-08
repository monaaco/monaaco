package IS2011.Interfaz;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class BotonAvanzado extends JButton {
	
	private Color colorFondo,colorPresionado;
	private Shape figura;
	private ImageIcon iCon;
	private ImageIcon iConS;
	
	
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
	public ImageIcon getICon(){
		return iCon;
	}
	
	public ImageIcon getIConS(){
		return iConS;
	}
	
	protected void paintComponent( Graphics g )
	{
		if(getModel().isArmed()){
				g.setColor(Color.black);
				//this.setIcon(iConS);
		}
		
		g.fillOval(21,3,getSize().width-42,getSize().height-7);
		super.paintComponent(g);
	}
	
	protected void paintBorder( Graphics g )
	{
		g.setColor(Color.black);
		//g.drawOval(17,3,getSize().width-38,getSize().height-7);
	}
	
	public boolean contains(int x,int y)
	{
			
		if(figura==null || !figura.getBounds().equals(getBounds()))
				figura = new Ellipse2D.Float(21,3,getSize().width-42,getSize().height-7);
		return (figura.contains(x,y));
	}

}
