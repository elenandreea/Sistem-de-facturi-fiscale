package Tema;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuFrame extends JFrame {

	CardLayout cl = new CardLayout();
	private JPanel contentPane = new JPanel();
	MainMenu MainPanel = new MainMenu(cl,contentPane);
	Fisiere FisierePanel = new Fisiere(cl,contentPane);
	
	/**
	 * Create the frame.
	 */
	public MenuFrame() {
		contentPane.setLayout(cl);
		contentPane.add(MainPanel, "1");
		contentPane.add(FisierePanel, "2");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setContentPane(contentPane);
		contentPane.setVisible(true);
	}

}
