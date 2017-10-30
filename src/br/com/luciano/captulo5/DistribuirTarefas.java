package br.com.luciano.captulo5;

import java.net.Socket;
import java.util.*;
import java.io.*;
import java.util.concurrent.*;

class DistribuirTarefas implements Runnable {	
	
	private Socket socket;
	private ServidorTarefas servidor;
	private ExecutorService threadPool;
	
	DistribuirTarefas(ExecutorService threadPool, Socket socket, ServidorTarefas servidor) {
		this.threadPool = threadPool;
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
						ComandoC1 c1 = new ComandoC1(saidaCliente);
						this.threadPool.execute(c1);
						break;
					case "C2":
						saidaCliente.println("Confirmacao C2");
						ComandoC2 c2 = new ComandoC2(saidaCliente);
						this.threadPool.execute(c2);
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