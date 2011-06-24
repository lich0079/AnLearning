/**
 *  created on Jan 9, 2010
 */
package decoratorPattern;

/**
 * @author Bane
 * 
 */
public class WomanDecorator extends Decorator {

    /**
     * @param component
     */
    public WomanDecorator(Component component) {
        super(component);
    }

    @Override
    public void show() {
        System.out.println("I'm woman");
        super.show();
    }

    
    
}
