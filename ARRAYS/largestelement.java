public class LargestElementOptimal {

    public static int largestElement(int[] arr) {

        int largest = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > largest) {
                largest = arr[i];
            }
        }

        return largest;
    }

    public static void main(String[] args) {

        int[] arr = {12, 45, 2, 78, 34, 99, 56};

        System.out.println("Largest Element = " + largestElement(arr));
    }
}
