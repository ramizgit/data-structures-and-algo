package array;

public class TwoSumIIProblem {
    public static void main(String[] args){ //input array is sorted
        printPairs(new int[]{2,7,11,15}, 9);
        printPairs(new int[]{2,3,4}, 6);
        printPairs(new int[]{-1, 0}, -1);

    }

    public static void printPairs(int[] arr, int target)
    {
        int start = 0;
        int end = arr.length-1;

        while(start < end){
            int sum = arr[start] + arr[end];
            if( sum == target){
                System.out.println( (start + 1) +" "+ (end+1) );
            }

            if( sum < target){
                start++;
            }else {
                end--;
            }
        }
    }
}
