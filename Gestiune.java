package Tema;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

public class Gestiune {

	Vector<Produs> produse = new Vector<>();
	Vector<Magazin> magazine = new Vector<>();
	Hashtable <String,Hashtable<String,Double>> taxe = new Hashtable <>();
	
	Tema t = new Tema();
	
	private static Gestiune instance = null;
	
	private Gestiune() {}
	
	public static Gestiune getInstance() {
		if(instance == null)
			instance = new Gestiune();
		
		return instance;
	}
	
	void setProduse(Vector<Produs> produse) {
		this.produse = produse;
		
	}
	
	Vector<Produs> getProduse() {
		return this.produse;
	}
	
	void setMagazine(Vector<Magazin> magazine) {
		this.magazine = magazine;
	}
	
	void setTaxe(Hashtable <String,Hashtable<String,Double>> taxe) {
		this.taxe = taxe;
	}
	
	public String toString() {
		return magazine.toString() +" "+ taxe.toString() + " " + produse.toString();
	}
	
	Set<String> ordonareTari(){
	
		Set<String> st = taxe.keySet();
		return st;
	}
	
	void printare(String s) {
		
		try {
			DecimalFormat df = new DecimalFormat("###.####");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(s), "utf-8"));
			Vector <String> accesat = new Vector <String>();
			
			int type1 = 0;
			int type2 = 0;
			int type3 = 0;
			String type = "MiniMarket";
			
			for(int i=0; i< magazine.size();i++) {
						
				if(magazine.get(i).getType().equals("MiniMarket"))
					type1++;
				if(magazine.get(i).getType().equals("MediumMarket"))
					type2++;
				if(magazine.get(i).getType().equals( "HyperMarket"))
					type3++;
			}
			
			int contor = 0;
			while(contor < magazine.size())	{
				if(contor == 0 || contor == type1 || contor == (type1 + type2) ) {
					writer.write(type);
					writer.newLine();
				}
				double totalM = 999999;
				int indexM=0;;
				
				for(int j=0; j< magazine.size(); j++)
					if(magazine.get(j).getType().equals(type)) {
						
						if(totalM > magazine.get(j).getTotalFaraTaxe()) 
							if(!accesat.contains(magazine.get(j).nume)){
								totalM = magazine.get(j).getTotalFaraTaxe();
								indexM = j;
						}
					}

				contor++;
				accesat.add(magazine.get(indexM).nume);
				
				writer.write(magazine.get(indexM).getNume());
				writer.newLine();
				writer.newLine();
				writer.write("Total "+df.format(magazine.get(indexM).getTotalFaraTaxe())+" "+ df.format(magazine.get(indexM).getTotalCuTaxe())+" "+df.format(magazine.get(indexM).getTotalCuTaxeScutite()));
				writer.newLine();
				writer.newLine();
				writer.write("Tara");
				writer.newLine();
				Set<String> tari=this.ordonareTari();

				for(String t : tari) {
					if(magazine.get(indexM).getTotalTaraFaraTaxe(t)!=0) {
						writer.write(t +" "+ df.format(magazine.get(indexM).getTotalTaraFaraTaxe(t))+" "+ df.format(magazine.get(indexM).getTotalTaraCuTaxe(t))+" "+ df.format(magazine.get(indexM).getTotalTaraCuTaxeScutite(t)));
					}else {
						writer.write(t +" 0");
					}
					writer.newLine();
				}
				writer.newLine();
				
				
				Vector <String>denFact = new Vector <String> ();
				
				int nrFact=0;
				while(nrFact < magazine.get(indexM).f.size()) {
				
					int indexF = 0;
					double totalF = magazine.get(indexM).f.get(indexF).getTotalCuTaxe();
					for(int j=0; j<magazine.get(indexM).f.size();j++)
						if(totalF > magazine.get(indexM).f.get(j).getTotalCuTaxe()) 
							if(!denFact.contains(magazine.get(indexM).f.get(j).denumire)){
								totalF = magazine.get(indexM).f.get(j).getTotalCuTaxe();
								indexF = j;
							}
				
					nrFact++;
					denFact.add(magazine.get(indexM).f.get(indexF).denumire);
					writer.write(magazine.get(indexM).f.get(indexF).denumire);
					writer.newLine();
					writer.newLine();
					writer.write("Total "+df.format(magazine.get(indexM).f.get(indexF).getTotalFaraTaxe())+" "+df.format(magazine.get(indexM).f.get(indexF).getTotalCuTaxe()));
					writer.newLine();
					writer.newLine();
					
					writer.write("Tara");
					writer.newLine();
					for(String t : tari) {
						if(magazine.get(indexM).f.get(indexF).getTotalTaraFaraTaxe(t) !=0){
							writer.write(t +" "+ df.format(magazine.get(indexM).f.get(indexF).getTotalTaraFaraTaxe(t))+" "+ df.format(magazine.get(indexM).f.get(indexF).getTotalTaraCuTaxe(t)));
						}else
							writer.write(t +" 0");
						
						writer.newLine();
					}
					writer.newLine();
			
				}
				
				if(contor >= type1)
					type = "MediumMarket";
				if(contor >= type1 + type2)
					type = "HyperMarket";		
			}
				
			writer.close();
			
		}catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	void rescrieProduse() {
		
		try {
			
			DecimalFormat df = new DecimalFormat("###.####");
			PrintWriter writer = new PrintWriter("/home/andreea/Downloads/poo/AnexeTema/produse.txt");
			Set<String> tari=this.ordonareTari();
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
