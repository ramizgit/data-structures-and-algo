package google;

import java.util.*;

public class IntervalBinarySearch {

    //https://leetcode.com/discuss/post/7563717/title-google-l4-swe-iii-android-framewor-jb9v/

    //non-overlapping intervals O(log n)
    public int intervalSearch(int[][] intervals, int target)
    {
        int low = 0;
        int high = intervals.length - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            int start = intervals[mid][0];
            int end = intervals[mid][1];

            if(target >= start && target <= end){
                return mid;
            }else if(target < start){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return -1;
    }

    //overlapping intervals O(log n + k) ----k = number of intervals scanned (potential matches)
    public List<Integer> intervalSearchOverlapIntervals(int[][] intervals, int target)
    {
        List<Integer> result = new ArrayList<>();
        if (intervals == null || intervals.length == 0) return result;

        int low = 0;
        int high = intervals.length - 1;
        int found = -1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            int start = intervals[mid][0];
            int end = intervals[mid][1];

            if(target >= start && target <= end){
                found = mid;
                break;
            }else if(target < start){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        if(found == -1){
            return result;
        }

        int left = found - 1;
        while(left >= 0 && intervals[left][0] <= target){
            if(intervals[left][1] >= target){
                result.addFirst(left);
            }
            left--;
        }

        result.add(found);

        int right = found + 1;
        while(right < intervals.length && intervals[right][0] <= target){
            if(intervals[right][1] >= target){
                result.add(right);
            }
            right++;
        }

        return result;
    }
}
