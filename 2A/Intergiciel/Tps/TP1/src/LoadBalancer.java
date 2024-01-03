
import java.net.*;
import java.util.Random;
import java.io.*;

public class LoadBalancer extends Thread {
	
	static String hosts[] = {"localhost", "localhost"};
	static int ports[] = {8081,8082};
	static int nbHosts = 2;
	static Random rand = new Random();
	private Socket clientSocket;
	private static int loadBalancerPort = 8080;
	private static ServerSocket loadBalancerSocket;
	
	private LoadBalancer(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	public void run() {
		
		byte[] buffer = new byte[2048];
		int nbLuDeClient = 0;
		int nbLuDeC = 0;


	         try {
	      
	             // Initialiser la socket sortie du load balancer
	        	 int randomHost = rand.nextInt(nbHosts);
	        	 Socket output = new Socket(hosts[randomHost], ports[randomHost]);
	        	 
	        	 // Récupérer la data du client
	        	 InputStream inputClient = this.clientSocket.getInputStream();
	        	 OutputStream outputClient = this.clientSocket.getOutputStream();
	        	 
	        	// Récupérer la data de la comanche
	        	 InputStream inputComanche = output.getInputStream();
	        	 OutputStream outputComanche = output.getOutputStream();
	            
	        	 // Lire la data du client
	        	 nbLuDeClient = inputClient.read(buffer);
	        	 
	        	 // Ecrire sur la sortie
	        	 outputComanche.write(buffer, 0, nbLuDeClient);   	 
	        	 
	     
	        	 // Lire la date de C
	        	 nbLuDeC = inputComanche.read(buffer);
	        	 
	        	 // Ecrire sur la sortuie vers le client
	        	 outputClient.write(buffer, 0, nbLuDeC);
	        	 
	        	 // Fermer l'output
	        	 output.close();
	        	 this.clientSocket.close();
	             
	         } catch (Exception e) {
	            e.printStackTrace();
	
	         }
	     
	}
	
	public static void main(String [] args) {
		
	      // Initialiser le loadBalancer
		try {
			loadBalancerSocket = new ServerSocket(loadBalancerPort);
			loadBalancerSocket.setSoTimeout(10000);
			
			while (true) {
				// Attendre une connection
				Socket lBSocket = loadBalancerSocket.accept();
			
				// Instantiation du load balancer
				LoadBalancer a = new LoadBalancer(lBSocket);
				a.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
