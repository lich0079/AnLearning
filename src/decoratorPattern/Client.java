/**
 *  created on Jan 9, 2010
 */
package decoratorPattern;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author Bane
 * 
 */
public class Client extends TestCase {
    public void testDecorator() throws Exception {
        Map a=new HashMap();
        
        Component component=new CoreComponent();
        a.put("test", component);
        Component orgin=component;
        component.show();
        System.out.println("================"+component);
        
        component=decorateMan(component);
        component.show();
        System.out.println("================"+component);
        
        component=decorateWoman(component);
        component.show();
        System.out.println("================"+component);
        
        System.out.println("================"+orgin);
        Component value=(Component) a.get("test");
        
        assertNotSame(orgin, component);
        System.out.println("================"+value);//map放进去什么 取出来就是什么 
        assertSame(orgin, value);
    }

    /**
     * @param component
     * @return
     */
    private Component decorateWoman(Component component) {
        return new WomanDecorator(component);
    }

    /**
     * @param component
     * @return
     */
    private Component decorateMan(Component component) {
        return new ManDecorator(component);
    }
    
    
    
    
}
