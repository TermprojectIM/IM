package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DB.DBManager;
import main.Main;


public class frmMap extends JPanel{
	Image img[] = new Image[30];
	JButton btnSearch;
	JTextField text;
	Main main;
	frmInfo info;
	int i = 2;
	String str;
	String attribute[] = {"Area", "Position","name","content"};
	String result[][] = new String[100][4];
	public frmMap(JTextField text,frmInfo info) {
		this.info=info;
		this.text = text;
		btnSearch = new JButton();
		btnSearch.setBounds(913,124,60,50);
		btnSearch.setVisible(true);
		btnBlind(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("검색 눌림");
            	str = text.getText();
    			if(str.equals("시작")){
    				img[0] = img[1];
    			} else if (str.equals("서울")) {
    				img[0] = img[2];
    			} else if (str.equals("대구")) {
    				img[0] = img[3];
    			} else if(str.equals("부산")){
    				img[0] = img[4];
    			} else if (str.equals("여수")) {
    				img[0] = img[5];
    			} else if(str.equals("강릉")){
    				img[0] = img[6];
    			} else if (str.equals("경주")) {
    				img[0] = img[7];
    			}  
    			else{
    				str="오류";
    			}
    			repaint();
   				if(!str.equals("오류")&&!str.equals("시작")){
    	    		result = main.readSelfInfo(str);
    	    		DefaultTableModel  model = new DefaultTableModel(attribute,0);
    				int cnt = result.length;//100개이상 데이터 없다고 가정하고, 출력 틀 계속 맞춰주기 위해서 임의로 설정
    				System.out.println("테스트 : "+cnt);
    				for(int i=0;i<cnt;i++){
    					model.addRow(result[i]); //모델위에 데이터있는것만큼 다 올리고 나머진 null로 add!
    				}  
    				System.out.println("테스트"+model.getRowCount());
    				info.table.setModel(model);
    			}
            }
        });
		img[1] = Toolkit.getDefaultToolkit().createImage("img/all10,6.5.png");
		img[2] = Toolkit.getDefaultToolkit().createImage("img/서울경기 - 서울(최).png");
		img[3] = Toolkit.getDefaultToolkit().createImage("img/경상도 - 대구(최).png");
		img[4] = Toolkit.getDefaultToolkit().createImage("img/경상도 - 부산(최).png");
		img[5] = Toolkit.getDefaultToolkit().createImage("img/전라도 - 여수(최).png");
		img[6] = Toolkit.getDefaultToolkit().createImage("img/강원도 - 강릉(최).png");
		img[7] = Toolkit.getDefaultToolkit().createImage("img/경상도 - 경주(최).png");
		img[0] = img[1]; 
	}
	
	public String getText(){
		return str;
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
	public void btnBlind(JButton btn) {
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
	}

	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(img[0], 0, 0, this);
	}
	
	public void setImg(String str) {
		this.str = str;
	}
}