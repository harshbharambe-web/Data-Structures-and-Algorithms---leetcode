## 1. Maximum Consecutive Ones

### Problem
Given a binary array `nums`, return the maximum number of consecutive 1's in the array.

---

### Approach
- Traverse the array once
- Maintain a counter for consecutive 1's
- If current element is 1:
  - Increment count
  - Update maximum count
- If element is 0:
  - Reset count to 0

---

### Key Idea
- Count streaks of consecutive 1's
- Reset when sequence breaks

---

### Complexity
- Time: O(n)
- Space: O(1)
