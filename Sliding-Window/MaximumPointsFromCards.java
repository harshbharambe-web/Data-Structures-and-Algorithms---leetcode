/*
Problem: Maximum Points You Can Obtain from Cards
Platform: LeetCode

Approach:
- Instead of picking k cards from ends, think in reverse:
- Find minimum sum subarray of size (n - k)
- Total sum - min subarray = maximum points

Time Complexity: O(n)
Space Complexity: O(1)
*/

class Solution {
    public int maxScore(int[] cardPoints, int k) {

        int n = cardPoints.length;

        int totalSum = 0;
        for (int num : cardPoints) {
            totalSum += num;
        }

        // window size
        int windowSize = n - k;

        // edge case
        if (windowSize == 0) return totalSum;

        int windowSum = 0;
        int minSum = Integer.MAX_VALUE;

        int left = 0;

        for (int right = 0; right < n; right++) {

            windowSum += cardPoints[right];

            // when window size reached
            if (right - left + 1 == windowSize) {

                minSum = Math.min(minSum, windowSum);

                windowSum -= cardPoints[left];
                left++;
            }
        }

        return totalSum - minSum;
    }
}
