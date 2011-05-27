package IS2011.Interfaz;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;


/**
 * Para conseguir un menu apropiado redefinimos el JPopUp menu, le dotamos
 * de bordes redondeados y transparencia
 * @ extends JPopUpMenu
 * @author Jazuma
 *
 */
public class Menu extends JPopupMenu{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
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
	 * @uml.property  name="tran"
	 */
	private float tran= 0.5f;
	
	/**
	 * @uml.property  name="ia"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="menuPrincipal:IS2011.Interfaz.InterfazAvanzada"
	 */
	private InterfazAvanzada ia;
	
	public Menu(InterfazAvanzada interfaz){
		super();
		ia = interfaz;
		this.setSize(300,300);
		this.setBounds(50, 50,300,300);

		ItemsMenu sincronizar = new ItemsMenu("Sincronizar Biblioteca");
		sincronizar.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent arg0) {
					ia.sincronizaBiblioteca();
				}
		});
		this.add(sincronizar);
		ItemsMenu preferencias = new ItemsMenu("Preferencias");
		preferencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreferenciasDialog pd = new PreferenciasDialog(ia, true);
				ia.repaint();
			}
		});
		this.add(preferencias);
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
		        try {
					URI uri = new URI("http://twitter.com/#!/Monaaco");
					String s = "<HTML>Monaaco Player V1.0 2011<br/><br/>Manuel Baez Sanchez<br/>Alfredo Cerezo Luna<br/>Jorge Cordero Sanchez<br/>Miguel González Perez<br/>Beatriz Torres Salcedo<br/> Mas información <FONT color=\"#000099\"><U>http://twitter.com/#!/Monaaco</U></FONT> .</HTML>";
			        if (Desktop.isDesktopSupported()) {
		                Desktop desktop = Desktop.getDesktop();
		                desktop.browse(uri);
			        }
					JOptionPane.showMessageDialog(null,s);
	            } catch (IOException e) {
					e.printStackTrace();

	            } catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});	
		this.add(acercaDe);
		
		ItemsMenu salir = new ItemsMenu("Salir");
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ia.salir();
			}
		});	
		this.add(salir);
		
		
		
		this.setColorPrimario(Color.black);
		this.setColorSecundario(Color.white);
		this.setBackground(Color.black);
		//this.setOpaque(true);
		this.setBorderPainted(false);
		
	}
	
	/**
	 * Redefinimos un método paintComponent para que lo pinte redondeado
	 * y con transparencia
	 * @param g Graphics usado para la interfaz.
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
	 
	 	/**
		 * Accesora al color primario del degradado
		 * @return  Color primario actual
		 * @uml.property  name="colorPrimario"
		 */
	 	public Color getColorPrimario() {
	        return colorPrimario;
	    }
	 	
	 	/**
		 * Establece el primer color del degradado
		 * @param colorPrimario  nuevo color primario
		 * @uml.property  name="colorPrimario"
		 */
	    public void setColorPrimario(Color colorPrimario) {
	        this.colorPrimario = colorPrimario;
	    }

	 	/**
		 * Accesora al color secundario del degradado
		 * @return  Color secundario actual
		 * @uml.property  name="colorSecundario"
		 */
	    public Color getColorSecundario() {
	        return colorSecundario;
	    }
	    
	 	/**
		 * Establece el segundo color del degradado
		 * @param colorSecundario  nuevo color secundario
		 * @uml.property  name="colorSecundario"
		 */
	    public void setColorSecundario(Color colorSecundario) {
	        this.colorSecundario = colorSecundario;
	    }

	 	/**
		 * Accesora al color del contorno del degradado
		 * @return  Color contorno actual
		 * @uml.property  name="colorContorno"
		 */
	    public Color getColorContorno() {
	        return colorContorno;
	    }

	 	/**
		 * Establece el color del contorno del degradado
		 * @param colorContorno  nuevo color del contorno
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
	    
	    /**
		 * Accesora al porcentaje de transparencia
		 * @return  porcentaje de transparencia
		 * @uml.property  name="tran"
		 */
	    public float getTran() {
	    	return tran;
	    }

	    /**
		 * Mutadora del porcentaje de transparencia
		 * @param tran  nuevo porcentaje de transparencia
		 * @uml.property  name="tran"
		 */
	    public void setTran(float tran) {
	    	this.tran = tran;
	    }

}
