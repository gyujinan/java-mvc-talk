package login;

import conversation.ConversationController;
import conversation.ConversationModel;
import observer.Observer;

public class LoginController implements Observer{
	
	LoginModel model;
	LoginView view;
	String userName;
	
	public LoginController(LoginModel m){
		model=m;
		model.addObserver(this);
		view = new LoginView(this, model);
		view.createView();
	}
	
	public void requestLogin(String ip, String u) {
		userName = u;
		model.tryLogin(ip);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(model.getState()) {
			ConversationModel converModel = new ConversationModel(model.getSocket(),userName);
			new Thread(converModel).start();
			ConversationController converCon = new ConversationController(converModel);
		}
	}
}
