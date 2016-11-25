package main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import DB.DBManager;
import Network.Client;
import view.frmAdminMain;
import view.frmEtc;
import view.frmFollow;
import view.frmHelp;
import view.frmHelpset;
import view.frmLogin;
import view.frmMenu;
import view.frmMyPage;
import view.frmSelf;
import view.frmSignup;

public class Main {
	Socket socket;
	DBManager db = new DBManager();
	Client client;
	frmMenu fm; frmSelf fs; frmHelpset fhs;frmHelp fh; frmAdminMain fam; frmEtc fe; frmFollow fF; frmMyPage fM;
	public static void main(String[] args) {
		Main main = new Main();
		//main.connectServer("165.229.125.216", 5002);
		main.connectDB("localhost", "im", "im", "1234"); 
		frmLogin Login = new frmLogin();
		Login.setMain(main);  
	}
	
	public void connectServer(String ip,int port){
		try {
			socket = new Socket();
			System.out.println("�뀒�뒪�듃以�");
			socket.connect(new InetSocketAddress(ip, port));
			System.out.println("�뿰寃곗셿猷�!");
			client = new Client(socket);
	 		client.send();
	 		client.receive();
		} catch (IOException e) {
			System.out.println("server is closed");
		}
	}
	
	public void connectDB(String ip,String name,String id, String password){
		boolean dbconnect = db.init(ip,name,id,password);
		if(!dbconnect) System.out.println("DB �뿰寃� �떎�뙣"); else System.out.println("DB �뿰寃� �꽦怨�");
	}
	
	public String[][] readUserList(){
		return db.ReadUserList();
	}
	
	public String[] ReadUserInfo(String str){
		return db.ReadUserInfo(str);
	}
	
	public String[][] readSelfInfo(String str){
		return db.ReadSelfInfo(str);
	}
	
	public String[][] readSelfInfo(){
		return db.ReadSelfInfo();
	}
	
	public String[][] readMypageInfo(){
		return db.ReadMypageInfo();
	}
	
	public boolean RmMember(String id){
		return db.RmMember(id);
	}
	
	public boolean RmSelfInfo(String name){
		return db.RmSelfInfo(name);
	}
	
	public boolean RmMypageInfo(String title){
		return db.RmMypageInfo(title);
	}
	
	public boolean insertSelfInfo(String area,String position, String name, String content){
		return db.InsertSelfInfo(area, position, name, content);
	}
	
	public boolean insertMypageInfo(String title,String content){
		return db.InsertMypageInfo(title, content);
	}
	
	public boolean UpdateUserRank(String id,String rank){
		return db.UpdateUserGrade(id, rank);
	}
	
	public void showFrameadmin(frmLogin Login){
		Login.dispose();
		fam = new frmAdminMain(this);
	}
	
	public void showFrameMain(frmLogin Login,String id){
		Login.dispose(); //�쟾李� �븞蹂댁씠寃뚰븯怨�  �깉李쎈쓣�슦湲�
		fm = new frmMenu(id);
		fm.setMain(this);
	}
	
	public void showFrameSignup(){
		//�쉶�썝媛��엯李쎌� �씠�쟾李� �븞蹂댁씠寃� �븷 �븘�슂媛� �뾾�쓬.
		frmSignup signup = new frmSignup();
	}
	
	public void showFollowFrame(frmMenu fm){
		fm.dispose();
		fF = new frmFollow(this);
	}
	
	public void showFrameMypage(frmMenu fm,String id){
		fm.dispose();
		fM = new frmMyPage(this,id);
	}

	public void showSelfFrame(frmMenu fm){
		fm.dispose();
		fs = new frmSelf(this);
	}
	public void showHelpsetFrame(frmMenu fm){
		fm.dispose();
		fhs = new frmHelpset(this);
	}
	public void showHelpFrame(frmHelpset fhs,String day,String begin,String finish){
		fhs.dispose();
		fh = new frmHelp(this,day,begin,finish);
	}
	
	public void showFrameEtc(frmMenu fm){
		fm.dispose();
		fe = new frmEtc(this);
	}
}
