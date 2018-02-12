package Tema;

public class ProdusComandat {
	
	private Produs p;
	private double taxa;
	private int cantitate;
	
	public ProdusComandat(Produs p, double taxa, int cantitate) {
		this.p = p;
		this.taxa = taxa;
		this.cantitate = cantitate;
	}
	
	void setProdus(Produs p) {
		this.p = p;
	}
	
	Produs getProdus() {
		return p;
	}
	
	void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	
	double getTaxa() {
		return taxa;
	}
	
	void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	int getCantitate() {
		return cantitate;
	}
	
	public String toString() {
		return p.getDenumire() + " " + p.getTaraOrigine() + " " +  taxa + " " + cantitate + " " + "\n" ;
	}
	
	
}
