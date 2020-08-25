package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionUnit extends Thread implements Serializable{
	Socket client;
	ConnectionCenter model;
	byte[] buff;
	InputStream in;
	OutputStream out;
	ConnectionUnit(Socket c, ConnectionCenter m){
		client = c;
		model = m;
		buff = new byte[1024];
		createStream();
		
	}
	
	private void createStream() {
		try {
			in = client.getInputStream();
			out = client.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void sendMessage(byte[] b) {
		try {
			out.write(b);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.removeConnection(this);
		}
	}
	
	public String getIpAddress() {
		InetAddress ip = client.getLocalAddress();
		
		return new String(ip.toString()); 
	}
	
	@Override
	public void run() {
		for(;;) {
			try {
				in.read(buff);
				model.sendMessageAll(buff);
				buff = new byte[1024];
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				model.removeConnection(this);
				this.stop();
				e.printStackTrace();
			}
		}
	}
	
}