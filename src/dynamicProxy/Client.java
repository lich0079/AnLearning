/**
 * created on Jan 13, 2010 9:42:31 AM by lich0079@gmail.com
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
package dynamicProxy;

import junit.framework.TestCase;

/**
 * @author lich0079@gmail.com
 * 
 */
public class Client extends TestCase {
    public void testProxy() throws Exception {
        IPerson Warrior=new RealPerson("Warrior");
        IPerson Priest=new RealPerson("Priest");
        DynamicLogger log1=new DynamicLogger();
        DynamicLogger log2=new DynamicLogger();
        IPerson proxyWarrior=(IPerson) log1.newProxyObject(Warrior);
        IPerson proxyPriest=(IPerson) log2.newProxyObject(Priest);
        proxyWarrior.walk();
        proxyWarrior.speak();
        System.out.println("====");
        proxyPriest.walk();
        proxyPriest.speak();
    }
}
