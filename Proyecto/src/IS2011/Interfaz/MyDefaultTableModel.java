package IS2011.Interfaz;

import javax.swing.table.DefaultTableModel;

public class MyDefaultTableModel extends DefaultTableModel{
	
	/** 
	 * Impide editar cualquier celda de la tabla
	 * @param Devuelve falso para que no se puedan modificar
	 */
	public boolean isCellEditable(int a, int b){
		return false;
	}
}
