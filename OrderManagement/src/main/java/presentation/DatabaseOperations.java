package presentation;

import java.util.ArrayList;

import bll.ClientBLL;
import bll.OrderItemsBLL;
import bll.OrderTotalBLL;
import bll.ProdusBLL;
import model.Client;
import model.OrderItems;
import model.OrderTotal;
import model.Produs;
/**
 * Clasa contine apelurile metodelor care folosesc baza de date, precum si creerea unor noi clienti/produse/comenzi
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class DatabaseOperations {
	private static int i = 0; //contor pt id Client
	private static int j = 0; //contor pt id OrderItems
	private static int k = 0; //contor pentru cod OrderTotal
	private Reports rep = new Reports();
	OrderItemsBLL oiBll = new OrderItemsBLL();
	OrderTotalBLL oBll = new OrderTotalBLL();
	ClientBLL cBll = new ClientBLL();
	ProdusBLL pBll = new ProdusBLL();
	OrderTotal or = new OrderTotal();
	
	/**
	 * Aceasta metoda este folosita pentru a crea clientul pe care ulterior il inseram
	 * @param nume numele clientului cautat 
	 * @param adr adresa acestuia
	 */
	void insertClient(String nume, String adr) {
	  	Client c = new Client(i++, nume, adr);
		ClientBLL cBll = new ClientBLL();
		Client cl = cBll.findClientByName(nume);
		if(cl == null)
		   cBll.insertClient(c);
		else
			System.out.println("Clientul cu numele "+ nume + " exista deja!");
		
	}
	/**
	 * Aceasta metoda este folosita pentru a sterge clientul specificat
	 * @param nume numele 
	 * @param adr adresa clientului pe care vrem sa il stergem
	 */
	 void deleteClient(String nume, String adr) {
		ClientBLL cBll = new ClientBLL();
		Client c = cBll.findClient(nume, adr);
		cBll.deleteClient(c);
		
	}
	 
		/**
		 * Aceasta metoda este folosita pentru a crea produsul pe care ulterior il inseram
		 * @param den numele produsului dorit
		 * @param cant cantitatea produsului dorit
		 * @param pret pretul produsului dorit
		 */
	 void insertProduct(String den, String cant, String pret) {
		Produs p = new Produs(den,Integer.parseInt(cant), Float.parseFloat(pret));
		ProdusBLL pBll = new ProdusBLL();
		Produs p1 = pBll.findProdus(den);
		if(p1 == null) 
		   pBll.insertProduct(p);
		else 
			if ( p1.getPret() == Integer.parseInt(pret)) {
			      pBll.updateCantitate(Integer.parseInt(cant) + p1.getCantitate(), den);
			}
			else {
				System.out.println("NU PUTETI INTROCUDE UN PRODUS CU ACEEASI DENUMIRE SI PRET DIFERIT DEOARECE DENUMIREA E UNICA!\n");
				
			}
	}
	
	 /**
		 * Aceasta metoda este folosita pentru a sterge produsul specificat
		 * @param den denumirea produsului pe care vrem sa il stergem
		 */
	 void deleteProduct(String den) {
		ProdusBLL pBll = new ProdusBLL();
		Produs p = pBll.findProdus(den);
		pBll.deleteProduct(p);	
	}

	 /**
		 * Aceasta metoda este folosita pentru a elimina duplicatele in caz ca un client comanda aceleasi produse intr-un singur order
		 * @param den sirul de denumiri ale produselor 
		 * @param cant  sirul de cantitati
		 * @return sirurile fara duplicate si cantitatea actualizata
		 */ 
	    public static TwoArrays removeDuplicates(ArrayList<String> den, ArrayList<String> cant) 
	    { 

	        ArrayList<String> newList = new ArrayList<String>(); 
	        ArrayList<String> newCant = new ArrayList<String>(); 
	          
	        for(int i = 0; i < den.size(); i++) {
	        	if(!newList.contains(den.get(i))) {
	        		newList.add(den.get(i));
	        		newCant.add(cant.get(i));
	        	}
	        	else {
	        		int poz = newList.indexOf(den.get(i));
	        		int rez = Integer.parseInt(cant.get(i)) + Integer.parseInt(cant.get(poz));
	        		newCant.set(poz, String.valueOf(rez));
	        	}
	        }
	        
	      return new TwoArrays(newList, newCant);
	    } 
	    
	    
		/**
		 * Aceasta metoda este folosita pentru a crea o noua comanda si a genera factura in caz ca este valida, respectiv mesaj de eroare daca nu e
		 * @param nume numele clientului cautat
		 * @param den denumirea produsului dorit 
		 * @param cant cantitatea produsului
		 */    
	  void createOrder(String nume, ArrayList<String> den, ArrayList<String> cant) {
			if(cBll.findClientByName(nume) == null) {
				rep.creareFactura(or, 2);
				return;}
			for(int i = 0;i < den.size(); i++) {
			if(pBll.findProdus(den.get(i)) == null) {
				rep.creareFactura(or, 3); 
				return;}}
		    TwoArrays rez = removeDuplicates(den, cant);	
		    den = rez.a; cant = rez.b;
			ArrayList<Integer> val = new ArrayList<Integer>();
			for(int i = 0;i < den.size(); i++) {
				val.add (pBll.findProdus(den.get(i)).getCantitate() - Integer.parseInt(cant.get(i)));
				if(val.get(i) < 0) { 
					rep.creareFactura(or, 1);	
					return; 
			}}
			for(int i = 0;i < den.size(); i++) 
					pBll.updateCantitate(val.get(i), den.get(i));
			for(int i = 0;i < den.size(); i++) {
			    OrderItems oi = new OrderItems(j++, nume, den.get(i), Integer.parseInt(cant.get(i)));	
			    oiBll.insertOrderItems(oi);}		
				Client c = cBll.findClientByName(nume);	
				float pret = 0;
				String denumire = "";
				String cantitate = "";
				for(int i = 0;i < den.size(); i++) {
					pret = pret + Integer.parseInt(cant.get(i))*pBll.findProdus(den.get(i)).getPret();
					denumire = denumire + den.get(i) + " ";
					cantitate = cantitate + cant.get(i) + " ";	}
				OrderTotal o = new OrderTotal(k++, nume, c.getAdresa(), denumire, cantitate, pret);
				oBll.insertOrderTotal(o);
				rep.creareFactura(o, 0);
	  }
}
