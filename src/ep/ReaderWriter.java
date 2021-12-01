//package ep;
//
//
//
//class ReaderWriter{
//    private static volatile int activeReaders = 0;
//    private static volatile boolean hasWriter = false;
//
//    public static boolean getter_has_writer(){ return hasWriter; }
//
//    public static void setter_has_writer(boolean entry){
//        hasWriter = entry;
//    }
//
//    public static int getter_activer_readers(){
//        return activeReaders;
//    }
//
//    public static void setter_active_readers(int entry){
//        activeReaders = entry;
//    }
//
//    static class Reader implements Runnable {
//        BD db;
//        char implementacao;
//        Locker locker = new Locker();
//
//        public Reader(BD entry_db, char implementacao1){
//            db = entry_db;
//            implementacao = implementacao1;
//        }
//
//        @Override
//        public void run() {
//            if(implementacao=='t'){
//                //System.out.println("COMECOU READER");
//                start_reading();
//                // acessos ao bd
//                db.acessosAleatoriosReader();
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                stop_reading();
//                //System.out.println("ACABOU READER");
//
//
//            } else {
//                //LOCK LOCKS
//                //System.out.println("COMECOU READER 2");
//
//                try{
//                    locker.lock();
//                } catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//
//                //acessos ao bd
//
//                db.acessosAleatoriosReader();
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                locker.unlock();
//                //System.out.println("ACABOU READER 2");
//
//
//                //UNLOCK LOCKS
//            }
//        }
//
//        synchronized public void start_reading(){ //SYNCRHONIZED???
//            while(getter_has_writer()){
//                try{
//                    wait();
//                } catch(InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            setter_active_readers(getter_activer_readers()+1);
//        }
//
//        synchronized public void stop_reading(){
//            setter_active_readers(getter_activer_readers()-1);
//            notifyAll();
//        }
//
//
//    }
//
//     static class Writer implements Runnable {
//        BD db;
//        char implementacao;
//        int activeReaders = 0;
//        boolean hasWriter = false;
//        Locker locker = new Locker();
//
//        public Writer(BD entry_db, char implementacao1){
//            db = entry_db;
//            implementacao = implementacao1;
//        }
//
//        @Override
//        public void run() {
//            if(implementacao=='t'){
//                //System.out.println("COMECOU WRITER");
//                start_writting();
//
//                db.acessosAleatoriosWriter();
//                try {
//                    Thread.sleep(1);
////                    System.out.println("sleepou o writers");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                stop_writting();
//                //System.out.println("ACABOU WRITER");
//            } else {
//                //System.out.println("COMECOU WRITER 2");
//
//                try{
//                    locker.lock();
//                } catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//
//                db.acessosAleatoriosWriter();
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                locker.unlock();
//                //System.out.println("TERMINOU WRITER 2");
//
//            }
//        }
//
//        synchronized public void start_writting(){
//            while(!(getter_activer_readers()==0 && !getter_has_writer())){
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            setter_has_writer(true);
//        }
//
//        synchronized public void stop_writting(){
//            setter_has_writer(false);
//            notifyAll();
//        }
//    }
//}
