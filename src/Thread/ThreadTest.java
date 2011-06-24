package Thread;

public class ThreadTest {
    int a;
    private boolean _maintenanceThreadStarted = true;

    public void task() {
        System.out.println(++a + "  " + Thread.currentThread().getId());
    }

    public void startThread() {
        (new Thread() {
            public void run() {
                while (_maintenanceThreadStarted) {
                    task();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void startServiceThread() {
        Runnable r = new Runnable() {
            public void run() {
                while (_maintenanceThreadStarted) {
                    task();
                    if (a > 100) {
                        _maintenanceThreadStarted = false;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        Thread _serviceThread = new Thread(r, "SessionPoolServer-RequestServiceThread");
        _serviceThread.start();
    }

    public void stopMaintenanceThread() {
        _maintenanceThreadStarted = false;
    }

    public static void main(String[] args) throws Exception {
        ThreadTest a = new ThreadTest();
        a.startThread();
        new ThreadTest().startServiceThread();
        Thread.sleep(5000);
        a.stopMaintenanceThread();
        System.out.println("end");
    }

}
