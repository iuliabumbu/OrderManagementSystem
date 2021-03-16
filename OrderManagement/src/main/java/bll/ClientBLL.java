package bll;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import dao.ClientDAO;
import model.Client;
/**
 * Clasa utilizata pentru a apela metodele corespunzatoare din pachetul DAO incapsuleaza logica aplicatiei in cazul tabelului "client"
 * @author Bumbu Iulia-Diana
 * @since April 09, 2020
 */
public class ClientBLL {

	public Client findClient(String nume, String adresa) {
		Client cl = ClientDAO.findClient(nume,adresa);
		if (cl == null) {
			System.out.println("The client with name " + nume + " and address "+adresa+" was not found!");
		}
		return cl;
	}
	
	public Client findClientByName(String nume) {
		Client cl = ClientDAO.findClientByName(nume);
		if (cl == null) {
			System.out.println("The client with name " + nume + " was not found!");
		}
		return cl;
	}

	public int insertClient(Client client) {
		
		return ClientDAO.insert(client);
	}
	
    public int deleteClient(Client client) {
		
		return ClientDAO.delete(client);
	}
    
    public ArrayList<Client> findAllClients(){
    	return ClientDAO.findAll();
    }
}