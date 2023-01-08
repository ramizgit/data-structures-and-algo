package array;

public class ArrayPutOddBeforeEven {
    public static void main(String[] args)
    {
        int[] arr = putOddBeforeEven(new int[]{1,2,3,4,5,6,7,8,9});
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i]);
        }
    }

    public static int[] putOddBeforeEven(int[] arr)
    {
        int begin = 0;
        int end = arr.length-1;

        while(begin < end)
        {
            //move begin index until we find even number
            while(begin < end && (arr[begin] & 1) != 0)
            {
                begin++;
            }

            //move end index until we find odd number
            while(begin < end && (arr[end] & 1) == 0)
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
