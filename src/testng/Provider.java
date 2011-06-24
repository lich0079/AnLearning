/**
 * created on Apr 20, 2010 12:28:25 AM by lich0079@gmail.com
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
package testng;

import org.testng.annotations.DataProvider;

/**
 * @author lich0079@gmail.com
 * 
 */
public class Provider {
    @DataProvider(name = "create")
    public static Object[][] createData() {
        return new Object[][] { new Object[] { new Integer(42) }, new Object[] { new Integer(11) } };
    }
}
