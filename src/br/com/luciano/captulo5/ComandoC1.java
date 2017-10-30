package br.com.luciano.captulo5;

import java.io.*;

class ComandoC1 implements Runnable {

	private PrintStream saida;

	public ComandoC1(PrintStream saida) {
		this.saida = saida;
	}

	public void run() {
		System.out.println("Executando comando c1");
		try {
            // faz algo bem demorado
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //devolvendo resposta para o cliente
        saida.println("Comando c1 executado com sucesso!");
	}
}