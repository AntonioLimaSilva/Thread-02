package br.com.luciano.captulo4;

import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.io.*;

class ServidorTarefas {	

	private ServerSocket servidor;
	private ExecutorService threadPool;

	//Uma outra de o cache dessa variavel
	private AtomicBoolean estaRodando;

	private ServidorTarefas() throws IOException {
		System.out.println("---- Iniciando Servidor ----");
		this.servidor = new ServerSocket(12345);
		this.threadPool = Executors.newCachedThreadPool();
		this.estaRodando = new AtomicBoolean(true);
	}

	public void rodar() throws IOException {
		while(this.estaRodando.get()) {
			try {
				Socket socket = this.servidor.accept();
				System.out.println("Aceitando novo cliente na porta " + socket.getPort());

				this.threadPool.execute(new DistribuirTarefas(socket, this));
			} catch(SocketException e) {
				System.out.println("SocketException, esta rodando? " + this.estaRodando);
			}
		}
	}

	public void parar() throws IOException {
		this.estaRodando.set(false);
		this.threadPool.shutdown();
		this.servidor.close();
	}

	public static void main(String ... args) throws Exception {
		ServidorTarefas servidor = new ServidorTarefas();
		servidor.rodar();
	}
}