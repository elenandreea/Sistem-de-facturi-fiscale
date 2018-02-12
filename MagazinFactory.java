package Tema;
import java.util.Vector;

public class MagazinFactory {
	
	public Magazin getMagazin(String MagazinType,String nume, Vector <Factura> f) {
		if(MagazinType == null)
			return null;
		
		if(MagazinType.equalsIgnoreCase("MiniMarket")) {
			return new MiniMarket(nume,f,MagazinType);
			
		} else if(MagazinType.equalsIgnoreCase("MediumMarket")) {
			return new MediumMarket(nume,f,MagazinType);
			
		} else if(MagazinType.equalsIgnoreCase("HyperMarket")) {
			return new HyperMarket(nume,f,MagazinType);
		}
		
		return null;
	}
		
}
