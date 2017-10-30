package br.com.luciano.captulo2;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class Principal {

	public static final void main(String args[]) {

		Array array = new Array(6);

		TarefaArray tarefa1 = new TarefaArray(array, 1);
		TarefaArray tarefa2 = new TarefaArray(array, 11);

		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(tarefa1);
		executorService.execute(tarefa2);

		executorService.shutdown();

		try {
			boolean taskEnded = executorService.awaitTermination(1, TimeUnit.MINUTES);
			//Tarefa terminou
			if(taskEnded) {
				System.out.printf("%nContents of Array:%n");
				System.out.println(array);
			} else {
				System.out.println("Timed out while waiting for tasks to finish.");
			}
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}