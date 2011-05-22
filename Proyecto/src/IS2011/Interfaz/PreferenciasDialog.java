package IS2011.Interfaz;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.colorchooser.ColorSelectionModel;

import IS2011.Configuracion.Preferencias;
import IS2011.biblioteca.Track;

public class PreferenciasDialog extends JDialog{
	
	private Preferencias preferencias;
	
	private JLabel textoSkin;
	private JLabel textoRuta;
	private JLabel textoColorF;
	private JLabel textoColorB;
	
	private JButton selColF;
	private JButton selColB;
	private JButton ok;
	
	public PreferenciasDialog(JFrame comp, boolean modal, Preferencias config){
		super(comp, modal);
		preferencias = config;
		setTitle("Preferencias");
		init();
		setVisible(true);
	}

	public void init(){
		setSize(400,600);
		textoSkin = new JLabel("Carpeta del skin");
		textoRuta = new JLabel("Carpeta de la ruta a indexar con la bilbioteca:");
		textoColorF = new JLabel("Color frontal");
		textoColorB = new JLabel("Color de fondo");
		
		selColF = new JButton();
		selColF.setBackground(preferencias.getFgColor());
		selColF.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(null, "Selecciona color frontal", preferencias.getFgColor());
				if(newColor != null){
					preferencias.setFgColor(newColor);
					selColF.setBackground(newColor);
				}
			}
		});
		
		selColB = new JButton();		
		selColB.setBackground(preferencias.getBgColor());
		selColB.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(null, "Selecciona color de fondo", preferencias.getBgColor());
				if(newColor != null){
					preferencias.setBgColor(newColor);
					selColB.setBackground(newColor);
				}
			}
		});
		
		
		ok = new JButton("Ok");
		
		this.setLayout(new GridBagLayout());
		
		add(textoColorF);
		add(selColF);
		add(textoColorB);
		add(selColB);
		add(textoRuta);
		add(textoSkin);
	}

}
