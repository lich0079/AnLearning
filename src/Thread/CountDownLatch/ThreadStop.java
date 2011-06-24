/**
 * created on Jan 28, 2010 9:46:12 AM by lich0079@gmail.com
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
public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        int threadNumber = 3;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
        for (int i = 0; i < threadNumber; i++) {
            final int threadID = i;
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("threadID:[%s] finished!!", threadID));
                    countDownLatch.countDown();
                }
            }.start();
        }

        countDownLatch.await();
        System.out.println("main thread finished!!");
    }
}
