/**
 * created on Apr 20, 2010 12:21:17 AM by lich0079@gmail.com
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
import org.testng.annotations.Test;

/**
 * @author lich0079@gmail.com
 * 
 */
public class DataProviderTest {

    @DataProvider(name = "test1")
    public Object[][] createData1() {
        return new Object[][] { { "Cedric", new Integer(36) }, { "Anne", new Integer(37) }, };
    }

    // This test method declares that its data should be supplied by the Data
    // Provider
    // named "test1"
    @Test(dataProvider = "test1", invocationCount = 3)
    public void verifyData1(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }

    @Test(dataProvider = "create", dataProviderClass = Provider.class)
    public void test(Integer n) {
        System.out.println("n = "+n);
    }

}
