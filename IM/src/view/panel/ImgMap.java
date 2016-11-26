package view.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ImgMap extends JPanel implements Runnable {
	String str="뻑큐";
	Image img[] = new Image[4];
	int i = 2;

	public ImgMap() {
		img[1] = Toolkit.getDefaultToolkit().createImage("img/join.png");
		img[2] = Toolkit.getDefaultToolkit().createImage("img/login.png");
		img[3] = Toolkit.getDefaultToolkit().createImage("img/imback.png");
		img[0] = img[1]; // 이미지 0번은 초기이미지로 1번에다가 주소값을 넣어준다?
	}

	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(img[0], 0, 0, this);
	}
	public void setImg(String str) {
		this.str = str;
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(3000); //3초 마다
				i = (i==3) ? 1 : ++i;
				img[0] = img[i];
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
