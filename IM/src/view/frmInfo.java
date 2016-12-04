
package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.Main;
import DB.DBManager;

public class frmInfo extends JPanel {
	DBManager db = new DBManager();
	String result[][] = new String[100][4];
	DefaultTableModel model;
	JTable table;
	JScrollPane scrollpane;
	public frmInfo(){
		String attribute[] = {"Area", "Position","name","content"};
		model = new DefaultTableModel(attribute,0);
		table = new JTable(model);
		scrollpane = new JScrollPane(table);
		
		int cnt=result.length;
		System.out.println(cnt);
		for(int i=0;i<cnt;i++){
			model.addRow(result[i]);
		}
	}
}
