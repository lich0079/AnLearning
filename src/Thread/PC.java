package Thread;

import java.util.Timer;
import java.util.TimerTask;

public class PC {
    int count;
    int max = 20;
    boolean start = true;
    protected Object mutex = new Object();
//    private Timer timer=new Timer();

    public void produce() {
        count++;
        System.out.println("produce 1 ,total = " + count);
    }

    public void consume() {
        count--;
        System.out.println("consume 1 ,total = " + count);
    }

    public void makeProducer() {
        (new Thread() {
            public void run() {
                synchronized (mutex) {
                    while (start) {
                        if (count < max) {
                            produce();
                            mutex.notifyAll();
                        } else {
                            System.out.println("max");
                            try {
                                mutex.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                System.out.println("makeProducer  end ");
            }
        }).start();
    }

//    public void makeProducer2() {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                synchronized (mutex) {
//                    if (count < max) {
//                        produce();
//                        mutex.notifyAll();
//                    } else {
//                        System.out.println("max");
//                        try {
//                            mutex.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }, 0,50);
//    }
//
//    public void makeConsumer2() {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                synchronized (mutex) {
//                    if (count > 0) {
//                        consume();
//                        mutex.notifyAll();
//                    } else {
//                        System.out.println("none");
//                        try {
//                            mutex.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }, 0,50);
//    }

    public void makeConsumer() {
        (new Thread() {
            public void run() {
                synchronized (mutex) {
                    while (start) {
                        if (count > 0) {
                            consume();
                            mutex.notifyAll();
                        } else {
                            System.out.println("none");
                            try {
                                mutex.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                System.out.println("makeConsumer  end ");
            }
        }).start();
    }

    public void stop() {
        start = false;
    }

    public static void main(String[] args) throws Exception {
        PC a = new PC();
        a.makeProducer();
        a.makeConsumer();
        Thread.sleep(10000);
        a.stop();
        System.out.println("===============");
    }
}
