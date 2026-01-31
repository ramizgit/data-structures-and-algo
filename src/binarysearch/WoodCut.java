package binarysearch;

public class WoodCut {

    public static void main(String[] args)
    {

    }

    private static int maxWookLen(int[] wood, int k)
    {
        int low = 1;
        int high = 0;
        for(int w : wood){
            high = Math.max(high, w);
        }
        int answer = 0;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(canCut(wood, k, mid)){
                answer = mid; //possible answer
                low = mid + 1; //try bigger
            }else{
                high = mid - 1; //try smaller
            }
        }

        return answer;
    }

    private static boolean canCut(int[] wood, int k, int l)
    {
        int sum = 0;
        for(int w : wood){
            sum += w/l;
        }

        return sum >= k;
    }
}
