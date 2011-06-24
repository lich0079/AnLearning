import junit.framework.TestCase;

public class MyTestCase extends TestCase {

    private Object large;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        large=new Object();
        assertNotNull(large);
        System.out.println("setUp: large="+large);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        large=null;
        assertNull(large);
        System.out.println("tearDown: large="+large);
    }

    public void testMethod1() throws Throwable {
        System.out.println(large);
        System.out.println("testMethod1");
    }
    
    public void testMethod2() throws Throwable {
        System.out.println(large);
        System.out.println("testMethod2");
    }
    
    public void testMethod3() throws Throwable {
        assertEquals(true, true);
        assertNotNull(large);
        assertNull(null);
        assertTrue(1==1);
        System.out.println("testMethod3");
    }
}
