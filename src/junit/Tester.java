package junit;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class Tester {
    
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
    
    public static Test suite() {
        TestSuite suite=new TestSuite();
        suite.addTestSuite(Testcase1.class);
        suite.addTestSuite(Testcase2.class);
        return suite;
    }
}
