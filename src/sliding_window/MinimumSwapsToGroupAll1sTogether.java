package slidingWindow;

public class MinimumSwapsToGroupAll1sTogether {

    public int minSwaps(int[] arr)
    {
        int totalOnes = 0;
        for(int n : arr){
            totalOnes += n;
        }

        if (totalOnes == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int currWindowOnes = 0;
        int maxWindowOnes = 0;

        while(right < arr.length){
            currWindowOnes += arr[right];

            if(right - left + 1 > totalOnes){ //totalOnes is window size
                currWindowOnes -= arr[left];
                left++;
            }

            maxWindowOnes = Math.max(maxWindowOnes, currWindowOnes);

            right++;
        }

        return totalOnes - maxWindowOnes;
    }
}
