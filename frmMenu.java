package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Main;
import view.panel.PanImgload;

public class frmMenu extends JFrame implements MouseListener{
	private JLayeredPane layeredPane = new JLayeredPane(); // 셀로판지 식;
	BufferedImage img = null;
	Main main;
	JTextField follow,help,self,etc,mypage;
	JButton btnHelp,btnMypage,btnSelf,btnEtc,btnFollow;
	String id;
	public frmMenu(String id) {
		frmMenu fm=this;
		this.id = id;
		follow = new JTextField(); help = new JTextField();
		self = new JTextField(); etc = new JTextField(); mypage = new JTextField();
		Color color = new Color(0xFF0000,true); //검은색을 존나 투명하게해서 안보이게 만듬
		setTitle("Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1033, 800);
		setLayout(null);

		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);

		setPanel(layeredPane).setBounds(0, 0, 1033, 800);
		JPanel sMenu = new PanImgload("img/selectMenu.png");
		setPanel(sMenu).setBounds(0, 0, 1033, 800);
		
		follow.setFont(new Font("배달의민족 한나", Font.BOLD, 15));help.setFont(new Font("배달의민족 한나", Font.BOLD, 15));
		self.setFont(new Font("배달의민족 한나", Font.BOLD, 15));etc.setFont(new Font("배달의민족 한나", Font.BOLD, 15));
		mypage.setFont(new Font("배달의민족 한나", Font.BOLD, 15)); setTextfield(mypage,"마이 페이지",888,65,100,40);
		setTextfield(follow,"패키지 여행을 원한다면",175,250,220,100); setTextfield(help,"맞춤형 패키지 여행을 원한다면",332,250,235,100);
		setTextfield(self,"스스로 일정 생성을 원한다면",510,250,250,100); setTextfield(etc,"맛집, 숙박업소",743,250,220,100);
		btnHelp = new JButton("2"); btnMypage = new JButton("5"); btnFollow = new JButton("1");
		btnSelf = new JButton("3"); btnEtc = new JButton("4");
		btnHelp.setBounds(367, 335, 130, 130);btnSelf.setBounds(542, 335, 130, 130);
		btnFollow.setBounds(187, 335, 130, 130);btnMypage.setBounds(865, 20, 135, 50);
		btnEtc.setBounds(723, 335, 130, 130);
		btnHelp.setForeground(color); btnSelf.setForeground(color); btnMypage.setForeground(color);
		btnFollow.setForeground(color); btnEtc.setForeground(color);
		btnHelp.addMouseListener(this); btnSelf.addMouseListener(this); btnMypage.addMouseListener(this);
		btnEtc.addMouseListener(this); btnFollow.addMouseListener(this); 
		btnBlind(btnHelp);btnBlind(btnSelf);btnBlind(btnFollow);
		btnBlind(btnMypage);btnBlind(btnEtc);
		
	      btnHelp.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("Help 선택됨");
	        	  main.showHelpsetFrame(fm);
	          }
	      });
	      btnSelf.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("Self 선택됨");
	        	  main.showSelfFrame(fm);
	          }
	      });
	      btnFollow.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("Follow 선택됨");
	        	  main.showFollowFrame(fm);
	          }
	      });
	      btnMypage.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("Mypage 선택됨");
	        	  main.showFrameMypage(fm,id);
	          }
	      });
	      btnEtc.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("냠냠쿨쿨 선택됨");
	        	  main.showFrameEtc(fm);
	          }
	      });
		add(setJLayered(sMenu,btnHelp,btnSelf,btnFollow,btnEtc,btnMypage,help,self,etc,follow,mypage));
		setVisible(true);

	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
	public void btnBlind(JButton btn) {
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
	}

	public void setTextfield(JTextField j,String str,int a,int b,int c,int d){
		j.setBounds(a, b, c, d);
		j.setOpaque(false);
		j.setForeground(Color.black);
		j.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		j.setText(str);
		j.setVisible(false);
	}
	// 배경투명처리 및 레이아웃 널
	// J컴포넌트로 받는 이유는 J패널 이외에 다른 것도 받기 위해서 예를들면 레이어드패인
	public JComponent setPanel(JComponent panel) {
		panel.setLayout(null);
		panel.setOpaque(false);
		return panel;
	}

	// 다중 parameter로 잡았음
	// 코드중복을줄이기위해 안써도 상관없음 ㅋㅋ
	public JLayeredPane setJLayered(Component... components) {
		int i = 0;
		for (Component component : components)
			layeredPane.add(component, new Integer(i++));
		return layeredPane;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		switch(((JButton) e.getSource()).getText()) { 
		   case "1" : follow.setVisible(true); break; 
		   case "2" : help.setVisible(true);; break; 
		   case "3" : self.setVisible(true); break;
		   case "4" : etc.setVisible(true); break; 
		   case "5" : mypage.setVisible(true); break;
		} 
	}

	@Override
	public void mouseExited(MouseEvent e) {
		follow.setVisible(false);
		help.setVisible(false);
		self.setVisible(false);
		etc.setVisible(false);
		mypage.setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
