/**
 * created on Jan 13, 2010 9:34:18 AM by lich0079@gmail.com
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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @author lich0079@gmail.com
 * 
 */
public class DynamicLogger implements InvocationHandler {

    private Object target;


    public Object newProxyObject(Object target) { // 在JVM中动态生成代理类
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public  DynamicLogger() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
     *      java.lang.reflect.Method, java.lang.Object[])
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        System.out.println("invoke method is "+method.getName());
        Object result = method.invoke(target, args);
        after();
        // System.out.println(proxy);
        return result;
    }

    private void before() {
        System.out.println("now is " +new Date());
    }

    private void after() {
        System.out.println("now is " +new Date());
    }
}
