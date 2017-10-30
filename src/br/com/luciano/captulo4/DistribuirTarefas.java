package br.com.luciano.captulo4;

import java.net.Socket;
import java.util.*;
import java.io.*;

class DistribuirTarefas implements Runnable {	
	
	private Socket socket;
	private ServidorTarefas servidor;
	
	DistribuirTarefas(Socket socket, ServidorTarefas servidor) {
		this.socket = socket;
		this.servidor = servidor;
	}

	public void run() {
		try { 
			System.out.println("Distribuindo as tarefas para o cliente " +  socket);
			Scanner entradaCliente = new Scanner(socket.getInputStream());
			PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

			while(entradaCliente.hasNextLine()) {
				String comando = entradaCliente.nextLine();
				System.out.println(comando);

				switch(comando) {
					case "C1":
						saidaCliente.println("Confirmacao C1");
						break;
					case "C2":
						saidaCliente.println("Confirmacao C2");
						break;
					case "C3":
						System.out.println("Confirmacao C1");
						break;
					case "FIM":
						System.out.println("Servidor Finalizado");
						this.servidor.parar();
						break;
					default:
						saidaCliente.println("Nao confere!");
				}
			}
			saidaCliente.close();
			entradaCliente.close();   
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
	}	
	
}