package Tema;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class AfisariCategorie extends JPanel {

	/**
	 * Create the panel.
	 */
	public AfisariCategorie(CardLayout cd, JPanel jp, File fTaxe) {
		
		setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(166, 26, 260, 230);
		add(textPane);
		
		StringBuilder afisare = new StringBuilder();
		DecimalFormat df = new DecimalFormat("###.####");
		
		try {
			
			RandomAccessFile raf = new RandomAccessFile(fTaxe,"r");
			String line = null;
			line = raf.readLine();
			String words[];
			
			Vector<String> categorii = new Vector<String>();
			
			while((line = raf.readLine())!=null) {
				words = line.split(" ");
				categorii.add(words[0]);
				
			}
			
			Magazin mag = Gestiune.getInstance().magazine.get(0);
			
			for(int i = 0; i < categorii.size(); i++) {
				for(int j = 0; j < Gestiune.getInstance().magazine.size(); j++) { 
					if(mag.getTotalCategorieCuTaxe(categorii.get(i)) < Gestiune.getInstance().magazine.get(j).getTotalCategorieCuTaxe(categorii.get(i)))
						mag = Gestiune.getInstance().magazine.get(j);
				}
				afisare.append("Nume:"+mag.nume+"\n" );
				afisare.append("Total fara taxe:"+df.format(mag.getTotalFaraTaxe())+ "\n");
				afisare.append("Total cu taxe:"+ df.format(mag.getTotalCuTaxe()) +"\n");
				afisare.append("Total cu taxe scutite:"+df.format(mag.getTotalCuTaxeScutite())+"\n"+"\n"); 
				mag = Gestiune.getInstance().magazine.get(0);
			}
					
			
			textPane.setText(afisare.toString());
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
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
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(this.getClass().getResource("/line-chart-icon.png")));
		lblNewLabel.setBounds(12, 60, 142, 132);
		add(lblNewLabel);

	}

}
