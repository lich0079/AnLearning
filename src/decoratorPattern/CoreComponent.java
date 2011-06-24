/**
 *  created on Jan 9, 2010
 */
package decoratorPattern;

/**
 * @author Bane
 * 
 */
public class CoreComponent implements Component {

    /*
     * CoreComponent
     * 
     * @see decoratorPattern.Component#show()
     */
    public void show() {
        System.out.println("I'm CoreComponent");
    }

}
