package algorithm.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Client2 {
    public static void main(String[] args) {
        int num = 6;
        Gragh g = new Gragh();
        initPoints(g, num);
        initEdges(g, num);
        int[][] matrix = initMatrix(g);
        print(g, matrix);
        mstw(g, matrix);

    }

    public static void mstw(Gragh g, int[][] matrix) {
        int start = 0;
        System.out.println("mstw \nstart  with " + g.getPoints().get(start).getLabel());

        List set = new ArrayList();
        Point p = g.getPoints().get(start);
        set.add(p);
        g.getPoints().remove(p);
        System.out.println("put "+p);

        // ������������й������յ㲻�ڼ����еı߷ŵ������б?�ҵ���С�ıߣ�������յ�ŵ������У��Ѻ�����ص�
        // ��Ҳ�ŵ������б� ����һ����С�ıߡ���������
        //��СȨֵ�����������Ψһ��
        Queue q = new PriorityQueue();
        while (g.getPoints().size() > 0) {
            for (Iterator iterator = g.getEdges().iterator(); iterator.hasNext();) {
                Edge edge = (Edge) iterator.next();
                if(edge.getStart()==p && !set.contains(edge.getEnd())){//��p���� ���ֲ���set��
                    q.add(edge);
                }
                if (edge.getEnd()==p && !set.contains(edge.getStart())) {
                    q.add(edge);
                }
            }
            Edge low = (Edge) q.poll();
            if(!(set.contains(low.getStart()) && set.contains(low.getEnd()))){
                set.add(low);
                System.out.println("put "+low);
                if (set.contains(low.getStart())) {
                    set.add(low.getEnd());
                    System.out.println("put "+low.getEnd());
                    g.getPoints().remove(low.getEnd());
                    p = low.getEnd();
                } else {
                    set.add(low.getStart());
                    System.out.println("put "+low.getStart());
                    g.getPoints().remove(low.getStart());
                    p = low.getStart();
                }
            }
            
        }

//        for (Iterator iterator = set.iterator(); iterator.hasNext();) {
//            Object object = (Object) iterator.next();
//            System.out.println(object);
//        }
    }

    public static void initPoints(Gragh g, int num) {
        for (int i = 0; i < num; i++) {
            Point p = new Point(String.valueOf((char) (65 + i)));
            p.setIndex(i);
            g.getPoints().add(p);
        }
    }

    public static void initEdges(Gragh g, int num) {
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < g.getPoints().size(); i++) {
            for (int j = i + 1; j < g.getPoints().size(); j++) {
                int weight = r.nextInt(5);
                if (weight > 0) {
                    Edge e = new Edge();
                    e.setStart(g.getPoints().get(i));
                    e.setEnd(g.getPoints().get(j));
                    e.setWeight(weight);
                    g.getEdges().add(e);
                }
            }
        }
    }

    public static int[][] initMatrix(Gragh g) {
        int[][] matrix = new int[g.getPoints().size()][g.getPoints().size()];
        for (Iterator iterator = g.getEdges().iterator(); iterator.hasNext();) {
            Edge edge = (Edge) iterator.next();
            matrix[edge.getStart().getIndex()][edge.getEnd().getIndex()] = edge.getWeight();
            matrix[edge.getEnd().getIndex()][edge.getStart().getIndex()] = edge.getWeight();
        }
        return matrix;
    }

    public static void print(Gragh g, int[][] matrix) {
        System.out.print("   ");
        for (int i = 0; i < g.getPoints().size(); i++) {
            System.out.print(g.getPoints().get(i).getLabel() + "  ");
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(g.getPoints().get(i).getLabel() + "  ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
        for (Iterator iterator = g.getEdges().iterator(); iterator.hasNext();) {
            Edge edge = (Edge) iterator.next();
            System.out.println(edge);
        }
    }
}
