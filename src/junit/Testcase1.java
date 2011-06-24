package junit;

import junit.framework.TestCase;

public class Testcase1 extends TestCase {
public Testcase1() {
    // TODO Auto-generated constructor stub
}
    @Override
    protected void setUp() throws Exception {
        System.out.println("-");
        System.out.println("setUp");
    }

    @Override
    protected void tearDown() throws Exception {
        System.out.println("tearDown");
    }
    
    public void testRead() throws Exception{
        System.out.println("test1");
    }

}
