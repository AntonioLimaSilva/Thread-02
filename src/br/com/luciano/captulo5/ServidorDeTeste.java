package br.com.luciano.captulo5;

/*
    Aqui existe um problema, onde as duas Thread estão em cache no proprio sistema operacional
    e ficam bloqueadas
*/
public class ServidorDeTeste {

    private volatile boolean estaRodando = false;

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTeste servidor = new ServidorDeTeste();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
        Thread tarefa = new Thread(new Runnable() {

            public void run() {
                System.out.println("Servidor comecando, estaRodando = " + estaRodando );

                while(!estaRodando) {}

                if(estaRodando)
                    throw new RuntimeException("Deu erro na thread...");

                System.out.println("Servidor rodando, estaRodando = " + estaRodando );

                while(estaRodando) {}

                System.out.println("Servidor terminando, estaRodando = " + estaRodando );
            }
        });

        // Aqui seria um tratamento eficiente de exceção
        tarefa.setUncaughtExceptionHandler(new TratadorDeExcecao());

        tarefa.start();

    }

    private void alterandoAtributo() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = true");
        estaRodando = true;

        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = false");
        estaRodando = false;        
    }
}