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

public class frmAdminMenu extends JFrame {
	Main main;
	private JLayeredPane layeredPane = new JLayeredPane(); // 셀로판지 식;
	JButton btnInfo, btnGrade;


	public frmAdminMenu(int num, Main main) { // num이 1이면 등록모드, 2면 삭제모드
		this.main = main;
		setTitle("관리자 모드");
		setSize(620, 490);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);

		setPanel(layeredPane).setBounds(0, 0, 620, 490);
		JPanel backGround = new PanImgload("img/adminmain2.png");
		setPanel(backGround).setBounds(0, 0, 620, 490);

		btnInfo = new JButton(); btnGrade = new JButton();
		btnInfo.setBounds(159,168,100,100); btnGrade.setBounds(340,168,100,100);
		btnBlind(btnInfo); btnBlind(btnGrade);
		if (num == 1) {//등록 - Info
			btnInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("등록 - info 선택됨");
					dispose();
					frmRgtInfo fri = new frmRgtInfo(main);
				}
			});
			//등록 - 등급
			btnGrade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("등록 - 등급 선택됨");
					dispose();
					frmRgtMember frm = new frmRgtMember(main);
				}
			});
		} else if (num == 2) {
			btnInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("삭제 - Info 선택됨");
					dispose();
					frmRmInfo rif = new frmRmInfo(main);
				}
			});
			btnGrade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("삭제 - 등급 선택됨");
					dispose();
					frmRmMember rmem = new frmRmMember(main);
				}
			});
		}

		add(setJLayered(backGround, btnInfo, btnGrade));
		setVisible(true);

	}

	public JComponent setPanel(JComponent panel) {
		panel.setLayout(null);
		panel.setOpaque(false);
		return panel;
	}

	public void btnBlind(JButton btn) {
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
	}

	public JLayeredPane setJLayered(Component... components) {
		int i = 0;
		for (Component component : components)
			layeredPane.add(component, new Integer(i++));
		return layeredPane;
	}

}
