package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import observer.Observer;
import observer.Subject;

public class ConnectionCenter implements Subject, Runnable{
	List<ConnectionUnit> con;
	List<Observer> observer;
	ServerSocket server;
	
	public ConnectionCenter(){
		con = new LinkedList<ConnectionUnit>();
		observer = new ArrayList<Observer>();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("server is started");
		try {
			server = new ServerSocket(0413);
			for(;;) {
				Socket client = server.accept();
				ConnectionUnit c = new ConnectionUnit(client, this);
				con.add(c);
				c.start();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("server is ended");
		
	}

	public void sendMessageAll(byte[] buff) {
		Iterator<ConnectionUnit> iter = con.iterator();
		while(iter.hasNext()){
			ConnectionUnit c = iter.next();
			c.sendMessage(buff);
		}
		
	}
	
	public void removeConnection(ConnectionUnit c){
		con.remove(c);
	}
	
	public List<ConnectionUnit> getConnections(){
		return con;
	}
	
	@Override
	public void addObserver(Observer o) {
		// TODO Auto-generated method stub
		observer.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		observer.remove(o);
	}

	@Override
	public void notifyAllObserver() {
		// TODO Auto-generated method stub
		
		Iterator<Observer> iter = observer.iterator();
		while(iter.hasNext()) {
			Observer o = iter.next();
			o.update();
		}
		
	}



}
