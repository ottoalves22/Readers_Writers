package ep;

public class Writer extends contexto implements Runnable {
    public BD db;
    public char implementacao;
    public Locker locker = new Locker();

    public Writer(BD entry_db, char implementacao1){
        this.db = entry_db;
        this.implementacao = implementacao1;
    }

    @Override
    public void run() {
        if(implementacao=='t'){
            //System.out.println("COMECOU WRITER");
            start_writting();

            this.db.acessosAleatoriosWriter();
            try {
                Thread.sleep(1);
//                    System.out.println("sleepou o writers");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stop_writting();
            //System.out.println("ACABOU WRITER");
        } else {
            //System.out.println("COMECOU WRITER 2");

            try{
                this.locker.lock();
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            this.db.acessosAleatoriosWriter();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.locker.unlock();
            //System.out.println("TERMINOU WRITER 2");

        }
    }

    synchronized public void start_writting(){
        while(!(getter_activer_readers()==0 && !getter_has_writer())){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setter_has_writer(true);
    }

    synchronized public void stop_writting(){
        setter_has_writer(false);
        notifyAll();
    }
}