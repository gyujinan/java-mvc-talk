package conversation;

import observer.Observer;

public class ConversationController implements Observer{
	ConversationModel model;
	ConversationView view;
	
	public ConversationController(ConversationModel m) {
		model = m;
		model.addObserver(this);
		view = new ConversationView(model, this);
		view.createView();
	}
	
	public void sendMessage(String s) {
		model.sendMessage(s);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
