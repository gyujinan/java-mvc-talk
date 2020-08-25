package conversation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import observer.Observer;
import observer.Subject;

public class ConversationModel implements Subject, Runnable{
	InputStream in;
	OutputStream out;
	byte [] buff;
	List<String> conversation;
	List<Observer> observer;
	String userName = " [no name] ";
	

	public ConversationModel(Socket s, String u) {
		createStream(s);
		buff = new byte[1024];
		conversation = new ArrayList<String>();
		observer = new ArrayList<Observer>();
		userName = " ["+u+"] ";
		
	}
	
	public void sendMessage(String s) {
		try {
			
			String buff = userName + s;
			byte [] msg = buff.getBytes();
			out.write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getLastMsg() {
		String msg = conversation.get(conversation.size()-1);
		
		return msg;
	}
	
	private void createStream(Socket s) {
		try {
			
			in = s.getInputStream();
			out = s.getOutputStream();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(;;) {
			try {
				in.read(buff);
				conversation.add(new String(buff));
				buff = new byte[1024]; 
				notifyAllObserver();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}
