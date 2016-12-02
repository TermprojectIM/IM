package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import main.Main;
import view.panel.PanImgload;

public class frmMyPage extends JFrame implements ActionListener, MouseListener{
	Main main;
	private JLayeredPane layeredPane = new JLayeredPane();
	private JButton btnUserInformation = new JButton(new ImageIcon("img/userinfo.png"));
	private JButton btnMyGrade = new JButton(new ImageIcon("img/grade.png"));
	private JButton btnMyList = new JButton(new ImageIcon("img/epilogue.png"));
	private JButton btnHome = new JButton(new ImageIcon("img/home.png"));
	private JTextField userInfo = new JTextField();
	private JTextField grade = new JTextField();
	private JTextField epil = new JTextField();
	private JTextField t_name,t_id,t_phone;
	private CardLayout cardLayoutSet;
	private JPanel cardPanel;
	private JLabel grade_label;
	private JPanel image[] = new PanImgload [4];
	DefaultTableModel model;
	JScrollPane scrollpane;
	JTable epilTable;
	JButton btnAdd,btnDel,btnSave;
	String result[][]=null;
	String id;
	String u_info[]=null;
	int cnt=0;
	private JPanel jp;
	private JPanel jpBackGround = null;
	
	public frmMyPage(Main main,String id) {
		this.main = main;
		this.id=id;
		u_info=main.ReadUserInfo(id);
		setTitle("IM - MyPage");
		setSize(820,645);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
		setPanel(layeredPane).setBounds(0,0,820,645);
		jpBackGround = new PanImgload("img/frmMypage.png");		
		setPanel(jpBackGround).setBounds(0, 0, 820, 645);
		//텍스트 필드 설정
		userInfo.setFont(new Font("배달의민족 한나", Font.BOLD, 15)); grade.setFont(new Font("배달의민족 한나", Font.BOLD, 15));
		epil.setFont(new Font("배달의민족 한나", Font.BOLD, 15));  setTextfield(userInfo,"내 정보를 확인",145,175,100,40);
		setTextfield(grade,"내 등급을 확인",345,174,100,40); setTextfield(epil,"나만의 여행 후기",535,173,130,40);
		//버튼 설정 부분
		Color color = new Color(0xFF0000,true);
		btnMyList.setBounds(550, 100, 85, 85); btnMyGrade.setBounds(350, 100, 85, 85);
		btnUserInformation.setBounds(150, 100, 85, 85); btnHome.setBounds(745,10,45,45);
		btnUserInformation.setText("1"); btnMyGrade.setText("2"); btnMyList.setText("3"); 
		btnUserInformation.setForeground(color); btnMyGrade.setForeground(color); btnMyList.setForeground(color);
		
 		btnBlind(btnUserInformation); btnBlind(btnMyGrade); btnBlind(btnMyList); btnBlind(btnHome);

 		//등급위한 이미지 설정
 		image[0] = new PanImgload("img/unrank.png");
 		image[1] = new PanImgload("img/bronze.png");
 		image[2] = new PanImgload("img/silver.png");
 		image[3] = new PanImgload("img/gold.png");
 		
 		//패널
 		jp = new JPanel(); jp.setBackground(Color.white);
 		start();//이벤트
		add(setJLayered(jpBackGround,btnHome,btnMyList,btnMyGrade,btnUserInformation,userInfo,grade,epil, jp));
		
		//setPanelArea((JPanel) u_panel());
		setVisible(true);
	}
	
	public void setPanelArea(JPanel panel){
		//
		layeredPane.remove(jp);
		this.jp = panel;
		
		layeredPane.add(jp, new Integer(100));
		//layeredPane.revalidate();
		layeredPane.repaint();
	}
	
	public Container u_panel() {
		JPanel panel = new JPanel(); panel.setLayout(null);
		panel.setBackground(new Color(0xeeeeee,false));		
		panel.setBounds(50, 230, 705, 300);
		t_name = new JTextField();t_id = new JTextField();t_phone = new JTextField();
		JLabel label = new JLabel("ID:"); label.setFont(new Font("배달의민족 한나", Font.BOLD,18));
		JLabel label1 = new JLabel("이름:"); label1.setFont(new Font("배달의민족 한나", Font.BOLD,18));
		JLabel label2 = new JLabel("전화번호:"); label2.setFont(new Font("배달의민족 한나", Font.BOLD,18));
		label.setBounds(70, 0, 100, 100);label1.setBounds(50, 100, 100, 100);label2.setBounds(10, 200, 100, 100);
		/////////////////////////////////////////////
		//이부분에 가져온 아이디가 해당 아이디의 정보가 뜨도록 해야함.
		///////////////////////////////////////////////
		t_id.setFont(new Font("배달의민족 한나", Font.BOLD,18));setTextfield(t_id,u_info[0],150,0,100,100); t_id.setVisible(true);
		t_name.setFont(new Font("배달의민족 한나", Font.BOLD,18));setTextfield(t_name,u_info[1],150,100,100,100); t_name.setVisible(true);
		t_phone.setFont(new Font("배달의민족 한나", Font.BOLD,18));setTextfield(t_phone,u_info[2],150,200,150,100); t_phone.setVisible(true);
		panel.add(label); panel.add(label1); panel.add(label2); panel.add(t_name); panel.add(t_phone); panel.add(t_id);
		return panel;
	}

	public Container g_panel() {
		JPanel panel = new JPanel(); panel.setLayout(new GridLayout());
		panel.setBackground(new Color(0xeeeeee,false));	panel.setBounds(50, 230, 705, 300);
		JLabel label = new JLabel("                  나의 등급은 : "); label.setFont(new Font("배달의민족 한나", Font.BOLD,30));
		panel.add(label);
		if(u_info[3].equals("1")){
			panel.add(image[0]);
		}else if(u_info.equals("2")){
			panel.add(image[1]);
		}else if(u_info.equals("3")){
			panel.add(image[2]);
		}else if(u_info[3].equals("4")){
			panel.add(image[3]);
		}
		return panel;

	}

	public Container e_panel() {
		JPanel panel = new JPanel(); panel.setLayout(null);
		panel.setBackground(new Color(0xeeeeee,false)); panel.setBounds(50, 230, 705, 300);
		JLabel label = new JLabel("나만의 후기"); label.setFont(new Font("배달의민족 한나", Font.BOLD,15));
		label.setBounds(300,-10,100,50);
		//테이블
		String attribute[] = {"Title","Content"};
		model = new DefaultTableModel(attribute,0);
		epilTable = new JTable(model);
		scrollpane = new JScrollPane(epilTable);
		scrollpane.setBounds(0, 30, 705, 200);
		//디비에서 읽어서 보여주기
		result = main.readMypageInfo();
		cnt = result.length;
		for(int i=0;i<cnt;i++){
			model.addRow(result[i]);
		}
		epilTable.setModel(model);
		
		//버튼
		btnAdd = new JButton(new ImageIcon("img/mypageadd.png")); btnDel = new JButton(new ImageIcon("img/mypagedel.png")); 
		btnSave = new JButton(new ImageIcon("img/mypagesave.png"));
		btnAdd.setBounds(0,229,234,71); btnDel.setBounds(234,229,235,71); btnSave.setBounds(469,229,234,71);
		//이벤트
	      btnAdd.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("추가 선택됨");
	        	  JFrame frame = new JFrame("등록"); Dimension dim = new Dimension(300,200); frame.setLocation(1300,360); 
	        	  frame.setPreferredSize(dim); frame.setLayout(null); frame.pack();
	        	  JLabel l1 = new JLabel("Title"), l2 = new JLabel("Content"); 
	        	  l1.setBounds(55,0,50,30); l2.setBounds(175,0,50,30);
	        	  JTextArea title = new JTextArea(); JScrollPane tp = new JScrollPane(title); tp.setBounds(10,25,120,80);
	        	  JTextArea story = new JTextArea(); JScrollPane sp = new JScrollPane(story); sp.setBounds(150,25,120,80);
	        	  JButton btnOk = new JButton("확인"); btnOk.setBounds(100,110,80,40);
	        	  frame.add(l1); frame.add(l2); frame.add(tp); frame.add(sp); frame.add(btnOk);
	        	  frame.setVisible(true);
	        	  
	        	  //이벤트
	    	      btnOk.addActionListener(new ActionListener() {
	    	          public void actionPerformed(ActionEvent e) {
	    	        	  System.out.println("확인 선택됨");
	    	        	  String str1 = title.getText(); title.setText("");
	    	        	  String str2 = story.getText(); story.setText("");
	    	        	  Object data[] = {str1,str2};
	    	        	  DefaultTableModel model = (DefaultTableModel) epilTable.getModel();
	    	        	  model.addRow(data);
	    	        	  }
	    	      });
	        }
	      });
	      
	      btnDel.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("삭제 선택됨");
	        	  System.out.println((String)model.getValueAt(epilTable.getSelectedRow(),0));
	        	  boolean check = main.RmMypageInfo((String)model.getValueAt(epilTable.getSelectedRow(),0));
	        	  System.out.println("성공여부테스트 : " + check);
	        	  if(check){
	        		  JOptionPane.showMessageDialog(null, "후기가 삭제되었습니다.");
	        		  setPanelArea((JPanel)e_panel());
	        	  }else{
	        		  JOptionPane.showMessageDialog(null, "삭제 실패");
	        	  }
	        }
	      });
	      
	      btnSave.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  //사실 데이터 개수로 변동사항을 파악하니까 완벽하진 않은코드//////////////////////////////////////////////
	        	  if(cnt==model.getRowCount()){
	        		  JOptionPane.showMessageDialog(null, "변동사항이 없습니다.");
	        	  }else{////이거는 데이터가 추가되었을때
		        		  for(int i=cnt;i<model.getRowCount();i++){
		        			  String temp1= (String)model.getValueAt(i, 0);
		        			  System.out.println("바보"+temp1);
		        			  String temp2= (String)model.getValueAt(i, 1);
		        			  System.out.println(temp2);
		        	  		  boolean check = main.insertMypageInfo(temp1, temp2);
		        	  		  if(check){
		        	  			  JOptionPane.showMessageDialog(null, "데이터베이스 저장 성공");
		        	  			  setPanelArea((JPanel)e_panel());
		        	  		  }else{
		        	  			  JOptionPane.showMessageDialog(null, "데이터베이스 저장 실패");
		        	  		  }
		        	  	  }
	        	  }
	          	}
	      });
		
		//최종 추가
		panel.add(label);panel.add(scrollpane);panel.add(btnAdd);panel.add(btnDel);panel.add(btnSave);
		return panel;
	}

	public void start() {
		btnUserInformation.addActionListener(this);
		btnMyGrade.addActionListener(this);
		btnMyList.addActionListener(this);
		btnUserInformation.addMouseListener(this);
		btnMyGrade.addMouseListener(this);
		btnMyList.addMouseListener(this);
		btnHome.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnUserInformation) {
			JOptionPane.showMessageDialog(null, "유저정보 클릭.");
			setPanelArea((JPanel)u_panel());
		} else if (e.getSource() == btnMyGrade) {
			JOptionPane.showMessageDialog(null, "등급 클릭.");
			setPanelArea((JPanel)g_panel());
		} else if (e.getSource() == btnMyList) {
			JOptionPane.showMessageDialog(null, "나만의 후기 클릭.");
			setPanelArea((JPanel)e_panel());
		} else if (e.getSource() == btnHome){
			dispose();
			main.showFrameMain();
		}
	}
	
	public JComponent setPanel(JComponent panel){
		panel.setLayout(null);
		panel.setOpaque(false);
		return panel;
	}
	public void setTextfield(JTextField j,String str,int a,int b,int c,int d){
		j.setBounds(a, b, c, d);
		j.setOpaque(false);
		j.setForeground(Color.black);
		j.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		j.setText(str);
		j.setVisible(false);
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
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {
		switch(((JButton) e.getSource()).getText()) { 
		   case "1" : userInfo.setVisible(true); break; 
		   case "2" : grade.setVisible(true);; break; 
		   case "3" : epil.setVisible(true); break;
		} 
	}
	public void mouseExited(MouseEvent e)  {
		userInfo.setVisible(false);
		grade.setVisible(false);
		epil.setVisible(false);
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
//	public static void main(String [] args){
//		new frmMyPage();
//	}
}
