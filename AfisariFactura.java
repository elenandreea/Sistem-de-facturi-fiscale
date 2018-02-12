package Tema;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class AfisariFactura extends JPanel {

	/**
	 * Create the panel.
	 */
	public AfisariFactura(CardLayout cd, JPanel jp) {
		
	setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(166, 26, 260, 230);
		add(textPane);
		
		Factura factura = Gestiune.getInstance().magazine.get(0).f.get(0);
		for(int i = 0; i < Gestiune.getInstance().magazine.size(); i++) 
			for(int j = 0; j < Gestiune.getInstance().magazine.get(i).f.size(); j++)
				if(factura.getTotalFaraTaxe() < Gestiune.getInstance().magazine.get(i).f.get(j).getTotalFaraTaxe())
					factura = Gestiune.getInstance().magazine.get(i).f.get(j);
				
		
		
		DecimalFormat df = new DecimalFormat("###.####");
		textPane.setText("Nume:"+factura.denumire+"\n" +"Total fara taxe:"+df.format(factura.getTotalFaraTaxe()) + "\n" +"Total cu taxe:"+ df.format(factura.getTotalCuTaxe()));
		
		
		
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
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(this.getClass().getResource("/Business-Statistics-icon.png")));
		lblNewLabel.setBounds(12, 60, 142, 132);
		add(lblNewLabel);

	}

}
