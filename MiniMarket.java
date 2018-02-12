package Tema;
import java.util.Vector;

public class MiniMarket extends Magazin{
	
	
	public MiniMarket(String nume, Vector <Factura> f, String tip) {
		super(nume, f,tip);
	}

	@Override
	public double calculScutiriTaxe() {
		// TODO Auto-generated method stub
		double scutiri = 0;
		double totalM = super.getTotalCuTaxe();
		Vector <String> tari = new Vector <String>();
		int i,j;
		
		for(i=0; i<f.size(); i++)
			for(j=0; j<(f.get(i)).vect.size();j++)
				if(! tari.contains(f.get(i).vect.get(j).getProdus().getTaraOrigine()) ) {
					double totalT = getTotalTaraCuTaxe(f.get(i).vect.get(j).getProdus().getTaraOrigine());
					if(totalT > (0.5 * totalM))
						scutiri = 0.1;
					tari.add(f.get(i).vect.get(j).getProdus().getTaraOrigine());
				}
		
		return scutiri;
	}
	
}
