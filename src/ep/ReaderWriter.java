package ep;

import java.util.concurrent.Semaphore;

class ReaderWriter{
    static int readCount = 0; //USAR VOLATILE SE NECESSARIO https://codereview.stackexchange.com/questions/127234/reader-writers-problem-using-semaphores-in-java
    static Semaphore readLock = new Semaphore(1);
    static Semaphore writeLock = new Semaphore(1);

    static class Reader implements Runnable {
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
            try {
                //Acquire Section
                readLock.acquire();
                readCount++;
                if (readCount == 1) {
                    writeLock.acquire();
                }
                readLock.release();

                //Reading section
                System.out.println("Thread "+Thread.currentThread().getName() + " is READING");
                Thread.sleep(1500);
                System.out.println("Thread "+Thread.currentThread().getName() + " has FINISHED READING");

                //Releasing section
                readLock.acquire();
                readCount--;
                if(readCount == 0) {
                    writeLock.release();
                }
                readLock.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    static class Writer implements Runnable {

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
