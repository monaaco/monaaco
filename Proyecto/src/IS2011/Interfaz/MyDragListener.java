package IS2011.Interfaz;

import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

class MyDragListener implements DragSourceListener, DragGestureListener {
	  DragDropList list;

	  DragSource ds = new DragSource();

	  /**
	   * Constructora de MyDragListener implementada para la DragDropList
	   * @param list DragDropList lista a la que se asocia el listener
	   */
	  public MyDragListener(DragDropList list) {
	    this.list = list;
	    DragGestureRecognizer dgr = ds.createDefaultDragGestureRecognizer(list,
	        DnDConstants.ACTION_MOVE, this);
	  }

	  /**
	   * Convierte la selección del DragNDrop en string y comienza el Drag.
	   * @param dge 
	   */
	  public void dragGestureRecognized(DragGestureEvent dge) {
	    StringSelection transferable = new StringSelection(Integer.toString(list.getSelectedIndex()));
	    ds.startDrag(dge, DragSource.DefaultCopyDrop, transferable, this);
	  }

	  /**
	   * Metodo que implemente DragSourceListner y DragGestureListner, 
	   * en nuestro caso sin redefinir.
	   * @param dsde
	   */
	  public void dragEnter(DragSourceDragEvent dsde) {
	  }

	  /**
	   * Metodo que implemente DragSourceListner y DragGestureListner, 
	   * en nuestro caso sin redefinir.
	   * @param dse
	   */
	  public void dragExit(DragSourceEvent dse) {
	  }

	  /**
	   * Metodo que implemente DragSourceListner y DragGestureListner, 
	   * en nuestro caso sin redefinir.
	   * @param dsde
	   */
	  public void dragOver(DragSourceDragEvent dsde) {
	  }

	  /**
	   * Metodo que se ejecuta al Final del dragDrop, comprueba si 
	   * el comportamiento ha sido correcto.
	   * @param dsde
	   */
	  public void dragDropEnd(DragSourceDropEvent dsde) {
	    if (dsde.getDropSuccess()) {
	      System.out.println("Succeeded");
	    } else {
	      System.out.println("Failed");
	    }
	  }

	  /**
	   * Metodo que implemente DragSourceListner y DragGestureListner, 
	   * en nuestro caso sin redefinir.
	   * @param dsde
	   */
	  public void dropActionChanged(DragSourceDragEvent dsde) {
	  }
	}