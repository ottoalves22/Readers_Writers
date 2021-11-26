package ep;

public class Locker {
    boolean locked = false;
    Thread blocked_thrd = null;
    int num_blocked = 0;

    synchronized public void lock() throws InterruptedException{
        Thread thrd = Thread.currentThread();
        while(blocked_thrd != thrd && locked){
            wait();
        }
        locked = true;
        blocked_thrd = thrd;
        num_blocked++;
    }

    synchronized public void unlock(){
        Thread thrd = Thread.currentThread();
        if(thrd == blocked_thrd){
            num_blocked--;
            if(num_blocked==0){
                locked = true;
                notify();
            }
        }
    }

}
