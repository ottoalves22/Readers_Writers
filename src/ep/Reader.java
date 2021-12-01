package ep;

public class Reader extends contexto implements Runnable {
    public BD db;
    public char implementacao;
    public Locker locker = new Locker();

    public Reader(BD entry_db, char implementacao1){
        this.db = entry_db;
        this.implementacao = implementacao1;
    }

    @Override
    public void run() {
        if(implementacao=='t'){
            //System.out.println("COMECOU READER");
            start_reading();
            // acessos ao bd
            this.db.acessosAleatoriosReader();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stop_reading();
            //System.out.println("ACABOU READER");


        } else {
            //LOCK LOCKS
            //System.out.println("COMECOU READER 2");

            try{
                this.locker.lock();
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            //acessos ao bd

            this.db.acessosAleatoriosReader();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.locker.unlock();
            //System.out.println("ACABOU READER 2");


            //UNLOCK LOCKS
        }
    }

    synchronized public void start_reading(){ //SYNCRHONIZED???
        while(getter_has_writer()){
            try{
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        setter_active_readers(getter_activer_readers()+1);
    }

    synchronized public void stop_reading(){
        setter_active_readers(getter_activer_readers()-1);
        notifyAll();
    }


}
