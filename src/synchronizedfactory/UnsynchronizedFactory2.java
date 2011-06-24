package synchronizedfactory;

public class UnsynchronizedFactory2 {
    private static UnsynchronizedFactory2 instance = new UnsynchronizedFactory2() ;

    private UnsynchronizedFactory2() {
        System.out.println("UnsynchronizedFactory2 init");
    }

    public static UnsynchronizedFactory2 getInstance() {
       // System.out.println("UnsynchronizedFactory2 getInstance()");
        return instance;
    }
}
