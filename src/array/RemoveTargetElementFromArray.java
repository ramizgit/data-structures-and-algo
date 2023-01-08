package array;

public class RemoveTargetElementFromArray {
    public static void main(String[] args)
    {
        removeTargetElement(new int[]{0,1,2,2,3,0,4,2}, 2);
        removeTargetElement(new int[]{3,2,2,3}, 3);
        removeTargetElement(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,5,4,3}, 1);

    }

    public static void removeTargetElement(int[] arr, int target)
    {
        int left = 0;
        int right = arr.length-1;

        while(left < right){
            //keep moving left pointer till we find target element
            while(arr[left] != target &&  left < right){
                left++;
            }
            //keep moving right pointer till we find a non-target element
            while(arr[right] == target && right > left){
                right--;
            }

            if(left != right){
                //now swap
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;

                left++;
                right--;
            }
        }
        //print answer
        System.out.println("left pointer is at : "+ (left));
        for(int e : arr){
            System.out.print(e+ " ");
        }
        System.out.println();

    }
}
