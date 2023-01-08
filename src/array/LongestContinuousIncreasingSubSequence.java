package array;

public class LongestContinuousIncreasingSubSequence {

    public static void main(String[] args)
    {
        System.out.println(findLongestContinuousIncreasingSubSequence(new int[]{1,3,5,4,7})); //3
        System.out.println(findLongestContinuousIncreasingSubSequence(new int[]{2,2,2,2,2,})); //1
        System.out.println(findLongestContinuousIncreasingSubSequence(new int[]{1,3,5,4,7,6,1,2,3,4,5})); //5 
    }

    public static int findLongestContinuousIncreasingSubSequence(int[] arr)
    {
        int count = 1;
        int maxLength = 1;

        for(int i=1; i<arr.length; i++){
            if(arr[i] > arr[i-1]){
                //increment
                count++;
                maxLength = Math.max(maxLength, count);
            }else {
                //reset
                count=1;
            }
        }
        return maxLength;
    }
}
