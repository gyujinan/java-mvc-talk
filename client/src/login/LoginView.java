package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import observer.Observer;



public class LoginView implements Observer{
	
	LoginController controller;
	LoginModel model;
	
    JButton btnClo;
    JButton btnLogin;
    
    JTextField ipText;
    JTextField nameText;
    
    
    JFrame loginFrame;
    JPanel loginPanel;
    
    JLabel ipLabel;
    JLabel nameLabel;
    
    public LoginView(LoginController c , LoginModel m){
    	controller =c;
    	model =m;
    	model.addObserver(this);
    }
    
    
    
    public void createView() {
    	
    	loginFrame = new JFrame();
    	loginFrame.setTitle("login");
    	loginFrame.setSize(280, 150);
    	loginFrame.setResizable(false);
    	loginFrame.setLocation(800, 450);
    	loginFrame.setDefaultCloseOperation(loginFrame.EXIT_ON_CLOSE);
    	
    	loginPanel = new JPanel();
    	loginPanel.setLayout(null);
    	
    	ipLabel = new JLabel("ip");
    	ipLabel.setBounds(10, 10, 80, 25);
    	loginPanel.add(ipLabel);
    	
    	nameLabel = new JLabel("name");
    	nameLabel.setBounds(10, 40, 80, 25);
    	loginPanel.add(nameLabel);
    	
    	
		ipText = new JTextField(20);
		ipText.setBounds(100, 10, 160, 25);
		ipText.setText("127.0.0.1");
		loginPanel.add(ipText);
		
    	nameText = new JTextField(20);
		nameText.setBounds(100, 40, 160, 25);
		loginPanel.add(nameText);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(10, 80, 100, 25);
		loginPanel.add(btnLogin);
		
		btnClo = new JButton("cancel");
		btnClo.setBounds(160, 80, 100, 25);
		loginPanel.add(btnClo);

    	loginFrame.add(loginPanel);
    	createControl();
    	loginFrame.setVisible(true);
    	
    }
    
    private void createControl() {
    	btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ip = ipText.getText();
				String name = nameText.getText();
				controller.requestLogin(ip,name);
			}
    		
    	});
    	
    	btnClo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
    		
    	});
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(model.getState()) {
			loginFrame.setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "you have failed login");

		}
	}
}
