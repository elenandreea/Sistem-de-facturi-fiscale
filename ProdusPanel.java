package Tema;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ProdusPanel extends JPanel {
	private JTextField textDen;
	private JTextField textCat;
	private JTextField textTara;
	private JTextField textPret;
	
	String den;
	String cat;
	String pret;
	String tara;
	double pretDouble;

	/**
	 * Create the panel.
	 */
	public ProdusPanel(CardLayout cd, JPanel jp, File fProduse) {
		setLayout(null);
		
		JLabel lblDenumire = new JLabel("Denumire:");
		lblDenumire.setBounds(48, 37, 90, 15);
		add(lblDenumire);
		
		textDen = new JTextField();
		textDen.setBounds(48, 52, 114, 19);
		add(textDen);
		textDen.setColumns(10);
		
		JLabel lblCategorie = new JLabel("Categorie:");
		lblCategorie.setBounds(48, 99, 90, 15);
		add(lblCategorie);
		
		textCat = new JTextField();
		textCat.setBounds(48, 114, 114, 19);
		add(textCat);
		textCat.setColumns(10);
		
		JLabel lblTara = new JLabel("Tara:");
		lblTara.setBounds(48, 161, 70, 15);
		add(lblTara);
		
		textTara = new JTextField();
		textTara.setBounds(48, 176, 114, 19);
		add(textTara);
		textTara.setColumns(10);
		
		JLabel lblPret = new JLabel("Pret:");
		lblPret.setBounds(48, 223, 70, 15);
		add(lblPret);
		
		textPret = new JTextField();
		textPret.setBounds(48, 238, 114, 19);
		add(textPret);
		textPret.setColumns(10);
		
		JButton btnAdauga = new JButton("Adauga");
		btnAdauga.setBounds(274, 200, 117, 25);
		add(btnAdauga);
		
		btnAdauga.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				den = textDen.getText().trim();
				cat = textCat.getText().trim();
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
				int ok = 1;
				
				for(int i = 0; i< Gestiune.getInstance().getProduse().size(); i++) {
					if(Gestiune.getInstance().getProduse().get(i).getDenumire().compareTo(den) == 0 &&
							Gestiune.getInstance().getProduse().get(i).getCategorie().compareTo(cat)==0 &&
							Gestiune.getInstance().getProduse().get(i).getTaraOrigine().compareTo(tara) == 0 &&
							Gestiune.getInstance().getProduse().get(i).getPret() == pretDouble) {
						
						JOptionPane.showMessageDialog(null, "Optiune invalida", "Deja exista!", JOptionPane.ERROR_MESSAGE);
						ok = 0;
						break;	
					}
				}
				
				if(ok == 1) {
					try {
						
						
						RandomAccessFile raf = new RandomAccessFile(fProduse, "r");
						String line = "";
						String countries[];
						String words[];
						
						line = raf.readLine();
						countries = line.split(" ");
						
						Vector<String> tari = new Vector<String>();
						for(int i = 2; i < countries.length; i++)
							tari.add(countries[i]);
							
						Vector<String> denumiri = new Vector<String>();
						Vector<String> categorii = new Vector<String>();
						while ((line = raf.readLine()) != null) {
							words = line.split(" ");
							categorii.add(words[1]);
							denumiri.add(words[0]);
						}
						raf.close();
						
						int eroare = 0;
						if(!tari.contains(tara)) 
							eroare = 1;
						if(!categorii.contains(cat))
							eroare = 1;
						if(eroare == 1) {
							JOptionPane.showMessageDialog(null, "Optiune invalida", "Categorie sau tara inexistenta!", JOptionPane.ERROR_MESSAGE);
						}
						else {
							
							if(denumiri.contains(den)) {
								
								int gasireTara = 0;
								for(int i = 0; i< Gestiune.getInstance().getProduse().size(); i++) 
									if(Gestiune.getInstance().getProduse().get(i).getDenumire().compareTo(den) == 0 &&
											Gestiune.getInstance().getProduse().get(i).getCategorie().compareTo(cat)==0) 
										if(Gestiune.getInstance().getProduse().get(i).getTaraOrigine().compareTo(tara)==0)
											gasireTara = 1;
										
								if(gasireTara ==1)	{	
									JOptionPane.showMessageDialog(null, "Eroare!Pret deja stabilit!", "Eroare!", JOptionPane.ERROR_MESSAGE);
								}
								else {
									
									File tempFile = new File(fProduse.getAbsoluteFile()+ ".tmp");
									raf = new RandomAccessFile(fProduse, "r");
									PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
									
									line = null;
									String parts[];
									
									line = raf.readLine();
									pw.write(line+"\n");
									parts = line.split(" ");
													
									while((line = raf.readLine())!=null) {
										String templine = line;
										parts = line.split(" ");
										if(!parts[0].equals(den)) {
											pw.write(templine + "\n");
										}else {
											pw.write(den + " " + cat);
											for(int i = 0; i < tari.size(); i++)
												if(tara.equals(tari.get(i))) {
													pw.write(" "+pretDouble);
												}else {
													pw.write(" "+ parts[i+2]);
												}
											pw.write("\n");
											
											}
										}
									pw.close();
									
									fProduse.delete();
									tempFile.renameTo(fProduse);
								}
							}
							else {
								
								FileWriter fw = new FileWriter(fProduse,true);
								fw.write("\n"+den+" "+cat);
								for(int i = 0; i < tari.size();i++)
									if(tari.get(i).equals(tara))
										fw.write(" "+pretDouble);
									else
										fw.write(" 0");
								fw.close();
						
							}
							
							JOptionPane.showMessageDialog(null, "Produsul a fost adaugat cu succes!");
							cd.show(jp, "3");
								
						}
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
			
		});
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(this.getClass().getResource("/shop-cart-add-icon.png")));
		
		lblNewLabel.setBounds(232, 37, 159, 127);
		add(lblNewLabel);
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.setBounds(274, 233, 117, 25);
		add(btnInapoi);
		
		btnInapoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cd.show(jp, "3");
			}
			
		});

	}
}
