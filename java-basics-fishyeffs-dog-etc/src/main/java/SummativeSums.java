import java.util.ArrayList;

public class SummativeSums {
    public static void main(String[] args) {
        int[] arr1 = { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] arr2 = { 999, -60, -77, 14, 160, 301 };
        int[] arr3 = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,
                140, 150, 160, 170, 180, 190, 200, -99 };
        int sum = 0;

        ArrayList<int[]> arrays = new ArrayList<int[]>();
        arrays.add(arr1);
        arrays.add(arr2);
        arrays.add(arr3);


        int i = 0;
        for (int[] arr : arrays) {
            i++;
            for (int j = 0; j < arr.length; j++) {
                sum += arr[j];
            }
            System.out.println("#" + i + " Array Sum: " + sum);
            sum = 0;
        }
    }
}
