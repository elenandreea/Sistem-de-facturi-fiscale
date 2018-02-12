package Tema;

import javax.swing.JPanel;
import javax.swing.JList;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class AfisariTotal extends JPanel {

	/**
	 * Create the panel.
	 */
	public AfisariTotal(CardLayout cd, JPanel jp) {
		setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(166, 26, 260, 230);
		add(textPane);
		
		Magazin mag = Gestiune.getInstance().magazine.get(0);
		for(int i = 1; i < Gestiune.getInstance().magazine.size(); i++) {
			if(mag.getTotalCuTaxe() < Gestiune.getInstance().magazine.get(i).getTotalCuTaxe() )
				mag = Gestiune.getInstance().magazine.get(i);
				
		}
		
		DecimalFormat df = new DecimalFormat("###.####");
		textPane.setText("Nume:"+mag.nume+"\n" +"Total fara taxe:"+df.format(mag.getTotalFaraTaxe()) + "\n" +"Total cu taxe:"+ df.format(mag.getTotalCuTaxe()) +"\n"+ "Total cu taxe scutite:"+df.format(mag.getTotalCuTaxeScutite()));
		
		
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.setBounds(37, 231, 117, 25);
		add(btnInapoi);

		btnInapoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cd.show(jp, "8");
			}
			
		});
		
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(this.getClass().getResource("/Statistics-icon.png")));
		lblNewLabel.setBounds(12, 60, 142, 132);
		add(lblNewLabel);
	}
}
