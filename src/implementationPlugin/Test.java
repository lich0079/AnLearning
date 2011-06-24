package implementationPlugin;

public class Test {
    public static void main(String[] args) {
        IAction action=new AAction();
        action.action();
        System.out.println("----");
    }
}
