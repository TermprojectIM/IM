package view;

import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JTextField;

import main.Main;
import view.panel.PanImgload;

public class frmRgtInfo extends JFrame {
	private JLayeredPane layeredPane = new JLayeredPane();
	private JButton btnHome = new JButton(new ImageIcon("img/home.png"));
	Main main;
	JTextField area,position,name,content;
	JButton btnRgtInfo;
	
	public frmRgtInfo(Main main){
		this.main=main;
		setTitle("여행지 정보 등록");
		setSize(510,485);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
					(windowSize.height - frameSize.height) / 2);
		setPanel(layeredPane).setBounds(0,0,510,485);
		JPanel backGround = new PanImgload("img/rgtInfo.png");
		setPanel(backGround).setBounds(0, 0,510,485);
		area = new JTextField(15); position = new JTextField(15); name = new JTextField(15); content = new JTextField(100);
		area.setBounds(200,133,200,35); position.setBounds(200,183,200,35); name.setBounds(200,233,200,35); content.setBounds(200,283,200,35);
		btnRgtInfo = new JButton(); btnRgtInfo.setBounds(229,347,95,45); btnBlind(btnRgtInfo); 
		btnHome.setBounds(430,10,45,45); btnBlind(btnHome);
		
		btnRgtInfo.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("등록 선택됨");
	        	  String a= area.getText(); area.setText(""); String b= position.getText(); position.setText("");
	        	  String c= name.getText(); name.setText(""); String d= content.getText(); content.setText("");
	        	  ///////// 디비 쿼리문 날릴때 저 데이터들을 전달해주자
	        	  if(main.insertSelfInfo(a, b, c, d))
	        		  JOptionPane.showMessageDialog(null, "데이터 삽입 성공 하였습니다.");
	        	  else
	        		  JOptionPane.showMessageDialog(null, "데이터 삽입 실패 하였습니다.");
	        	  }
	      });
		
		btnHome.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  dispose();
	        	  main.showFrameadmin();
	          }
	      });
		

		add(setJLayered(backGround,area,position,name,content,btnRgtInfo,btnHome));
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
	
//	public static void main(String[] args){
//		new frmRgtInfo();
//		
//	}
}
