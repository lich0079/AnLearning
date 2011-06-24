package junit;

import junit.framework.TestCase;

public class Testcase2 extends TestCase {
public Testcase2() {
    // TODO Auto-generated constructor stub
}
    @Override
    protected void setUp() throws Exception {
        System.out.println("-");
        System.out.println("setUp2");
    }

    @Override
    protected void tearDown() throws Exception {
        System.out.println("tearDown2");
    }
    
    public void testRead() throws Exception{
        System.out.println("test12");
//        assertTrue(1==1);
    }

}
