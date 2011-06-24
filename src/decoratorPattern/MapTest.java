/**
 * created on Jan 22, 2010 10:05:26 AM by lich0079@gmail.com
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
package decoratorPattern;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author lich0079@gmail.com
 *
 */
public class MapTest extends TestCase{
    
    /**
     * test if the orgin map is not the same instance to the synchronized one
     * @throws Exception
     */
    public void testSynchronizedMap() throws Exception {
        Map container=new HashMap();
        Map origin=new HashMap();
        container.put("origin", origin);
        Map synchronizedone=Collections.synchronizedMap(origin);
        Map get=(Map) container.get("origin");
        assertSame(origin, get);
        assertNotSame(origin, synchronizedone);
    }
}
