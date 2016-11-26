package view.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardLayoutTest1 implements ActionListener {
	JButton btn1, btn2, btn3;
	CardLayout cardLayoutSet;
	JPanel cardPanel;

	public CardLayoutTest1() {
	}

	public Container panel1() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("1번 패널");
		panel.add(label);
		return panel;
	}

	public Container panel2() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("2번 패널");
		panel.add(label);
		return panel;

	}

	public Container panel3() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("3번 패널");
		panel.add(label);
		return panel;
	}

	public Container makeCardLayout() {
		cardLayoutSet = new CardLayout();
		cardPanel = new JPanel();
		// 일반 패널인 cardPanel을 카드 레이아웃인 cardLayoutSet으로 설정하였다
		cardPanel.setLayout(cardLayoutSet);
		CardLayoutTest1 test = new CardLayoutTest1();
		// 이름을 설정
		cardPanel.add("card1", test.panel1());
		cardPanel.add("card2", test.panel2());
		cardPanel.add("card3", test.panel3());
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(cardPanel);

		btn1 = new JButton("단추1");
		btn2 = new JButton("단추2");
		btn3 = new JButton("단추3");
		JPanel buttonLayout = new JPanel(new BorderLayout());
		buttonLayout.add(btn1, BorderLayout.LINE_START);
		buttonLayout.add(btn2, BorderLayout.CENTER);
		buttonLayout.add(btn3, BorderLayout.LINE_END);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		panel.add(buttonLayout, BorderLayout.PAGE_END);

		return panel;
	}

	public void createFrame() {
		JFrame frame = new JFrame("버튼으로 패널 변경");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(makeCardLayout());
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 카드 레이아웃인 부모를 선택하고 카드 레이아웃으로 설정한 자식을 선택하고,
		// 자식의 자식인 불러올 패널의 이름을 적는다
		if (e.getSource() == btn1)
			cardLayoutSet.show(cardPanel, "card1");
		else if (e.getSource() == btn2)
			cardLayoutSet.show(cardPanel, "card2");
		else if (e.getSource() == btn3)
			cardLayoutSet.show(cardPanel, "card3");
		;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardLayoutTest1 test = new CardLayoutTest1();
		test.createFrame();
	}
}