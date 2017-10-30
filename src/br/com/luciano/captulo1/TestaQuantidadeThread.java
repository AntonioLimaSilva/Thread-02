package br.com.luciano.captulo1;

import java.util.*;

class TestaQuantidadeThread {	
	public static void main(String ... args) throws Exception {
		Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();

		for (Thread thread : todasAsThreads) {
   			System.out.println(thread.getName());
		}

		//Aqui podemos descobrir quantos processadores temos disponível
		Runtime runtime = Runtime.getRuntime();
		int qtdProcessadores = runtime.availableProcessors();
		System.out.println("Qtd de processadores: " + qtdProcessadores);
	}
}