package Network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class ServerOption {
		Socket socket = null;
		List<ServerOption> clientlist = new Vector<ServerOption>();
		
		public ServerOption(Socket socket) {
			this.socket = socket;
			receive();
		}
		
		public void receive() {
			Thread thread = new Thread() {
				public void run() {
					try {
						while(true) {
							byte[] bytearr = new byte[100];
							InputStream inputStream = socket.getInputStream();
							int bytecount = inputStream.read(bytearr);
							String message = new String(bytearr, 0, bytecount, "UTF-8");
							send(message);
						}
					} catch(Exception e) {
						e.getMessage();
					}
				}
			};
			thread.start();
		}
		
		 // 데이터를 받으면 bytearr에 저장 후 bytecount에 바이트의 개수 저장
		
		public void send(String message) {
			try {
				String first = message.split("#")[0];
				int i = Integer.parseInt(first);
				byte[] bytearr = message.getBytes("UTF-8");
				OutputStream outputstream = clientlist.get(i-1).socket.getOutputStream();
				outputstream.write(bytearr);
				outputstream.flush();
			} catch(Exception e) {
				e.getMessage();
			}
		}
		
		public void Synchronization_list(List<ServerOption> clientlist) {
			this.clientlist = clientlist;
		}
	}