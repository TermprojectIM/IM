
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
		String content[][] = {{"대구광역시","어딘가의","장소","굿"},{"부산광역시","어딘가의","장소","배드"},{"울산","어딘가의","장소","쏘쏘"}};
		String add[]={"연습용","추가되는지","혼자서","테스트"};
		model = new DefaultTableModel(attribute,0);
		//model = new DefaultTableModel(content,attribute);
		table = new JTable(model);
		///table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //크기에맞춰 자동배치가 아닌 설정대로 되게! 즉 스크롤바가 보이게
		scrollpane = new JScrollPane(table);
		
		int cnt=result.length;
		System.out.println(cnt);
		for(int i=0;i<cnt;i++){
			model.addRow(result[i]);
		}
	}
	
//	public static void main(String []args){
//		frmInfo me = new frmInfo();
//		String attribute[] = {"Area", "Position","name","content"};
//		String content[][] = {{"대구광역시","어딘가의","장소","굿"},{"부산광역시","어딘가의","장소","배드"},{"울산","어딘가의","장소","쏘쏘"}};
//		String add[]={"연습용","추가되는지","혼자서","테스트"};
//		DefaultTableModel model = new DefaultTableModel(content,attribute);
//		
//		JFrame frame = new JFrame("테스트");
//		Dimension dim = new Dimension(400,150);
//		frame.setLocation(200,400);
//		frame.setPreferredSize(dim);
//		JTable table = new JTable(model);
//		JScrollPane scrollpane = new JScrollPane(table);
//		
//		me.connectDB("localhost", "im", "im", "1234");
//		me.result=me.readSelfInfo("수성못");
//		int cnt=me.result.length;
//		System.out.println(cnt);
//		for(int i=0;i<cnt;i++){
//			model.addRow(me.result[i]);
//		}
//		//model.addRow(add); //추가
//		//model.addRow(add);
//		
//		// table.getSelectedRow() == -1  클릭되지않았으면
//		frame.add(scrollpane);
//		frame.pack();
//		frame.setVisible(true);
//	}
	
}
