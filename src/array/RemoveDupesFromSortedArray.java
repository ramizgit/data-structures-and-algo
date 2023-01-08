package array;

public class RemoveDupesFromSortedArray {
    public static void main(String[] args)
    {
        removeDupes(new int[]{0,0,1,1,1,2,2,3,3,4});
        removeDupes(new int[]{0,1,2,2,3,3,4});
        removeDupes(new int[]{1,1,2});
    }

    public static void removeDupes(int[] arr)
    {
        int left = 1;
        int right = 1;

        while(right < arr.length)
        {
            while(right < arr.length && arr[right] == arr[right-1]) {
                right++;
            }

            //now swap
            arr[left] = arr[right];
            right++;
            left++;
        }

        System.out.println("left pointer is at : "+left);
        for(int e : arr){
            System.out.print(e+ " ");
        }
        System.out.println();
    }
}
