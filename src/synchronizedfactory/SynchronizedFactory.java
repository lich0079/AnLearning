package synchronizedfactory;

public class SynchronizedFactory {
    private static SynchronizedFactory instance;

    private SynchronizedFactory() {
        System.out.println("SynchronizedFactory init");
    }

    public static synchronized SynchronizedFactory getInstance() {
       // System.out.println("SynchronizedFactory getInstance()");
        if (instance == null) {
            instance = new SynchronizedFactory();

        }
        return instance;
    }
}
