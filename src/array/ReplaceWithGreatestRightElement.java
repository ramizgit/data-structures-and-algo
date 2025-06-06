package array;

public class ReplaceWithGreatestRightElement {

    public static void main(String[] args)
    {
        replaceElements(new int[]{17,18,5,4,6,1}); //[18,6,6,6,1,-1]
        replaceElements(new int[]{400}); //[-1]
    }

    private static int[] replaceElements(int[] arr)
    {
        int[] output = new int[arr.length];
        int rightMax = -1;

        for(int i=output.length-1; i>=0; i--){
            output[i] = rightMax;
            rightMax = Math.max(rightMax, arr[i]);
        }

        return output;
    }
}

