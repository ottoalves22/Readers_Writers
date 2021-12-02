package ep;

import java.io.IOException;
import java.util.Random;

public class Main {
    static Thread[] threads;

    public static void main(String[] args) throws IOException, InterruptedException  {
        BD leitor_txt = new BD();
        System.out.println("Lendo BD...");
        leitor_txt.lerBd();
        int proporcao = 100;
        double media = 0;
        long tempoInicial = 0;
        long tempoFinal;
        long tempoTotalExecucao = 0;

        for(int i=0; i<2; i++) {
            System.out.println("\nImplementação: " + (i+1));
            for (int j = 0; j < proporcao; j++) {
                for (int k = 0; k < 50; k++) {
                    //implementacao um
                    leitor_txt.resetText();
                    char c = 'c';
                    if(i==0) c = 't'; //T de thread
                    else c = 'l'; // Lde lock
                    createThreads(j, leitor_txt, c);

                    tempoInicial = System.currentTimeMillis();
                    for (Thread value : threads) {
                        value.start();
                    }
                    for (Thread thread : threads) {
                        thread.join();
                    }

                    tempoFinal = System.currentTimeMillis();
                    media += tempoFinal - tempoInicial;
                    tempoTotalExecucao += tempoFinal - tempoInicial;
                }
                media /= 50;
                System.out.format("%.3f%n", media);
            }
            System.out.println("Tempo Total de Execução: " + tempoTotalExecucao);
        }
    }

    public static void createThreads(int proportion, BD entry_db, char implementacao) {
        threads = new Thread[100];
        Random rand = new Random();
        int aux_rand;

        for(int i=0; i<proportion; i++){
            aux_rand = rand.nextInt(100);
            if(threads[aux_rand] == null){
                threads[aux_rand] = new Thread(new Writer(entry_db, implementacao));
            }
        }

        for(int i=0; i<100; i++){ // preenche o coiso com os threads pra cada classe
            if(threads[i] == null){
                threads[i] = new Thread(new Reader(entry_db, implementacao));
            }
        }
        rand(threads, threads.length); // embaralha as threads
    }

    static void rand(Thread[] array, int a) {
        Random rd = new Random();

        for (int i = a-1; i > 0; i--) {
            int j = rd.nextInt(i+1);
            Thread temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
