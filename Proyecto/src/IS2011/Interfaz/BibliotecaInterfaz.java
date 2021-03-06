package IS2011.Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;

import IS2011.Configuracion.GestorPreferencias;
import IS2011.biblioteca.GestorBiblioteca;
import IS2011.biblioteca.Playlist;
import IS2011.biblioteca.Track;

public class BibliotecaInterfaz extends JPanelTransparente{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	 
	/**
	 * @uml.property  name="agregarIcon"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ImageIcon agregarIcon = new ImageIcon("images/Skin3/agregar.png");
	/**
	 * @uml.property  name="filtrarIcon"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ImageIcon filtrarIcon = new ImageIcon("images/Skin3/filter.png");
	
	/**
	 * @uml.property  name="frame"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private BibliotecaInterfaz frame= null;
	
	/**
	 * @uml.property  name="interfazPadre"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="bibliotecaInterfaz:IS2011.Interfaz.InterfazAvanzada"
	 */
	private InterfazAvanzada interfazPadre= null;
	/**
	 * @uml.property  name="menuBI"
	 * @uml.associationEnd  
	 */
	private JMenuBar menuBI=null;
	
	/**
	 * @uml.property  name="menu"
	 * @uml.associationEnd  
	 */
	private JPopupMenu menu = null;
	/**
	 * @uml.property  name="anadirArchivos"
	 * @uml.associationEnd  
	 */
	private JMenuItem anadirArchivos=null;
	/**
	 * @uml.property  name="editarPropiedades"
	 * @uml.associationEnd  
	 */
	private JMenuItem editarPropiedades=null;
	/**
	 * @uml.property  name="filtroAvanzado"
	 * @uml.associationEnd  
	 */
	private JMenuItem filtroAvanzado=null;
	/**
	 * @uml.property  name="elimina"
	 * @uml.associationEnd  
	 */
	private JMenuItem elimina= null;
	/**
	 * @uml.property  name="anadirAPlayList"
	 * @uml.associationEnd  
	 */
	private JMenuItem anadirAPlayList=null;
	/**
	 * @uml.property  name="busquedaRapida"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JTextField busquedaRapida= null;
	/**
	 * @uml.property  name="agregar"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JButton agregar = null;
	/**
	 * @uml.property  name="filtro"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JButton filtro = null;
	/**
	 * @uml.property  name="popup"
	 * @uml.associationEnd  
	 */
	private JFrame popup = null;
	/**
	 * @uml.property  name="tabla"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ZebraJTable tabla= null;
	
	
	
	/**
	 * @uml.property  name="elQueOrdena"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private TableRowSorter<TableModel> elQueOrdena=null; 
	/**
	 * @uml.property  name="modelo"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private MyDefaultTableModel modelo= null;
	/**
	 * @uml.property  name="escrito"
	 */
	private String escrito;
	/**
	 * @uml.property  name="mili"
	 */
	private long mili;



	/**
	 * @uml.property  name="fgColorInterno"
	 */
	private Color fgColorInterno;
	/**
	 * @uml.property  name="bgColorInterno"
	 */
	private Color bgColorInterno;
	/**
	 * @uml.property  name="fgColor"
	 */
	@SuppressWarnings("unused")
	private Color fgColor;
	/**
	 * @uml.property  name="bgColor"
	 */
	@SuppressWarnings("unused")
	private Color bgColor;
	
	/**
	 * Constructora de la interterz de biblioteca
	 * @param ia - InterfazAvanzada a la que va asociada
	 */
	public BibliotecaInterfaz(InterfazAvanzada ia){
		//super("library");
		interfazPadre = ia;
		this.cargarPreferencias();
		this.setColorPrimario(Color.white);
		this.setColorSecundario(Color.white);
		initBibliotecaInterfaz();
		setTran(0.3f);
		frame = this;
		escrito = "";
		mili = System.currentTimeMillis();
		this.setSize(600,300);
		//this.setTran(0.5f);
		JButton a�adir = getAnadirArchivos();
		a�adir.setBounds(25,245,90, 45);
	    this.add(a�adir);
		
		JButton filtro1 = getFiltroAvanzado();
		filtro1.setBounds(125,245,90, 45);
		this.add(filtro1);
		
	}

	/**
	 * Inicializa los componentes de la interfaz de la biblioteca
	 */
	public void initBibliotecaInterfaz()
	{
		//this.setSize(650, 200);
		this.setLayout(null);
		//JMenuBar JMenuAux = getBarraMenu(); OBSOLETO
		//JMenuAux.setBounds(5, 10, 590, 10)
	
		//this.add(JMenuAux,BorderLayout.SOUTH);
		
		
		
		// Instanciamos nuestro modelo de datos, por ejemplo, DefaultTableModel
		// y lo metemos en el JTable
		modelo = new MyDefaultTableModel();
		modelo.addColumn("Titulo");
		modelo.addColumn("Artista");
		modelo.addColumn("Album");
		modelo.addColumn("Tiempo total (seg)");
		modelo.addColumn("Genero");
	
		
		tabla = new ZebraJTable(modelo);
		/*tabla.setBackground( Color.darkGray );
		tabla.setForeground( Color.white );
		tabla.setSelectionBackground( Color.yellow );
		tabla.setSelectionForeground( Color.black );*/
		tabla.setSize(600,200);
		
		
		getPopUp();

		// Instanciamos el TableRowSorter y lo a�adimos al JTable
		elQueOrdena = new TableRowSorter<TableModel>(modelo);
		tabla.setRowSorter(elQueOrdena);
		
		actualiza();
			
		tabla.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent e) 
		      {
		    	if(e.getClickCount()==2){
		    		int[] fila = new int[1];
		    		fila[0] = tabla.rowAtPoint(e.getPoint());
		    		meterEnPlayList(fila);
		    		repaint();
		    		//TODO CODIGO MOVIDO A meterEnPlayList
		    	}
		      }
		   });
		
		tabla.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent evt) {
		        if (evt.isPopupTrigger()) {
		            menu.show(evt.getComponent(), evt.getX(), evt.getY());
		            
		        }
		        GestorBiblioteca.getInstance().getArrayList();
		    }
		    public void mouseReleased(MouseEvent evt) {
		        if (evt.isPopupTrigger()) {
		            menu.show(evt.getComponent(), evt.getX(), evt.getY());
		           
		            
		        }
		    }
		});
		tabla.addKeyListener(new java.awt.event.KeyAdapter(){
			public void keyReleased(KeyEvent k){
				int c = k.getKeyCode();
				if(((65 <= c) && (c <= 90)) || c == 32){ //Si es una letra o un espacio
					acumulaLetra(k.getKeyChar());
				}
				else{	//Si no...
					escrito = "";
					switch(c){	// Elegimos el resto de las posibles teclas
					case 116:
						actualiza();
						break;
					case 127:	// suprimir
						borraElemBiblioteca();
						break;
					case 10: // ENTER
						if(tabla.getSelectedRowCount() == 1){
						int[] select = tabla.getSelectedRows();
						if(select[0] == 0) select[0] = (tabla.getRowCount() - 1);
						else select[0] = select[0] - 1;
						meterEnPlayList(select);
						}
						else meterEnPlayList(tabla.getSelectedRows());
						break;
					}
				}
			}
		});

		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setSize(600,200);
		scroll.setBounds(25, 25, 600, 200);
		this.add(scroll,BorderLayout.CENTER);
		
		busquedaRapida = new JTextField("");
		busquedaRapida.setSize(200,20);
		
		this.add(busquedaRapida);
		Document d = busquedaRapida.getDocument();
		d.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				filtraRapido();
				repaint();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				filtraRapido();
				repaint();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				filtraRapido();
				repaint();
			}
		});
		JLabel textoBusqueda = new JLabel("B�squeda:");
		textoBusqueda.setBounds(350,245,60, 20);
		textoBusqueda.setForeground(Color.white);
	    this.add(textoBusqueda);
		busquedaRapida.setBounds(425,245,200, 20);;
		this.add(busquedaRapida);
		repaint();
		
	}
	
	/**
	 * Crea y define los listener del menu PopUp que borra y agrega canciones
	 * @return menu devuelve el menu ya inicializado correctamente
	 */
	private JPopupMenu getPopUp(){
		
		menu = new JPopupMenu();
		
		menu.setBorderPainted(false);
		
		JMenuItem eliminar = new JMenuItem("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			 
	            public void actionPerformed(ActionEvent e)
	            {
	            	 borraElemBiblioteca();
		    		
	            }
	        });
		menu.add(eliminar);
		JMenuItem propiedades = new JMenuItem("Propiedades");
		propiedades.addActionListener(new ActionListener() {
			 
	            public void actionPerformed(ActionEvent e)
	            {
	            	         	
						int[] seleccionadas = tabla.getSelectedRows();
						int fila;
						if(seleccionadas.length > 0){
							for(int i =0;i< seleccionadas.length ;i++){
								fila = seleccionadas[i];
								fila = tabla.convertRowIndexToModel (fila);
								Track track = GestorBiblioteca.getInstance().getArrayList().get(fila);
								PropiedadesTrack pt = new PropiedadesTrack(interfazPadre, true ,track);
								pt.setVisible(true);
							}
							actualiza();
							repaint();
						}
					}
	        });

		menu.add(propiedades);
		JMenuItem Agregar = new JMenuItem("Agregar");
		Agregar.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	int[] seleccionadas = tabla.getSelectedRows();
	    		meterEnPlayList(seleccionadas);
	    		
            }
        });
		menu.add(Agregar);
		return menu;
		
	}
	
	/**
	  * Devuelve y crea ,en el caso de que no exista, la barra de men� 
	  * para la BibliotecaInterfaz, OBSOLETO
	  * @return JMenuBar 
	 */
/*	private JMenuBar getBarraMenu() {	
		// TODO m�s elementos �e iconos?
		if (menuBI == null) {
			menuBI = new JMenuBar();
			menuBI.setBorderPainted(false);
			
			JMenu menuArchivo = new JMenu("A�adir archivo");
			menuArchivo.add(getAnadirArchivos());
			menuArchivo.add(getAnadirAPlayList());
			JMenu menuFiltro = new JMenu("Filtro");
			menuFiltro.add(getFiltroAvanzado());
			JMenu menuPropiedades = new JMenu("Propiedades");
			menuPropiedades.add(getEditarPropiedades());
			JMenu menuEliminar = new JMenu("Eliminar");
			menuEliminar.add(getElimina());
			menuBI.add(menuArchivo);done
			menuBI.add(menuFiltro);
			menuBI.add(menuPropiedades);
			menuBI.add(menuEliminar);
		}
		return menuBI;
	}*/

	/**
	  * Devuelve y crea ,en el caso de que no exista, men� de a�adir a la 
	  * PlayList para la BibliotecaInterfaz OBSOLETO
	  * @return JMenuItem
	 */
	/*private JMenuItem getAnadirAPlayList() {
		if (anadirAPlayList == null) {
			anadirAPlayList = new JMenuItem("A�adir seleccionados a la PlayList");
			anadirAPlayList.addMouseListener(new java.awt.event.MouseAdapter() {
				public  void mouseReleased(java.awt.event.MouseEvent evt) {		
						int[] seleccionadas = tabla.getSelectedRows();
						if(seleccionadas.length != 0)
						{
							int fila;
							Track aux;
							Playlist listaRepr = interfazPadre.getListaReproduccion();
							for(int i= 0; i<seleccionadas.length ;i++)
							{
								fila = seleccionadas[i];
								fila = tabla.convertRowIndexToModel (fila);
								aux = GestorBiblioteca.getInstance().getArrayList().get(fila);
					        	listaRepr.add(aux.getLocation());
							}
							String[] temas = listaRepr.getListado();
		                    interfazPadre.getInfoPlaylist().actualizaTemas(temas);
		                    repaint();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "Selecciona las filas para pasar a la PlayList");
						}
                    }
				});
		}
		return anadirAPlayList;
	}*/

	/**
	  * Devuelve y crea ,en el caso de que no exista, el men� de
	  * eliminar de la biblioteca OBSOLETO
	  * @return JMenuItem
	 */
	/*private JMenuItem getElimina() {
		if (elimina == null) {
			elimina = new JMenuItem("Eliminar seleccionados de la biblioteca");
			elimina.addMouseListener(new java.awt.event.MouseAdapter() {
				public  void mouseReleased(java.awt.event.MouseEvent evt) {		
						borraElemBiblioteca();
						repaint();
						//He movido el codigo a este metodo para no duplicarlo con las key
                    }
				});
		}
		return elimina;
	}
*/
	/**
	  * Devuelve y crea ,en el caso de que no exista, 
	  * men� de a�adir archivos a la biblioteca
	  * @return agregar devuelve agregar ya inicializado correctamente
	 */
	private JButton getAnadirArchivos() {
		if (agregar == null) {
			agregar = new JButton(agregarIcon);
			agregar.setBorderPainted(false);
			//anadirArchivos = new JMenuItem("A�adir archivos a la biblioteca");
			agregar.addMouseListener(new java.awt.event.MouseAdapter() {
				@SuppressWarnings("deprecation")
				public  void mouseReleased(java.awt.event.MouseEvent evt) {
                /*    JFileChooser fc = new JFileChooser();
                    fc.setFileFilter(new FiltroMP3());
                    fc.setFileFilter(new FiltroOGG());
                    fc.setFileFilter(new FiltroSoportados());
                    fc.setFileSelectionMode(fc.FILES_AND_DIRECTORIES);
                    fc.setMultiSelectionEnabled(true);*/
                   
                    HiloCargar elHiloCagar = new HiloCargar(frame,interfazPadre);
                 //   elHilo.setPriority(Thread.MAX_PRIORITY);
                    elHiloCagar.start();
                   
                  /*  if( fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                       /* LoadingScreen ls = new LoadingScreen(interfazPadre);
                        ls.setVisible(true);
                    	
                    	
                        File[] array = fc.getSelectedFiles();
                        
                     	
                        for(int i=0; i<array.length; i++){
                            getAudioFiles(array[i]);
                        }
                       */
                        actualiza();
                      //  tabla.remove(label);
                       // ls.dispose();
                     //   elHilo.interrupt();
                        busquedaRapida.setText("");
                        repaint();
                    
                };
            });
		}
		return agregar;
	}
	
	/**
	 * Metodo recursivo que comprueba si los archivos son de las clases soportadas
	 * (mp3, ogg, wav) o directorio. 
	 * Si el tipo es compatible lo agrega a la biblioteca siempre y cuando este todavia
	 * no exista. Si es un directorio llama a este metodo recursivamente.
	 * @param file Archivo o directorio a leer.
	 */
	public void getAudioFiles(File file){
		if(file.isDirectory()){
			File[] array = file.listFiles();
			int n = array.length;
			int i;
			for(i = 0; i < n; i++){
				getAudioFiles(array[i]);
			}
		}
		else if(file.getName().toLowerCase().endsWith(".mp3") || file.getName().toLowerCase().endsWith(".ogg") || file.getName().toLowerCase().endsWith(".wav"))
		{
			HashMap<String,Boolean> hash = GestorBiblioteca.getInstance().getHashMap();
			if(!hash.containsKey(file.getAbsolutePath()))
			{
				Track aux = new Track(file.getAbsolutePath());
				GestorBiblioteca.getInstance().add(aux);
				hash.put(file.getAbsolutePath(), Boolean.TRUE);
			}
		}
	}
	
	/**
	  * Devuelve y crea ,en el caso de que no exista, men� de editar propiedades a la 
	  * biblioteca OBSOLETO
	  * @return JMenuItem 
	 */
	/*public JMenuItem getEditarPropiedades(){
		if(editarPropiedades==null){
			editarPropiedades= new JMenuItem("Editar propiedades");
			editarPropiedades.addMouseListener(new java.awt.event.MouseAdapter() {
				public  void mouseReleased(java.awt.event.MouseEvent evt) {
					//TODO Mostrar el PropiedadesTrack	
					int[] seleccionadas = tabla.getSelectedRows();
					int fila;
					if(seleccionadas.length > 0){
						for(int i =0;i< seleccionadas.length ;i++){
							fila = seleccionadas[i];
							fila = tabla.convertRowIndexToModel (fila);
							Track track = GestorBiblioteca.getInstance().getArrayList().get(fila);
							PropiedadesTrack pt = new PropiedadesTrack(interfazPadre, true ,track);
							pt.setVisible(true);
						}
						actualiza();
						repaint();
					}
				}
			});
		}
		return editarPropiedades;
	}*/

	/**
	  * Devuelve y crea ,en el caso de que no exista, men� de insertar un Filtro Avanzado a la 
	  * biblioteca
	  * @return filtro devuelve filtro ya inicializado correctamente.
	 */
	public JButton getFiltroAvanzado(){
		if(filtro==null){
			filtro = new JButton(filtrarIcon);
			filtro.setBorderPainted(false);
			//filtroAvanzado= new JMenuItem("Busqueda avanzada");
			filtro.addMouseListener(new java.awt.event.MouseAdapter() {
				public  void mouseReleased(java.awt.event.MouseEvent evt) {
					//TODO Mostrar un panel con los campos a buscar		
					FiltroDialog fd = new FiltroDialog(interfazPadre, true,elQueOrdena);
					fd.setVisible(true);
					repaint();
				}
			});
		}
		return filtro;
	}
	
	/**
	 * Carga sobre el modelo asociado al JTable, toda la biblioteca.
	 * Adem�s para ello quita los filtros que estuvieran asociados a la tabla
	 * y sus posibles ordenaciones.
	 */
	public void actualiza(){
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		/*1� Borrar todo lo que tenga la tabla*/
		elQueOrdena.setRowFilter(javax.swing.RowFilter.regexFilter(""));
		
		for(int i= modelo.getRowCount() - 1; i>=0 ;i--){
			 modelo.removeRow(i);
		}
		
		
		/*2� Meter todo en la tabla*/
		Iterator<Track> it = GestorBiblioteca.getInstance().getArrayList().iterator();
		Track aux= null;
		while (it.hasNext())
		{
			aux = (Track)it.next();
			((DefaultTableModel) modelo).addRow(new Object[] { aux.getName(), aux.getArtist(), aux.getAlbum(), aux.getTotalTime(), aux.getGenre()});
		}
		
		repaint();
	}
	
	/**
	 * Aplica un patron de filtrado sobre toda la tabla de la biblioteca teniendo como entrada 
	 * el TextArea de busquedaRapida. La expresi�n regular obtenida por el TextArea no distingue 
	 * entre may�sculas y min�sculas.
	 */
	public void filtraRapido(){
		elQueOrdena.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)"+busquedaRapida.getText()));
		repaint();
	}
	
	/**
	 * Mete en la PlayList una serie de Tracks de la tabla asociados con la biblioteca
	 * @param fila int[] posiciones de las filas de la tabla.
	 */
	public void meterEnPlayList(int[] fila){
        for(int i = 0;i<=fila.length-1;i++){
			if ((fila[i] > -1))
	        {
		       	fila[i] = tabla.convertRowIndexToModel(fila[i]);
		       	Track aux = GestorBiblioteca.getInstance().getArrayList().get(fila[i]);
		       	Playlist listaRepr = interfazPadre.getListaReproduccion();
		       	listaRepr.add(aux.getLocation());
		       	//interfazPadre.setCurrentTrack(listaRepr.current());
		        String[] temas = listaRepr.getListado();
		        interfazPadre.getInfoPlaylist().actualizaTemas(temas);
		       	//pasamos de la fila recibida a la fila sin filtrar
		       	//Anade a la listaReproduccion
		        interfazPadre.getInfoPlaylist().marcaActual();
		        repaint();
	        }
        }
	}
	
	/**
	 * Borra las filas seleccionadas de la biblioteca.
	 * Borra tanto de la tabla como de la biblioteca. Tras acabar
	 * actualiza el estado de la tabla.
	 */
	public void borraElemBiblioteca(){
		int[] seleccionadas = tabla.getSelectedRows();
		if(seleccionadas.length != 0)
		{
			//Nos devuelve un vecotor con las filas a borrar ordenadas
			
			//Lo pasamos al global para evitar el filtro y lo borramos
			// en el mismo paso gracias al orden
			int fila;
			for(int i= seleccionadas.length - 1; i>=0 ;i--)
			{
				fila = seleccionadas[i];
				fila = tabla.convertRowIndexToModel (fila);
				GestorBiblioteca.getInstance().getArrayList().remove(fila);
			}
        	actualiza();
        	busquedaRapida.setText("");
        	repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "Selecciona las filas antes de borrar.");
		}
	}
	
	/**
	 * Acumula letras para la busqueda rapida por caracteres sin campo de texto.
	 * @param c nuevo car�cter a procesar
	 */
	public void acumulaLetra(char c){
		if(System.currentTimeMillis() - mili > 1000){
			escrito = "";
		}
		mili = System.currentTimeMillis();
		escrito += c;
		buscaNombre(escrito);
	}
	
	/**
	 * Busca en la biblioteca por nombre.
	 * @param nombre String nombre por el que buscar
	 */
	public void buscaNombre(String nombre){
		Iterator<Track> it = GestorBiblioteca.getInstance().getArrayList().iterator();
		Track tr;
		int i = 0;
		while(it.hasNext()){
			tr = (Track)it.next();
			if(tr.getAlbum().toLowerCase().contains(nombre.toLowerCase()) || tr.getArtist().toLowerCase().contains(nombre.toLowerCase()) ||
			tr.getName().toLowerCase().contains(nombre.toLowerCase()) || tr.getGenre().toLowerCase().contains(nombre.toLowerCase())){
				tabla.setRowSelectionInterval(i, i);
				return;
			}
			i++;
		}
		repaint();
	}

	/**
	 * sincroniza la biblioteca con la ruta que se marca
	 * @param rutaIndexada
	 */
	public void sincroniza(String rutaIndexada) {
		/*Borramos de la biblioteca los que no existan*/
		Iterator<Track> it = GestorBiblioteca.getInstance().getArrayList().iterator();
		Track tr;
		File f;
		while(it.hasNext()){
			tr = (Track)it.next();
			if(tr.getLocation()== null){
				it.remove();
			}
			else
			{
				f = new File(tr.getLocation());
				if(!f.exists())
				{
					it.remove();
				}
			}
		}
		/*Actualizamos la carpeta indexada*/
		getAudioFiles(new File(rutaIndexada));
		
		actualiza();
	}
	
	/**
	 * carga las preferencias de la clase GestorPreferencias
	 */
	public void cargarPreferencias(){
		this.setRutaIconos(GestorPreferencias.getInstance().getSkin());
		this.setBgColor(GestorPreferencias.getInstance().getBgColor());
		this.setFgColor(GestorPreferencias.getInstance().getFgColor());	
		this.setBgColorInterno(GestorPreferencias.getInstance().getBgColorInterno());
		this.setFgColorInterno(GestorPreferencias.getInstance().getFgColorInterno());	
		//Reproducir la cancion que se estaba rerpoduciendo al apagar
		//TODO m�s cosas
		
	}

	/**
	 * @param  fgColorInterno2
	 * @uml.property  name="fgColorInterno"
	 */
	private void setFgColorInterno(Color fgColorInterno2) {
		this.fgColorInterno = fgColorInterno2;
		this.setColorPrimario(fgColorInterno);
	}

	/**
	 * @param  bgColorInterno2
	 * @uml.property  name="bgColorInterno"
	 */
	private void setBgColorInterno(Color bgColorInterno2) {
		this.bgColorInterno = bgColorInterno2;
		this.setColorSecundario(bgColorInterno);

	}

	/**
	 * Carga las imagenes de botones e iconos
	 * @param skin los iconos a cargar
	 */
	private void setRutaIconos(String skin) {
		String ruta = GestorPreferencias.getInstance().getSkin();
		agregarIcon = new ImageIcon(ruta+"/agregar.png");
		filtrarIcon = new ImageIcon(ruta+"/filter.png");
	}

	/**
	 * Mutadora del color principal de la interfaz.
	 * @param fgColor2  nuevo color principal.
	 * @uml.property  name="fgColor"
	 */
	private void setFgColor(Color fgColor2) {
		this.fgColor = fgColor2;
	}

	/**
	 * Mutadora del color secundario de la interfaz.
	 * @param bgColor2  nuevo color secundario.
	 * @uml.property  name="bgColor"
	 */
	private void setBgColor(Color bgColor2) {
		this.bgColor = bgColor2;
	}
	
}
