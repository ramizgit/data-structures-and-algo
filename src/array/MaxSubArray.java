package array;

public class MaxSubArray {
    public static void main(String[] args)
    {
        System.out.println(getMaxSubArraySum(new int[]{-2,1,-3,4,-1,2,1,-5,10})); //11
        System.out.println(getMaxSubArraySum(new int[]{-2,3,4,-1,2,1,-5,10})); //14
        System.out.println(getMaxSubArraySum(new int[]{-2,3,4,-1,2,1,-5,2})); //9
        //System.out.println(getMaxSubArraySum(new int[]{-2,-3,-1,-4,-5})); //-1

        printMaxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,10}); //
        printMaxSubArray(new int[]{-2,3,4,-1,2,1,-5,10});
        printMaxSubArray(new int[]{-2,3,4,-1,2,1,-5,2});
    }

    public static int getMaxSubArraySum(int[] arr)
    {
        int sum = 0;
        int max = 0;

        for(int i=0; i<arr.length; i++){
            sum += arr[i];

            //reset if sum is negative
            sum = Math.max(sum, 0);

            max = Math.max(max, sum);
        }

        return max;
    }

    public static void printMaxSubArray(int[] arr)
    {
        int sum = 0;
        int max = 0;
        int start = 0;
        int end = 0;

        for(int i=0; i<arr.length; i++){
            sum += arr[i];

            //reset if sum is negative
            if(sum < 0){
                sum = 0;
                start = i;
            }

            if(sum > max){
                max = sum;
                end = i;
            }
        }

        System.out.println("start idx : "+ (start+1));
        System.out.println("end idx : "+end);
    }
}
