package IS2011.Interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
	
	private JLabel textoColorFInterno;
	private JLabel textoColorBInterno;
	
	/**
	 * Combo box con los skins disponibles (Carpetas dentro del directorio images)
	 */
	private JComboBox skinsBox = null;
	private JTextField rutaMusica = null;
	
	private JButton selColFButton = null;
	private JButton selColBButton = null;
	
	private JButton selColFInternoButton = null;
	private JButton selColBInternoButton = null;
	
	private JButton guardarButton = null;
	private JButton cancelarButton = null;
	
	private JFrame comp = null;
	
	public PreferenciasDialog(JFrame comp, boolean modal){
		super(comp, modal);
		this.comp = comp;
		setTitle("Preferencias");
		init();
		setVisible(true);
	}

	/**
	 * Inicializa el Jdialog
	 */
	public void init(){
		setSize(400,200);
		setLocationRelativeTo(null);
		textoSkin = new JLabel("Carpeta del skin");
		textoRuta = new JLabel("Carpeta con la bilbioteca:");
		textoColorF = new JLabel("Color frontal");
		textoColorB = new JLabel("Color de fondo");
		
		textoColorFInterno = new JLabel("Color frontal secundario");
		textoColorBInterno = new JLabel("Color de fondo secundario");

		
		GridBagConstraints constraints = new GridBagConstraints();
		this.getContentPane().setLayout(new GridBagLayout());		
				
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
		this.getContentPane().add(textoColorBInterno,constraints);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getSelColBInternoButton(),constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(textoColorFInterno,constraints);
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getSelColFInternoButton(),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(textoSkin,constraints);
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getSkinsBox(),constraints);

		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(textoRuta,constraints);
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;		
		this.getContentPane().add(getRutaMusica(),constraints);

		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getGuardarButton(),constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 10;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getCancelarButton(),constraints);
		
		//this.getContentPane().setBackground(Color.gray);
		//this.getContentPane().setForeground(Color.white);
		
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
	 * @return the skinsBox
	 */
	public JComboBox getSkinsBox() {
		if(skinsBox == null){
			Vector<String> items = new Vector<String>();
			File dir;
			File f = new File("images");
			File[] array = f.listFiles();
			int n = array.length;
			int i;
			for(i = 0; i < n; i++){
				dir = array[i];
				if(dir.isDirectory()){
					try {
						items.add(dir.getAbsolutePath());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			//= {"Skin3", "skinMig"};
			skinsBox = new JComboBox(items);
		}
		return skinsBox;
	}

	/**
	 * @return the rutaMusica
	 */
	public JTextField getRutaMusica() {
		if (rutaMusica == null){
			rutaMusica = new JTextField();	
			rutaMusica.setText(GestorPreferencias.getInstance().getRutaIndexada());
			rutaMusica.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser fc = new JFileChooser();
					fc.setMultiSelectionEnabled(false);
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					if(fc.showOpenDialog(comp) == JFileChooser.APPROVE_OPTION) {
                    	File dir = fc.getSelectedFile();
                    	if(dir != null){
                    		rutaMusica.setText(dir.getAbsolutePath());
                    	}
					}
				}
			});
		}
		return rutaMusica;
	}

	/**
	 * @return the selColFInternoButton
	 */
	public JButton getSelColFInternoButton() {
		if (selColFInternoButton == null){
			selColFInternoButton = new JButton();		
			selColFInternoButton.setBackground(GestorPreferencias.getInstance().getBgColor());
			selColFInternoButton.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(null, "Selecciona color de fondo", GestorPreferencias.getInstance().getBgColor());
					if(newColor != null){
						selColFInternoButton.setBackground(newColor);
					}
				}
			});
		}
		return selColFInternoButton;
	}

	/**
	 * @return the selColBInternoButton
	 */
	public JButton getSelColBInternoButton() {
		if (selColBInternoButton == null){
			selColBInternoButton = new JButton();		
			selColBInternoButton.setBackground(GestorPreferencias.getInstance().getBgColor());
			selColBInternoButton.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(null, "Selecciona color de fondo", GestorPreferencias.getInstance().getBgColor());
					if(newColor != null){
						selColBInternoButton.setBackground(newColor);
					}
				}
			});
		}
		return selColBInternoButton;
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
					guardarPreferencias();
					dispose();
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
					dispose();
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
	public void guardarPreferencias(){
		GestorPreferencias.getInstance().setBgColor(getSelColBButton().getBackground());
		GestorPreferencias.getInstance().setFgColor(getSelColFButton().getBackground());
		GestorPreferencias.getInstance().setBgColorInterno(getSelColBInternoButton().getBackground());
		GestorPreferencias.getInstance().setFgColorInterno(getSelColBInternoButton().getBackground());
		GestorPreferencias.getInstance().setRutaIndexada(getRutaMusica().getText());
		GestorPreferencias.getInstance().setSkin(getSkinsBox().getSelectedItem().toString());
		GestorPreferencias.getInstance().guardarXML();
		JOptionPane.showMessageDialog(null, "Los cambios se aplicarán al reiniciar la aplicación");
	}
	
}
