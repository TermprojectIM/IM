package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import data.HelpInfo;
import main.Main;
import view.panel.PanImgload;

public class frmHelp extends JFrame {
	Main main;
	private JLayeredPane layeredPane = new JLayeredPane(); // 셀로판지 식;
	private JButton btnHome = new JButton(new ImageIcon("img/home.png"));
	JTextField search;
	JButton btnPut,btnDay,btnMake,btnDel;
	JComboBox <String>box;
	JTable table;
	DefaultTableModel model;
	JScrollPane scrollpane;
	String result[][];
	public frmHelp(Main main,String day,String begin,String finish){
		frmHelp fh= this;
		this.main=main;
		setTitle("IM - 맞춤형 패키지여행");
		//원래는 1040이였음
		setSize(1500,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
		
		setPanel(layeredPane).setBounds(0,0,1500,800);
		
		//���
		JPanel backGround = new PanImgload("img/op2.png");
		setPanel(backGround).setBounds(0, 0, 1500, 800);
		search = new JTextField(25);
		setPanel(search).setBounds(538, 121, 205, 50);
		search.setFont(new Font("배달의민족 한나", Font.BOLD, 20));
		search.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		search.setForeground(Color.darkGray);

		String[] items = {"당일 여행", "1박 2일", "2박 3일", "3박 4일", "4박 5일", "5박 6일", "장기 여행"};
		box = new JComboBox<String>(items);
		box.setSelectedIndex(0);
		box.setBounds(530, 190, 450,50);
		box.setVisible(true);
		
		frmInfo info = new frmInfo();
		frmMap imgM = new frmMap(search,info);
		imgM.btnSearch.setBounds(750,118,52,52);
		imgM.setBounds(50,140,380,591);
		imgM.setMain(this.main);
		info.scrollpane.setBounds(530,250,450,488);
		//우선순위를 증가하면서 즉 뒤에있는것의 우선순위가 높게 설정
		
		box.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	String str = (String)box.getSelectedItem();
            	System.out.println(str + "선택 되었습니다.");
            }
        });
		
		//테이블 추가 헬프패키지 목록들을 담기위해서 ㅋㅋ
		String attribute[] = {"날짜","지역"};
		model = new DefaultTableModel(attribute,0);
		table = new JTable(model);
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(1025, 0, 460, 700);
		
		
		
		
		btnPut = new JButton();
		btnPut.setBounds(830, 120, 140, 50);
		btnMake = new JButton(new ImageIcon("img/btnMake.png")); btnDel = new JButton(new ImageIcon("img/btnDel.png"));
		btnMake.setBounds(1024,700,230,65); btnDel.setBounds(1255,700,230,65); btnHome.setBounds(960,12,45,45);
		btnBlind(btnMake); btnBlind(btnDel); btnBlind(btnPut); btnBlind(btnHome);
		
		//홈
		btnHome.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  dispose();
	        	  main.showFrameMain();
	          }
	      });
		
		
		//담기 이벤트
		btnPut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String str = imgM.getText();
            	Object data[]={"",str};
	        	DefaultTableModel model = (DefaultTableModel) table.getModel();
	        	model.addRow(data);
            	System.out.println(str+" 담기 눌림");
            }
        });
		//항목 삭제 이벤트
		btnDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
	        	DefaultTableModel model = (DefaultTableModel) table.getModel();
	        	model.removeRow(table.getSelectedRow());
            	System.out.println("삭제 눌림");
            }
        });
		//차트 만들기 이벤트
		btnMake.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("만들기 눌림");
				int cnt = table.getRowCount();
				result = new String[cnt][2];
				for (int i = 0; i < cnt; i++) {
					for (int j = 0; j < 2; j++)
						result[i][j] = (String) table.getValueAt(i, j);
				}

				// 출력테스트
				System.out.println("테이블에 " + table.getRowCount() + "개의 레코다가 존재합니다.");
				System.out.println("그 레코드의 값은 : ");
				for (int i = 0; i < cnt; i++) {
					System.out.println("날짜 : " + result[i][0] + " 지역 : " + result[i][1]);
				}
				// 이제 이 받은 것 넘겨주기만 하면 됨

				HelpInfo[] hi = new HelpInfo[cnt];

				for (int i = 0; i < cnt; i++) {
					String temp[][] = main.readHelpInfo(result[i][1]);
					hi[i] = new HelpInfo(result[i][0], result[i][1], Integer.parseInt(temp[0][0]), temp[0][1], temp[0][2], temp[0][3], temp[0][4]);
				}
				frmMakeChartHelp fmch = new frmMakeChartHelp(hi, day, begin, finish);
            }
        });
		
		
		add(setJLayered(backGround,imgM,imgM.btnSearch,search,box,btnPut,info.scrollpane,scrollpane,btnMake,btnDel,btnHome));
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
//		new frmHelp();
//	}

}
