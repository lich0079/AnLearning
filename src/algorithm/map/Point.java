package algorithm.map;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Point {
    private int index;
    private String label;
    private boolean visted;
    
    public Point(String label) {
        this.label=label;
    }
    public Point() {
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public boolean isVisted() {
        return visted;
    }
    public void setVisted(boolean visted) {
        this.visted = visted;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public static void main(String[] args) {
        Queue q=new PriorityQueue();
        q.add(5);
        q.add(3);
        q.add(1);
        q.add(2);
        q.add(7);
        q.add(8);
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
    }
    @Override
    public String toString() {
        return "point : "+this.getLabel()+"  index: "+this.getIndex();
    }
    
    
}
