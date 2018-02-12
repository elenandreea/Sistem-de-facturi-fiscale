package Tema;

import java.util.Vector;

public class MediumMarket extends Magazin {

	public MediumMarket(String nume, Vector<Factura> f, String tip) {
		super(nume, f, tip);
	}

	public double getTotalCategorieCuTaxe(String categorie) {
		double total = 0;
		for (int i = 0; i < f.size(); i++)
			for (int j = 0; j < (f.get(i)).vect.size(); j++)
				if (f.get(i).vect.get(j).getProdus().getCategorie().equals(categorie))
					total = total + f.get(i).vect.get(j).getProdus().getPret() * f.get(i).vect.get(j).getCantitate()
							* (f.get(i).vect.get(j).getTaxa() + 100) / 100;
		return total;
	}

	@Override
	public double calculScutiriTaxe() {
		// TODO Auto-generated method stub
		double scutiri = 0;
		double totalM = super.getTotalCuTaxe();

		Vector<String> categorii = new Vector<String>();
		int i, j;

		for (i = 0; i < f.size(); i++)
			for (j = 0; j < (f.get(i)).vect.size(); j++)
				if (!categorii.contains(f.get(i).vect.get(j).getProdus().getCategorie())) {
					double totalC = this.getTotalCategorieCuTaxe(f.get(i).vect.get(j).getProdus().getCategorie());
					if (totalC > (0.5 * totalM))
						scutiri = 0.05;
					categorii.add(f.get(i).vect.get(j).getProdus().getCategorie());
				}

		return scutiri;

	}

}
