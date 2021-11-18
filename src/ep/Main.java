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
        leitor_txt.lerBd();
        int proporcao = 69;
        createThreads(proporcao);

            int media = 0;
            long tempoInicial = System.currentTimeMillis();

            //inicia a porra das threads
            for(Thread thread : threads){
                thread.start();
            }

            long tempoFinal = System.currentTimeMillis();

            //manda as malditas threads esperarem
            /*Segundo algum jao da alura; Quando a thread MAIN executa t1.join(),
            ela vai aguardar até o t1 terminar. Em outras palavras,
            com join() vc pode dizer para a
            thread esperar a finalização da outra. */
            for(Thread thread : threads){
                thread.join();
            }
            media += tempoFinal - tempoInicial;

    }


    public static void createThreads(int proportion) throws FileNotFoundException {
        threads = new Thread[100];
        Random rand = new Random();
        int aux_rand;
        for(int i=0; i<proportion; i++){
            aux_rand = rand.nextInt(100);
            if(threads[aux_rand] == null){
                threads[aux_rand] = new Thread(new Writer());
                //System.out.print("Escreveu o Writer");
                //System.out.println(aux_rand);
            }
        }
        for(int i=0; i<100; i++){ // preenche o coiso com os threads pra cada classe
            if(threads[i] == null){
                threads[i] = new Thread(new Reader());
                //System.out.print("Escreveu o Reader ");
                //System.out.println(i);
            }
        }
    }

}
