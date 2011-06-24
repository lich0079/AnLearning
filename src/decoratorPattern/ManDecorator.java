/**
 *  created on Jan 9, 2010
 */
package decoratorPattern;

/**
 * @author Bane
 * 
 */
public class ManDecorator extends Decorator {

    /**
     * @param component
     */
    public ManDecorator(Component component) {
        super(component);
    }

    @Override
    public void show() {
        System.out.println("I'm man");
        super.show();
    }

    
    
}
