package UI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Luokka luo oman ikkunan ennen varsinaisen peli-ikkunan luomista ja kysyy käyttäjää syöttämään nimen.
 * 
 * @author mabenj
 *
 */
public class NicknameQuery {
	private String name;

	public NicknameQuery(List<Image> icons) {
		name = "";

		// frame
		JFrame f = new JFrame("Blackjack | Enter Name");
		f.setIconImages(icons);
		f.getContentPane().setBackground(Color.darkGray);
		
		// submit button
		JButton b = new JButton("Submit");
		b.setBounds(135, 100, 130, 40);
		b.setBackground(new Color(28,28,28));
		b.setForeground(Color.orange);

		// enter name label
		JLabel label = new JLabel();
		label.setText("Enter a name:");
		label.setBounds(45, 15, 130, 100);
		label.setForeground(Color.orange);

		// textfield to enter name
		JTextField textfield = new JTextField();
		textfield.setBounds(135, 50, 130, 30);

		
		f.add(textfield);
		f.add(label);
		f.add(b);
		f.setSize(350, 200);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setAlwaysOnTop(true);
		f.getRootPane().setDefaultButton(b);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textfield.getText().equals("")) {
					name = "Jari Avanto";
				} else {
					name = textfield.getText();
				}

				f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));

			}
		});
	}

	public String getName() {
		while (name.equals("")) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return name;
	}
}
