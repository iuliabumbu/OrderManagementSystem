package presentation;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import bll.ClientBLL;
import bll.OrderItemsBLL;
import bll.OrderTotalBLL;
import bll.ProdusBLL;
import model.Client;
import model.OrderItems;
import model.OrderTotal;
import model.Produs;
/**
 * Clasa comanda inceperea executiei si prelucrarea informatiilor
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class MainClass {
	protected static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());
	private Reports rep = new Reports();
	private DatabaseOperations op = new DatabaseOperations();
	
	/**
	 * Aceasta metoda este folosita pentru a parsa liniile fisierului cu datele de intrare
	 * @param sir Stringul corespunzator liniei pe care dorim sa o manipulam
	 */
	
	public void parse(String sir) {		
		if(sir.contains(":")) {
		String[] parts = sir.split(": ");
			if(parts[0].equals("Insert client") || parts[0].equals("Insert Client")) {
				String[] part = parts[1].split(", ");
				op.insertClient(part[0], part[1]);
			} else if(parts[0].equals("Delete client") || parts[0].equals("Delete Client")) {
				String[] part = parts[1].split(", ");
				op.deleteClient(part[0], part[1]);	
			} else if(parts[0].equals("Insert product") || parts[0].equals("Insert Product")) {
            	String[] part = parts[1].split(", ");
				op.insertProduct(part[0], part[1], part[2]);	
			} else if(parts[0].equals("Delete Product") || parts[0].equals("Delete product"))
            	op.deleteProduct(parts[1]);
			 else if(parts[0].equals("order") || parts[0].equals("Order")) {
			      String[] part = parts[1].split(", ", 2);
				  ArrayList<String> denumire = new ArrayList<String>();
	              ArrayList<String> cantitate = new ArrayList<String>();
	              if(part[1].contains(";")) {
	              String[] arrSplit = part[1].split("; ");
	            	 for (int i=0; i < arrSplit.length; i++) {
	            		 String[] p = arrSplit[i].split(", ");
	            		 denumire.add(p[0]);
	            		 cantitate.add(p[1]);}
	              }else {String[] p = part[1].split(", ");
	            		denumire.add(p[0]);
	            		cantitate.add(p[1]);}
					op.createOrder(part[0], denumire, cantitate);		
		     }}else { String[] parts = sir.split(" ");
                if(parts[0].equals("Report")) {
            	if(parts[1].equals("client") || parts[1].equals("Client")) 
            		rep.reportClient();	
            	else if(parts[1].equals("product") || parts[1].equals("Product")) 
            		rep.reportProduct();		
                else if(parts[1].equals("order") || parts[1].equals("Order")) 
                	rep.reportOrders();					
			}}}

	/**
	 * Aceasta metoda este folosita pentru a deschide fisierul de intrare
	 * si a salva intr-un sir toate liniile acestuia pentru ca ulterior sa le parsam
	 * @param args contine argumentul - calea spre fisirul de intrare
	 */
	public static void main(String[] args) throws SQLException {
		ArrayList<String> line = new ArrayList<String>();
		MainClass main = new MainClass();
		try
	      {  FileReader fileReader = new FileReader(args[0]);
	         BufferedReader bufferedReader = new BufferedReader(fileReader);
	         String s;
	         while ((s = bufferedReader.readLine()) != null)
	        	   line.add(s);
	         fileReader.close();
	         bufferedReader.close();
	       }catch (Exception e) {
	         System.out.println(e.toString());
	      }
		for(String linie: line) {
			main.parse(linie);
		}
	}
}
