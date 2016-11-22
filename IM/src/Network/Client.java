package Network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Client {
	Socket socket;
	public Client(Socket socket) {
		this.socket = socket;
	}
	
	public void receive() {
		Thread thread = new Thread() {
			public void run() {
				while(true) {
					try {
						byte[] bytearr = new byte[100];
						InputStream inputStream = socket.getInputStream();
						int bytecount = inputStream.read(bytearr);
						String message = new String(bytearr, 0, bytecount, "UTF-8");
						String first = message.split("#")[0];
						String second = message.split("#")[1];
						JOptionPane.showMessageDialog(null, second, first +"번 클라이언트에게 받은 메세지", JOptionPane.PLAIN_MESSAGE);
					} catch(Exception e) {
						stopClient();
					}
				}
			}
		};
		thread.start();
	}
	
	public void send() {
		Thread thread = new Thread() {
			public void run() {
				Scanner scanner = new Scanner(System.in);
				try {
					while(true) {
						System.out.print("보낼 메세지 : ");
						scanner = new Scanner(System.in);
						String message = scanner.nextLine();
						byte[] bytearr = message.getBytes("UTF-8");
						OutputStream outputstream = socket.getOutputStream();
						outputstream.write(bytearr);
						outputstream.flush();
					}
				} catch(Exception e) {
					stopClient();
				}
				scanner.close();
			}
		};
		thread.start();
	}
	
	public void stopClient() {
		try {
			if(socket != null && !socket.isClosed())
				socket.close();
			
		} catch(Exception e) {
			e.getMessage();
		}
	}
}
