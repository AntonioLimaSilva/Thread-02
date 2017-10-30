package br.com.luciano.captulo1;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

class ServidorTarefas {	
	public static void main(String ... args) throws Exception {
		System.out.println("---- Iniciando Servidor ----");
		ServerSocket servidor = new ServerSocket(12345);

		//ExecutorService threadPool = Executors.newFixedThreadPool(2);
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		//ExecutorService threadPool = Executors.newCachedThreadPool();

		while(true) {
			Socket socket = servidor.accept();
			System.out.println("Aceitando novo cliente na porta: " + socket.getPort());
			threadPool.execute(new DistribuirTarefas(socket));
		}
	}
}