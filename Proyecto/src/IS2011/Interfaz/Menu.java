package IS2011.Interfaz;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.sun.awt.AWTUtilities;
/**
 * Para conseguir un menu apropiado redefinimos el JPopUp menu, le dotamos
 * de bordes redondeados y transparencia
 * @ extends JPopUpMenu
 * @author Jazuma
 *
 */
public class Menu extends JPopupMenu{
	
	private Color colorPrimario = new Color(0x666f7f);
	private Color colorSecundario = new Color(0x262d3d);
	private Color colorContorno = new Color(0x262d3d);
	private int arcw=20;
	private int arch=20;

	private float tran= 0.5f;
	
	public Menu(){
		super();
		this.setSize(300,300);
		this.setBounds(50, 50,300,300);

		ItemsMenu eliminar = new ItemsMenu("Eliminar");
		this.add(eliminar);
		ItemsMenu monaaco = new ItemsMenu("MOnaaco");
		this.add(monaaco);
		ItemsMenu BodaAntuan = new ItemsMenu("BodaAntuan");
		this.add(BodaAntuan);
		ItemsMenu Castores = new ItemsMenu("Castores");
		this.add(Castores);
		ItemsMenu agregar = new ItemsMenu("Agregar");
		this.add(agregar);
		ItemsMenu preferencias = new ItemsMenu("Preferencias");
		ItemsMenu documentacion = new ItemsMenu("Consulta el manual");
		documentacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ System.getProperty("user.dir")+ "\\doc\\ManualdeusuarioMonaaco.pdf");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		this.add(documentacion);
		ItemsMenu acercaDe = new ItemsMenu("Acerca de Monnaco Player");
		acercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = "Monaaco Player V1.0 2011\n\nManuel Baez Sanchez\nAlfredo Cerezo Luna\nJorge Cordero Sanchez\nMiguel González Perez\nBeatriz Torres Salcedo";
                JOptionPane.showMessageDialog(null,s);
			}
		});	
		this.add(acercaDe);
		
		this.setColorPrimario(Color.black);
		this.setColorSecundario(Color.white);
		this.setBackground(Color.black);
		//this.setOpaque(true);
		this.setBorderPainted(false);
		
	}
	/**
	 * Redefinimos us método paintComponent para que lo pinte redondeado
	 * y con transparencia
	 */
	 protected void paintComponent(Graphics g) {
	     
		    Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			AlphaComposite old = (AlphaComposite) g2.getComposite();
			g2.setComposite(AlphaComposite.SrcOver.derive(getTran()));
		 
		 
		 	//Graphics2D g2 = (Graphics2D) g;
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
	          /*  g2.setPaint(new GradientPaint(0.0f, 0.0f,getColorContorno(),
	                    0.0f, getHeight(), getColorContorno()));*/
	            g2.drawRoundRect(0, 0, getWidth()-2 , getHeight() -2, 18, 18);
	       
	        g2.setPaint(oldPaint);
	        super.paintComponent(g);
	        
	        
	       // g2.setComposite(old);

	    }
	 	public Color getColorPrimario() {
	        return colorPrimario;
	    }
	 	/**
	 	 * Establece el primer color del degradado
	 	 * @param colorPrimario
	 	 */
	    public void setColorPrimario(Color colorPrimario) {
	        this.colorPrimario = colorPrimario;
	    }

	    public Color getColorSecundario() {
	        return colorSecundario;
	    }
	    /**
	     * Establece el color secundario del degradado
	     * @param colorSecundario
	     */
	    public void setColorSecundario(Color colorSecundario) {
	        this.colorSecundario = colorSecundario;
	    }

	    public Color getColorContorno() {
	        return colorContorno;
	    }

	    public void setColorContorno(Color colorContorno) {
	        this.colorContorno = colorContorno;
	    }

	    public int getArcw() {
	        return arcw;
	    }

	    public void setArcw(int arcw) {
	        this.arcw = arcw;
	    }

	    public int getArch() {
	        return arch;
	    }

	    public void setArch(int arch) {
	        this.arch = arch;
	    }
	    public float getTran() {
	    	return tran;
	    	}

	    public void setTran(float tran) {
	    	this.tran = tran;
	    }

}
