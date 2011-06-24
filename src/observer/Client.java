/**
 * created on Feb 4, 2010 10:27:57 AM by lich0079@gmail.com
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
package observer;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import junit.framework.TestCase;

/**
 * @author lich0079@gmail.com
 * 
 */
public class Client extends TestCase {
    public void test1() throws Exception {
        Model model = new Model();
        model.addObserver(new Observer() {

            public void update(Observable o, Object arg) {
                System.out.println(o + " is changed");
            }

        });

        model.setAge(11);
    }

    public void testPCS() throws Exception {
        Node a = new Node();
        a.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("change-------------");
                System.out.println("evt class  "+evt.getClass().getName());
                System.out.println( "evt source  "+evt.getSource());
                System.out.println(evt.getPropertyName() + "  " + evt.getOldValue() + "  " + evt.getNewValue());
                System.out.println("evt PropagationId "+evt.getPropagationId());
                if (evt instanceof IndexedPropertyChangeEvent) {
                    System.out.println("index   "+((IndexedPropertyChangeEvent) evt).getIndex() );

                }
                System.out.println("---------------");
            }

        });
        a.addPropertyChangeListener("name", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("888888888888property name change88888888888888");
            }
        });
        a.addPropertyChangeListener("id", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("888888888888property id change88888888888888");
            }
        });
        a.setId(222);
        a.setId(333);
        a.setAddress("bj");
        a.setAddress("sh");
        // a.setName("first");
        // a.setName("second");
    }
}
