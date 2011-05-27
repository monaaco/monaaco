package IS2011.Interfaz;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

import IS2011.Configuracion.GestorPreferencias;

public class PreferenciasDialog extends JDialog{
	

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @uml.property  name="textoSkin"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel textoSkin;
	/**
	 * @uml.property  name="textoRuta"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel textoRuta;
	 
	/**
	 * @uml.property  name="textoColorF"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel textoColorF;
	/**
	 * @uml.property  name="textoColorB"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel textoColorB;
	
	/**
	 * @uml.property  name="textoColorFInterno"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel textoColorFInterno;
	/**
	 * @uml.property  name="textoColorBInterno"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JLabel textoColorBInterno;
	
	/**
	 * Combo box con los skins disponibles (Carpetas dentro del directorio images)
	 * @uml.property  name="skinsBox"
	 * @uml.associationEnd  
	 */
	private JComboBox skinsBox = null;
	/**
	 * @uml.property  name="rutaMusica"
	 * @uml.associationEnd  
	 */
	private JTextField rutaMusica = null;
	
	/**
	 * @uml.property  name="selColFButton"
	 * @uml.associationEnd  
	 */
	private JButton selColFButton = null;
	/**
	 * @uml.property  name="selColBButton"
	 * @uml.associationEnd  
	 */
	private JButton selColBButton = null;
	
	/**
	 * @uml.property  name="selColFInternoButton"
	 * @uml.associationEnd  
	 */
	private JButton selColFInternoButton = null;
	/**
	 * @uml.property  name="selColBInternoButton"
	 * @uml.associationEnd  
	 */
	private JButton selColBInternoButton = null;
	
	/**
	 * @uml.property  name="guardarButton"
	 * @uml.associationEnd  
	 */
	private JButton guardarButton = null;
	/**
	 * @uml.property  name="cancelarButton"
	 * @uml.associationEnd  
	 */
	private JButton cancelarButton = null;
	
	/**
	 * @uml.property  name="comp"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
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
		this.setSize(400,200);
		setLocationRelativeTo(null);
		textoSkin = new JLabel("Carpeta del skin");
		textoRuta = new JLabel("Carpeta con la bilbioteca:");
		textoColorF = new JLabel("Color externo primario ");
		textoColorB = new JLabel("Color externo secundario ");
		
		textoColorFInterno = new JLabel("Color interno primario");
		textoColorBInterno = new JLabel("Color interno secundario");

		
		GridBagConstraints constraints = new GridBagConstraints();
		this.getContentPane().setLayout(new GridBagLayout());		
				
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST; 
		this.getContentPane().add(textoColorF,constraints);
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getSelColFButton(),constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(textoColorB,constraints);
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getSelColBButton(),constraints);
				
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(textoColorBInterno,constraints);
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getSelColBInternoButton(),constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(textoColorFInterno,constraints);
		constraints.gridx = 2;
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
		constraints.gridy = 6;
		constraints.ipadx = 20;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		this.getContentPane().add(getSkinsBox(),constraints);
		constraints.ipadx = 0;
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		this.getContentPane().add(textoRuta,constraints);
		constraints.gridx = 2;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;		
		this.getContentPane().add(getRutaMusica(),constraints);

		constraints.gridx = 0;
		constraints.gridy = 11;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getGuardarButton(),constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 11;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.getContentPane().add(getCancelarButton(),constraints);
		
		//this.getContentPane().setBackground(Color.gray);
		//this.getContentPane().setForeground(Color.white);
		
	}

	/**
	 * Botón color foreground
	 * @return
	 * @uml.property  name="selColFButton"
	 */
	private JButton getSelColFButton() {
		if (selColFButton == null){
			selColFButton = new JButton();
			selColFButton.setBackground(GestorPreferencias.getInstance().getFgColor());
			selColFButton.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(null, "Selecciona color primario", GestorPreferencias.getInstance().getFgColor());
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
	 * Botón color backgroud
	 * @return
	 * @uml.property  name="selColBButton"
	 */
	private JButton getSelColBButton() {
		if (selColBButton == null){
			selColBButton = new JButton();		
			selColBButton.setBackground(GestorPreferencias.getInstance().getBgColor());
			selColBButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(null, "Selecciona color secundario", GestorPreferencias.getInstance().getBgColor());
					if(newColor != null){
						selColBButton.setBackground(newColor);
					}
				}
			});
		}
		return selColBButton;
	}

	
	/**
	 * @return  the skinsBox
	 * @uml.property  name="skinsBox"
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
						if(!dir.isHidden())	items.add(dir.getName());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			skinsBox = new JComboBox(items);
			skinsBox.setSize(100, 20);
		}
		return skinsBox;
	}

	/**
	 * @return  the rutaMusica
	 * @uml.property  name="rutaMusica"
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
	 * @return  the selColFInternoButton
	 * @uml.property  name="selColFInternoButton"
	 */
	public JButton getSelColFInternoButton() {
		if (selColFInternoButton == null){
			selColFInternoButton = new JButton();		
			selColFInternoButton.setBackground(GestorPreferencias.getInstance().getFgColorInterno());
			selColFInternoButton.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(null, "Selecciona color interno primario", GestorPreferencias.getInstance().getBgColor());
					if(newColor != null){
						selColFInternoButton.setBackground(newColor);
					}
				}
			});
		}
		return selColFInternoButton;
	}

	/**
	 * @return  the selColBInternoButton
	 * @uml.property  name="selColBInternoButton"
	 */
	public JButton getSelColBInternoButton() {
		if (selColBInternoButton == null){
			selColBInternoButton = new JButton();		
			selColBInternoButton.setBackground(GestorPreferencias.getInstance().getBgColorInterno());
			selColBInternoButton.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					Color newColor = JColorChooser.showDialog(null, "Selecciona color interno secundario", GestorPreferencias.getInstance().getBgColor());
					if(newColor != null){
						selColBInternoButton.setBackground(newColor);
					}
				}
			});
		}
		return selColBInternoButton;
	}


	/**
	 * Botón guardar, guarda las preferencias
	 * @return
	 * @uml.property  name="guardarButton"
	 */
	private JButton getGuardarButton() {
		if (guardarButton == null){
			guardarButton = new JButton("Guardar");		
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
	 * Botón cancelar, para salir sin guardar
	 * @return
	 * @uml.property  name="cancelarButton"
	 */
	private JButton getCancelarButton() {
		if (cancelarButton == null){
			cancelarButton = new JButton("Cancelar");		
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
	 */
	public void guardarPreferencias(){
		GestorPreferencias.getInstance().setBgColor(getSelColBButton().getBackground());
		GestorPreferencias.getInstance().setFgColor(getSelColFButton().getBackground());
		GestorPreferencias.getInstance().setBgColorInterno(getSelColBInternoButton().getBackground());
		GestorPreferencias.getInstance().setFgColorInterno(getSelColFInternoButton().getBackground());
		GestorPreferencias.getInstance().setRutaIndexada(getRutaMusica().getText());
		GestorPreferencias.getInstance().setSkin("images/"+getSkinsBox().getSelectedItem().toString());
		GestorPreferencias.getInstance().guardarXML();
		JOptionPane.showMessageDialog(null, "Los cambios se aplicarán al reiniciar la aplicación");
	}
	
}
