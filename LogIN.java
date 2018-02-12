package Tema;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class LogIN  {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwords;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIN window = new LogIN();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogIN() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Login = new JLabel("Login System");
		Login.setBounds(152, 41, 161, 15);
		frame.getContentPane().add(Login);
		
		JLabel user = new JLabel("USERNAME");
		user.setBounds(51, 106, 100, 15);
		frame.getContentPane().add(user);
		
		JLabel pass = new JLabel("PASSWORD");
		pass.setBounds(51, 164, 100, 15);
		frame.getContentPane().add(pass);
	
		textField = new JTextField();
		textField.setBounds(198, 104, 150, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwords = new JPasswordField();
		passwords.setBounds(198, 162, 150, 19);
		frame.getContentPane().add(passwords);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				
				String password = passwords.getText();
				String username = textField.getText();
				
				if(password.equals("andreea")&& username.equals("admin")) {

					
					MenuFrame menu = new MenuFrame();
					menu.setVisible(true);
					passwords.setText(null);
					textField.setText(null);
					frame.setVisible(false);
					
				}else {
					JOptionPane.showMessageDialog(null, "Invalid login", "Login error", JOptionPane.ERROR_MESSAGE);
					passwords.setText(null);
					textField.setText(null);
				}
			}
		});
		
		LoginButton.setBounds(146, 215, 127, 25);
		frame.getContentPane().add(LoginButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(39, 68, 383, 2);
		frame.getContentPane().add(separator);
		
	}
}
