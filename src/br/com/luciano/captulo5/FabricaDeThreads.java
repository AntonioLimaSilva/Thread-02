package br.com.luciano.captulo5;

import java.util.concurrent.*;

class FabricaDeThreads implements ThreadFactory {

	private static int numero;

    @Override
    public Thread newThread(Runnable tarefa) {
        Thread thread = new Thread(tarefa, "Thread Servidor Tarefas " + numero);
        numero++;
        thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
        return thread;
	}

}