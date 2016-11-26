package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import view.panel.PanImgload;
import main.Main;

public class frmAdminMain extends JFrame{
	Main main;
	private JLayeredPane layeredPane = new JLayeredPane(); // 셀로판지 식;
	JButton btnRgt,btnRm;
	frmAdminMenu fam;
	public frmAdminMain(Main main){
		this.main = main;
		setTitle("관리자 모드");
		setSize(620,490);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
		
		setPanel(layeredPane).setBounds(0,0,620,490);
		JPanel backGround = new PanImgload("img/adminmain.png");
		setPanel(backGround).setBounds(0,0, 620,490);
		
		btnRgt = new JButton(); btnRm = new JButton();
		btnRgt.setBounds(160,165,100,100); btnRm.setBounds(340,165,100,100);
		btnBlind(btnRgt); btnBlind(btnRm);
		btnRgt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				System.out.println("등록 선택됨");
				dispose();
				fam = new frmAdminMenu(1,main);
			}	      
		});
		btnRm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				System.out.println("삭제 선택됨");
				dispose();
				fam = new frmAdminMenu(2,main);
			}	      
		});
		
		
		
		
		
		
		add(setJLayered(backGround,btnRgt,btnRm));
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
//		new frmAdminMain();
//	}
	
}
