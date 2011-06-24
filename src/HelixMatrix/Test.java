package HelixMatrix;

public class Test {
    static int m = 4;
    static int n = 4;

    public static void main(String[] args) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(getValue(i, j, m, n, 0,0) + "   ");
            }
            System.out.println();
        }
    }

    private static int getValue(int i, int j, int m, int n, int level,int before) {
        if (i == level || i == m - level - 1 || j == level || j == n - level - 1) {
            if (i == level && j != n - 1) {// top
                return i-level + j-level + 1;
            } else if (j == n - 1 && i != m - 1) {// right
                return i-level + j-level + 1;
            } else if (i == m - 1 && j != level) {// bottom
                return m + n - 1 + n - 1 - j;
            } else if (j == level && i != level) {// left
                return m + n + n - 2 + m - 1 - i+level*2;
            }

        }
//        System.out.println(i+" "+j+" "+m+" "+n+" "+level+" "+before+" ");
        return getValue(i, j, m-1, n-1, level+1, (m+n)*2-4);
    }
}
