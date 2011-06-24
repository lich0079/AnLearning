package synchronizedfactory;

public class UnsynchronizedFactory {
    private static UnsynchronizedFactory instance;

    private UnsynchronizedFactory() {
        System.out.println("UnsynchronizedFactory init");
    }

    public static UnsynchronizedFactory getInstance() {
        //System.out.println("UnsynchronizedFactory getInstance()");
        if (instance == null) {
            synchronized (UnsynchronizedFactory.class) {
                if (instance == null) {
                    instance = new UnsynchronizedFactory();
                }
            }
        }
        return instance;
    }
}
