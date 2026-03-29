/*
Problem: Maximum Sum Subarray of Size K
Platform: LeetCode / Sliding Window Pattern

Approach:
- Use fixed size sliding window
- Add elements while expanding window
- When window size becomes k:
    - Calculate max sum
    - Remove left element (shrink window)
    - Move left pointer

Time Complexity: O(n)
Space Complexity: O(1)
*/

class Solution {
    public int maxSumSubarray(int[] arr, int k) {

        int left = 0;
        int sum = 0;
        int maxSum = 0;

        for (int right = 0; right < arr.length; right++) {

            sum += arr[right];

            // when window size == k
            if (right - left + 1 == k) {

                maxSum = Math.max(maxSum, sum);

                sum -= arr[left];
                left++;
            }
        }

        return maxSum;
    }
}
