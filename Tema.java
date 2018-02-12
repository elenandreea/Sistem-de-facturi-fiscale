package Tema;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

public class Tema {

	Vector<Produs> citireProdus(File s) {

		Vector<Produs> vProduse = new Vector<Produs>();
		FileInputStream fis = null;

		String words[];
		try {

			RandomAccessFile raf = new RandomAccessFile(s, "r");
			String line = "";
			String countries[];
			line = raf.readLine();
			countries = line.split(" ");
			while ((line = raf.readLine()) != null) {
				words = line.split(" ");
				for (int i = 2; i < countries.length; i++) {
					double pret = Double.parseDouble(words[i]);	
					if(pret != 0) {
					Produs p = new Produs(words[0], words[1], countries[i], pret);
					vProduse.add(p);
					}
	
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return vProduse;

	}

	Vector<Magazin> citireProdusComanda(File s, Vector<Produs> vProduse,
			Hashtable<String, Hashtable<String, Double>> h) {

		Vector<Vector<ProdusComandat>> vcomenzi = new Vector<Vector<ProdusComandat>>();
		Vector<Magazin> vmagazine = new Vector<Magazin>();
		Set<String> st = h.keySet();

		FileInputStream fis = null;

		String words[];

		try {

			int ok = 1;
			RandomAccessFile raf = new RandomAccessFile(s, "r");
			String line;

			while (ok != 0 && (line = raf.readLine()) != null) {
				
				words = line.split(" |:");

				// if(words[0].equals("Magazin")) 
				Vector<Factura> vFacturi = new Vector<Factura>();
				String tipM = words[1];
				String numeM = words[2];
				
				line = raf.readLine(); // linia goala

				while (ok != 0 && (line = raf.readLine()) != null && (words = line.split(" |:")).length == 1) {

					
					words = line.split(" |:");
					// if(words[0].startsWith("Factura")) 
					String numeF = words[0];

					line = raf.readLine();
					words = line.split(" |:");
				
					// if(words[0].startsWith("Denumire")) {
					line = raf.readLine();

					Vector<ProdusComandat> vComandat = new Vector<ProdusComandat>();
					while ((words = line.split(" |:")).length != 1) {

						Produs p = new Produs();
						for (int i = 0; i < vProduse.size(); i++)
							if ((vProduse.get(i)).getDenumire().equals(words[0])
									&& (vProduse.get(i)).getTaraOrigine().equals(words[1]))
								p = (vProduse.get(i));

						Double taxa = 0.0;
						taxa = h.get(words[1]).get(p.getCategorie());

						int cantitate = Integer.parseInt(words[2]);
						ProdusComandat pComandat = new ProdusComandat(p, taxa, cantitate);
						vComandat.add(pComandat);

						if ((line = raf.readLine()) == null) {
							ok = 0;
							break;
						}
					}

					Factura f = new Factura(numeF, vComandat);
					vFacturi.add(f);
				}

				MagazinFactory m = new MagazinFactory();
				vmagazine.add(m.getMagazin(tipM, numeM, vFacturi));
				
				//System.out.println("MAGAZIN:" + m.getMagazin(tipM, numeM, vFacturi));

				if (ok != 0) {

					long deplasare = line.length();
					long po = raf.getFilePointer();
					po = po - deplasare - 1;
					raf.seek(po);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return vmagazine;

	}

	Hashtable<String, Hashtable<String, Double>> citireTaxe(File s) {

		FileInputStream fis = null;
		Hashtable<String, Hashtable<String, Double>> H = new Hashtable<String, Hashtable<String, Double>>();

		try {
			RandomAccessFile raf = new RandomAccessFile(s, "r");
			String line;
			String words[];
			Vector<String> vTari = new Vector<String>();

			Vector<Vector<Double>> vProcente = new Vector<Vector<Double>>();

			int i;
			line = raf.readLine();
			words = line.split(" ");
			for (i = 1; i < words.length; i++) {
				vTari.add(words[i]); // tari
			}

			Vector<String> vCategorie = new Vector<String>();
			while ((line = raf.readLine()) != null) {
				words = line.split(" ");

				vCategorie.add(words[0]);
				Vector<Double> aux = new Vector<Double>();
				double taxa = 0;
				for (i = 1; i < words.length; i++) {
					taxa = Double.parseDouble(words[i]);
					aux.add(taxa);
					
				}
				vProcente.add(aux);

			}
			//System.out.println(vProcente);
			//System.out.println(vCategorie);

			int index = 0;
			for (index = 0; index < vTari.size(); index++) {
				Hashtable h = new Hashtable();
				for (i = 0; i < vProcente.size(); i++)
					h.put(vCategorie.get(i), vProcente.get(i).get(index));
				
				H.put(vTari.get(index), h);

			}
			//System.out.println(H);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return H;

	}

}
