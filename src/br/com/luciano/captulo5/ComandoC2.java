package br.com.luciano.captulo5;

import java.io.*;

class ComandoC2 implements Runnable {
	
	private PrintStream saida;

	public ComandoC2(PrintStream saida) {
		this.saida = saida;
	}

	public void run() {
		System.out.println("Executando comando c2");
		try {
            // faz algo bem demorado
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("Exception no camando C2!!!");

        //devolvendo resposta para o cliente
        //saida.println("Comando c2 executado com sucesso!");
        
	}

}