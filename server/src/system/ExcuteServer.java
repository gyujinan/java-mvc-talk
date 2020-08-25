package system;

import connection.ConnectionCenter;

public class ExcuteServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("started server");
		try {
			ConnectionCenter center = new ConnectionCenter();
			new Thread(center).start();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Server exception: " + e.toString());

			e.printStackTrace();
		}
		
		
		System.out.println("end server");
		
	}

}
