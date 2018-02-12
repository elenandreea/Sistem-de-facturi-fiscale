package Tema;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CautarePanel extends JPanel {
	private JTextField textDenumire;
	private JTextField textCategorie;
	private JTextField textTara;
	private JTextField textPret;
	
	String den;
	String cat;
	String tara;
	String pret;
	Double pretDouble;

	/**
	 * Create the panel.
	 */
	public CautarePanel(CardLayout cd, JPanel jp, File fProduse) {
		setLayout(null);
		
		JLabel lblDenumire = new JLabel("Denumire:");
		lblDenumire.setBounds(46, 34, 92, 15);
		add(lblDenumire);
		
		textDenumire = new JTextField();
		textDenumire.setBounds(46, 50, 114, 19);
		add(textDenumire);
		textDenumire.setColumns(10);
		
		JLabel lblCategorie = new JLabel("Categorie:");
		lblCategorie.setBounds(46, 95, 92, 15);
		add(lblCategorie);
		
		textCategorie = new JTextField();
		textCategorie.setBounds(46, 110, 114, 19);
		add(textCategorie);
		textCategorie.setColumns(10);
		
		JLabel lblTara = new JLabel("Tara:");
		lblTara.setBounds(46, 156, 70, 15);
		add(lblTara);
		
		textTara = new JTextField();
		textTara.setBounds(46, 171, 114, 19);
		add(textTara);
		textTara.setColumns(10);
		
		JLabel lblPret = new JLabel("Pret:");
		lblPret.setBounds(46, 221, 70, 15);
		add(lblPret);
		
		textPret = new JTextField();
		textPret.setBounds(46, 237, 114, 19);
		add(textPret);
		textPret.setColumns(10);
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.setBounds(255, 234, 117, 25);
		add(btnInapoi);
		
		btnInapoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cd.show(jp, "3");
			}
		});
		
		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/Search-icon.png")));
		label.setBounds(223, 34, 170, 170);
		add(label);
		
		JButton btnCauta = new JButton("Cauta");
		btnCauta.setBounds(255, 198, 117, 25);
		add(btnCauta);
		
		btnCauta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				den = textDenumire.getText().trim();
				cat = textCategorie.getText().trim();
				tara = textTara.getText().trim();
				pret = textPret.getText().trim();
				
				if(den.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Seteaza numele produsului", "Eroare!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(cat.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Seteaza categoria produsului!", "Eroare!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(tara.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecteaza tara de origine!", "Eroare!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(pret.isEmpty()) {			
					JOptionPane.showMessageDialog(null, "Seteaza pretul produsului!", "Eroare!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				pretDouble = Double.parseDouble(pret);
				int ok = 0;
				
				for(int i = 0; i< Gestiune.getInstance().getProduse().size(); i++) {
					if(Gestiune.getInstance().getProduse().get(i).getDenumire().compareTo(den) == 0 &&
							Gestiune.getInstance().getProduse().get(i).getCategorie().compareTo(cat)==0 &&
							Gestiune.getInstance().getProduse().get(i).getTaraOrigine().compareTo(tara) == 0 &&
							Gestiune.getInstance().getProduse().get(i).getPret() == pretDouble) {
						ok = 1;	
					}
				}
				
				if(ok == 1) {
					JOptionPane.showMessageDialog(null,"S-a gasit produsul!", "Produsul exista!", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Nu s-a gasit produsul!", "Produsul exista!", JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
		});
		
		

	}

}
