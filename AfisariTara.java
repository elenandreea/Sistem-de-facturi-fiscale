package Tema;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class AfisariTara extends JPanel {

	/**
	 * Create the panel.
	 */
	public AfisariTara(CardLayout cd, JPanel jp, File fTaxe) {
		
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
			words = line.split(" ");
			Vector<String> tari = new Vector<String>();
			for(int i = 1; i < words.length; i++)
				tari.add(words[i]);
			
			Magazin mag = Gestiune.getInstance().magazine.get(0);
			for(int i = 0; i < tari.size(); i++) {
				for(int j = 0; j < Gestiune.getInstance().magazine.size(); j++) {
					if(mag.getTotalTaraCuTaxe(tari.get(i)) < Gestiune.getInstance().magazine.get(j).getTotalTaraCuTaxe(tari.get(i)))
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
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(this.getClass().getResource("/mobile-statistics-icon.png")));
		lblNewLabel.setBounds(12, 60, 142, 132);
		add(lblNewLabel);

	}

}
