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

import main.Main;
import view.panel.PanImgload;

public class frmFollow extends JFrame implements ActionListener {
	Main main;
	private JLayeredPane layeredPane = new JLayeredPane(); // 셀로판지 식;
	private JButton put1 = new JButton(new ImageIcon("img/put.png"));
	private JButton put2 = new JButton(new ImageIcon("img/put.png"));
	private JButton put3 = new JButton(new ImageIcon("img/put.png"));
	private JButton put4 = new JButton(new ImageIcon("img/put.png"));
	private JButton put5 = new JButton(new ImageIcon("img/put.png"));
	private JButton btnHome = new JButton(new ImageIcon("img/home.png"));
	String id;
	public frmFollow(Main main){
		this.main = main;
		setTitle("IM - 패키지 여행");
		setSize(820,880);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
		setPanel(layeredPane).setBounds(0,0,820,880);	
		
		JPanel backGround = new PanImgload("img/follow.png");
		setPanel(backGround).setBounds(0, 0, 820, 880);
		//버튼 설정 부분
		put1.setBounds(700,45,85,85);put2.setBounds(700,210,85,85);put3.setBounds(700,380,85,85);
		put4.setBounds(700,550,85,85);put5.setBounds(700,710,85,85);btnHome.setBounds(750,1,45,45);
		btnBlind(put1); btnBlind(put2); btnBlind(put3); btnBlind(put4); btnBlind(put5); btnBlind(btnHome);
 		start();//이벤트
		add(setJLayered(backGround,put1,put2,put3,put4,put5,btnHome));
		setVisible(true);
	}	
	
	public void start(){
		put1.addActionListener(this);
		put2.addActionListener(this);
		put3.addActionListener(this);
		put4.addActionListener(this);
		put5.addActionListener(this);
		btnHome.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==put1){
			System.out.println("1 - 담겼습니다");
			JOptionPane.showMessageDialog(null, "마이페이지에 저장하였습니다.");
		}else if(e.getSource()==put2){
			System.out.println("2 - 담겼습니다");
			JOptionPane.showMessageDialog(null, "마이페이지에 저장하였습니다.");
		}else if(e.getSource()==put3){
			System.out.println("3 - 담겼습니다");
			JOptionPane.showMessageDialog(null, "마이페이지에 저장하였습니다.");
		}else if(e.getSource()==put4){
			System.out.println("4 - 담겼습니다");
			JOptionPane.showMessageDialog(null, "마이페이지에 저장하였습니다.");
		}else if(e.getSource()==put5){
			System.out.println("5 - 담겼습니다");
			JOptionPane.showMessageDialog(null, "마이페이지에 저장하였습니다.");
		}else if(e.getSource()==btnHome){
			dispose();
      	    main.showFrameMain();
		}
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
	
	
//	public static void main(String [] args){
//		new frmFollow();
//	}

}
