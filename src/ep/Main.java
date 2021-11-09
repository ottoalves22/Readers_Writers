package ep;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Main {
    public Thread[] threads;



    public static void main(String[] args) throws IOException, FileNotFoundException, InterruptedException  {
        BD leitor_txt = new BD();
        leitor_txt.lerBd();
        /*
        for (int i=0; i<100; i++){
            System.out.println(leitor_txt.texto[i]);
        }*/

    }



    public void createThreads(int proportion, int implementation) throws FileNotFoundException {
        this.threads = new Thread[100];
        Random rand = new Random();
        int aux_rand;
        for(int i=0; i<proportion; i++){
            aux_rand = rand.nextInt(101);
            if(this.threads[aux_rand] == null){
                this.threads[aux_rand] = new Thread(new Writer());
            }
        }
        for(int i=0; i<=100; i++){ // preenche o coiso com os threads pra cada classe
            if(this.threads[i] == null){
                this.threads[i] = new Thread(new Reader());
            }
        }
    }

    //AQUI DA PRA MEXER MAIS, TA MUITO PARECIDO
    public void joinThreads() throws InterruptedException{
        int i = 0;
        while(i<this.threads.length){
            this.threads[i].join();
            i++;
        }
    }
}
