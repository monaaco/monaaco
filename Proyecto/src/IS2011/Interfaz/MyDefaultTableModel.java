package IS2011.Interfaz;

import javax.swing.table.DefaultTableModel;

public class MyDefaultTableModel extends DefaultTableModel{
	
	/** 
	 * Impide editar cualquier celda de la tabla
	 */
	public boolean isCellEditable(int a, int b){
		return false;
	}
}
