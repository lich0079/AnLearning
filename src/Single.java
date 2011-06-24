public class Single {
    private int id;
    
    private Single(){
        System.out.println("init.....");
    }
    private static class FooHolder {
        static final Single foo = new Single();
        static {
            foo.id=3;
            System.out.println("init id................");
        }
    }

    public static Single getFoo() {
        return FooHolder.foo;
    }
    
    public static void main(String[] args) throws Exception {
        Class.forName("Single");
        System.out.println("---------------");
        Single s=Single.getFoo();
        System.out.println("1111111111111111111111111");
    }
}
