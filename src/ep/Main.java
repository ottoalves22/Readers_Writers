package ep;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import ep.ReaderWriter.Writer;
import ep.ReaderWriter.Reader;

public class Main {
    static Thread[] threads;



    public static void main(String[] args) throws IOException, FileNotFoundException, InterruptedException  {
        BD leitor_txt = new BD();
        int proporcao = 101;
        int media = 0;
        long tempoInicial = 0;
        long tempoFinal;

        for(int i=0; i<2; i++) {
            System.out.println("Implementação: " + (i+1));
            for (int j = 0; j < proporcao; j++) {
                for (int k = 0; k < 50; k++) {
                    //implementacao um
                    leitor_txt.lerBd();
                    char c = 'c';
                    if(i==0) c = 't'; //T de thread
                    else c = 'l'; // Lde lock
                    createThreads(j, leitor_txt, c);
                    tempoInicial = System.currentTimeMillis();
                    for (Thread thread : threads) {
                        thread.start();
                    }
                    for (Thread thread : threads) {
                        thread.join(); // aqui ta foda
                    }
                    tempoFinal = System.currentTimeMillis();
                    media += tempoFinal - tempoInicial;

                }
                media /= proporcao;
                System.out.println("Média - escritores: " + j + " e leitores: " + (100 - j) + " - " + media);
            }
            tempoFinal = System.currentTimeMillis();
            System.out.println("A execução levou " + ((tempoFinal - tempoInicial) / 60000) + " minutos");
        }
    }


    public static void createThreads(int proportion, BD entry_db, char implementacao) throws FileNotFoundException {
        threads = new Thread[100];
        Random rand = new Random();
        int aux_rand;
        for(int i=0; i<proportion; i++){
            aux_rand = rand.nextInt(100);
            if(threads[aux_rand] == null){
                threads[aux_rand] = new Thread(new Writer(entry_db, implementacao));
                //System.out.print("Escreveu o Writer");
                //System.out.println(aux_rand);
            }
        }
        for(int i=0; i<100; i++){ // preenche o coiso com os threads pra cada classe
            if(threads[i] == null){
                threads[i] = new Thread(new Reader(entry_db, implementacao));
                //System.out.print("Escreveu o Reader ");
                //System.out.println(i);
            }
        }
    }

}
