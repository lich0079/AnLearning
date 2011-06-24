/**
 * created on Apr 1, 2010 10:04:02 AM by lich0079@gmail.com
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
package algorithm.sort;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author lich0079@gmail.com
 * 
 */
public class Billion {
    public static Set<Integer> getTop100(int[] inputArray) {

        TreeSet<Integer> top100 = new TreeSet();
        for (int i = 0; i < inputArray.length; i++) {

            if (top100.size() < 100) {
                top100.add(inputArray[i]);
            } else if ((Integer) top100.first() < inputArray[i]) {
                Object obj = top100.first();
                top100.remove(obj);
                top100.add(inputArray[i]);
            }

        }

        return top100;

    }

    public static void main(String[] args) {

        int numberCount = 100000000;

        int maxNumber = numberCount;

        int inputArray[] = new int[numberCount];

        Random random = new Random();

        for (int i = 0; i < numberCount; ++i) {

            inputArray[i] = Math.abs(random.nextInt(maxNumber));

        }

        System.out.println("Sort begin...");

        long current = System.currentTimeMillis();

        Set<Integer> result = getTop100(inputArray);

        System.out.println("Spend time:" + (System.currentTimeMillis() - current));

    }
}
