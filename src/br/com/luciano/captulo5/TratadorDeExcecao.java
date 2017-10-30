package br.com.luciano.captulo5;

import java.lang.Thread.UncaughtExceptionHandler;

class TratadorDeExcecao implements UncaughtExceptionHandler {
	
	@Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Deu excecao na thread " + t.getName() + ", " + e.getMessage());
    }

}