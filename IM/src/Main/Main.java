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
	Client client; String id;
	frmMenu fm; frmSelf fs; frmHelpset fhs;frmHelp fh; frmAdminMain fam; frmEtc fe; frmFollow fF; frmMyPage fM;
	public static void main(String[] args) {
		Main main = new Main();
		main.connectDB("165.229.125.45", "im", "im", "1234");
		frmLogin Login = new frmLogin();
		Login.setMain(main);
	}
	
	public void connectServer(String ip,int port){
		try {
			socket = new Socket();
			System.out.println("테스트중");
			socket.connect(new InetSocketAddress(ip, port));
			System.out.println("연결완료!");
			client = new Client(socket);
	 		client.send();
	 		client.receive();
		} catch (IOException e) {
			System.out.println("server is closed");
		}
	}
	
	public void connectDB(String ip,String name,String id, String password){
		boolean dbconnect = db.init(ip,name,id,password);
		if(!dbconnect) System.out.println("DB 연결 실패"); else System.out.println("DB 연결 성공");
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
	
	public String readComment(String str){
		return db.ReadComment(str);
	}
	
	public String[][] readSelfInfo(){
		return db.ReadSelfInfo();
	}
	
	public String[][] readHelpInfo(String str){
		return db.ReadHelpInfo(str);
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
	
	public boolean RmFollowInfo(String pname){
		return db.RmFollowInfo(pname);
	}
	
	public boolean insertSelfInfo(String area,String position, String name, String content){
		return db.InsertSelfInfo(area, position, name, content);
	}
	
	public boolean insertMypageInfo(String title,String content){
		return db.InsertMypageInfo(title, content);
	}
	
	public boolean insertFollowInfo(String pname,String content, String cost, String date){
		return db.InsertFollow(pname, content, cost, date);
	}
	
	public boolean UpdateUserRank(String id,String rank){
		return db.UpdateUserGrade(id, rank);
	}
	
	public void showFrameadmin(frmLogin Login){
		Login.dispose();
		fam = new frmAdminMain(this);
	}
	
	public void showFrameadmin(){//홈버튼을 위한
		fam = new frmAdminMain(this);
	}
	
	public void showFrameMain(frmLogin Login,String id){
		Login.dispose(); //전창 안보이게하고  새창띄우기
		this.id=id;
		fm = new frmMenu(id);
		fm.setMain(this);
	}
	public void showFrameMain(){//홈버튼을 위한
		//전창 안보이게하고  새창띄우기
		fm = new frmMenu(id);
		fm.setMain(this);
	}
	
	public String[][] ReadFollowInfo(){
		return db.ReadFollowInfo();
	}
	
	public void showFrameSignup(){
		//회원가입창은 이전창 안보이게 할 필요가 없음.
		frmSignup signup = new frmSignup();
	}
	
	public void showFollowFrame(frmMenu fm){
		fm.dispose();
		fF = new frmFollow(this);
	}
	
	public void showFrameMypage(frmMenu fm,String id){
		fm.dispose();
		fM = new frmMyPage(this,this.id);
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
