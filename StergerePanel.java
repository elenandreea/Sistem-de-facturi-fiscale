package Tema;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Vector;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;

public class StergerePanel extends JPanel {
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
	public StergerePanel(CardLayout cd, JPanel jp, File fProduse) {
		setLayout(null);
		
		JLabel lblDenumire = new JLabel("Denumire:");
		lblDenumire.setBounds(45, 43, 90, 15);
		add(lblDenumire);
		
		textDenumire = new JTextField();
		textDenumire.setBounds(45, 57, 114, 19);
		add(textDenumire);
		textDenumire.setColumns(10);
		
		JLabel lblCategorie = new JLabel("Categorie:");
		lblCategorie.setBounds(45, 102, 90, 15);
		add(lblCategorie);
		
		textCategorie = new JTextField();
		textCategorie.setBounds(45, 115, 114, 19);
		add(textCategorie);
		textCategorie.setColumns(10);
		
		JLabel lblTara = new JLabel("Tara:");
		lblTara.setBounds(45, 160, 70, 15);
		add(lblTara);
		
		textTara = new JTextField();
		textTara.setBounds(45, 174, 114, 19);
		add(textTara);
		textTara.setColumns(10);
		
		JLabel lblPret = new JLabel("Pret:");
		lblPret.setBounds(45, 222, 70, 15);
		add(lblPret);
		
		textPret = new JTextField();
		textPret.setBounds(45, 238, 114, 19);
		add(textPret);
		textPret.setColumns(10);
		
		JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/shop-cart-exclude-icon.png")));
		label.setBounds(255, 33, 134, 128);
		add(label);
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.setBounds(255, 232, 117, 25);
		add(btnInapoi);
		
		btnInapoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cd.show(jp, "3");
			}
			
		});
		
		
		JButton btnStergereButton = new JButton("Stergere");
		btnStergereButton.setBounds(255, 195, 117, 25);
		add(btnStergereButton);
		
		btnStergereButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
				
				if(ok == 0) {
					JOptionPane.showMessageDialog(null,"Nu s-a gasit produsul!", "Produsul nu exista!", JOptionPane.WARNING_MESSAGE);
				}else {
					
					Tema t = new Tema();
					Vector<Produs> vect = new Vector<Produs>();
					vect = t.citireProdus(fProduse);
					
					int nr = 0;
					for(int i = 0; i < vect.size(); i++)
						if(den.equals(vect.get(i).getDenumire()))
							nr++;
					
					
					try {
						
						String s = "home/andreea/Downloads/poo/AnexeTema/produse2.txt";				
						
						File tempFile = new File(fProduse.getAbsoluteFile()+ ".tmp");
						RandomAccessFile raf = new RandomAccessFile(fProduse, "r");
						PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
						
						String line = null;
						String []words;
						

						
						line = raf.readLine();
						pw.write(line+"\n");
						words = line.split(" ");
						
						Vector<String> tari = new Vector<String>();
						for(int i = 2; i < words.length; i++)
							tari.add(words[i]);

						System.out.println(tari);

						if(nr > 1) {
						
							while((line = raf.readLine())!=null) {
								String templine = line;
								words = line.split(" ");
								if(!words[0].equals(den)) {
									pw.write(templine + "\n");
								}else {
									pw.write(den + " " + cat);
									for(int i = 0; i < tari.size(); i++)
										if(tara.equals(tari.get(i))) {
											System.out.println("aici"+i);
											pw.write(" 0");
										}else {
										
											pw.write(" "+ words[i+2]);
										}
									pw.write("\n");
								
								}
							}
						}
						
						if(nr == 1) {
							
							while((line = raf.readLine())!=null) {
								String templine = line;
								words = line.split(" ");
								if(!words[0].equals(den)) {
									pw.write(templine + "\n");
								}
							}
							
						}
						
						pw.close();
						fProduse.delete();
						tempFile.renameTo(fProduse);
						
					}catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
			
		});

	}

}
