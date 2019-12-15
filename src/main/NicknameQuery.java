package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NicknameQuery {
	private String nickname;

	public NicknameQuery(JLabel nameTxt) {
		nickname = "";
		
		JFrame f = new JFrame("Blackjack | Enter Name");
		
		// submit button
		JButton b = new JButton("Submit");
		b.setBounds(135, 100, 130, 40);
		
		
		// enter name label
		JLabel label = new JLabel();
		label.setText("Enter a nickname :");
		label.setBounds(10, 10, 130, 100);
		
		// empty label which will show event after button clicked
		JLabel label1 = new JLabel();
		label1.setBounds(10, 110, 200, 100);
		
		// textfield to enter name
		JTextField textfield = new JTextField();
		textfield.setBounds(135, 50, 130, 30);
		
		// add to frame
		f.add(label1);
		f.add(textfield);
		f.add(label);
		f.add(b);
		f.setSize(350, 200);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.getRootPane().setDefaultButton(b);
		
		
		// action listener
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(textfield.getText().equals("")) {
					nameTxt.setText("Jari Jarruraita's");
					nickname = "Jari Jarruraita";
				}else {
					nameTxt.setText(textfield.getText() + "'s");					
					nickname = textfield.getText();
				}
				label1.setText("Name has been submitted.");
				
				f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
				
			}
		});
	}
	
	public String getName() {
		return nickname;
	}
}
