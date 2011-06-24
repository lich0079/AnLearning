public class xx {

    static boolean yesOrNo(String s) {
        s = s.toLowerCase();
        if (s.equals("yes") || s.equals("y") || s.equals("t")) {
            s = "true";
        }
        System.out.println();
        return Boolean.getBoolean(s);
    }

    public static void main(String[] args) {
        System.out.println(yesOrNo("true") + " " + yesOrNo("Yes"));
        String a1 =new String("123") ;
        String a2="123"   ;
       System.out.println(a1==a2);
       System.out.println(a1.intern()==a2);

    }
}
