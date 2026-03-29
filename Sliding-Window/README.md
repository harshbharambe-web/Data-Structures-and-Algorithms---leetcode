# Sliding Window Problems

## 1. Longest Substring Without Repeating Characters

Approach:
- Sliding window technique
- Hash array to store last seen index
- Move left pointer when duplicate found

Time Complexity: O(n)
Space Complexity: O(1)


## 2. Maximum Sum Subarray of Size K

### Approach
- Fixed size sliding window
- Expand window by adding elements
- When window size reaches k:
  - Calculate max sum
  - Remove left element
  - Slide window forward

### Complexity
- Time: O(n)
- Space: O(1)

## 3. Maximum Points You Can Obtain from Cards

### Approach
- Convert problem into finding minimum subarray of size (n - k)
- Calculate total sum of array
- Use sliding window to find minimum sum
- Subtract min sum from total sum

### Complexity
- Time: O(n)
- Space: O(1)
