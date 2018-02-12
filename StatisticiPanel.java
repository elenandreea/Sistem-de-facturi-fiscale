package Tema;

import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class StatisticiPanel extends JPanel {

	/**
	 * Create the panel.
	 * 
	 */
	
	
	public StatisticiPanel(CardLayout cd, JPanel jp, File fTaxe) {
		setLayout(null);
		
		AfisariTotal aTotal = new AfisariTotal(cd,jp);
		jp.add(aTotal, "9");
		AfisariFactura aFactura = new AfisariFactura(cd,jp);
		jp.add(aFactura, "10");
		AfisariTara aTara = new AfisariTara(cd,jp,fTaxe);
		jp.add(aTara, "11");
		AfisariCategorie aCategorie = new AfisariCategorie(cd,jp,fTaxe);
		jp.add(aCategorie, "12");
		
		
		JButton btnTotal = new JButton("Total");
		btnTotal.setBounds(44, 62, 117, 25);
		add(btnTotal);
		
		btnTotal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				cd.show(jp, "9");
				
			}
			
		});
		
		JButton btnCategorie = new JButton("Categorie");
		btnCategorie.setBounds(44, 99, 117, 25);
		add(btnCategorie);
		
		btnCategorie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cd.show(jp, "12");
			}
			
		});
		
		JButton btnTara = new JButton("Tara");
		btnTara.setBounds(44, 136, 117, 25);
		add(btnTara);
		
		btnTara.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cd.show(jp, "11");
			}
			
		});
		
		JButton btnFactura = new JButton("Factura");
		btnFactura.setBounds(44, 200, 117, 25);
		add(btnFactura);
		
		btnFactura.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cd.show(jp, "10");
			}
			
		});
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.setBounds(278, 200, 117, 25);
		add(btnInapoi);
		
		btnInapoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cd.show(jp, "1");
			}
			
		});
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(this.getClass().getResource("/chart-icon.png")));
		lblNewLabel.setBounds(254, 44, 163, 132);
		add(lblNewLabel);

	}
}
