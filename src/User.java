public class User {
    int a;

    public static void main(String[] args) {
        User a=new User();
        a.a=1;
        System.out.println(a.a);
        m1(a);
        System.out.println(a.a);

        int i = 123;
        System.out.println(i);
        m2(i);
        System.out.println(i);
    }

    private static void m1(User s) {
        s.a=3;
        s = new User();
        s.a=2;
    }

    private static void m2(int i) {
        i = 321;
    }

}
