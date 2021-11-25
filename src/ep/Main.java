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
        int proporcao = 100;
        int media = 0;

            for(int i=0; i<=1; i++){
                for(int j=0; j<proporcao; j++){
                    for(int k=0; k<50; k++){
                        if(i==0){ // implementacao um
                            leitor_txt.lerBd();

                            createThreads(j, leitor_txt);

                            long tempoInicial = System.currentTimeMillis();

                            for(Thread thread : threads){
                                thread.start();
                            }
                            for(Thread thread : threads){
                                thread.join();
                            }
                            long tempoFinal = System.currentTimeMillis();

                            media += tempoFinal - tempoInicial;
                        } else { //implementacao dois
                            System.out.println("Imp 2");
                        }
                    }
                }
            }
    }


    public static void createThreads(int proportion, BD entry_db) throws FileNotFoundException {
        threads = new Thread[100];
        Random rand = new Random();
        int aux_rand;
        for(int i=0; i<proportion; i++){
            aux_rand = rand.nextInt(100);
            if(threads[aux_rand] == null){
                threads[aux_rand] = new Thread(new Writer(entry_db));
                //System.out.print("Escreveu o Writer");
                //System.out.println(aux_rand);
            }
        }
        for(int i=0; i<100; i++){ // preenche o coiso com os threads pra cada classe
            if(threads[i] == null){
                threads[i] = new Thread(new Reader(entry_db));
                //System.out.print("Escreveu o Reader ");
                //System.out.println(i);
            }
        }
    }

}
