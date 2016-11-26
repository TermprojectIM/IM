package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
//import java.awt.List;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.Main;

public class frmEtc extends Frame implements ActionListener, WindowListener, ListSelectionListener {
   private JLabel list_lb = new JLabel("글목록");
   private JLabel read_lb = new JLabel("글보기");
   private JList list_li = new JList(new DefaultListModel());
   //private JList list_li = new JList(10, false);
   private JTextArea read_ta = new JTextArea();
   private JButton write_bt = new JButton("글쓰기");
   private JButton refresh_bt = new JButton("새로고침");
   private JButton edit_bt = new JButton("수정");
   private JButton delete_bt = new JButton("삭제");
   private JButton end_bt = new JButton("종료");

   private JDialog write_dlg = new JDialog(this, "글쓰기", true);
   private JLabel dlg_toptitle_lb = new JLabel("글쓰기", JLabel.CENTER);
   private JLabel dlg_title_lb = new JLabel("글제목 : ", JLabel.RIGHT);
   private JLabel dlg_author_lb = new JLabel("작성일 : ", JLabel.RIGHT);
   private JLabel dlg_attach_lb = new JLabel("첨부파일 : ", JLabel.RIGHT);
   private JTextField dlg_title_tf = new JTextField();
   private JTextField dlg_author_tf = new JTextField();
   private JTextField dlg_attach_tf = new JTextField();
   private JTextField dlg_write_ta = new JTextField();
   private JButton dlg_attach_bt = new JButton("찾아보기");
   private JButton dlg_register_bt = new JButton("등록");
   private JButton dlg_cancel_bt = new JButton("취소");
   Main main;
   DefaultListModel listmodel;
   
   public frmEtc(Main main) {
      super("게시판");
      this.main =main;
      this.init();
      this.start();
      this.setSize(500, 500);
      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frm = this.getSize();
      int xpos = (int) (screen.getWidth() / 2 - frm.getWidth() / 2);
      int ypos = (int) (screen.getHeight() / 2 - frm.getHeight() / 2);
      this.setLocation(xpos, ypos);
      this.setVisible(true);
   }

   public void init_dlg() {
      write_dlg.setLayout(new BorderLayout(10, 10));
      write_dlg.add("North", dlg_toptitle_lb);
      Panel p = new Panel(new BorderLayout(5, 5));
      Panel p2 = new Panel(new BorderLayout(3, 3));
      Panel p3 = new Panel(new GridLayout(3, 1));
      p3.add(dlg_title_lb);
      p3.add(dlg_author_lb);
      p3.add(dlg_attach_lb);
      p2.add("West", p3);
      Panel p4 = new Panel(new GridLayout(3, 1));
      p4.add(dlg_title_tf);
      p4.add(dlg_author_tf);
      Panel p5 = new Panel(new BorderLayout());
      dlg_attach_tf.setEnabled(false);
      p5.add("Center", dlg_attach_tf);
      p5.add("East", dlg_attach_bt);
      p4.add(p5);
      p2.add("Center", p4);
      p.add("North", p2);
      p.add("Center", dlg_write_ta);
      write_dlg.add("Center", p);
      Panel p1 = new Panel(new FlowLayout(FlowLayout.RIGHT));
      p1.add(dlg_register_bt);
      p1.add(dlg_cancel_bt);
      write_dlg.add("South", p1);
   }

   public void init() {
      this.init_dlg();
      this.setLayout(new GridLayout(1, 2, 10, 10));
      Panel p = new Panel(new BorderLayout(5, 5));
      p.add("North", list_lb);
      p.add("Center", list_li);
      Panel p2 = new Panel(new GridLayout(1, 2, 5, 5));
      p2.add(write_bt);
      p2.add(refresh_bt);
      p.add("South", p2);
      this.add(p);
      Panel p1 = new Panel(new BorderLayout(5, 5));
      p1.add("North", read_lb);
      read_ta.setEditable(false);
      p1.add("Center", read_ta);
      Panel p3 = new Panel(new GridLayout(1, 3, 5, 5));
      p3.add(edit_bt);
      p3.add(delete_bt);
      p3.add(end_bt);
      p1.add("South", p3);
      this.add(p1);
   }

   public void start() {
      write_bt.addActionListener(this);
      dlg_attach_bt.addActionListener(this);
      dlg_cancel_bt.addActionListener(this);
      dlg_register_bt.addActionListener(this);
      this.addWindowListener(this);
      refresh_bt.addActionListener(this);
      end_bt.addActionListener(this);
      list_li.addListSelectionListener(this); // 글읽기
      //list_li.addActionListener(this);
      edit_bt.addActionListener(this);
      delete_bt.addActionListener(this);
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == write_bt) {
         write_dlg.setSize(300, 400);
         Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
         Dimension dlg = write_dlg.getSize();
         int xpos = (int) (screen.getWidth() / 2 - dlg.getWidth() / 2);
         int ypos = (int) (screen.getHeight() / 2 - dlg.getHeight() / 2);
         write_dlg.setLocation(xpos, ypos);
         write_dlg.setVisible(true);
      } else if (e.getSource() == dlg_attach_bt) {
         FileDialog fdlg = new FileDialog(this, "찾아보기", FileDialog.LOAD);
         fdlg.setVisible(true);
         String dir = fdlg.getDirectory();
         String file = fdlg.getFile();
         if (dir == null || file == null)
            return;
         dlg_attach_tf.setText(dir.trim() + file.trim());
      } else if (e.getSource() == dlg_cancel_bt) {
         dlg_title_tf.setText("");
         dlg_author_tf.setText("");
         dlg_attach_tf.setText("");
         dlg_write_ta.setText("");
         write_dlg.setVisible(false);
      } else if (e.getSource() == dlg_register_bt) {
         String title = dlg_title_tf.getText();
         String author = dlg_author_tf.getText();
         if (title == null || author == null || title.trim().length() == 0 || author.trim().length() == 0) {
            return;
         }
         title = title.trim();
         author = author.trim();
         File dir = new File("boardData");
         if (!dir.exists()) {
            dir.mkdir();
         }
         File file = new File(dir, title + " (" + author + ")");
         try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            String attach = dlg_attach_tf.getText();
            if (attach == null || attach.trim().length() == 0) {
               out.println("null");
            } else {
               out.println(attach);
            }
            out.println(dlg_write_ta.getText().trim());
            out.close();
         } catch (IOException ee) {
         }
         dlg_title_tf.setText("");
         dlg_author_tf.setText("");
         dlg_attach_tf.setText("");
         dlg_write_ta.setText("");
         write_dlg.setVisible(false);
      } else if (e.getSource() == refresh_bt) {
         File dir = new File("boardData");
         String[] JList = dir.list();
         listmodel= (DefaultListModel)list_li.getModel();
         listmodel.removeAllElements();
         //list_li.clear();
         if (JList != null) {
            for (int i = 0; i < JList.length; i++) {
               //list_li.add(i + 1 + " : " + JList[i]);
               listmodel.addElement(i + 1 + " : " + JList[i]);
            }
            list_li.setModel(listmodel);
         }
      } else if (e.getSource() == end_bt) {
         System.exit(0);
      } else if (e.getSource() == edit_bt) {
         if (edit_bt.getLabel().equals("수정")) {

            edit_bt.setLabel("수정중");
            read_ta.setEditable(true);
         } else {
            String data = read_ta.getText().trim();
            String title = data.substring(data.indexOf("글제목 : ") + 5, data.indexOf("작성일 : "));
            String author = data.substring(data.indexOf("작성일 : ") + 5, data.indexOf("첨부파일 : "));
            String attach = data.substring(data.indexOf("첨부파일 : ") + 6, data.indexOf("내용 : "));
            String contents = data.substring(data.indexOf("내용 : ") + 4);
            File dir = new File("boardData");
            File file = new File(dir, title.trim() + " (" + author.trim() + ")");
            try {
               PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
               out.println(attach);
               out.println(contents);
               out.close();
            } catch (IOException ee) {
            }
            edit_bt.setLabel("수정");
            read_ta.setEditable(false);
         }
      } else if (e.getSource() == delete_bt) {
         String data = read_ta.getText().trim();
         String title = data.substring(data.indexOf("글제목 : ") + 5, data.indexOf("작성일 : "));
         String author = data.substring(data.indexOf("작성일 : ") + 5, data.indexOf("첨부파일 : "));
         File dir = new File("boardData");
         File file = new File(dir, title.trim() + " (" + author.trim() + ")");
         file.delete();
         read_ta.setText("");
         String[] JList = dir.list();
         listmodel= (DefaultListModel)list_li.getModel();
         listmodel.removeAllElements();
         //list_li.clear();
         if (JList != null) {
            for (int i = 0; i < JList.length; i++) {
               //list_li.add(i + 1 + " : " + JList[i]);
               listmodel.addElement(i + 1 + " : " + JList[i]);
            }
            list_li.setModel(listmodel);
         }
      }
   }

   public void windowClosing(WindowEvent e) {
      if (e.getSource() == this) {
         System.exit(0);
      }
   }

   public void windowClosed(WindowEvent e) {
   }

   public void windowOpened(WindowEvent e) {
   }

   @SuppressWarnings("deprecation")
   public void windowActivated(WindowEvent e) {
      if (e.getSource() == this) {
         File dir = new File("boardData");
         String[] JList = dir.list();
//         list_li.clear();
         listmodel= (DefaultListModel)list_li.getModel();
         listmodel.removeAllElements();
         if (JList != null) {
            for (int i = 0; i < JList.length; i++) {
               //list_li.add(i + 1 + " : " + JList[i]);
               listmodel.addElement(i + 1 + " : " + JList[i]);
            }
            list_li.setModel(listmodel);
         }
      }
   }

   public void windowDeactivated(WindowEvent e) {
   }

   public void windowIconified(WindowEvent e) {
   }

   public void windowDeiconified(WindowEvent e) {
   }

   @Override
   public void valueChanged(ListSelectionEvent e) {
      // TODO Auto-generated method stub
      if (e.getValueIsAdjusting()) {//This line prevents double events
         String str = (String) list_li.getSelectedValue();
         //String data = (String) list_li.getSelectedItem();
         str = str.substring(str.indexOf(":") + 1).trim();
         File d = new File("boardData");
         File f = new File(d, str);
         String a = "";
         String c = "";
         try {
            BufferedReader in = new BufferedReader(new FileReader(f));
            a = in.readLine();
            while (true) {
               String s = in.readLine();
               if (s == null)
                  break;
               c += "\n" + s;
            }
            in.close();
         } catch (IOException ee) {
         }
         read_ta.setText("");
         read_ta.append("글제목 : " + str.substring(0, str.indexOf("(")) + "\n\n");
         read_ta.append("작성일 : " + str.substring(str.indexOf("(") + 1, str.lastIndexOf(")")) + "\n\n");
         read_ta.append("첨부파일 : " + a + "\n\n");
         read_ta.append("내용 : " + c);
         ///////////////////////////////////////////////////////////////////
      }      
   }
   
   // public static void main(String args[]) {
   // 
   // new frmEtc();
   //}
   
}