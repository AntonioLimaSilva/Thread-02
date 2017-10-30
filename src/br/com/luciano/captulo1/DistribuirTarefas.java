package br.com.luciano.captulo1;

import java.net.Socket;
import java.util.*;

class DistribuirTarefas implements Runnable {	
	
	private Socket socket;
	
	DistribuirTarefas(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try { 
			System.out.println("Distribuindo as tarefas para o cliente " +  socket);
			Scanner entradaCliente = new Scanner(socket.getInputStream());
			
			while(entradaCliente.hasNextLine()) {
				String comando = entradaCliente.nextLine();
				System.out.println(comando);
			}
			    
			entradaCliente.close();   
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
	}	
	
}