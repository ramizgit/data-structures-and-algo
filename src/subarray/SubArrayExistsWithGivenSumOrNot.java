package subarray;

public class SubArrayExistsWithGivenSumOrNot {
    public static void main(String[] args)
    {
        System.out.println(subArrayExists(new int[]{1,2,3,4,5}, 7)); //true
        System.out.println(subArrayExists(new int[]{1,2,3,4,5}, 8)); //false
        System.out.println(subArrayExists(new int[]{1,4,20,3,10,5}, 33)); //true

    }

    public static boolean subArrayExists(int[] arr, int target)
    {
        int i = 0;
        int j = 0;
        int sum = arr[i];

        while(i<arr.length && j<arr.length){
            if(sum == target){
                return true;
            }else if(sum < target && j < arr.length-1){
                //increase j pointer, i.e., increase window size
                j++;
                sum += arr[j];
            }else {
                //increase i pointer, i.e., decrease window size
                sum -= arr[i];
                i++;
            }
        }
        return false;
    }
}
