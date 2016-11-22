package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.panel.PanImgload;

public class frmSelf extends JFrame {
	private JLayeredPane layeredPane = new JLayeredPane(); // 셀로판지 식;
	JTextField search;
	JButton btnMake,btnDay;
	JComboBox <String>box;
	
	public frmSelf(){
		setTitle("IM - 스스로 여행짜기");
		setSize(1040,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
		
		setPanel(layeredPane).setBounds(0,0,1040,800);
		
		//배경
		JPanel backGround = new PanImgload("img/op3.png");
		setPanel(backGround).setBounds(0, 0, 1040, 800);
		search = new JTextField(25);
		setPanel(search).setBounds(540, 124, 375, 50);
		search.setFont(new Font("배달의민족 한나", Font.BOLD, 20));
		search.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		search.setForeground(Color.darkGray);

		String[] items = {"당일 여행", "1박 2일", "2박 3일", "3박 4일", "4박 5일", "5박 6일", "장기 여행"};
		box = new JComboBox<String>(items);
		box.setSelectedIndex(2);
		box.setLocation(750, 200);
		box.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	String str = (String)box.getSelectedItem();
            	System.out.println(str + "선택 되었습니다.");
            }
        });
		
		
		btnMake = new JButton();
		btnMake.setBounds(770, 190, 200, 50);
		btnMake.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("만들기 눌림");
            	
            }
        });
		
		btnBlind(btnMake);
		//frmMap imgM = new frmMap(search);
		//imgM.setBounds(100,260,450,450);	
		//우선순위를 증가하면서 즉 뒤에있는것의 우선순위가 높게 설정
		//add(setJLayered(backGround,imgM,imgM.btnSearch,search,btnMake,box));
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

	public static void main(String[] args) {
		new frmSelf();
	}
}
