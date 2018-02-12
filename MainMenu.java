package Tema;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	
	JButton btnIncarcaFisiere;
	
	public MainMenu(CardLayout cd, JPanel jp) {
		setLayout(null);
		
		btnIncarcaFisiere = new JButton("Incarca fisiere");
		btnIncarcaFisiere.setBounds(49, 101, 134, 25);
		add(btnIncarcaFisiere);
		
		btnIncarcaFisiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cd.show(jp, "2");
			}
		});
		
		JButton btnAdministratie = new JButton("Administratie");
		btnAdministratie.setBounds(249, 101, 134, 25);
		add(btnAdministratie);
		
		btnAdministratie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cd.show(jp, "3");
			}
		});
		
		JButton btnStatistici = new JButton("Statistici");
		btnStatistici.setBounds(49, 183, 134, 25);
		add(btnStatistici);
		
		btnStatistici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cd.show(jp, "8");
			}
		});
		
		JButton btnIesire = new JButton("Iesire");
		btnIesire.setBounds(249, 183, 134, 25);
		add(btnIesire);

		btnIesire.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
		JLabel lblMenu = DefaultComponentFactory.getInstance().createTitle("Menu");
		lblMenu.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setBounds(151, 28, 130, 25);
		add(lblMenu);

	}
}
