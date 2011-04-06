import javax.swing.*;
import javax.xml.stream.*;  
import javax.xml.stream.events.XMLEvent;  

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;  
import java.util.ArrayList;

public class Pruebas extends JFrame{
	private JFrame principal= null;
	private JMenuBar barraMenu = null;
	private JPanel busquedaPanel = null;
	private JPanel consultaPanel = null;
	private JFrame resultadoFrame = null;
	private JPanel estadoPanel = null;
	private JLabel encontradosLabel = null;
	private JLabel estadoLabel = null;
	private JTextArea resultadoTextArea = null;
	private ArrayList<Track> resultado = null;
	
	public Pruebas(){
		principal = this;
		JPanel panel1 = new JPanel();
		principal.setVisible(true);
		principal.setJMenuBar(getBarraMenu());
		principal.setContentPane(getBusquedaPanel());
	}
	
	private JPanel getBusquedaPanel() {
		// TODO Auto-generated method stub
		if( busquedaPanel == null){
			busquedaPanel = new JPanel();
			busquedaPanel.setLayout(new BorderLayout());
			busquedaPanel.add(getConsultaPanel(), BorderLayout.NORTH);
			//busquedaPanel.add(getResultadoPanel(), BorderLayout.CENTER);
			//busquedaPanel.add(getEstadoPanel(), BorderLayout.SOUTH);
			
		}	
		return busquedaPanel;
	}

	private JPanel getEstadoPanel() {
		// TODO 
		if( estadoPanel == null){
			estadoPanel = new JPanel();
			estadoPanel.setLayout(new FlowLayout());
			estadoLabel = new JLabel("Finalizado");
			encontradosLabel = new JLabel("Número de archivos encontrados: " + resultado.size());
			estadoPanel.add(estadoLabel);
			estadoPanel.add(encontradosLabel);
			
		}
		return estadoPanel;
	}

	private JTextArea getResultadoPanel() {
		// TODO
		if( resultadoTextArea == null){
			resultadoTextArea = new JTextArea();
			resultadoTextArea.setText(resultado.toString());
			//resultadoPanel = new JPanel();
			//resultadoPanel.add(resultadoTextArea);
		}
		return resultadoTextArea;
	}

	private JMenuBar getBarraMenu() {
		// TODO
		if( barraMenu == null ){
			barraMenu = new JMenuBar();
			barraMenu.add(new JLabel("XML reader!"));
			barraMenu.add(new JButton("BosssssssssssssssssssssssS"));
			//JButton salirButton = new JButton("Salir");
			//barraMenu.add(salirButton);
		}
		return barraMenu;
	}

	private JPanel getConsultaPanel() {
		if( consultaPanel == null ){
			consultaPanel = new JPanel();
			consultaPanel.setLayout(new FlowLayout());		
	    	final JTextField textoConsulta = new JTextField(12);
	    	

	    	final FileReader reader = null;
	    	final XMLEventReader parser = null;
	    	
	    	JButton buscar = new JButton("Buscar");
	    	buscar.addActionListener( new ActionListener(){
	    		@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						resultadoTextArea = null;
						resultado = new ArrayList<Track>();
			        	boolean encontrado = false;
				        String currentTitle   = null; 
				        String currentArtist = null; 
				        Integer currentTime = null;
				        String consulta = textoConsulta.getText();
				        FileReader reader = new FileReader("xml/ejemplo2.xml");  
				        XMLEventReader parser  = XMLInputFactory.newInstance().createXMLEventReader(reader);  
						XMLEvent evt = null;
						String tag = null;
			             while (parser.hasNext()){  
			                 evt = parser.nextEvent();  
			                 if (evt.isStartElement()){  
			                     tag = evt.asStartElement().getName().getLocalPart();  
			                     if ("Title".equals(tag)){  
			                         currentTitle = parser.getElementText();  
			                     }else if ("Artist".equals(tag)){
			                    	 currentArtist = String.valueOf(parser.getElementText());
			                    	 if (consulta.contains(currentArtist)) encontrado = true;
			                    	 		
			                         
			                         if (encontrado == false) //si no es el artita saltate los campos Time y Album;
			                        	 for (int i=0; i<5; i++)
			                        		 parser.nextEvent();
			                         
			                     }else if ("Time".equals(tag)){
			                    	 currentTime = Integer.valueOf(parser.getElementText());
			                     }
			                     }else if (evt.isEndElement()&&(encontrado == true)){
			                    	 tag = evt.asEndElement().getName().getLocalPart();
			                    	 if ("Track".equals(tag)){ 
					                	 Track track = new Track();
					                	 track.setAuthor(currentArtist);
					                	 track.setTitle(currentTitle);
					                	 track.setTime(currentTime);
					                	 resultado.add(track);
					                	 encontrado = false;
					                 }
			                 }
			                   
			             }  
			               
			         } catch (Exception e1) {  
			             // Para este tutorial no hacemos nada más que imprimir la excepción  
			             System.out.println(e1);  
			         } finally {  
			             try {  
			                 reader.close();  
			             } catch (Exception e1){}              
			             try {  
			                 parser.close();  
			             } catch (Exception e1){}          
			         }//try catch
			           System.out.println(resultado);  
			           System.out.println("Elementos encontrados: " + resultado.size());

	    			}//actioneventblabla
	    		}//action listener
	    	 );
	    	consultaPanel.add(textoConsulta);
	    	consultaPanel.add(buscar);
	    	consultaPanel.setVisible(true);
	    	consultaPanel.setEnabled(true);   
		}
		return consultaPanel;
	}

	public JFrame getPrincipal(){
		return this.principal;
	}

     public static void main(String[] args) {      
    	Pruebas p = new Pruebas();
 		p.setVisible(true);
 		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		p.setTitle("kcArtist");
 		//p.setResizable(false);
 		p.setSize(400,100);
 		p.setLocationRelativeTo(null);
 		
 	}
 
}
