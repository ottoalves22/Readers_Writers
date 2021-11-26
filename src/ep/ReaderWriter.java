package ep;

class ReaderWriter{
    static class Reader implements Runnable {
        int activeReaders = 0;
        boolean hasWriter = false;
        BD db;
        char implementacao;
        Locker locker = new Locker();

        public Reader(BD entry_db, char implementacao1){
            db = entry_db;
            implementacao = implementacao1;
        }

        @Override
        public void run() {
            //System.out.println("rodou reader");
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
                try{
                    locker.lock();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

                //acessos ao bd
                db.acessosAleatoriosReader();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                locker.unlock();
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
        int activeReaders = 0;
        boolean hasWriter = false;
        Locker locker = new Locker();

        public Writer(BD entry_db, char implementacao1){
            db = entry_db;
            implementacao = implementacao1;
        }

        @Override
        public void run() {
            //System.out.println("rodou writer");
            if(implementacao=='t'){
                start_writting();

                db.acessosAleatoriosWriter();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                stop_writting();
                return;
            } else {
                //LOCK LOCKS
                try{
                    locker.lock();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

                db.acessosAleatoriosWriter();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                locker.unlock();
                //UNLOCK LOCKS
            }
        }

        synchronized public void start_writting(){
            while(activeReaders != 0 && hasWriter){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            hasWriter = true;
        }

        synchronized public void stop_writting(){
            hasWriter = false;
            notifyAll();
        }
    }
}
