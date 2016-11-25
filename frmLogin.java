package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;
import DB.DBManager;
import data.UserInformationData;

public class frmLogin extends JFrame implements ActionListener {

	Main main;
	BufferedImage img = null;
	JTextField loginTextField;
	JPasswordField passwordField;
	JButton btnLogin, btnId, btnPass, btnJoin;
	// �깮�꽦�옄
	public frmLogin() {
		setTitle("濡쒓렇�씤 �뀒�뒪�듃");
		setSize(1040, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// �젅�씠�븘�썐 �꽕�젙
		setLayout(null);
		
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1040, 800);
		layeredPane.setLayout(null);

		// �뙣�꼸1
		// �씠誘몄� 諛쏆븘�삤湲�  
		try {
			img = ImageIO.read(new File("img/imback.png"));
		} catch (IOException e) {
			System.out.println("�씠誘몄� 遺덈윭�삤湲� �떎�뙣");
			System.exit(0);
		}

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1040, 800);

		// 濡쒓렇�씤 �븘�뱶
		loginTextField = new JTextField(15);
		loginTextField.setBounds(430, 410, 160, 27);
		layeredPane.add(loginTextField);
		loginTextField.setOpaque(false);
		loginTextField.setForeground(Color.pink);
		//loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		// �뙣�뒪�썙�뱶
		passwordField = new JPasswordField(15);
		passwordField.setBounds(430, 460, 160, 27);
		passwordField.setOpaque(false);
		passwordField.setForeground(Color.pink);
		// passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		layeredPane.add(passwordField);

		// 濡쒓렇�씤踰꾪듉 異붽�
		btnLogin = new JButton(new ImageIcon("img/login.png"));
		btnJoin = new JButton(new ImageIcon("img/join.png"));
		btnLogin.setBounds(575, 398, 104, 48);
		btnJoin.setBounds(580, 450, 104, 48);
		btnId = new JButton();
		btnId.setBounds(346, 400, 100, 50);
		btnPass = new JButton();
		btnPass.setBounds(345, 450, 100, 50);

		// 踰꾪듉 �닾紐낆쿂由�

		btnId.setText("I  D");
		btnPass.setText("Password");
		btnBlind(btnLogin);
		btnBlind(btnId);
		btnBlind(btnPass);
		btnBlind(btnJoin);

		btnLogin.addActionListener(this);
        btnJoin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("회占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙");
            	main.showFrameSignup();
            }
        });

		layeredPane.add(btnLogin);
		layeredPane.add(btnId);
		layeredPane.add(btnPass);
		layeredPane.add(btnJoin);

		// 留덉�留� 異붽��뱾
		layeredPane.add(panel);
		add(layeredPane);
		setVisible(true);
	}
	
	public void setMain(Main main){
		this.main=main;
	}

	public void actionPerformed(ActionEvent e) {
		String id = loginTextField.getText();
		char[] pass = passwordField.getPassword();
		String password = new String(pass);

		if (id.equals("") || password.equals("")) {
			// 硫붿떆吏�瑜� �궇由곕떎. 
			JOptionPane.showMessageDialog(null, "�븘�씠�뵒 �삉�뒗 鍮꾨�踰덊샇瑜� 紐⑤몢 �엯�젰�빐二쇱꽭�슂.");
		} else {

			// 濡쒓렇�씤 李� 嫄곗쭞 �뿬遺�瑜� �뙋�떒
			UserInformationData uid = DBManager.UserIDLogin(id, password);
			if (uid!=null) {
				// 濡쒓렇�씤 �꽦怨듭씪 寃쎌슦
				JOptionPane.showMessageDialog(null, "濡쒓렇�씤 �꽦怨�!");
				///
				//if(id==admin)
				//frmAdmin
				if(id.equals("admin")){
					main.showFrameadmin(this);
				}else{
					main.showFrameMain(this,id);
				}
			} else {
				// 濡쒓렇�씤 �떎�뙣�씪 寃쎌슦
				JOptionPane.showMessageDialog(null, "�븘�씠�뵒 �삉�뒗 鍮꾨�踰덊샇媛� ���졇�뒿�땲�떎.");
			}

		}
		password = null;
	}

	public void btnBlind(JButton btn) {
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
	}

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

}