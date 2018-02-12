package Tema;
import java.util.Vector;

public abstract class Magazin implements IMagazin {
	
	String nume;
	Vector <Factura> f;
	String type;
	
	public Magazin() {
		this(null,null,null);
	}
		
	public Magazin(String nume, Vector <Factura> f, String type) {
		this.f=f;
		this.nume=nume;
		this.type=type;
	}
	
	String getType() {
		return type;
	}
	
	String getNume() {
		return nume;
	}
	
	Vector<Factura> getFactura(){
		return f;
	}

	public double getTotalFaraTaxe() {
		double total = 0;
		for(int i=0; i<f.size(); i++)
			total = total + f.get(i).getTotalFaraTaxe();
		return total;
	}
	
	public double getTotalCuTaxe() {
		double total = 0;
		for(int i=0; i<f.size(); i++)
			total = total + f.get(i).getTotalCuTaxe();
		return total;
	}
	
	public double getTotalCuTaxeScutite() {
		double scutiri = this.calculScutiriTaxe();
		double totalM = this.getTotalCuTaxe();
		
		if(scutiri==0)
			return totalM;
		
		return totalM - scutiri * totalM;
	}
	
	public double getTotalTaraFaraTaxe(String tara) {
		double total = 0;
		for(int i=0; i<f.size(); i++)
			total = total + f.get(i).getTotalTaraFaraTaxe(tara);
		return total;
	}
	
	public double getTotalTaraCuTaxe(String tara) {
		double total = 0;
		for(int i=0; i<f.size(); i++)
			total = total + f.get(i).getTotalTaraCuTaxe(tara);
		return total;
	}
	
	public double getTotalTaraCuTaxeScutite(String tara) {
		
		double scutiri = calculScutiriTaxe();
		double totalT = this.getTotalTaraCuTaxe(tara);
		
		if(scutiri==0)
			return totalT;
		
		return totalT  - scutiri*totalT;
	}
	
	public abstract double calculScutiriTaxe();
	
	public String toString() {
		return nume +" " + f.toString();
	}
	
	public double getTotalCategorieCuTaxe(String categorie) {
		double total = 0;
		for(int i=0; i<f.size(); i++)
			total = total + f.get(i).getTotalCategorieCuTaxe(categorie);
		return total;
	}
	
}
