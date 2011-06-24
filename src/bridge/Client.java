/**
 * created on Feb 4, 2010 1:56:49 PM by lich0079@gmail.com
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
package bridge;

import junit.framework.TestCase;

/**
 * @author lich0079@gmail.com
 * 
 */
public class Client extends TestCase {
    public void test1() {
        People black=new BlackPeople(new Chinese());
        black.speak();
        People black2=new BlackPeople(new English());
        black2.speak();
        
        System.out.println("=============");
        People white=new WhitePeople(new Chinese());
        white.speak();
        People white2=new WhitePeople(new English());
        white2.speak();
    }
}
