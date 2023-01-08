package array;

public class ArrayRemoveTargetNum {
    public static void main(String[] args)
    {
        int[] arr = removeTargetNumFromArr(new int[]{1,2,3,2,2,6,7,8,9,2,2,2}, 2);
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i]);
        }
    }

    public static int[] removeTargetNumFromArr(int[] arr, int target)
    {
        int begin=0;
        int end = arr.length-1;

        while(begin < end)
        {
            while(begin < end && arr[begin] != target)
            {
                begin++;
            }
            while (begin < end && arr[end] == target)
            {
                end--;
            }
            //swap
            int tmp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = tmp;
        }
        return arr;
    }

}
