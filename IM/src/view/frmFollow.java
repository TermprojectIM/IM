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
	String pname[]=new String[5]; String content[]= new String[5];
	String cost[]=new String[5]; String date[]=new String[5];
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
 		pname[0]="겨울연가의 추억"; content[0]="남이섬 눈꽃 & 강촌 레일 바이크 & 춘천명동 닭갈비 여행"; cost[0]="64000"; date[0]="당일";
 		pname[1]="단풍 휴양림"; content[1]="대둔산 단풍 케이블카 & 장태산 휴양림"; cost[1]="89000"; date[1]="당일";
 		pname[2]="남도 감성여행"; content[2]="남도 한바퀴 (담양, 곡성 탐방) 기차여행"; cost[2]="107000"; date[2]="당일";
 		pname[3]="청양 기차여행"; content[3]="청양 출렁다리 & 고운식물원 기차여행"; cost[3]="79000"; date[3]="당일";
 		pname[4]="충남 기차여행"; content[4]="충남 서천 구석구석 힐링여행 스카이워크/동백나무숲 가이드"; cost[4]="69000"; date[4]="당일";
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
			boolean check=main.insertFollowInfo(pname[0], content[0], cost[0], date[0]);
			if(check)
				JOptionPane.showMessageDialog(null, "마이페이지에 저장하였습니다.");
			else
				JOptionPane.showMessageDialog(null, "이미 저장한 항목입니다.");
		}else if(e.getSource()==put2){
			boolean check=main.insertFollowInfo(pname[1], content[1], cost[1], date[1]);
			if(check)
				JOptionPane.showMessageDialog(null, "마이페이지에 저장하였습니다.");
			else
				JOptionPane.showMessageDialog(null, "이미 저장한 항목입니다.");
		}else if(e.getSource()==put3){
			boolean check=main.insertFollowInfo(pname[2], content[2], cost[2], date[2]);
			if(check)
				JOptionPane.showMessageDialog(null, "마이페이지에 저장하였습니다.");
			else
				JOptionPane.showMessageDialog(null, "이미 저장한 항목입니다.");
		}else if(e.getSource()==put4){
			boolean check=main.insertFollowInfo(pname[3], content[3], cost[3], date[3]);
			if(check)
				JOptionPane.showMessageDialog(null, "마이페이지에 저장하였습니다.");
			else
				JOptionPane.showMessageDialog(null, "이미 저장한 항목입니다.");
		}else if(e.getSource()==put5){
			boolean check=main.insertFollowInfo(pname[4], content[4], cost[4], date[4]);
			if(check)
				JOptionPane.showMessageDialog(null, "마이페이지에 저장하였습니다.");
			else
				JOptionPane.showMessageDialog(null, "이미 저장한 항목입니다.");
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

}
