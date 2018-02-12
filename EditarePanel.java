package Tema;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class EditarePanel extends JPanel {
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
	public EditarePanel(CardLayout cd, JPanel jp, File fProduse) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(this.getClass().getResource("/Apps-text-editor-icon.png")));
		
		lblNewLabel.setBounds(274, 43, 130, 135);
		add(lblNewLabel);
		
		JLabel lblDenumire = new JLabel("Denumire:");
		lblDenumire.setBounds(41, 43, 95, 15);
		add(lblDenumire);
		
		textDenumire = new JTextField();
		textDenumire.setBounds(41, 57, 114, 19);
		add(textDenumire);
		textDenumire.setColumns(10);
		
		JLabel lblCategorie = new JLabel("Categorie:");
		lblCategorie.setBounds(41, 102, 95, 15);
		add(lblCategorie);
		
		textCategorie = new JTextField();
		textCategorie.setBounds(41, 115, 114, 19);
		add(textCategorie);
		textCategorie.setColumns(10);
		
		JLabel lblTara = new JLabel("Tara:");
		lblTara.setBounds(41, 160, 70, 15);
		add(lblTara);
		
		textTara = new JTextField();
		textTara.setBounds(41, 174, 114, 19);
		add(textTara);
		textTara.setColumns(10);
		
		JLabel lblPret = new JLabel("Pret:");
		lblPret.setBounds(41, 222, 70, 15);
		add(lblPret);
		
		textPret = new JTextField();
		textPret.setBounds(41, 236, 114, 19);
		add(textPret);
		textPret.setColumns(10);
		
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.setBounds(274, 229, 117, 25);
		add(btnInapoi);
		
		btnInapoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cd.show(jp, "3");
			}
			
		});
		
		JButton btnEditare = new JButton("Editare");
		btnEditare.setBounds(274, 192, 117, 25);
		add(btnEditare);
		
		btnEditare.addActionListener(new ActionListener() {

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
							Gestiune.getInstance().getProduse().get(i).getTaraOrigine().compareTo(tara) == 0) {
						ok = 1;	
					}
				}
				
				if(ok == 1) {
					
					
					try {
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
						
						while((line = raf.readLine())!=null) {
							String templine = line;
							words = line.split(" ");
							if(!words[0].equals(den)) {
								pw.write(templine + "\n");
							}else {
								pw.write(den + " " + cat);
								for(int i = 0; i < tari.size(); i++)
									if(tara.equals(tari.get(i))) {
										//System.out.println("aici"+i);
										pw.write(" "+pretDouble);
									}else {
									
										pw.write(" "+ words[i+2]);
									}
								pw.write("\n");
							
							}
						}
						pw.close();
						
						fProduse.delete();
						tempFile.renameTo(fProduse);
						
						JOptionPane.showMessageDialog(null,"S-a gasit produsul!", "Produsul adaugat!", JOptionPane.INFORMATION_MESSAGE);
						
					}catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}else {
					JOptionPane.showMessageDialog(null,"Nu s-a gasit produsul!", "Produsul nu exista!", JOptionPane.WARNING_MESSAGE);
				}
				
				
				
			}
			
		});

	}

}
