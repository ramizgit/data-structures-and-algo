package array;

import java.util.Arrays;

public class ThreeSum {
    public static void main(String[] args)
    {
        printThreeSum(new int[]{-1,0,1,2,-1,-4}, 0);
        printThreeSum(new int[]{-3,1,1,1,2,2,2,-5}, 0);
        printThreeSum(new int[]{-4,1,1,1,2,2,2,3,-5}, 0);
    }

    public static void printThreeSum(int[] arr, int target)
    {
        Arrays.sort(arr);

        for(int i=0; i<arr.length-1; i++)
        {
            if(i == 0 || arr[i] != arr[i-1]){
                int sum = target - arr[i];
                //now look for 'sum' in rest of the array
                //since array is sorted, use left and right pointers
                int left = i+1;
                int right = arr.length-1;

                while(left < right){
                    int tmpSum = arr[left] + arr[right];
                    if( tmpSum == sum){
                        System.out.println(arr[i]+ " "+ arr[left]+ " "+arr[right]);

                        //start : to avoid duplicates
                        while(left < right && arr[left] == arr[left+1]){
                            left++;
                        }
                        while(right > left && arr[right] == arr[right-1]){
                            right--;
                        }
                        //end : to avoid duplicates

                        left++;
                        right--;
                    }else if(tmpSum < sum){
                        left++;
                    }else {
                        right--;
                    }
                }
            }
        }
    }
}
