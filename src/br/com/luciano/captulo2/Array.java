package br.com.luciano.captulo2;

import java.security.SecureRandom;
import java.util.Arrays;

class Array {
	
	private static final SecureRandom generator = new SecureRandom();
	private final int[] array;
	private int index = 0;

	Array(int size) {
		this.array = new int[size];
	}

	/*
	*  Trabalhar com objetos mutáveis não é seguro caso o método não seja synchronized, pois havendo mais 
	*  de uma thread trabalhando, produzira resultados inconsistentes, é preciso que o método seja 
	*  synchronized, dessa forma a execução desse método será atômica, ou seja, é executado de uma vez só
	*/
	public synchronized void add(int value) {
		int position = index;

		try {
			Thread.sleep(generator.nextInt(500));
		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		this.array[position] = value;
		System.out.printf("%s wrote %2d to element %d.%n", 
			Thread.currentThread().getName(), value, position);

		++index;
		System.out.printf("Next write index: %d%n", index);
	}

	@Override
	public String toString() {
		return Arrays.toString(array);
	}
}