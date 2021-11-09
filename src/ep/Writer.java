package ep;

import java.util.concurrent.Semaphore;

public class Writer implements Runnable{
    static Semaphore writeLock = new Semaphore(1);
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

// VEIO DAQUI https://codereview.stackexchange.com/questions/127234/reader-writers-problem-using-semaphores-in-java
