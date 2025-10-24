package binarysearch;

public class Sqrt {

    public static void main(String[] args)
    {
        System.out.println(sqrt(16));
        System.out.println(sqrt(14));
        System.out.println(sqrt(8));
    }

    private static int sqrt(int num)
    {
        int low = 1;
        int high = num;
        int result = 0;

        while (low <= high){
            int mid = low + (high - low) / 2;
            if(mid * mid == num){
                return mid;
            } else if (mid * mid < num) {
                low = mid + 1;
                result = Math.max(result, mid);
            }else {
                high = mid - 1;
            }
        }

        return result;
    }

    //******JUST ANOTHER WAY TO SOLVE*************
    private static int suqareRoot(int num)
    {
        int left = 1;
        int right = num;

        while(left <= right){
            int mid = left + (right - left)/2;
            int square = mid * mid;

            if(square == num){
                return mid;
            }else if(square > num){
                right = mid -1;
            }else{
                left = mid + 1;
            }
        }

        return right; //for floor
        //return left; //for ceiling
    }
}
