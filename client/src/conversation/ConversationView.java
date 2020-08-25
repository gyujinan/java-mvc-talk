package conversation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import observer.Observer;



public class ConversationView implements Observer{
	ConversationModel model;
	ConversationController controller;
	

	JFrame frame;

	JPanel displayPanel;
	JPanel inputPanel;

	JTextArea display;
	JScrollPane scroll;
	JTextField input;

	public ConversationView(ConversationModel m, ConversationController c) {
		model = m;
		model.addObserver(this);
		controller = c;
	}

	public void createView() {
		frame = new JFrame("Conversation");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		

		displayPanel = new JPanel();
		displayPanel.setLayout(new FlowLayout());
		display = new JTextArea(16, 27);
		Font displayFont = new Font("Serif", Font.BOLD, 20);
		display.setFont(displayFont);
		display.setEditable(false);

		scroll = new JScrollPane(display);
		displayPanel.add(scroll);

		inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		input = new JTextField(27);
		Font inputFont = new Font("Serif", Font.BOLD, 20);
		input.setFont(inputFont);
		inputPanel.add(input);
		
		frame.setLayout(new BorderLayout());
		frame.add(displayPanel, BorderLayout.CENTER);
		frame.add(inputPanel, BorderLayout.SOUTH);

		createControl();
		frame.setVisible(true);

	}

	private void createControl() {
		input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == input)

				{
					controller.sendMessage(input.getText());
					input.setText("");
				}
			}
		});
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		String msg = model.getLastMsg();
		display.append(msg+"\n");

	}

}
