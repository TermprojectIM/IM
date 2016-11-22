package Network;


import java.net.*;
import java.util.List;
import java.util.Vector;

public class Server {
	ServerSocket serversocket = null;
	Socket socket = null;
	List<ServerOption> clientlist = new Vector<ServerOption>();
	
	
	
	public static void main(String args[]) {
		Server server = new Server();
		server.Accept();
	}

	public Server() { 
		try {
			serversocket = new ServerSocket();
			serversocket.bind(new InetSocketAddress("165.229.125.216", 1234));
		} catch(Exception e) {
			stopServer();
		}
	}
	public Server(String ip,int port){
		try{
			serversocket = new ServerSocket();
			serversocket.bind(new InetSocketAddress(ip,port));
		}catch(Exception e){
			stopServer();
		}
	}
	
	public void Accept() {
		Thread thread = new Thread() {
			public void run() {
				while(true) {
					try {
						System.out.println("[Waiting...]");
						socket = serversocket.accept();
						System.out.println("[Connecting...]");
						ServerOption client = new ServerOption(socket);
						clientlist.add(client);
						client.Synchronization_list(clientlist);
						System.out.println("[" + (clientlist.indexOf(client)+1) + "번 클라이언트가 접속하였습니다.]");
						System.out.println();
					} catch(Exception e) {
						stopServer();
					}
				}
			}
		};
		thread.start();
	}
	
	public void stopServer() {
		System.out.println("Error!");
		try {
			for(int i = 0 ; i < clientlist.size() ; i++) {
				clientlist.get(i).socket.close();
				clientlist.remove(i);
			}
			if(serversocket != null && !serversocket.isClosed()) { 
				serversocket.close();
			}
		} catch(Exception e) {
			e.getMessage();
		}
	}
}
