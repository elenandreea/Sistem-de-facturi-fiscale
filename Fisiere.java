package Tema;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Fisiere extends JPanel {
	private JTextField caleProduse;
	private JTextField caleTaxe;
	private JTextField caleFacturi;
	
	File fProduse;
	File fTaxe;
	File fFacturi;
	/**
	 * Create the panel.
	 */
	public Fisiere(CardLayout cd, JPanel jp) {
		setLayout(null);
		
		JLabel lblIncarcaProduse = new JLabel("Incarca produse:");
		lblIncarcaProduse.setBounds(48, 59, 129, 15);
		add(lblIncarcaProduse);
		
		caleProduse = new JTextField();
		caleProduse.setBounds(48, 73, 175, 19);
		add(caleProduse);
		caleProduse.setColumns(10);
		
		JLabel lblIncarcaTaxe = new JLabel("Incarca taxe:");
		lblIncarcaTaxe.setBounds(48, 100, 129, 15);
		add(lblIncarcaTaxe);
		
		caleTaxe = new JTextField();
		caleTaxe.setBounds(48, 114, 175, 19);
		add(caleTaxe);
		caleTaxe.setColumns(10);
		
		JLabel lblIncarcaFacturi = new JLabel("Incarca facturi:");
		lblIncarcaFacturi.setBounds(48, 140, 114, 15);
		add(lblIncarcaFacturi);
		
		caleFacturi = new JTextField();
		caleFacturi.setBounds(48, 154, 175, 19);
		add(caleFacturi);
		caleFacturi.setColumns(10);
		
		JButton btnProduse = new JButton("produse");
		btnProduse.setBounds(286, 70, 117, 25);
		add(btnProduse);
		
		btnProduse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser f = new JFileChooser();
				f.setDialogTitle("Incarcare date...");
				f.setDialogType(JFileChooser.OPEN_DIALOG);
				f.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				JButton open = new JButton ();
				if (f.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
					caleProduse.setText(f.getSelectedFile().getAbsolutePath());
					fProduse = f.getSelectedFile();
				}
				
				
			}
			
		});
		
		JButton btnTaxe = new JButton("taxe");
		btnTaxe.setBounds(286, 111, 117, 25);
		add(btnTaxe);
		
		btnTaxe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser f = new JFileChooser();
				JButton open = new JButton ();
				f.setDialogTitle("Incarcare date...");
				f.setDialogType(JFileChooser.OPEN_DIALOG);
				f.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				if (f.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
					caleTaxe.setText(f.getSelectedFile().getAbsolutePath());
					fTaxe = f.getSelectedFile();
				}
				
				
			}
			
		});
		
		JButton btnFacturi = new JButton("facturi");
		btnFacturi.setBounds(286, 151, 117, 25);
		add(btnFacturi);
		
		btnFacturi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser f = new JFileChooser();
				JButton open = new JButton ();
				f.setDialogTitle("Incarcare date...");
				f.setDialogType(JFileChooser.OPEN_DIALOG);
				f.setFileSelectionMode(JFileChooser.FILES_ONLY);

				if (f.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
					caleFacturi.setText(f.getSelectedFile().getAbsolutePath());
					fFacturi = f.getSelectedFile();
				}
				
				
			}
			
		});
		
		JButton btnCreeazaOutput = new JButton("Creeaza output");
		btnCreeazaOutput.setBounds(137, 208, 145, 25);
		add(btnCreeazaOutput);
		
		btnCreeazaOutput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

					Tema t = new Tema();
					Vector <Produs> v = new Vector <Produs>();
					
					v = t.citireProdus(fProduse);
					Hashtable<String,Hashtable<String,Double>> h = new Hashtable <String,Hashtable<String,Double>>();
					h = t.citireTaxe(fTaxe);
					Vector<Magazin> vMagazine = new Vector<Magazin>();
					vMagazine = t.citireProdusComanda(fFacturi,v,h);
				
					Gestiune.getInstance().setMagazine(vMagazine);
					Gestiune.getInstance().setProduse(v);
					Gestiune.getInstance().setTaxe(h);
					String output = "/home/andreea/Downloads/poo/AnexeTema/output.txt";
					Gestiune.getInstance().printare(output);
					
					Administrare administrare = new Administrare(cd,jp,fProduse,fTaxe,fFacturi);
					jp.add(administrare, "3");
					StatisticiPanel statistici = new StatisticiPanel(cd,jp,fTaxe);
					jp.add(statistici, "8");
				
			}
			
		});
		
		
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.setBounds(147, 237, 117, 25);
		add(btnInapoi);
		
		btnInapoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cd.show(jp, "1");
			}
			
		});

	}

}
