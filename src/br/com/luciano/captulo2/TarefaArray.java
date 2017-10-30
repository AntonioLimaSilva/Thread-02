package br.com.luciano.captulo2;

import java.security.SecureRandom;
import java.util.Arrays;

class TarefaArray implements Runnable {

	private final Array array;
	private final int startValue;

	TarefaArray(Array array, int value) {
		this.array = array;
		this.startValue = value;
	}
	
	@Override
	public void run() {
		for(int i = startValue; i < startValue + 3; i++)
			array.add(i);
	}

}