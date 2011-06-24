/**
 * created on Mar 16, 2010 4:07:29 PM by lich0079@gmail.com
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
package platformIndependentChar;

import java.io.File;

/**
 * 不同平台的分割符 换行符是不同的
 * @author lich0079@gmail.com
 * 
 */
public class Client {
    public static void main(String[] args) {
       String  line_separator=System.getProperty("line.separator");
       String pathSeparator=File.pathSeparator;
       String separator=File.separator;
       System.out.println(line_separator+"     "+separator+"   "+pathSeparator);
    }
}
