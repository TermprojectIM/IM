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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import main.Main;
import view.panel.PanImgload;

public class frmSelf extends JFrame {
	Main main;
	private JLayeredPane layeredPane = new JLayeredPane(); // 셀로판지 식;
	private JButton btnHome = new JButton(new ImageIcon("img/home.png"));
	JTextField search;
	JButton btnMake,btnDay;
	JComboBox <String>box;
	frmSelfmake smk;
	public frmSelf(Main main){
		this.main=main;
		setTitle("IM - 스스로 여행짜기");
		setSize(1040,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
		
		setPanel(layeredPane).setBounds(0,0,1040,800);
		
		//���
		JPanel backGround = new PanImgload("img/op3.png");
		setPanel(backGround).setBounds(0, 0, 1040, 800);
		search = new JTextField(25);
		setPanel(search).setBounds(540, 124, 375, 50);
		search.setFont(new Font("배달의민족 한나", Font.BOLD, 20));
		search.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		search.setForeground(Color.darkGray);

		String[] items = {"당일 여행", "1박 2일", "2박 3일", "3박 4일", "4박 5일", "5박 6일", "장기 여행"};
		box = new JComboBox<String>(items);
		box.setSelectedIndex(0);
		box.setBounds(530, 190, 230,50);
		box.setVisible(true);
		
//		box.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e) {
//            	String str = (String)box.getSelectedItem();
//            	System.out.println(str + "선택 되었습니다.");
//            }
//        });
//		
		
		btnMake = new JButton();
		btnMake.setBounds(770, 190, 200, 50); btnHome.setBounds(960,12,45,45);
		btnMake.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String str = (String)box.getSelectedItem();
            	if(str.equals("당일 여행")){
            		System.out.println(str + " 만들기 눌림");
            		smk = new frmSelfmake("당일 여행");
            	}else if(str.equals("1박 2일")){
            		System.out.println(str + " 만들기 눌림");
            		smk = new frmSelfmake("1박 2일");
            	}else if(str.equals("2박 3일")){
            		System.out.println(str + " 만들기 눌림");
            		smk = new frmSelfmake("2박 3일");
            	}else{
            		System.out.println("일단은 구현안하고 임의로 항목만 둔 것");
            	}
            }
        });
		
		btnHome.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  dispose();
	        	  main.showFrameMain();
	          }
	      });
		
		btnBlind(btnMake); btnBlind(btnHome);
		frmInfo info = new frmInfo();
		frmMap imgM = new frmMap(search,info);
		imgM.setBounds(50,140,380,591);
		imgM.setMain(this.main);
		info.scrollpane.setBounds(530,250,450,488);
		//우선순위를 증가하면서 즉 뒤에있는것의 우선순위가 높게 설정
		add(setJLayered(backGround,imgM,imgM.btnSearch,search,box,btnMake,btnHome,info.scrollpane));
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


//	public static void main(String[] args) {
//		new frmSelf();
//	}
}


