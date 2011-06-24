package algorithm.map;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Client {
    public static void main(String[] args) {
        // graghN(6);//����ͼ����ؼ���
        graghD(4);//����ͼ����ؼ���
//        graghNW(4);
    }

    public static void graghNW(int num) {
        Point[] pointsArray = initPoints(num);
        int[][] matrix = initMatrix(num);
        deployMatrixNW(matrix);
        print(pointsArray, matrix);
        mstw(pointsArray, matrix);
    }
    
    public static void mstw(Point[] points, int[][] matrix) {
        int start = 0;
        System.out.println("dfs \nstart mstw with " + points[start].getLabel());

        Stack stack = new Stack();
        Point p=points[start];
        stack.push(p);
        p.setVisted(true);
        
        Queue q=new PriorityQueue();
        for (int i = 0; i < matrix[p.getIndex()].length; i++) {
            if(matrix[p.getIndex()][i]!=0 && !stack.contains(points[i])){
                q.add(matrix[p.getIndex()][i]);
            }
        }
    }
    
    
    public static void graghD(int num) {
        Point[] pointsArray = initPoints(num);
        int[][] matrix = initMatrix(num);
        deployMatrixD(matrix);
        print(pointsArray, matrix);
        int[][] result = Warshall(pointsArray, matrix);
        print(pointsArray, result);
        // ���������޷����?ͼ
    }

    public static int[][] Warshall(Point[] points, int[][] matrix) {
        for (int p = 0; p < points.length; p++) {
            for (int x = 0; x < points.length; x++) {
                for (int y = 0; y < points.length; y++) {
                    if (matrix[x][p] == 1 && matrix[p][y] == 1 && x != y) {
                        matrix[x][y] = 1;
                        // ÿ�μ���һ���Ƿ���ͨ ����n�� ÿ��ˢ����ͨ�� ���ͳ�Ϊ��n�����ͨ��
                    }
                }
            }
        }
        return matrix;
    }

    public static void deployMatrixD(int[][] matrix) {
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j) {
                    continue;
                }
                int value = r.nextBoolean() ? 1 : 0;
                matrix[i][j] = value;
            }
        }
    }

    public static void graghN(int num) {
        Point[] pointsArray = initPoints(num);
        int[][] matrix = initMatrix(num);
        deployMatrix(matrix);
        print(pointsArray, matrix);

        dfs(pointsArray, matrix);
        for (int i = 0; i < pointsArray.length; i++) {
            pointsArray[i].setVisted(false);
        }
        bfs(pointsArray, matrix);
    }

    public static Point[] initPoints(int num) {
        Point[] pointsArray = new Point[num];
        for (int i = 0; i < pointsArray.length; i++) {
            pointsArray[i] = new Point(String.valueOf((char) (65 + i)));
            pointsArray[i].setIndex(i);
        }
        return pointsArray;
    }

    public static int[][] initMatrix(int num) {
        int[][] matrix = new int[num][num];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    public static void deployMatrix(int[][] matrix) {
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int value = r.nextBoolean() ? 1 : 0;
                matrix[i][j] = value;
                matrix[j][i] = value;
            }
        }
    }
    
    public static void deployMatrixNW(int[][] matrix) {
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                int value = r.nextInt(5);
                matrix[i][j] = value;
                matrix[j][i] = value;
            }
        }
    }

    public static void print(Point[] points, int[][] matrix) {
        System.out.print("   ");
        for (int i = 0; i < points.length; i++) {
            System.out.print(points[i].getLabel() + "  ");
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(points[i].getLabel() + "  ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * dfs�㷨�߹����ͼ��·���ض�����С�����
     */
    public static void dfs(Point[] points, int[][] matrix) {
        int start = 0;
        System.out.println("dfs \nstart with " + points[start].getLabel());

        Stack stack = new Stack();
        stack.push(points[start]);
        points[start].setVisted(true);
        move(points, matrix, stack);
    }

    public static void move(Point[] points, int[][] matrix, Stack stack) {
        Point top = (Point) stack.peek();
        boolean flag = false;
        for (int i = 0; i < matrix[top.getIndex()].length; i++) {
            if (matrix[top.getIndex()][i] == 1 && !points[i].isVisted()) {// ˵������
                stack.push(points[i]);
                points[i].setVisted(true);
                flag = true;// rule No.1
                System.out.println("move from " + top.getLabel() + " to " + points[i].getLabel());
                move(points, matrix, stack);
                break;
            }
        }
        if (!flag) {
            Point p = (Point) stack.pop();
            if (!stack.isEmpty()) {// rule No.2
                System.out.println("there is no unvisted point connect to " + p.getLabel() + ",return to " + ((Point) stack.peek()).getLabel());
                move(points, matrix, stack);
            } else {
                System.out.println("stack is null,end search");// rule No.3
            }
        }
    }

    public static void bfs(Point[] points, int[][] matrix) {
        int start = 0;
        System.out.println("bfs \nstart with " + points[start].getLabel());

        Queue queue = new LinkedList();
        queue.add(points[start]);
        points[start].setVisted(true);
        move(points, matrix, queue);
    }

    public static void move(Point[] points, int[][] matrix, Queue queue) {
        while (!queue.isEmpty()) {
            Point top = (Point) queue.poll();
            System.out.println("stand at " + top.getLabel());
            for (int i = 0; i < matrix[top.getIndex()].length; i++) {
                if (matrix[top.getIndex()][i] == 1 && !points[i].isVisted()) {// ˵������
                    queue.add(points[i]);
                    points[i].setVisted(true);
                    System.out.println("from " + top.getLabel() + " visit " + points[i].getLabel());
                }
            }
        }
        System.out.println("queue is null,end search");
    }
}
