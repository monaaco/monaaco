package IS2011.Interfaz;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.JList;
import javax.swing.TransferHandler;

class MyListDropHandler extends TransferHandler {
	  /**
	 * @uml.property  name="list"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	DragDropList list;

	  /**
	   * Constructora del handler sobre la DragNDropList
	   * @param list DragDropList lista a asociar con el handler 
	   */
	  public MyListDropHandler(DragDropList list) {
	    this.list = list;
	  }

	  /**
	   * Este m�todo comprueba que se pueda importar los datos sobre la lista 
	   * durante el DragNDrop
	   * @param support
	   * @return si se pueden importar los datos o no.
	   */
	  public boolean canImport(TransferHandler.TransferSupport support) {
	    if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
	      return false;
	    }
	    JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
	    if (dl.getIndex() == -1) {
	      return false;
	    } else {
	      return true;
	    }
	  }

	  /**
	   * Este metodo se encarga de reordenar la tabla y la PlayList asociada,
	   * una vez conseguido el cambio, la DragDropList se repinta.
	   * @param support 
	   * @return si se ha podido reordenar la tabla y la Playlist.
	   */
	  public boolean importData(TransferHandler.TransferSupport support) {
	    int index;
		if (!canImport(support)) {
	      return false;
	    }

	    Transferable transferable = support.getTransferable();
	    String indexString;
	    try {
	      indexString = (String) transferable.getTransferData(DataFlavor.stringFlavor);
	      index = Integer.parseInt(indexString);
	    } catch (Exception e) {
	      return false;
	    }

	    index = Integer.parseInt(indexString);
	    JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
	    int dropTargetIndex = dl.getIndex();
	    
	    list.mueve(index, dropTargetIndex);
	    list.repaint();
	    
	    System.out.println(dropTargetIndex + " : ");
	    System.out.println("inserted");
	    return true;
	  }
}