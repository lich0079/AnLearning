/**
 * created on Feb 4, 2010 1:53:23 PM by lich0079@gmail.com
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

/**
 * @author lich0079@gmail.com
 *
 */
public class WhitePeople extends People {

    /**
     * 
     */
    public WhitePeople() {
    }
    
    public WhitePeople(Language language){
        this.language=language;
    }    
    
    @Override
    void speak() {
        System.out.println("I'm white");
        language.speak();
    }

}