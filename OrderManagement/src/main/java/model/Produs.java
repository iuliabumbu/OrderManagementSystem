package model;
/**
 * Clasa contine detaliile unui produs: denumire, cantitate, pret precum si constructor, get-eri si set-eri
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class Produs {
	private String denumire;
	private int cantitate;
	private float pret;
	
	public Produs(String denumire, int cantitate, float pret) {
		super();
		this.denumire = denumire;
		this.cantitate = cantitate;
		this.pret = pret;
	}
	
	public String getDenumire() {
		return denumire;
	}
	public void setNume(String denumire) {
		this.denumire = denumire;
	}
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	public float getPret() {
		return pret;
	}
	public void setPret(int pret) {
		this.pret = pret;
	}

	public String toString() {
		return "Produs [denumire=" + denumire + ", cantitate=" + cantitate + ", pret=" + pret + "]";
	}
	
	public static String printHeader() {
		String s;
	    s = String.format("%30s %25s %10s %25s %10s", "Denumire", "|", "Cantitate", "|", "Pret");
	    s = s +String.format("\n%s", "----------------------------------------------------------------------------------------------------------------");
	    
	    return s;
	}
	public String printLine() {
		String s;
	    s = String.format("\n%30s %25s %10d %25s %10f", this.getDenumire(), "|", this.getCantitate(), "|", this.getPret());
	    
	    return s;
	}
	

}
