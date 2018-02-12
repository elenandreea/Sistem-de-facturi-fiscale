package Tema;
import java.util.Vector;

public class Factura {
	
	String denumire;
	Vector <ProdusComandat> vect;
	
	public Factura(String denumire,Vector <ProdusComandat> vect ) {
		this.denumire = denumire;
		this.vect = vect;
	}
	
	double getTotalFaraTaxe() {
		double total = 0;
		for(int i = 0; i < vect.size(); i++)
			total = total + (vect.get(i)).getCantitate() * (vect.get(i)).getProdus().getPret();
		return total;
	}
	
	double getTotalCuTaxe() {
		double total = 0;
		for(int i = 0; i < vect.size(); i++)
			total = total + (vect.get(i)).getCantitate() * (vect.get(i)).getProdus().getPret() * (100 + (vect.get(i)).getTaxa()) /100;
		return total;
	}
	
	double getTaxe() {
		double taxe = 0;
		for(int i = 0; i < vect.size(); i++)
			taxe = taxe + (vect.get(i)).getCantitate() * (vect.get(i)).getProdus().getPret() * (vect.get(i)).getTaxa()/100;
		return taxe;
	}
	
	double getTotalTaraFaraTaxe(String tara) {
		double total = 0;
		for(int i = 0; i < vect.size(); i++)
			if((vect.get(i)).getProdus().getTaraOrigine().equals(tara))
				total = total + (vect.get(i)).getCantitate() * (vect.get(i)).getProdus().getPret();
		return total;
	}
	
	double getTotalTaraCuTaxe(String tara) {
		double total = 0;
		for(int i = 0; i < vect.size(); i++)
			if((vect.get(i)).getProdus().getTaraOrigine().equals(tara))
				total = total + (vect.get(i)).getCantitate() * (vect.get(i)).getProdus().getPret() * (100 + (vect.get(i)).getTaxa())/100;
		return total;
	}
	
	double getTaxeTara(String tara) {
		double taxe = 0;
		for(int i = 0; i < vect.size(); i++)
			if((vect.get(i)).getProdus().getTaraOrigine().equals(tara))
				taxe = taxe + (vect.get(i)).getCantitate() * (vect.get(i)).getProdus().getPret() * (vect.get(i)).getTaxa()/100;
		return taxe;
	}
	
	public String toString() {
		return denumire +" "+ vect ;
	}
	
	double getTotalCategorieCuTaxe(String categorie) {
		double total = 0;
		for(int i = 0; i < vect.size(); i++)
			if((vect.get(i)).getProdus().getCategorie().equals(categorie))
				total = total + (vect.get(i)).getCantitate() * (vect.get(i)).getProdus().getPret() * (100 + (vect.get(i)).getTaxa())/100;
		return total;
	}
}
