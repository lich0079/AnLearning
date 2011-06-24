package algorithm.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int num;
    private Node parent;
    private List<Node> children=new ArrayList<Node>();

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node(int num) {
        super();
        this.num = num;
    }
    
    public void add(Node child) {
        children.add(child);
        child.setParent(this);
    }

}
