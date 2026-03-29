/*
Problem: Longest Substring Without Repeating Characters
Platform: LeetCode

Approach:
- Sliding Window + Hash Array
- Expand window using right pointer
- Shrink window using left pointer when duplicate found
- Store last index of characters

Time Complexity: O(n)
Space Complexity: O(1)
*/

import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {

        int left = 0;
        int maxLen = 0;

        int n = s.length();
        int hash[] = new int[256];
        Arrays.fill(hash, -1);

        for (int right = 0; right < n; right++) {

            char ch = s.charAt(right);

            if (hash[ch] != -1 && hash[ch] >= left) {
                left = hash[ch] + 1;
            }

            int len = right - left + 1;
            maxLen = Math.max(maxLen, len);

            hash[ch] = right;
        }

        return maxLen;
    }
}
