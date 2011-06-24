/**
 * created on Jan 26, 2010 9:25:22 PM by lich0079@gmail.com
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
package innerClass;

/**
 * @author lich0079@gmail.com
 * 
 */
public class ActionManager {
    
    public static final IAction work=new WorkAction();
    public static final IAction speak=new SpeakAction();
    
    private static class WorkAction extends Person implements IAction{
        public void action(){
            sleep();
            System.out.println("work");
        }
    }
    
    private static class SpeakAction extends Person  implements IAction{
        public void action(){
            sleep();
            System.out.println("speak");
        }
    }
}
