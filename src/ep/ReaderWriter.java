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
        public Reader(BD entry_db){
            db = entry_db;
        }
        /* Precisa por o seguinte aqui no RUN
        *   if(implementacao == 1) leitorEescritor();
            else if(implementacao == 2)
                try {
                    naoLeitorEescritor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        * */

        @Override
        public void run() {
            start_reading();
            bdRandomAccess(db);
            stop_reading();
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

        public void bdRandomAccess(BD bd){
            bd.acessosAleatoriosReader();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class Writer implements Runnable {
        BD db;
        public Writer(BD entry_db){
            db = entry_db;
        }

        /*
        * Precisa por o seguinte no RUN segundo o EP do repositorio
        *   if(implementacao == 1) leitorEescritor();
            else if(implementacao == 2)
                try {
                    naoLeitorEescritor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        * */
        @Override
        public void run() {
            try {
                writeLock.acquire();
                System.out.println("Thread "+Thread.currentThread().getName() + " is WRITING");
                Thread.sleep(2500);
                System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");
                writeLock.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

// VEIO DAQUI https://codereview.stackexchange.com/questions/127234/reader-writers-problem-using-semaphores-in-java
