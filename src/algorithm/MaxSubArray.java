package algorithm;

public class MaxSubArray {
    public static void main(String[] args) {
        int array[] = { 31, -41, 59, 26, -53, 58, 97, -93, -23, 84 };
//        int array[] = { -31, -41, -111,-12};
        int maxsofar = 0;
        int maxendinghere = 0;
        for (int i = 0; i < array.length; i++) {
            maxendinghere = (maxendinghere + array[i] > 0 ) ? (maxendinghere + array[i]) : 0;
            maxsofar = maxsofar > maxendinghere ? maxsofar : maxendinghere;
            System.out.println(i+":   "+maxendinghere + "   " + maxsofar);
        }
        System.out.println("final:  "+maxendinghere + "   " + maxsofar);
    }
}
