package Tema;
import java.util.Vector;

public class HyperMarket extends Magazin{
	
	//public String tip;
	
	public HyperMarket(String nume, Vector <Factura> f,String tip) {
		super(nume, f,tip);
		//this.tip = tip;
	}

	@Override
	public double calculScutiriTaxe() {
		// TODO Auto-generated method stub
		double scutiri = 0;
		double totalM = super.getTotalCuTaxe();
		
		double totalF = 0;
		for(int i=0; i<f.size(); i++) {
			totalF = f.get(i).getTotalCuTaxe();
			if(totalF > (0.1 * totalM)) 
				scutiri = 0.01 ;
		}
			
		return scutiri;
	}

}
