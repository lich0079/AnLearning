/**
 * created on Jan 28, 2010 10:07:20 AM by lich0079@gmail.com
 *
 * Copyright 2001-2010 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author lich0079@gmail.com
 * 
 */
public class Driver2 { // ...
    public static void main(String[] args) throws InterruptedException {
        int N = 5;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i)
            // create and start threads
            new Thread(new Worker(startSignal, doneSignal)).start();

        System.out.println("don't let run yet"); // don't let run yet
        startSignal.countDown(); // let all threads proceed
        System.out.println("startSignal.countDown()ed");
        doneSignal.await(); // wait for all to finish
        System.out.println("main down");
    }
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
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {
        } // return;
    }

    void doWork() {
        System.out.println("doWork");
    }
}
