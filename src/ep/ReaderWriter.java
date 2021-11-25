package ep;

import java.util.concurrent.Semaphore;

class ReaderWriter{
    static int readCount = 0; //USAR VOLATILE SE NECESSARIO https://codereview.stackexchange.com/questions/127234/reader-writers-problem-using-semaphores-in-java
    static Semaphore readLock = new Semaphore(1);
    static Semaphore writeLock = new Semaphore(1);


    static class Reader implements Runnable {
        int activeReaders = 0;
        boolean hasWriter = false;
        BD db;
        char implementacao;

        public Reader(BD entry_db, char implementacao1){
            db = entry_db;
            implementacao = implementacao1;
        }

        @Override
        public void run() {
            if(implementacao=='t'){
                start_reading();

                // acessos ao bd
                db.acessosAleatoriosReader();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stop_reading();
                return;
            } else {
                //LOCK LOCKS

                //acessos ao bd
                db.acessosAleatoriosReader();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //UNLOCK LOCKS
            }
        }

        synchronized public void start_reading(){ //SYNCRHONIZED???
            while(!this.hasWriter){
                try{
                    wait();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.activeReaders++;
        }

        synchronized public void stop_reading(){
            activeReaders--;
            notifyAll();
        }


    }

    static class Writer implements Runnable {
        BD db;
        char implementacao;
        public Writer(BD entry_db, char implementacao1){
            db = entry_db;
            implementacao = implementacao1;
        }

        @Override
        public void run() {
            //if(implementacao=='t'){
             //   start_writting();
             //   return;
            //} else {
                //LOCK LOCKS

                //acessos ao bd

                //UNLOCK LOCKS
           // }
        }
    }

}

// VEIO DAQUI https://codereview.stackexchange.com/questions/127234/reader-writers-problem-using-semaphores-in-java
