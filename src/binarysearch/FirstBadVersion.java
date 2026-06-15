package consistenthashing.binarysearch;

public class FirstBadVersion {

    //https://leetcode.com/problems/first-bad-version/description/

    public int firstBadVersion(int n)
    {
        int low = 1;
        int high = n;
        int answer = 0;

        while(low <= high){

            int mid = low + (high - low)/2;

            if(isBadVersion(mid)){
                answer = mid; //possible answer
                high = mid - 1; //look for an earlier bad version
            }else{
                low = mid + 1; //try higher
            }
        }

        return answer;
    }

    //dummy method
    boolean isBadVersion(int version)
    {
        return true;
    }
}
