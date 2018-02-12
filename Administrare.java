package Tema;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;

public class Administrare extends JPanel {

	/**
	 * Create the panel.
	 */
	public Administrare(CardLayout cd, JPanel jp, File fProduse, File fTaxe, File fFacturi) {
		setLayout(null);
	
		
		DefaultListModel<Produs> listProduse = new DefaultListModel<Produs>();
		
		Vector<Produs> v = Gestiune.getInstance().getProduse();
		for(int i = 0; i< v.size(); i++ )
			listProduse.addElement(v.get(i));
		
		JList <Produs>list = new JList<Produs>(listProduse);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(178, 12, 231, 215);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(2);
	
		add(list);
						
		JButton btnOrdonare = new JButton("Denumire");
		btnOrdonare.setBounds(25, 36, 117, 25);
		add(btnOrdonare);
		
		btnOrdonare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				v.sort(new Comparator<Produs>() {

					@Override
					public int compare(Produs o1, Produs o2) {
						// TODO Auto-generated method stub
						return o1.getDenumire().compareTo(o2.getDenumire());
					}
					
				});
				
				list.setListData(v);
				
			}
		});
		
		
		JLabel lblOrdonareDupa = new JLabel("Ordonare dupa:");
		lblOrdonareDupa.setBounds(25, 18, 117, 15);
		add(lblOrdonareDupa);
		
		JButton btnTara = new JButton("Tara");
		btnTara.setBounds(25, 62, 117, 25);
		add(btnTara);
		
		btnTara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				v.sort(new Comparator<Produs>() {

					@Override
					public int compare(Produs o1, Produs o2) {
						// TODO Auto-generated method stub
						return o1.getTaraOrigine().compareTo(o2.getTaraOrigine());
					}
					
				});
				
				list.setListData(v);
				
			}
		});
		
		JButton btnAdaugare = new JButton("Adaugare");
		btnAdaugare.setBounds(25, 101, 117, 25);
		add(btnAdaugare);
		
		btnAdaugare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdusPanel prodpane = new ProdusPanel(cd,jp,fProduse);
				jp.add(prodpane, "4");
				cd.show(jp, "4");
			}
		});
		
		JButton btnStergere = new JButton("Stergere");
		btnStergere.setBounds(25, 181, 117, 25);
		add(btnStergere);
		
		btnStergere.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StergerePanel stergpane = new StergerePanel(cd,jp,fProduse);
				jp.add(stergpane, "5");
				cd.show(jp, "5");
				
			}
			
		});
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.setBounds(25, 239, 117, 25);
		add(btnInapoi);
				
		btnInapoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cd.show(jp, "1");
			}
		});
		
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(230, 239, 117, 25);
		add(btnRefresh);	
		
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Tema t = new Tema();
				Vector <Produs> v = new Vector <Produs>();
				
				v = t.citireProdus(fProduse);
				list.setListData(v);
				
			}
			
		});
		
		
		JButton btnCautare = new JButton("Cautare");
		btnCautare.setBounds(25, 127, 117, 25);
		add(btnCautare);
		
		btnCautare.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CautarePanel cautpane = new CautarePanel(cd,jp,fProduse);
				jp.add(cautpane, "6");
				cd.show(jp, "6");
				
			}
			
		});
		
		
		JButton btnEditare = new JButton("Editare");
		btnEditare.setBounds(25, 154, 117, 25);
		add(btnEditare);
		
		btnEditare.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditarePanel editpanel = new EditarePanel(cd,jp,fProduse);
				jp.add(editpanel, "7");
				cd.show(jp, "7");
			}
			
		});
		
		
		
		

	}
}
