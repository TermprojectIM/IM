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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import data.Info;
import data.SelfInfo;
import main.Main;
import view.panel.PanImgload;

public class frmSelfmake extends JFrame {
	private JLayeredPane layeredPane = new JLayeredPane();
	Main main;
	JTable make;
	JTextField period, time, name;
	JButton btnAdd, btnDel, btnMkc;
	DefaultTableModel model;
	JScrollPane scrollpane;

	public frmSelfmake(String day) {

		// 당일 여행
		setTitle(day);
		setSize(600, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);
		setPanel(layeredPane).setBounds(0, 0, 600, 600);
		JPanel backGround = new PanImgload("img/selfmake.png");
		setPanel(backGround).setBounds(0, 0, 600, 600);

		// 여기서부터 텍스트필드입니다.
		period = new JTextField(15);
		time = new JTextField(15);
		name = new JTextField(15);
		period.setBounds(60, 182, 200, 30);
		period.setBorder(null);
		time.setBounds(60, 231, 200, 30);
		time.setBorder(null);
		name.setBounds(60, 281, 200, 29);
		name.setBorder(null);

		// 여기서부터 테이블입니다.
		String attribute[] = { "날짜", "시간", "장소" };
		model = new DefaultTableModel(attribute, 0);
		make = new JTable(model);
		scrollpane = new JScrollPane(make);
		scrollpane.setBounds(330, 170, 230, 230);
		// 여기서부터 버튼 이벤트 입니다.
		btnAdd = new JButton();
		btnDel = new JButton();
		btnMkc = new JButton();
		btnAdd.setBounds(10, 335, 80, 50);
		btnDel.setBounds(108, 335, 80, 50);
		btnMkc.setBounds(206, 335, 80, 50);
		btnBlind(btnAdd);
		btnBlind(btnDel);
		btnBlind(btnMkc);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("추가 선택됨");
				String d = period.getText();
				period.setText("");
				String t = time.getText();
				time.setText("");
				String n = name.getText();
				name.setText("");
				// 기본 처리 끝 마친 후에
				Object data[] = { d, t, n };
				DefaultTableModel model = (DefaultTableModel) make.getModel();
				model.addRow(data);
			}
		});

		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("삭제 선택됨");
				DefaultTableModel model = (DefaultTableModel) make.getModel();
				model.removeRow(make.getSelectedRow());
			}
		});

		btnMkc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("차트 선택됨");
				System.out.println("테이블에" + make.getRowCount() + "개의 레코드가 존재합니다");
				// 여기서 넘겨주면됩니다
				SelfInfo[] data = new SelfInfo[model.getRowCount()];

				for (int i = 0; i < model.getRowCount(); i++) {
					data[i] = new SelfInfo((String) model.getValueAt(i, 0), (String) model.getValueAt(i, 1),
							(String) model.getValueAt(i, 2));
				}

				frmMakeChart fmc = new frmMakeChart(data, day);
				// 객체선언하고 뭐 하시면됩니다.
			}
		});

		add(setJLayered(backGround, btnAdd, btnDel, btnMkc, period, time, name, scrollpane));
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
