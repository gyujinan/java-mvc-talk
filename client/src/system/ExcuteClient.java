package system;

import login.LoginController;
import login.LoginModel;

public class ExcuteClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginModel model = new LoginModel();
		LoginController controller = new LoginController(model);
	}

}
