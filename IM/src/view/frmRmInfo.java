package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import view.panel.PanImgload;
import main.Main;

public class frmRmInfo extends JFrame {
	private JLayeredPane layeredPane = new JLayeredPane();
	private JButton btnHome = new JButton(new ImageIcon("img/home.png"));
	Main main;
	JTable table;
	JTextField name;
	JButton btnDel;
	DefaultTableModel model;
	JScrollPane scrollpane;
	String result[][] = null;
	int cnt=0;
	public frmRmInfo(Main main){
		this.main=main;
		setTitle("여행지 정보 삭제");
		setSize(640,520);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
					(windowSize.height - frameSize.height) / 2);
		setPanel(layeredPane).setBounds(0,0,640,520);
		JPanel backGround = new PanImgload("img/rmInfo.png");
		setPanel(backGround).setBounds(0, 0,640,520);
		//textfield
		name = new JTextField(20); name.setBounds(450,146,150,35); 
		name.setFont(new Font("배달의민족 한나", Font.BOLD, 15)); name.setForeground(Color.gray);
		
		//table
		String attribute[] = {"Area", "Position","name","content"};
		model = new DefaultTableModel(attribute,0);
		table = new JTable(model);
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(10,100,320,320);
		result=main.readSelfInfo();
		//처음 selfinfo 테이블 정보 다가져와서 올리기
		cnt = result.length;
		System.out.println(cnt);
		for(int i=0;i<cnt;i++){
			model.addRow(result[i]);
		}
		table.setModel(model);
		
		//button
		btnDel = new JButton();  btnDel.setBounds(469,240,100,60); btnBlind(btnDel);
		btnHome.setBounds(560,10,45,45); btnBlind(btnHome);
		btnDel.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("삭제 선택됨");
	        	  String n = name.getText(); name.setText("");
	        	  if(main.RmSelfInfo(n)){
	        		  JOptionPane.showMessageDialog(null, "데이터 삭제 성공하였습니다.");
	        		  model = new DefaultTableModel(attribute,0);
	        		  result=main.readSelfInfo();
	        		  cnt=result.length;
	        		  for(int i=0;i<cnt;i++){
	        			  model.addRow(result[i]);
	        		  }
	        		  table.setModel(model);
	        	  }
	        	  else
	        		  JOptionPane.showMessageDialog(null, "데이터 삭제 실패하였습니다.");
        	  	}
	      });
		
		btnHome.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  dispose();
	        	  main.showFrameadmin();
	          }
	      });
		
		add(setJLayered(backGround,scrollpane,name,btnDel,btnHome));
		setVisible(true);
	}
	
	
	public JComponent setPanel(JComponent panel){
		panel.setLayout(null);
		panel.setOpaque(false);
		return panel;
	}
	
	public void btnBlind(JButton btn) {
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
	}

	public JLayeredPane setJLayered(Component...components){
        int i = 0;
        for (Component component : components)
            layeredPane.add(component, new Integer(i++));
        return layeredPane;
    }	
}
