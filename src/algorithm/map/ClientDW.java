package algorithm.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class ClientDW {
    public static void main(String[] args) {
        int num = 6;
        Gragh g = new Gragh();
        initPoints(g, num);
        initEdges(g, num);
        int[][] matrix = initMatrix(g);
        print(g, matrix);
        path(g, matrix);
    }

    public static void path(Gragh g, int[][] matrix) {
        //���㷨������ѡһ����㣨��ǰ�㣩A ��������ӵ�������С�ı� �ѱߵ��յ�B����������һ������ˢ��·���?
        //Ȼ���B��Ϊ��� ���������ŵĲ������ϵĵ�x�У� ��x��B(��ǰ��)�ľ��� + B��A�ľ��루ȷ���˵ĵ�ǰ������·���� �Ƿ�� ·�����A��x���ľ���� 
        //���� ��ˢ�� A��x�ľ��� ����B��Ϊx��·���ϵ����һ����
        //������B�����ŵĵ� ˢ�º� ��Ȼ��ͳ��·���� �ҳ���С��һ����C ����������C��Ϊ��㡣������x+C  �Ƿ��·������Ľ�
        int start = 0;
        System.out.println("path \nstart  with " + g.getPoints().get(start).getLabel());
        Path[] pArray = new Path[g.getPoints().size()];
        List tree = new ArrayList();
        tree.add(g.getPoints().get(start));
        System.out.println("put " + g.getPoints().get(start));
        Point curentPoint = g.getPoints().get(start);

        while (tree.size() < g.getPoints().size()) {
            for (int i = 0; i < matrix[curentPoint.getIndex()].length; i++) {
                if (pArray[i] == null) {
                    pArray[i] = new Path();
                    pArray[i].getPath().add(curentPoint);
                    pArray[i].setDistance(999);
                }
                if (!tree.contains(g.getPoints().get(i))) {
                    int newD = matrix[curentPoint.getIndex()][i] + (pArray[curentPoint.getIndex()].getDistance() == 999 ? 0 : pArray[curentPoint.getIndex()].getDistance());
                    int oldD = pArray[i].getDistance();
                    System.out.println("old " + oldD + "   new " + newD);
                    if (newD < oldD) {
                        pArray[i].setDistance(newD);
                        pArray[i].getPath().add(curentPoint);
                    }

                }

            }
            printPathArray(pArray);
            Queue q = new PriorityQueue();
            int index = 0;
            int min = 9999;
            for (int i = 0; i < pArray.length; i++) {
                if (!tree.contains(g.getPoints().get(i))) {
                    if (pArray[i].getDistance() < min) {
                        min = pArray[i].getDistance();
                        index = i;
                    }
                }
            }
            tree.add(g.getPoints().get(index));
            curentPoint = g.getPoints().get(index);
            System.out.println("mininum distance is " + pArray[index].getDistance() + " from "
                    + pArray[index].getPath().get(pArray[index].getPath().size() - 1) + ", to " + g.getPoints().get(index).getLabel());
            System.out.println("put " + g.getPoints().get(index).getLabel());
        }
        for (int i = 0; i < pArray.length; i++) {
            System.out.print(pArray[i].getDistance() + "   ");
            for (int j = 1; j < pArray[i].getPath().size(); j++) {
                System.out.print(pArray[i].getPath().get(j).getLabel() + " ");
                System.out.print(g.getPoints().get(i).getLabel());
            }
            System.out.println();
        }
    }

    public static void printPathArray(Path[] pArray) {
        for (int i = 0; i < pArray.length; i++) {
            System.out.print(pArray[i].getDistance() + "  ");
        }
        System.out.println();
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
            for (int j = 0; j < g.getPoints().size(); j++) {
                if (i == j) {
                    continue;
                }
                int weight = r.nextInt(3);
                if (weight > 0) {
                    Edge e = new Edge();
                    e.setStart(g.getPoints().get(i));
                    e.setEnd(g.getPoints().get(j));
                    e.setWeight(weight);
                    g.getEdges().add(e);
                } else {
                    Edge e = new Edge();
                    e.setStart(g.getPoints().get(i));
                    e.setEnd(g.getPoints().get(j));
                    e.setWeight(999);
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
