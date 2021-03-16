package model;
/**
 * Clasa contine detaliile unei parti a comenzii: idOrder, numele clientului ce a plasat comanda, 
 * cantitatea respectiv produsul dorit precum si constructor, get-eri si set-eri
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class OrderItems {
	private int idOrder;
	private String numeClient;
	private String denumireProdus;
	private int cantitate;
	
	public OrderItems(int idOrder, String numeClient, String denumireProdus, int cantitate) {
		super();
		this.idOrder = idOrder;
		this.numeClient = numeClient;
		this.denumireProdus = denumireProdus;
		this.cantitate = cantitate;
	}
	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int id) {
		this.idOrder = id;
	}

	public String getNumeClient() {
		return numeClient;
	}

	public void setNumeClient(String numeClient) {
		this.numeClient = numeClient;
	}

	public String getDenumireProdus() {
		return denumireProdus;
	}

	public void setDenumireProdus(String denumireProdus) {
		this.denumireProdus = denumireProdus;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	
	public static String printHeader() {
		String s;
	    s = String.format("%20s %10s %20s %10s %20s %10s %10s", "IdOrder", "|", "Denumire", "|", "Cantitate", "|", "Pret");
	    s = s +String.format("\n%s", "----------------------------------------------------------------------------------------------------------------");
	    
	    return s;
	}
	public String printLine() {
		String s;
	    s = String.format("\n%20d %10s %20s %10s %20s %10s %10d", this.getIdOrder(), "|", this.getNumeClient(), "|", this.getDenumireProdus(), "|", this.getCantitate());
	  
	    return s;
	}	
	

}
