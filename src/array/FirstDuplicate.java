package array;

public class FirstDuplicate {
    public static void main(String[] args)
    {
        System.out.println(findFirstDup(new int[]{1,2,3,3,2,5}));
    }

    public static int findFirstDup(int[] arr)
    {
        for(int i=0; i<arr.length-1; i++)
        {
            int idx = Math.abs(arr[i]);
            if(arr[idx] < 0) {
                return idx;
            }else{
                arr[idx] = -1 * arr[idx];
            }
        }
        return -1;
    }
}
