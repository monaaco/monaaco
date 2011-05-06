package IS2011.Interfaz;

import javax.swing.table.DefaultTableModel;

public class MyDefaultTableModel extends DefaultTableModel{
	
	public boolean isCellEditable(int a, int b){
		return false;
	}
}
