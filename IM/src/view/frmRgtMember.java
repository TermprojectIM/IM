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

public class frmRgtMember extends JFrame {
	private JLayeredPane layeredPane = new JLayeredPane();
	private JButton btnHome = new JButton(new ImageIcon("img/home.png"));
	Main main;
	JTextField id,rank;
	JButton btnRgtMem;
	
	public frmRgtMember(Main main){
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
		JPanel backGround = new PanImgload("img/rgtMem.png");
		setPanel(backGround).setBounds(0, 0,510,485);
		id = new JTextField(15); rank = new JTextField(15);
		id.setBounds(200,147,200,35); rank.setBounds(200,224,200,35);
		btnRgtMem = new JButton(); btnRgtMem.setBounds(250,330,95,45); btnBlind(btnRgtMem);
		btnHome.setBounds(430,10,45,45); btnBlind(btnHome);
		
		btnRgtMem.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("등록 선택됨");
	        	  String a= id.getText(); id.setText(""); String b= rank.getText(); rank.setText("");


	        	  if(main.UpdateUserRank(a, b))
	        		  JOptionPane.showMessageDialog(null, "등급 등록 성공하였습니다.");
	        	  else
	        		  JOptionPane.showMessageDialog(null, "등급 등록 실패하였습니다.");
	        	  }
	      });
		
		btnHome.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  dispose();
	        	  main.showFrameadmin();
	          }
	      });
		
		add(setJLayered(backGround,id,rank,btnRgtMem,btnHome));
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
