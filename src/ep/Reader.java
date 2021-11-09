package ep;

import java.util.concurrent.Semaphore;

public class Reader implements Runnable{
    static int readCount = 0;
    static Semaphore readLock = new Semaphore(1);
    static Semaphore writeLock = new Semaphore(1);

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
            this.readLock.acquire();
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

// VEIO DAQUI https://codereview.stackexchange.com/questions/127234/reader-writers-problem-using-semaphores-in-java
