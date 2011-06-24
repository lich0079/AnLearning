/**
 * created on Mar 1, 2010 4:45:08 PM by lich0079@gmail.com
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
package forEach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.testng.annotations.Test;

/**
 * 测试 对于ArrayList的iterator遍历和for遍历的效率问题 注： 这个测试结果对其他容器类不一定成立 比如LinkedList
 * 
 * @author lich0079@gmail.com
 * 
 */
public class IteratorTest {

    static int size = 1000000;
    static List<Object> list1 = new ArrayList<Object>();
    static List<Object> list2 = new ArrayList<Object>();
    static {
        for (int i = 0; i < size; i++) {
            list1.add(new Object());
            list2.add(new Object());
        }
    }

    @Test
    public void iteratorTest() {
        long start = System.currentTimeMillis();
        for (Iterator<Object> iterator = list1.iterator(); iterator.hasNext();) {
            Object entry = (Object) iterator.next();
            // System.out.print(entry);
        }
        System.out.println("\niterator " + (System.currentTimeMillis() - start) + " ms");
    }

    @Test(dependsOnMethods = "iteratorTest")
    public void forTest() {
        long start = System.currentTimeMillis();
        for (int i = 0, size = list2.size(); i < size; i++) {
            // 这里是一次性求出size() ，假如在遍历的过程中list的内容有增减的话，遍历逻辑会出错
            Object entry = list2.get(i);
            // System.out.print(entry);
        }
        System.out.println("\nfor " + (System.currentTimeMillis() - start) + " ms");
    }

    @Test(groups = "alone", invocationCount = 10)
    public void compareTest() {
        long start = System.currentTimeMillis();
        for (Iterator<Object> iterator = list1.iterator(); iterator.hasNext();) {
            Object entry = (Object) iterator.next();
            // System.out.print(entry);
        }
        long time = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        for (int i = 0, size = list2.size(); i < size; i++) {
            Object entry = list2.get(i);
            // System.out.print(entry);
        }
        long time2 = System.currentTimeMillis() - start;
        System.out.println("iterator " + time + " ms, for " + time2 + " ms");
    }
}
