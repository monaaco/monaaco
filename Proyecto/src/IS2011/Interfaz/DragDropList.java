package IS2011.Interfaz;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JList;

import IS2011.biblioteca.Playlist;

public class DragDropList extends JList {
	  /**
	 * @uml.property  name="model"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	DefaultListModel model;
	  /**
	 * @uml.property  name="listaRep"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	Playlist listaRep;

	  /**
	   * Constructora de DragDropList, hereda de JList 
	   * pero esta lleva asociado DragNDrop
	   * @param list Strng[] listado para el JList
	   * @param lista PlayList listado de canciones al que est� asociado
	   */
	  public DragDropList(String[] list, Playlist lista) {
	    super(new DefaultListModel());
	    model = (DefaultListModel) this.getModel();
	    listaRep = lista;
	    //this.listado = listado;
	    setListData(list);
	    setDragEnabled(true);
	    setDropMode(DropMode.INSERT);
	    setTransferHandler(new MyListDropHandler(this));

	    new MyDragListener(this);
	  }
	  
	  /**
	   * Mueve, intercambia posiciones entre dos elementos 
	   * del listado.
	   * @param inicio int posici�n origen
	   * @param fin posici�n fin
	   */
	  public void mueve(int inicio, int fin){
		  listaRep.mueve(inicio, fin);
		  String[] lista = listaRep.getListado();
          lista[listaRep.getCurrentTrack()] = lista[listaRep.getCurrentTrack()].toUpperCase();    
          setSelectedIndex(listaRep.getCurrentTrack());
		  setListData(lista);
	  }
}