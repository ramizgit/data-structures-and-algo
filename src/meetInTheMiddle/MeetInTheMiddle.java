package meetInTheMiddle;

import java.util.*;

public class MeetInTheMiddle {

    public int closestSubsequenceSum(int[] nums, int goal) {

        int mid = nums.length / 2;

        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);

        List<Integer> leftSums = new ArrayList<>();
        List<Integer> rightSums = new ArrayList<>();

        generate(left, 0, 0, leftSums);
        generate(right, 0, 0, rightSums);

        Collections.sort(rightSums);

        int answer = Integer.MAX_VALUE;

        for (int leftSum : leftSums) {

            int need = goal - leftSum;

            int idx = Collections.binarySearch(rightSums, need);

            if (idx >= 0) {
                return 0;   // exact match
            }

            idx = -idx - 1; // insertion point

            // Candidate on the right
            if (idx < rightSums.size()) {
                answer = Math.min(answer,
                        Math.abs(goal - (leftSum + rightSums.get(idx))));
            }

            // Candidate on the left
            if (idx > 0) {
                answer = Math.min(answer,
                        Math.abs(goal - (leftSum + rightSums.get(idx - 1))));
            }
        }

        return answer;
    }

    private void generate(int[] nums, int index, int sum, List<Integer> sums) {

        if (index == nums.length) {
            sums.add(sum);
            return;
        }

        // Don't pick
        generate(nums, index + 1, sum, sums);

        // Pick
        generate(nums, index + 1, sum + nums[index], sums);
    }
}
