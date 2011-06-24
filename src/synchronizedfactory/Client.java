package synchronizedfactory;

import java.util.concurrent.CountDownLatch;

import junit.framework.TestCase;

public class Client extends TestCase {
    public static int N = 1000;
    static {
        SynchronizedFactory.getInstance();
        UnsynchronizedFactory.getInstance();
        UnsynchronizedFactory2.getInstance();

    }

    public void test1() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i)
            // create and start threads
            new Thread(new Worker(startSignal, doneSignal)).start();

        System.out.println("don't let run yet"); // don't let run yet
        long start = System.currentTimeMillis();
        startSignal.countDown(); // let all threads proceed
        System.out.println("startSignal.countDown()ed");
        doneSignal.await(); // wait for all to finish
        System.out.println("UnsynchronizedFactory2 complete " + (System.currentTimeMillis() - start));

    }

    public void test2() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i)
            // create and start threads
            new Thread(new Worker2(startSignal, doneSignal)).start();

        System.out.println("don't let run yet"); // don't let run yet
        long start = System.currentTimeMillis();
        startSignal.countDown(); // let all threads proceed
        System.out.println("startSignal.countDown()ed");
        doneSignal.await(); // wait for all to finish
        System.out.println("UnsynchronizedFactory complete " + (System.currentTimeMillis() - start));

    }

    public void test3() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i)
            // create and start threads
            new Thread(new Worker3(startSignal, doneSignal)).start();

        System.out.println("don't let run yet"); // don't let run yet
        long start = System.currentTimeMillis();
        startSignal.countDown(); // let all threads proceed
        System.out.println("startSignal.countDown()ed");
        doneSignal.await(); // wait for all to finish
        System.out.println("SynchronizedFactory complete " + (System.currentTimeMillis() - start));

    }

    class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 0;
            while (i < N) {
                doWork();
                doneSignal.countDown();
                i++;
            }
        }

        void doWork() {
            UnsynchronizedFactory2.getInstance();
        }
    }

    class Worker2 implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker2(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 0;
            while (i < N) {
                doWork();
                doneSignal.countDown();
                i++;
            }
        }

        void doWork() {
            UnsynchronizedFactory.getInstance();
        }
    }

    class Worker3 implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker3(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 0;
            while (i < N) {
                doWork();
                doneSignal.countDown();
                i++;
            }
        }

        void doWork() {
            SynchronizedFactory.getInstance();
        }
    }
}
