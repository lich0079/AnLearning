/**
 * created on Jan 13, 2010 9:19:26 AM by lich0079@gmail.com
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

/**
 * @author lich0079@gmail.com
 * 
 */
public class RealPerson implements IPerson {

    private String name;
    
    /**
     * @param name
     */
    public RealPerson(String name) {
        super();
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see dynamicProxy.IPerson#speak()
     */
    public void speak() {
        System.out.println(name+ " is speak");
    }

    /*
     * (non-Javadoc)
     * 
     * @see dynamicProxy.IPerson#walk()
     */
    public void walk() {
        System.out.println(name+ " is walk");
    }

}
