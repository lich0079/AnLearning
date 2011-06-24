/**
 *  created on Jan 9, 2010
 */
package decoratorPattern;

/**
 * @author Bane
 * 
 */
public class Decorator implements Component {

    private Component component;

    /**
     * @param component
     */
    public Decorator(Component component) {
        super();
        this.component = component;
    }

    /*
     * (non-Javadoc)
     * 
     * @see decoratorPattern.Component#show()
     */
    public void show() {
        component.show();
    }

}
