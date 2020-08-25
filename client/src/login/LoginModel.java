package login;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import observer.Observer;
import observer.Subject;

public class LoginModel implements Subject{
	boolean state;
	Socket socket;
	List<Observer> observer;
	
	public LoginModel(){
		state=false;
		observer = new ArrayList<Observer>();
	}
	public void tryLogin(String ip) {
		try {
			socket= new Socket(ip, 0413);
			state=true;
			notifyAllObserver();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Socket getSocket() {
		return socket;
		
	}
	public boolean getState() {
		return state;
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
