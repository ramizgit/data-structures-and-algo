package matrix;

public class MaxSumRectangleInMatrix {

    public static void main(String[] args)
    {
        int[][] input = {   {6, -5, -7, 4, -4},
                            {-9, 3, -6, 5, 2},
                            {-10, 4, 7, -6, 3},
                            {-8, 9, -3, 3, -7} };

        System.out.println(getMaxSumRectangle(input));
    }

    public static int getMaxSumRectangle(int[][] input)
    {
        int max = 0;
        int rows = input.length;

        for(int l=0; l<rows; l++){
            for(int i=l; i<rows; i++){
                int[] tmparr = copy(input[i]);
                for(int j=l; j<i; j++){
                    tmparr = add(tmparr, input[j]);
                }
                max = Math.max(max, getMaxSubArraySum(tmparr));
            }
        }

        return max;
    }

    public static int getMaxSubArraySum(int[] arr)
    {
        int sum = 0;
        int max = 0;

        for(int i=0; i<arr.length; i++){
            sum += arr[i];

            //reset if sum is negative
            if(sum < 0){
                sum = 0;
            }

            max = Math.max(max, sum);
        }

        return max;
    }

    public static int[] copy(int[] input){
        int[] output = new int[input.length];
        for(int i=0; i<input.length; i++){
            output[i] = input[i];
        }
        return output;
    }

    public static int[] add(int[] arr1, int[] arr2){
        int[] output = new int[arr1.length];
        for(int i=0; i<arr1.length; i++){
            output[i] = arr1[i] + arr2[i];
        }
        return output;
    }
}
