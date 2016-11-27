package view;

import java.awt.Component;
import java.awt.Dimension;
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

import main.Main;
import view.panel.PanImgload;

public class frmHelpset extends JFrame {
	Main main;
	private JLayeredPane layeredPane = new JLayeredPane(); // 셀로판지 식;
	JButton btnGo;
	JComboBox <String>start, end, date;
	
	public frmHelpset(Main main){
		frmHelpset fhs=this;
		this.main = main;
		setTitle("IM - 맞춤형 패키지 여행 설정");
		setSize(730,533);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
		setPanel(layeredPane).setBounds(0,0,730,533);
		JPanel backGround = new PanImgload("img/helpset.png");
		setPanel(backGround).setBounds(0, 0, 730, 533);
		
		String[] item1 = {"당일 여행", "1박 2일", "2박 3일", "3박 4일", "4박 5일", "5박 6일", "장기 여행"};
		String[] item2 = {"오전", "오후"};
		date = new JComboBox<String>(item1);
		date.setSelectedIndex(0);
		date.setBounds(300, 156, 240,40);
		date.setVisible(true);
		start = new JComboBox<String>(item2);
		start.setSelectedIndex(0);
		start.setBounds(300, 210, 240,40);
		start.setVisible(true);
		end = new JComboBox<String>(item2);
		end.setSelectedIndex(0);
		end.setBounds(300, 263, 240,40);
		end.setVisible(true);
		btnGo = new JButton();
		btnGo.setBounds(207,316,328,40);
		btnBlind(btnGo);
		
		
		btnGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String day,begin,finish;
            	day = (String)date.getSelectedItem();
            	begin = (String)start.getSelectedItem();
            	finish = (String)end.getSelectedItem();
            	System.out.println("몇박 몇일:"+ day + ", 출발 일정:" + begin + ", 종료 일정:" + finish);
            	main.showHelpFrame(fhs,day,begin,finish);
            }
        });
		
		
		add(setJLayered(backGround,date,start,end,btnGo));
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
