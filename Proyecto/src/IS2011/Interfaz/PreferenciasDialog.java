package IS2011.Interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.colorchooser.ColorSelectionModel;

import IS2011.Configuracion.GestorPreferencias;
import IS2011.Configuracion.Preferencias;
import IS2011.biblioteca.Track;

public class PreferenciasDialog extends JDialog{
	

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel textoSkin;
	private JLabel textoRuta;
	private JLabel textoColorF;
	private JLabel textoColorB;
	
	/**
	 * Combo box con los skins disponibles (Carpetas dentro del directorio images)
	 */
	private JComboBox skins = null;
	
	private JButton selColFButton = null;
	private JButton selColBButton = null;
	private JButton guardarButton = null;
	private JButton cancelarButton = null;
	
	public PreferenciasDialog(JFrame comp, boolean modal){
		super(comp, modal);
		setTitle("Preferencias");
		init();
		setVisible(true);
		
	}

	public void init(){
		setSize(400,200);
		textoSkin = new JLabel("Carpeta del skin");
		textoRuta = new JLabel("Carpeta con la bilbioteca:");
		textoColorF = new JLabel("Color frontal");
		textoColorB = new JLabel("Color de fondo");
		
		GridBagConstraints constraints = new GridBagConstraints();
		this.getContentPane().setLayout(new GridBagLayout());
				
		
		
		//constraints.weightx = 1.0;
		//constraints.fill = GridBagConstraints.EAST;
	   //	main.add(getStopButton(), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST; 
		this.getContentPane().add(textoColorF,constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getSelColFButton(),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(textoColorB,constraints);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getSelColBButton(),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(textoRuta,constraints);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(textoSkin,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getGuardarButton(),constraints);
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getCancelarButton(),constraints);
		
		this.getContentPane().setBackground(Color.gray);
		this.getContentPane().setForeground(Color.white);
		
	}

	/**
	 * Botón color foreground
	 * @return
	 */
	private JButton getSelColFButton() {
		if (selColFButton == null){
			selColFButton = new JButton();
			selColFButton.setBackground(GestorPreferencias.getInstance().getFgColor());
			selColFButton.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(null, "Selecciona color frontal", GestorPreferencias.getInstance().getFgColor());
					if(newColor != null){
						GestorPreferencias.getInstance().setFgColor(newColor);
						selColFButton.setBackground(newColor);
					}
				}
			});
		}
		return selColFButton;
	}

	/**
	 * 	Botón color backgroud
	 * @return
	 */
	private JButton getSelColBButton() {
		if (selColBButton == null){
			selColBButton = new JButton();		
			selColBButton.setBackground(GestorPreferencias.getInstance().getBgColor());
			selColBButton.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(null, "Selecciona color de fondo", GestorPreferencias.getInstance().getBgColor());
					if(newColor != null){
						selColBButton.setBackground(newColor);
					}
				}
			});
		}
		return selColBButton;
	}
	
	/**
	 * 	Botón guardar, guarda las preferencias
	 * @return
	 */
	private JButton getGuardarButton() {
		if (guardarButton == null){
			guardarButton = new JButton("Guardar");		
			guardarButton.setBackground(GestorPreferencias.getInstance().getBgColor());
			guardarButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//TODO obtener los datos del formulario y pasarselos a guardarPreferencias
					
					guardarPreferencias("skinMig", getSelColBButton().getBackground(), getSelColFButton().getBackground());
					//TODO salir.
				}
			});
		}
		return guardarButton;
	}
	
	/**
	 * 	Botón cancelar, para salir sin guardar
	 * @return
	 */
	private JButton getCancelarButton() {
		if (cancelarButton == null){
			cancelarButton = new JButton("Cancelar");		
			cancelarButton.setBackground(GestorPreferencias.getInstance().getBgColor());
			cancelarButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//TODO salir.
					
				}
			});
		}
		return cancelarButton;
	}

	
	/**
	 * Modifica las preferencias de la clase GestorPreferencias
	 * @param skin ruta de la carpeta que contiene las imagenes del skin
	 * @param bgColor (background color)
	 * @param fgColor (foreground color)
	 */
	public void guardarPreferencias(String skin, Color bgColor, Color fgColor){
		GestorPreferencias.getInstance().setBgColor(bgColor);
		GestorPreferencias.getInstance().setFgColor(fgColor);
		GestorPreferencias.getInstance().setSkin("images/" + skin);
		GestorPreferencias.getInstance().guardarXML();
		JOptionPane.showConfirmDialog(null, "Los cambios se aplicarán al reiniciar la aplicación");
	}
	
}
