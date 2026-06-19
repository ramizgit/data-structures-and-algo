package consistenthashing.twopointers;

/*There are two sorted streams by timestamp.

The first stream contains a small set of ground truth checkpoints.

The second stream contains noisy measured samples.

For each sample, compute its error by:
locating the surrounding checkpoints in time,
interpolating the expected position at that timestamp,
computing the distance error between the expected position and the sample position.
Return the sum of the distance errors for all valid samples.
Method Signature
double computeOverallErrorMetric(List<String> checkpoints, List<String> samples)
checkpoints[i] = "timestamp,x,y" represents one ground truth checkpoint.
samples[i] = "timestamp,x,y" represents one measured sample.
checkpoints is sorted in strictly increasing order of timestamp.
samples is sorted in non-decreasing order of timestamp.
Return the total distance error over all valid samples.
Answer will contain at max two decimal places, rest will be truncated.
Input Format
Each checkpoint string has the format "timestamp,x,y".

Each sample string has the format "timestamp,x,y".

timestamp is the time of the point.

x and y are the 2D coordinates of the point.
Rules
To evaluate one sample at time t:
Find the checkpoint immediately before or at t.
Find the checkpoint immediately after or at t.
If t exactly matches a checkpoint time, the expected position is that checkpoint position.
If t lies strictly between two checkpoints, linearly interpolate the expected x and y values independently.
The distance error for the sample is the Euclidean distance between the expected position and the sample position.
If a sample timestamp is outside the checkpoint range, ignore that sample and add 0 for it.
Interpolation
Suppose the surrounding checkpoints are (t1, x1, y1) and (t2, x2, y2), and the sample timestamp is t where t1 ≤ t ≤ t2.

Since checkpoint timestamps are strictly increasing, t1 < t2 whenever t lies strictly between two checkpoints.

In that case:

expectedX = x1 + (x2 - x1) * (t - t1) / (t2 - t1)

expectedY = y1 + (y2 - y1) * (t - t1) / (t2 - t1)
Distance Error
If the expected position is (expectedX, expectedY) and the sample position is (sampleX, sampleY), then:

distance = sqrt((expectedX - sampleX) * (expectedX - sampleX) + (expectedY - sampleY) * (expectedY - sampleY))
Constraints
2 ≤ checkpoints.size() ≤ 10^5
0 ≤ samples.size() ≤ 10^5
Each checkpoint or sample string contains exactly three comma-separated values: timestamp,x,y
0 ≤ timestamp ≤ 10^8
-10^6 ≤ x, y ≤ 10^6
checkpoints is sorted in strictly increasing order of timestamp
samples is sorted in non-decreasing order of timestamp
 */

import java.util.List;

public class ErrorDistanceBetweenCheckpointAndSamples {

    public double computeOverallErrorMetric(List<String> checkpoints, List<String> samples)
    {

        //todo : code

        return 0;
    }
}
