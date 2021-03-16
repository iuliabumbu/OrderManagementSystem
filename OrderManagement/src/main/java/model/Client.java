package model;
/**
 * Clasa contine detaliile unui client: id, nume, adresa precum si constructor, get-eri si set-eri
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class Client {
	private int id;
	private String nume;
	private String adresa;
	
	public Client(int id, String nume, String adresa) {
		super();
		this.id = id;
		this.nume = nume;
		this.adresa = adresa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	
	public static String printHeader() {
		String s;
	    s = String.format("%15s %25s %25s %25s %10s", "IdClient", "|", "Nume", "|", "Adresa");
	    s = s +String.format("\n%s", "----------------------------------------------------------------------------------------------------------------");
	    
	    return s;
	}
	public String printLine() {
		String s;
	    s = String.format("\n%15d %25s %25s %25s %10s", this.getId(), "|", this.getNume(), "|", this.getAdresa());
	    
	    return s;
	}
	
	

}