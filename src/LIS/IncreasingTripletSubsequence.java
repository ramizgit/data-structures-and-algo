package longestIncreasingSubseqVariants;

public class IncreasingTripletSubsequence {

    public static void main(String[] args)
    {
        System.out.println(isIncreasingTiplet(new int[]{1,2,3,4,5}));//true
        System.out.println(isIncreasingTiplet(new int[]{5,4,3,2,1}));//false
        System.out.println(isIncreasingTiplet(new int[]{5,4,3,2,1,2,3}));//true
        System.out.println(isIncreasingTiplet(new int[]{5,4,3,2,1,5,2,6,3}));//true
    }

    private static boolean isIncreasingTiplet(int[] arr)
    {
        int first = Integer.MAX_VALUE; //first min
        int second = Integer.MAX_VALUE; //second min

        for (int n : arr) {
            if (n <= first) {
                first = n;
            } else if (n <= second) {
                second = n;
            } else {
                return true;
            }
        }

        return false;
    }
}
