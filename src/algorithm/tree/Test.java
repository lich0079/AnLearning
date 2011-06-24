package algorithm.tree;

import java.util.List;
import java.util.Stack;

public class Test {

    private static Stack<Node> stack=new Stack<Node>();
    
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);
        Node n11 = new Node(11);
        n1.add(n2);
        n1.add(n3);
        n2.add(n4);
        n2.add(n5);
        n5.add(n8);
        n3.add(n6);
        n3.add(n7);
        n7.add(n9);
        n6.add(n10);
        n6.add(n11);
        System.out.println("prefix");
        prefixVisit(n1);
        System.out.println("==================");
        reverseVisit(n11);
//        while (!stack.empty()) {
//            Node node=stack.pop();
//            System.out.println(node.getNum());
//        }
//        System.out.println("start reverseVisit");
//        reverseVisit(n11);
//        System.out.println("==================");
    }

    public static void prefixVisit(Node node) {
        List<Node> children = node.getChildren();
        for (Node child : children) {
            prefixVisit(child);
        }
//stack.push(node);
        System.out.println(node.getNum());
    }

    public static void postfixVisit(Node node) {
        System.out.println("postfixVisit "+node.getNum() );
        List<Node> children = node.getChildren();
        for (int i = children.size() - 1; i >= 0; i--) {
            postfixVisit(children.get(i));
        }

        System.out.println(node.getNum());
    }

    public static void reverseVisit(Node node) {
        System.out.println(node.getNum());
        List<Node> children = node.getChildren();
        for (int i = children.size()-1; i >= 0; i--) {
            reverseVisit (children.get(i));
      }
//        System.out.println("reverseVisit "+node.getNum() );
//        Node parent = node.getParent();
//        if (parent == null){
//            System.out.println(node.getNum());
//            return;
//        }
//        List<Node> children = parent.getChildren();
////        System.out.println(node.getNum());
//        int index=children.indexOf(node);
//        for (int i = index; i >= 0; i--) {
//            if (children.get(i) != node)
//                postfixVisit(children.get(i));
//        }
//        reverseVisit(parent);
    }
}
