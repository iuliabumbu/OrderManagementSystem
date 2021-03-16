package presentation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import bll.ClientBLL;
import bll.OrderTotalBLL;
import bll.ProdusBLL;
import model.Client;
import model.OrderTotal;
import model.Produs;
/**
 * Clasa contine toate metodele care genereaza pdf-uri corespunzatoare rezultatelor
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class Reports {
	private static int countRepClient = 1;
	private static int countRepProdus = 1;
	private static int countRepOrder = 1;
	private static int countFactura = 1;
	
	/**
	 * Aceasta metoda este folosita pentru a genera raportul pentru comenzi intr-un nou document pdf
	 */
	void reportOrders() {
		OrderTotalBLL oBll = new OrderTotalBLL();
		 ArrayList<OrderTotal> list = oBll.findTotalOrderItems();
		 Document document = new Document();
		 try {
			    String nume ="OrdersReport"+countRepOrder+".pdf";
			    countRepOrder++;
				PdfWriter.getInstance(document, new FileOutputStream(nume));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (DocumentException e1) {
				e1.printStackTrace();
			}
			 document.open();
			 PdfPTable table = new PdfPTable(6);
		     PdfPCell header = new PdfPCell();
		     header.setBackgroundColor(BaseColor.LIGHT_GRAY);
		     header.setBorderWidth(2);
		     header.setPhrase(new Phrase("Cod"));
	         table.addCell(header);
	         header.setPhrase(new Phrase("Client"));
       	     table.addCell(header);
       	     header.setPhrase(new Phrase("Adresa"));
    	     table.addCell(header);
    	     header.setPhrase(new Phrase("Produse"));
       	     table.addCell(header);
       	   header.setPhrase(new Phrase("Cantitate"));
     	     table.addCell(header);
		     header.setPhrase(new Phrase("Pret"));
		     table.addCell(header);
		    
		     for(OrderTotal x : list) {
		    	 table.addCell(x.getCod()+"");
		    	 table.addCell(x.getClient());
		    	 table.addCell(x.getAdresa());
		    	 table.addCell(x.getProduse());
		    	 table.addCell(x.getCantitati());
		    	 table.addCell(x.getPret()+"");
		     }
		     try {
				document.add(table);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			 document.close();
	}
	/**
	 * Aceasta metoda este folosita pentru a genera raportul pentru produse intr-un nou document pdf
	 */
	void reportProduct() {
		ProdusBLL pBll = new ProdusBLL();
		 ArrayList<Produs> list = pBll.findAllProducts();
		 Document document = new Document();
		 try {
			 String nume ="ProductReport"+countRepProdus+".pdf";
			    countRepProdus++;
				PdfWriter.getInstance(document, new FileOutputStream(nume));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 document.open();
			 PdfPTable table = new PdfPTable(3);
		     PdfPCell header = new PdfPCell();
		     header.setBackgroundColor(BaseColor.LIGHT_GRAY);
		     header.setBorderWidth(2);
		     header.setPhrase(new Phrase("Denumire"));
	         table.addCell(header);
	         header.setPhrase(new Phrase("Cantitate"));
       	     table.addCell(header);
		     header.setPhrase(new Phrase("Pret"));
		     table.addCell(header);
		    
		     for(Produs x : list) {
		    	 table.addCell(x.getDenumire());
		    	 table.addCell(x.getCantitate()+"");
		    	 table.addCell(x.getPret()+"");
		     }
		     try {
				document.add(table);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			 document.close();
	}
	/**
	 * Aceasta metoda este folosita pentru a genera raportul pentru clienti intr-un nou document pdf
	 */
	void reportClient() {
		 ClientBLL cBll = new ClientBLL();
		 ArrayList<Client> list = cBll.findAllClients();
		 Document document = new Document();
		 try {
			String nume ="ClientReport"+countRepClient+".pdf";
			countRepClient++;
			PdfWriter.getInstance(document, new FileOutputStream(nume));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		 document.open();
         PdfPTable table = new PdfPTable(3);
	     PdfPCell header = new PdfPCell();
	     PdfPCell header2 = new PdfPCell();
	     PdfPCell header3 = new PdfPCell();
	     header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	     header.setBorderWidth(2);
	     header.setPhrase(new Phrase("Client Id"));
         table.addCell(header);
         header.setPhrase(new Phrase("Nume"));
   	     table.addCell(header);
	     header.setPhrase(new Phrase("Adresa"));
	     table.addCell(header);
	    
	     for(Client x : list) {
	    	 table.addCell(x.getId()+"");
	    	 table.addCell(x.getNume());
	    	 table.addCell(x.getAdresa());
	     }
	     try {
			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		 document.close();
	}
	/**
	 * Aceasta metoda este folosita pentru a genera factura unei comenzi intr-un nou document pdf
	 * @param x  comanda pentru care dorim sa generam factura 
     * @param validate un intreg pe baza caruia stabilim daca comanda dorita este valida
	 */
	void creareFactura(OrderTotal x, int validate) {
		 Document document = new Document();
		 try {
			 String nume ="Factura"+countFactura+".pdf";
			    countFactura++;
			PdfWriter.getInstance(document, new FileOutputStream(nume));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		 document.open();
		try {
		if(validate == 1) {
				document.add(new Paragraph("Nu exista stoc suficient pentru a efectua comanda!"));
		}
		else if(validate == 2){
				document.add(new Paragraph("Nu exista clientul care a efectuat comanda!"));
		}
		else if(validate == 3){
					document.add(new Paragraph("Nu exista produsul dorit!"));
		}else {
				document.add(new Paragraph("FACTURA:"));
				document.add(new Paragraph(x.printHeader()));
				document.add(new Paragraph(x.printLine()));
			  }
		}catch (DocumentException e) {
				e.printStackTrace();
		}
		document.close();
	}
	

}