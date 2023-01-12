package array;

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
        //i < j < k
        int i = Integer.MAX_VALUE;
        int j = Integer.MAX_VALUE;

        for(int p=0; p<arr.length; p++){

            if(arr[p] > j){
                return true;
            }

            if(arr[p] < i){
                i = arr[p]; //left min
            }else {
                if(arr[p] > i && arr[p] < j){
                    j = arr[p];
                }
            }
        }
        return false;
    }
}
