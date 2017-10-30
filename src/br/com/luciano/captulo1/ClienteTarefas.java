package br.com.luciano.captulo1;

import java.net.Socket;
import java.util.*;
import java.io.*;

class ClienteTarefas {	
	public static void main(String ...args) throws Exception {
		Socket socket = new Socket("localhost", 12345);
		System.out.println("Conexao Estabelecida");

		PrintStream saida = new PrintStream(socket.getOutputStream());
		for(int i = 0; i < 10; i++)
			saida.println("V1");

		Scanner entrada = new Scanner(System.in);
		entrada.nextLine();

		saida.close();
		entrada.close();
		socket.close();
	}
}