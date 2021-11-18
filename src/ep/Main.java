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
        for(Thread thread : threads){
            System.out.print("gang");
            thread.start();
            //System.out.println(leitor_txt.texto[i]);
        }

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

    //AQUI DA PRA MEXER MAIS, TA MUITO PARECIDO
    public void joinThreads() throws InterruptedException{
        int i = 0;
        while(i<threads.length){
            threads[i].join();
            i++;
        }
    }
}
