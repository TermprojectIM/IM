package view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

//시계글씨
@SuppressWarnings("serial")
public class ClockMessage extends JPanel implements Runnable{
	int i = Calendar.getInstance().get(Calendar.AM_PM);
	String[] ampm= {"AM","PM"};
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	String time = sdf.format(new Date());
	JLabel timeLabel, ampmLabel;
	
	public ClockMessage(){
		this.setLayout(null);
		
		timeLabel = new JLabel(time);
		timeLabel.setBounds(0,0,100,20);
		timeLabel.setForeground(new Color(36, 205, 198));
		timeLabel.setFont(new Font("배달의민족 한나", Font.BOLD, 12));
		
        ampmLabel = new JLabel(ampm[i]);
        ampmLabel.setBounds(15, 20, 100, 30);
        ampmLabel.setForeground(new Color(36, 205, 198));
        ampmLabel.setFont(new Font("배달의민족 한나", Font.BOLD, 12));

        add(timeLabel, BorderLayout.NORTH);
        add(ampmLabel, BorderLayout.CENTER);
	}
	public void run() {
        do {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeLabel.setText(sdf.format(new Date()));
        } while (true);
	}
}