package model;
import java.util.ArrayList;
/**
 * Clasa contine detaliile unei comenzi: cod, numele clientului, adresa
 * un sir de produse cumparate precum si cantitatile corespunzatoare lor si pretul total de achitat
 * De asemena exista constructor, get-eri si set-eri
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class OrderTotal {
	private int cod;
	private String client;
	private String adresa;
	private String produse;
	private String cantitati;
	private float pret;

	
	public OrderTotal(int cod, String client, String adresa, String produse, String cantitati, float pret) {
		super();
		this.cod = cod;
		this.client = client;
		this.adresa = adresa;
		this.produse = produse;
		this.cantitati = cantitati;
		this.pret = pret;
	}
	public OrderTotal() {
		// TODO Auto-generated constructor stub
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getProduse() {
		return produse;
	}
	public void setProduse(String produse) {
		this.produse = produse;
	}
	
	public String getCantitati() {
		return cantitati;
	}
	public void setCantitate(String cantitati) {
		this.cantitati = cantitati;
	}
	public float getPret() {
		return pret;
	}
	public void setPret(int pret) {
		this.pret = pret;
	}
	
	public static String printHeader() {
		String s;
	    s = String.format("%10s %4s %10s %4s %15s %4s %20s %4s %15s %4s %10s", "Cod", "|", "Client", "|", "Adresa", "|", "Produse",  "|", "Cantitate", "|", "Pret");
	    s = s +String.format("\n%s", "----------------------------------------------------------------------------------------------------------------");
	    
	    return s;
	}
	public String printLine() {
		String s;
	    s = String.format("\n%10d %4s %10s %4s %15s %4s %20s %4s %15s %4s %10f", this.getCod(), "|", this.getClient(), "|", this.getAdresa(), "|",
	    		         this.getProduse(), "|", this.cantitati, "|", this.getPret());
	  
	    return s;
	}
	

}
