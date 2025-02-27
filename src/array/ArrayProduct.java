package array;

public class ArrayProduct {
    
    public static void main(String[] args)
    {
        getProduct(new int[]{1,2,3,4});
    }

    public static void getProduct(int[] arr)
    {
        int[] leftProduct = new int[arr.length];
        int[] rightProduct = new int[arr.length];
        int[] finalProduct = new int[arr.length];

        //populate left product
        leftProduct[0] = 1;
        for(int i=1; i < arr.length; i++)
        {
            leftProduct[i] = arr[i-1] * leftProduct[i-1];
        }

        //populate right product
        rightProduct[rightProduct.length-1] = 1;
        for(int i=arr.length-2; i>=0; i--)
        {
            rightProduct[i] = arr[i+1] * rightProduct[i+1];
        }

        //populate final product
        for(int i=0; i < arr.length; i++)
        {
            finalProduct[i] = leftProduct[i] * rightProduct[i];
        }
    }
}
