package arrays.allElementExceptCurr;

public class ProductOfArrayExceptSelf {

    //https://leetcode.com/problems/product-of-array-except-self/description/

    public int[] getProduct_constantSpace(int[] arr)
    {
        if(arr == null || arr.length == 0){
            return new int[0];
        }

        int n = arr.length;

        //traverse left to right, store prefix in result
        int[] result = new int[n];
        result[0] = 1;

        for(int i=1; i<n; i++){
            result[i] = result[i-1] * arr[i-1];
        }

        //traverse right to left and calculate suffix on the fly
        int suffix = 1;
        for(int i=n-1; i>=0; i--){
            result[i] *= suffix;
            suffix *= arr[i];
        }

        return result;
    }


    //O(n) space
    public int[] getProduct(int[] arr)
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

        return finalProduct;
    }
}
