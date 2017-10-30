package br.com.luciano.captulo3;

import java.net.Socket;
import java.util.*;
import java.io.*;

class ClienteTarefas {	
	public static void main(String ...args) throws Exception {
		Socket socket = new Socket("localhost", 12345);
		System.out.println("Conexao Estabelecida");

		Thread threadEnviaComando = new Thread(new Runnable(){
			public void run() {
				try {
					System.out.println("Pode enviar comandos!");
					PrintStream saida = new PrintStream(socket.getOutputStream());
					Scanner teclado = new Scanner(System.in);

					while(teclado.hasNextLine()) {
						String linha = teclado.nextLine();
						if(linha.trim().equals(""))
							break;
						saida.println(linha);
					}

					saida.close();
					teclado.close();
				} catch(IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
		
		Thread threadRecebeResposta = new Thread(new Runnable(){
			public void run() {
				try {
					System.out.println("Recebendo dados do servidor");

					Scanner respostaServidor = new Scanner(socket.getInputStream());

					while(respostaServidor.hasNextLine()) {
						String linha = respostaServidor.nextLine();
						System.out.println(linha);
					}

					respostaServidor.close();	
				} catch(IOException e) {
					throw new RuntimeException(e);
				}
			}		
		});
		


		threadEnviaComando.start();
		threadRecebeResposta.start();

		/*Aqui eu estou juntando a Thread main com a Thread Envia Comando, a Thread main deve esperar o termino
		* da ThreadEnviaComando, caso contrário havera uma exception, pelo fato da Thread main fechar o socket
		* faz com que a thread que executa espere até o outro acabar.
		*/
		threadEnviaComando.join();

		System.out.println("Fechando o socket do cliente");

		socket.close();
	}
}