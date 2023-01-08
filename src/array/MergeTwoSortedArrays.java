package array;

public class MergeTwoSortedArrays {
    public static void main(String[] args)
    {
        int[] arr1 = new int[]{1,2,3,0,0,0};
        int[] arr2 = new int[]{2,5,6};

        merge(arr1, 3, arr2, 3);

        for(int i=0; i < arr1.length; i++){
            System.out.print(arr1[i]+ " ");
        }
        System.out.println();

        int[] arr3 = new int[]{5,6,7,8,0,0,0,0,0};
        int[] arr4 = new int[]{1,2,3,9,10};

        merge(arr3, 4, arr4, 5);

        for(int i=0; i < arr3.length; i++){
            System.out.print(arr3[i]+ " ");
        }
        System.out.println();

    }

    public static int[] merge(int[] arr1, int m, int[] arr2, int n)
    {
        int last = arr1.length-1;

        while(m > 0 && n > 0){
            if(arr1[m-1] > arr2[n-1]){
                arr1[last] = arr1[m-1];
                m--;
            }else {
                arr1[last] = arr2[n-1];
                n--;
            }
            last--;
        }

        //check if any left in arr2
        while(n>0){
            arr1[last] = arr2[n-1];
            last--;
            n--;
        }
        return arr1;
    }
}
