<div align="center">

# 📊 Arrays — DSA Practice Repository

![Java](https://img.shields.io/badge/Language-Java-orange?style=flat-square&logo=java)
![Topic](https://img.shields.io/badge/Topic-Arrays-blue?style=flat-square)
![Pattern](https://img.shields.io/badge/Pattern-Two%20Pointer-green?style=flat-square)
![Problems](https://img.shields.io/badge/Problems-15-purple?style=flat-square)

Every problem here is solved with **both** a Brute Force and an Optimal approach, complete with dry runs, complexity analysis, and pattern notes — built for interview prep.

</div>

---

## 📚 Table of Contents

| # | Problem | Pattern |
|---|---------|---------|
| 1 | [Maximum Consecutive Ones](#1-maximum-consecutive-ones) | Traversal / Counting |
| 2 | [Remove Duplicates from Sorted Array](#2-remove-duplicates-from-sorted-array-leetcode-26) | Two Pointer |
| 3 | [Concatenation of Array](#3-concatenation-of-array) | Simulation |
| 4 | [Longest Common Prefix](#4-longest-common-prefix-leetcode-14) | Traversal / Simulation |
| 5 | [Largest Element in an Array](#5-largest-element-in-an-array) | Traversal / Simulation |
| 6 | [Second Largest Element in an Array](#6-second-largest-element-in-an-array) | Traversal / Simulation |
| 7 | [Left Rotate Array by One Place](#7-left-rotate-array-by-one-place) | Simulation |
| 8 | [Rotate Array (Right, by k)](#8-rotate-array-leetcode-189) | Reversal Trick |
| 9 | [Left Rotate Array by k Places](#9-left-rotate-array-by-k-places) | Reversal Trick |
| 10 | [Move Zeroes](#10-move-zeroes-leetcode-283) | Two Pointer |
| 11 | [Single Number](#11-single-number-leetcode-136) | XOR / Bit Manipulation |
| 12 | [Two Sum](#12-two-sum-leetcode-1) | HashMap / Complement Lookup |
| 13 | [Sort Colors](#13-sort-colors-leetcode-75) | Three-Way Partitioning |
| 14 | [Majority Element](#14-majority-element-leetcode-169) | Boyer-Moore Voting |
| 15 | [Best Time to Buy and Sell Stock](#15-best-time-to-buy-and-sell-stock-leetcode-121) | Running Min / Kadane-style |

---

## 1. Maximum Consecutive Ones

**LeetCode:** [485. Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/)

### 🧩 Problem
Given a binary array `nums`, return the maximum number of consecutive `1`'s in the array.

### 💡 Approach 1 — Brute Force
- For every index `i`, expand forward and count how many consecutive `1`'s start there.
- Keep track of the maximum count seen.
- Involves a nested loop → less efficient.

```java
class Solution {
    public int findMaxConsecutiveOnesBrute(int[] nums) {
        int maxCount = 0;

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 1) {
                    count++;
                    maxCount = Math.max(maxCount, count);
                } else {
                    break;
                }
            }
        }
        return maxCount;
    }
}
```
- **Time:** O(n²)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (Single Pass)
- Traverse the array once.
- Maintain a counter for consecutive `1`'s.
- If current element is `1` → increment count, update max.
- If current element is `0` → reset count to `0`.

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0, maxCount = 0;

        for (int num : nums) {
            if (num == 1) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 0;
            }
        }
        return maxCount;
    }
}
```

### 📝 Dry Run (Optimal)
`nums = [1,1,0,1,1,1]`

| i | nums[i] | count | maxCount |
|---|---------|-------|----------|
| 0 | 1 | 1 | 1 |
| 1 | 1 | 2 | 2 |
| 2 | 0 | 0 | 2 |
| 3 | 1 | 1 | 2 |
| 4 | 1 | 2 | 2 |
| 5 | 1 | 3 | 3 |

**Result:** `3` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(1) |
| **Single Pass (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Array Traversal / Counting

---

## 2. Remove Duplicates from Sorted Array (LeetCode 26)

### 🧩 Problem
Given a **sorted** integer array `nums`, remove duplicates **in-place** so each unique element appears once. Return the count of unique elements `k`. The first `k` elements should hold the unique values in original order.

### 💡 Approach 1 — Brute Force (Extra Space)
- Use a `LinkedHashSet` (or list) to collect unique values while preserving order.
- Copy the unique values back into `nums`.

```java
class Solution {
    public int removeDuplicatesBrute(int[] nums) {
        if (nums.length == 0) return 0;

        Set<Integer> unique = new LinkedHashSet<>();
        for (int num : nums) {
            unique.add(num);
        }

        int i = 0;
        for (int val : unique) {
            nums[i++] = val;
        }
        return i;
    }
}
```
- **Time:** O(n)
- **Space:** O(n) — extra set used

### 💡 Approach 2 — Optimal (Two Pointer, In-Place)
- If array is empty, return `0`.
- `i` → tracks the last unique element's index.
- `j` → scans ahead through the array.
- If `nums[i] != nums[j]` → increment `i`, copy `nums[j]` into `nums[i]`.
- Return `i + 1`.

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
```

### 📝 Dry Run (Optimal)
`nums = [0,0,1,1,1,2,2,3,3,4]`

| j | nums[j] | nums[i] | Action | i (after) |
|---|---------|---------|--------|-----------|
| 1 | 0 | 0 | same → skip | 0 |
| 2 | 1 | 0 | diff → i++, copy | 1 |
| 3 | 1 | 1 | same → skip | 1 |
| 4 | 1 | 1 | same → skip | 1 |
| 5 | 2 | 1 | diff → i++, copy | 2 |
| 6 | 2 | 2 | same → skip | 2 |
| 7 | 3 | 2 | diff → i++, copy | 3 |
| 8 | 3 | 3 | same → skip | 3 |
| 9 | 4 | 3 | diff → i++, copy | 4 |

**Result:** `k = 5`, array → `[0,1,2,3,4,_,_,_,_,_]` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force (Set) | O(n) | O(n) |
| **Two Pointer (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Two Pointers · In-Place Modification

### 🧠 Mnemonic
**"Compare → New? → Move → Copy"**

---

## 3. Concatenation of Array

**LeetCode:** [1929. Concatenation of Array](https://leetcode.com/problems/concatenation-of-array/)

### 🧩 Problem
Given an array `nums` of size `n`, return a new array of size `2n` where the second half is a duplicate of the first half.

### 💡 Approach 1 — Brute Force (Two Separate Loops)
- Create a new array of size `2n`.
- First loop: copy `nums` into indices `0` to `n-1`.
- Second loop: copy `nums` again into indices `n` to `2n-1`.

```java
class Solution {
    public int[] getConcatenationBrute(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];

        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];
        }
        for (int i = 0; i < n; i++) {
            ans[i + n] = nums[i];
        }
        return ans;
    }
}
```
- **Time:** O(n)
- **Space:** O(n)

### 💡 Approach 2 — Optimal (Single Combined Loop)
- Fill both halves in **one** pass instead of two separate loops.

```java
class Solution {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];

        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];
            ans[i + n] = nums[i];
        }
        return ans;
    }
}
```

### 📝 Dry Run (Optimal)
`nums = [1,2,1]`

| i | ans[i] | ans[i+n] |
|---|--------|----------|
| 0 | 1 | 1 |
| 1 | 2 | 2 |
| 2 | 1 | 1 |

**Result:** `[1,2,1,1,2,1]` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force (Two Loops) | O(n) | O(n) |
| **Optimal (One Loop)** | **O(n)** | **O(n)** *(output requires it)* |

### 🎯 Pattern
👉 Array Traversal / Simulation

---

## 4. Longest Common Prefix (LeetCode 14)

### 🧩 Problem
Find the longest common prefix string among an array of strings. If no common prefix exists, return `""`.

### 💡 Approach 1 — Brute Force (Vertical Scanning)
- Compare characters **column by column** across all strings.
- For each character position, check if it matches in every string.
- Stop at the first mismatch or when the shortest string ends.

```java
class Solution {
    public String longestCommonPrefixBrute(String[] strs) {
        if (strs.length == 0) return "";

        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
```
- **Time:** O(n × m)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (Horizontal Scanning)
- Take the first string as a reference prefix.
- Compare it against every other string as a whole.
- Shrink the prefix whenever a mismatch or string-end is found.
- Return the final prefix.

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}
```

### 📝 Dry Run (Optimal)
`strs = ["flower","flow","flight"]`

| Step | Compare with | prefix (before) | prefix (after) |
|------|---------------|------------------|-----------------|
| 1 | "flow" | "flower" | "flow" |
| 2 | "flight" | "flow" | "fl" |

**Result:** `"fl"` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force (Vertical Scan) | O(n × m) | O(1) |
| Optimal (Horizontal Scan) | O(n × m) | O(1) |

*Both are the same order here — the difference is scanning style (column-wise vs string-wise), and horizontal scan is generally preferred for cleaner early exits.*

### 🎯 Pattern
👉 Array Traversal / Simulation

---

## 5. Largest Element in an Array

### 🧩 Problem
Given an array of integers, find the largest element.

### 💡 Approach 1 — Brute Force (Sorting)
- Sort the array in ascending order.
- Return the last element.

```java
class Solution {
    public int largestBrute(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - 1];
    }
}
```
- **Time:** O(n log n)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (Single Pass)
- Assume `nums[0]` is the largest.
- Traverse once, updating whenever a bigger element is found.

```java
class Solution {
    public int largestOptimal(int[] nums) {
        int largest = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > largest) {
                largest = nums[i];
            }
        }
        return largest;
    }
}
```

### 📝 Dry Run (Optimal)
`nums = [2,5,1,8,3]`

| i | nums[i] | largest |
|---|---------|---------|
| - | - | 2 |
| 1 | 5 | 5 |
| 2 | 1 | 5 |
| 3 | 8 | 8 |
| 4 | 3 | 8 |

**Result:** `8` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force (Sort) | O(n log n) | O(1) |
| **Single Pass (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Array Traversal / Simulation

---

## 6. Second Largest Element in an Array

### 🧩 Problem
Given an array of integers, find the second largest **distinct** element. If it doesn't exist, return `-1`.

### 💡 Approach 1 — Brute Force (Sorting)
- Sort the array.
- Traverse backward from the largest to find the first different value.

```java
class Solution {
    public int secondLargestBrute(int[] nums) {
        Arrays.sort(nums);
        int largest = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] != largest) return nums[i];
        }
        return -1;
    }
}
```
- **Time:** O(n log n)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (Single Pass)
- Maintain `largest` and `secondLargest`.
- Update both while traversing; skip duplicates of `largest`.

```java
class Solution {
    public int secondLargestOptimal(int[] nums) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }
        return secondLargest == Integer.MIN_VALUE ? -1 : secondLargest;
    }
}
```

### 📝 Dry Run (Optimal)
`nums = [8,2,8,5,3]`

| num | largest | secondLargest |
|-----|---------|-----------------|
| 8 | 8 | MIN |
| 2 | 8 | 2 |
| 8 | 8 | 2 (duplicate skipped) |
| 5 | 8 | 5 |
| 3 | 8 | 5 |

**Result:** `5` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force (Sort) | O(n log n) | O(1) |
| **Single Pass (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Array Traversal / Simulation

---

## 7. Left Rotate Array by One Place

### 🧩 Problem
Rotate the array to the **left by one position**.

```text
Input:  nums = [1,2,3,4,5]
Output: [2,3,4,5,1]
```

### 💡 Approach 1 — Brute Force (Extra Array)
- Create a new array of the same size.
- Copy `nums[1..n-1]` into positions `0..n-2` of the new array.
- Place `nums[0]` at the last index of the new array.
- Copy back into `nums`.

```java
class Solution {
    public void leftRotateByOneBrute(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n];

        for (int i = 1; i < n; i++) {
            temp[i - 1] = nums[i];
        }
        temp[n - 1] = nums[0];

        System.arraycopy(temp, 0, nums, 0, n);
    }
}
```
- **Time:** O(n)
- **Space:** O(n)

### 💡 Approach 2 — Optimal (In-Place Shift)
- Store `nums[0]` in a temp variable.
- Shift every element one position left: `nums[i] = nums[i+1]`.
- Place the stored value at the last index.

```java
class Solution {
    public void leftRotateByOne(int[] nums) {
        int n = nums.length;
        int first = nums[0];

        for (int i = 0; i < n - 1; i++) {
            nums[i] = nums[i + 1];
        }
        nums[n - 1] = first;
    }
}
```

### 📝 Dry Run (Optimal)
`nums = [1,2,3,4,5]`

| Step | Array State |
|------|--------------|
| Store first = 1 | `[1,2,3,4,5]` |
| Shift left | `[2,3,4,5,5]` |
| Place first at end | `[2,3,4,5,1]` ✅ |

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force (Extra Array) | O(n) | O(n) |
| **In-Place Shift (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Array Traversal / Simulation

---

## 8. Rotate Array (LeetCode 189)

### 🧩 Problem
Rotate the array to the **right by k steps** (k non-negative).

```text
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
```

### 💡 Approach 1 — Brute Force
Rotate one step at a time, `k` times.

```java
public void rotateBruteForce(int[] nums, int k) {
    int n = nums.length;
    k = k % n;

    for (int i = 0; i < k; i++) {
        int last = nums[n - 1];
        for (int j = n - 1; j > 0; j--) {
            nums[j] = nums[j - 1];
        }
        nums[0] = last;
    }
}
```
- **Time:** O(n × k)
- **Space:** O(1)

### 💡 Approach 2 — Extra Array
Place each element directly at its rotated index.

```java
public void rotateExtraArray(int[] nums, int k) {
    int n = nums.length;
    k = k % n;

    int[] temp = new int[n];
    for (int i = 0; i < n; i++) {
        temp[(i + k) % n] = nums[i];
    }
    System.arraycopy(temp, 0, nums, 0, n);
}
```
- **Time:** O(n)
- **Space:** O(n)

### 💡 Approach 3 — Reversal Trick (Optimal)
Reverse whole array → reverse first `k` → reverse remaining `n-k`.

```java
public void rotateOptimal(int[] nums, int k) {
    int n = nums.length;
    k = k % n;

    reverse(nums, 0, n - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, n - 1);
}

private void reverse(int[] nums, int start, int end) {
    while (start < end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
    }
}
```
- **Time:** O(n)
- **Space:** O(1)

### 📝 Dry Run (Optimal)
`nums = [1,2,3,4,5,6,7]`, `k = 3`

| Step | Action | Array State |
|------|--------|--------------|
| 1 | Reverse whole array | `[7,6,5,4,3,2,1]` |
| 2 | Reverse first k=3 | `[5,6,7,4,3,2,1]` |
| 3 | Reverse remaining n-k=4 | `[5,6,7,1,2,3,4]` ✅ |

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n × k) | O(1) |
| Extra Array | O(n) | O(n) |
| **Reversal (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Reversal Trick · Two Pointer

---

## 9. Left Rotate Array by k Places

### 🧩 Problem
Rotate the array to the **left by k steps**.

```text
Input: nums = [1,2,3,4,5,6,7], k = 2
Output: [3,4,5,6,7,1,2]
```

### 💡 Approach 1 — Brute Force
Rotate one step to the left at a time, repeat `k` times.

```java
public void rotateLeftBruteForce(int[] nums, int k) {
    int n = nums.length;
    k = k % n;

    for (int i = 0; i < k; i++) {
        int first = nums[0];
        for (int j = 0; j < n - 1; j++) {
            nums[j] = nums[j + 1];
        }
        nums[n - 1] = first;
    }
}
```
- **Time:** O(n × k)
- **Space:** O(1)

### 💡 Approach 2 — Extra Array
Place each element directly at its left-rotated index.

```java
public void rotateLeftExtraArray(int[] nums, int k) {
    int n = nums.length;
    k = k % n;

    int[] temp = new int[n];
    for (int i = 0; i < n; i++) {
        temp[((i - k) % n + n) % n] = nums[i];
    }
    System.arraycopy(temp, 0, nums, 0, n);
}
```
- **Time:** O(n)
- **Space:** O(n)

### 💡 Approach 3 — Reversal Trick (Optimal)
Reverse first `k` → reverse remaining `n-k` → reverse whole array.

```java
public void rotateLeftOptimal(int[] nums, int k) {
    int n = nums.length;
    k = k % n;

    reverse(nums, 0, k - 1);
    reverse(nums, k, n - 1);
    reverse(nums, 0, n - 1);
}
```
- **Time:** O(n)
- **Space:** O(1)

### 📝 Dry Run (Optimal)
`nums = [1,2,3,4,5,6,7]`, `k = 2`

| Step | Action | Array State |
|------|--------|--------------|
| 1 | Reverse first k=2 | `[2,1,3,4,5,6,7]` |
| 2 | Reverse remaining n-k=5 | `[2,1,7,6,5,4,3]` |
| 3 | Reverse whole array | `[3,4,5,6,7,1,2]` ✅ |

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n × k) | O(1) |
| Extra Array | O(n) | O(n) |
| **Reversal (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Reversal Trick — mirror image of right rotate (swap the reversal order)

---

## 10. Move Zeroes (LeetCode 283)

### 🧩 Problem
Move all `0`'s to the end of the array while maintaining the relative order of non-zero elements — **in-place**.

```text
Input:  nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
```

### 💡 Approach 1 — Brute Force (Extra Array)
Copy non-zero elements to a new array in order, remaining slots stay `0`.

```java
class Solution {
    public void moveZeroesBrute(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n];
        int index = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                temp[index++] = nums[i];
            }
        }
        System.arraycopy(temp, 0, nums, 0, n);
    }
}
```
- **Time:** O(n)
- **Space:** O(n)

### 💡 Approach 2 — Optimal (Two Pointer, In-Place)
`j` tracks where the next non-zero element should go; swap with `nums[i]` while scanning.

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int j = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }
}
```
- **Time:** O(n)
- **Space:** O(1)

### 📝 Dry Run (Optimal)
`nums = [0,1,0,3,12]`

| i | nums[i] | j (before) | Action | Array State | j (after) |
|---|---------|------------|--------|--------------|-----------|
| 0 | 0 | 0 | skip | `[0,1,0,3,12]` | 0 |
| 1 | 1 | 0 | swap nums[1] & nums[0] | `[1,0,0,3,12]` | 1 |
| 2 | 0 | 1 | skip | `[1,0,0,3,12]` | 1 |
| 3 | 3 | 1 | swap nums[3] & nums[1] | `[1,3,0,0,12]` | 2 |
| 4 | 12 | 2 | swap nums[4] & nums[2] | `[1,3,12,0,0]` | 3 |

**Result:** `[1,3,12,0,0]` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force (Extra Array) | O(n) | O(n) |
| **Two Pointer (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Two Pointer — same idea as `reverse()` in Rotate Array and shifting logic in Remove Duplicates.

---

## 11. Single Number (LeetCode 136)

### 🧩 Problem
Given a non-empty array of integers `nums`, every element appears **twice** except for one. Find that single one — must run in O(n) time and O(1) extra space.

```text
Input: nums = [4,1,2,1,2]
Output: 4
```

### 💡 Approach 1 — Brute Force (HashMap Frequency Count)
- Count occurrences of every number.
- Scan again for the one with count `1`.

```java
class Solution {
    public int singleNumberBrute(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (countMap.get(num) == 1) {
                return num;
            }
        }
        return -1; // unreachable given constraints
    }
}
```
- **Time:** O(n)
- **Space:** O(n) — extra map used; violates the O(1) space requirement

### 💡 Approach 2 — Optimal (XOR Accumulation)
- XOR every element together in a single pass.
- Duplicate pairs cancel to `0` (`a ^ a = 0`); XOR with `0` leaves a value unchanged (`a ^ 0 = a`).
- Whatever survives is the single number.

```java
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
```

### 📝 Dry Run (Optimal)
`nums = [4,1,2,1,2]`

| Step | num | result (running XOR) |
|------|-----|------------------------|
| start | — | 0 |
| 1 | 4 | 0 ^ 4 = 4 |
| 2 | 1 | 4 ^ 1 = 5 |
| 3 | 2 | 5 ^ 2 = 7 |
| 4 | 1 | 7 ^ 1 = 6 |
| 5 | 2 | 6 ^ 2 = 4 |

**Result:** `4` ✅

**Why it works (regrouping via associativity/commutativity):**
```
4 ^ 1 ^ 2 ^ 1 ^ 2
= 4 ^ (1 ^ 1) ^ (2 ^ 2)   // pairs cancel: a ^ a = 0
= 4 ^ 0 ^ 0
= 4                        // a ^ 0 = a
```
Java evaluates left-to-right, but XOR is order-independent, so the final result always matches this regrouped form regardless of where duplicates sit in the array.

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force (HashMap) | O(n) | O(n) |
| **XOR Accumulation (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 XOR / Bit Manipulation — same "cancel-the-pairs" idea reappears in Single Number II/III.

---

## 12. Two Sum (LeetCode 1)

### 🧩 Problem
Given an array of integers `nums` and an integer `target`, return the indices of the two numbers that add up to `target`. Exactly one solution exists; the same element cannot be used twice. Array is **not sorted**.

```text
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
```

### 💡 Approach 1 — Brute Force (Nested Loop)
Try every pair of elements and check if they sum to `target`.

```java
class Solution {
    public int[] twoSumBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; // unreachable given constraints
    }
}
```
- **Time:** O(n²)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (HashMap Complement Lookup)
- For each element, compute the `complement` needed to hit `target`.
- Check if that complement was already seen (O(1) lookup).
- If not, store the current value with its index and move on.

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(); // value -> index

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }
        return new int[]{-1, -1}; // unreachable given constraints
    }
}
```

### 📝 Dry Run (Optimal)
`nums = [2,7,11,15]`, `target = 9`

| i | nums[i] | complement | in map? | map after |
|---|---------|------------|---------|-----------|
| 0 | 2 | 9-2=7 | No (empty) | {2:0} |
| 1 | 7 | 9-7=2 | Yes → index 0 | return `[0,1]` |

**Result:** `[0,1]` ✅

**Why it works:** The map only ever holds elements seen *before* the current index, so an element can never pair with itself. `containsKey()` is O(1) average, turning an O(n²) pair search into a single O(n) pass.

**Note on Two Pointers:** Two-pointer (`left`/`right` converging) only works on a **sorted** array, since moving a pointer inward changes the sum in a predictable direction. This array is unsorted and original indices must be preserved, so sorting would scramble index positions — making two-pointer unsuitable here. *(Two-pointer is the correct approach for the sorted variant: LeetCode 167 — Two Sum II.)*

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force (Nested Loop) | O(n²) | O(1) |
| **HashMap (Optimal)** | **O(n)** | **O(n)** |

### 🎯 Pattern
👉 HashMap / Complement Lookup — reusable pattern seen again in 3Sum, 4Sum, Subarray Sum Equals K.

---

## 13. Sort Colors (LeetCode 75)

### 🧩 Problem
Given an array `nums` with `n` objects colored red, white, or blue (represented by `0`, `1`, `2`), sort them **in-place** so objects of the same color are adjacent, in the order red, white, blue — **without using a library sort function**.

```text
Input:  nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
```

### 💡 Approach 1 — Brute Force (Library Sort)
- Just call `Arrays.sort()`. Works, but violates the problem's constraint and doesn't demonstrate understanding of the structure (only 3 distinct values).

```java
class Solution {
    public void sortColorsBrute(int[] nums) {
        Arrays.sort(nums);
    }
}
```
- **Time:** O(n log n)
- **Space:** O(1)

### 💡 Approach 2 — Better (Counting Sort, Two Pass)
- Count occurrences of `0`, `1`, `2` in one pass.
- Overwrite the array in a second pass using the counts.

```java
class Solution {
    public void sortColorsCounting(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num == 0) count0++;
            else if (num == 1) count1++;
            else count2++;
        }

        int i = 0;
        while (count0-- > 0) nums[i++] = 0;
        while (count1-- > 0) nums[i++] = 1;
        while (count2-- > 0) nums[i++] = 2;
    }
}
```
- **Time:** O(n) — but two passes
- **Space:** O(1)

### 💡 Approach 3 — Optimal (Dutch National Flag Algorithm, Single Pass)
- Maintain three pointers: `low` (boundary for `0`s), `mid` (current element), `high` (boundary for `2`s).
- `nums[mid] == 0` → swap with `low`, advance both `low` and `mid`.
- `nums[mid] == 1` → already in place, advance `mid`.
- `nums[mid] == 2` → swap with `high`, decrement `high` — **don't** advance `mid`, since the swapped-in element from `high` hasn't been checked yet.
- Loop while `mid <= high`.

```java
class Solution {
    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[mid];
                nums[mid] = nums[low];
                nums[low] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
                // mid stays put — recheck swapped-in value
            }
        }
    }
}
```

### 📝 Dry Run (Optimal)
`nums = [2,0,1]`

| low | mid | high | nums[mid] | Action | Array State |
|-----|-----|------|-----------|--------|--------------|
| 0 | 0 | 2 | 2 | swap(mid,high), high-- | `[1,0,2]` |
| 0 | 0 | 1 | 1 | mid++ | `[1,0,2]` |
| 0 | 1 | 1 | 0 | swap(low,mid), low++, mid++ | `[0,1,2]` |
| — | 2 | 1 | — | mid > high → stop | `[0,1,2]` |

**Result:** `[0,1,2]` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force (Library Sort) | O(n log n) | O(1) |
| Counting Sort (Two Pass) | O(n) | O(1) |
| **Dutch National Flag (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Three-Way Partitioning / Two Pointer — a specialized variant of the two-pointer pattern for arrays with a small, fixed set of distinct values (also underlies quicksort's 3-way partition scheme).

---

## 14. Majority Element (LeetCode 169)

### 🧩 Problem
Given an array of size `n`, return the majority element — the element that appears **more than `⌊n/2⌋` times**. The problem guarantees a majority element always exists.

```text
Input:  nums = [2,2,1,1,1,2,2]
Output: 2
```

### 💡 Approach 1 — Brute Force (HashMap Frequency Count)
- Count frequency of each element using a hashmap.
- Traverse the map to find the element with the maximum frequency.

```java
class Solution {
    public int majorityElementBrute(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int candidate = nums[0];
        int maxCount = 0;
        for (int key : map.keySet()) {
            if (map.get(key) > maxCount) {
                maxCount = map.get(key);
                candidate = key;
            }
        }
        return candidate;
    }
}
```
- **Time:** O(n)
- **Space:** O(n) — extra hashmap used

### 💡 Approach 2 — Optimal (Boyer-Moore Voting Algorithm)
- Maintain a `candidate` and a `count`.
- If `count == 0`, set `candidate = nums[i]`.
- If `nums[i] == candidate`, increment `count`; otherwise decrement it.
- Since the majority element appears more than `n/2` times, it always "outlasts" the cancellations from every other element combined — whatever candidate remains at the end is guaranteed correct.

```java
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}
```

### 📝 Dry Run (Optimal)
`nums = [2,2,1,1,1,2,2]`

| index | element | count before | action | candidate | count after |
|---|---|---|---|---|---|
| 0 | 2 | 0 | count=0 → candidate=2 | 2 | 1 |
| 1 | 2 | 1 | match → count++ | 2 | 2 |
| 2 | 1 | 2 | no match → count-- | 2 | 1 |
| 3 | 1 | 1 | no match → count-- | 2 | 0 |
| 4 | 1 | 0 | count=0 → candidate=1 | 1 | 1 |
| 5 | 2 | 1 | no match → count-- | 1 | 0 |
| 6 | 2 | 0 | count=0 → candidate=2 | 2 | 1 |

**Result:** `2` ✅ (appears 4/7 times — matches the guarantee of >⌊n/2⌋)

Note that the candidate flips mid-way (2 → 1 → 2) and `count` even hits `0` a few times — that's expected. The algorithm only guarantees the **final** candidate is correct, since the true majority element always has enough "votes" to outlast every dip.

**Edge case:** Single-element array `[5]` → `count == 0` sets `candidate = 5`, then `candidate == nums[0]` → `count++` → returns `5` ✅

**Related insight — sorting approach:** Since the majority element occupies more than half the array, after sorting it is always guaranteed to sit at index `n/2` (0-indexed). This gives an alternative O(n log n) solution via `Arrays.sort()` + return `nums[n/2]`.

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force (HashMap) | O(n) | O(n) |
| Sorting (`arr[n/2]`) | O(n log n) | O(1) |
| **Boyer-Moore Voting (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Boyer-Moore Voting Algorithm — candidate/counter "tug-of-war" pattern, foundational for Majority Element II and other voting-based problems.

---

## 15. Best Time to Buy and Sell Stock (LeetCode 121)

### 🧩 Problem
Given an array `prices` where `prices[i]` is the stock price on day `i`, find the maximum profit achievable by buying on one day and selling on a **later** day. If no profit is possible, return `0`. Only **one** transaction is allowed (one buy + one sell).

```text
Input:  prices = [7,1,5,3,6,4]
Output: 5   // buy at 1, sell at 6
```

### 💡 Approach 1 — Brute Force (Check Every Pair)
- For every day `i`, check every later day `j` and compute `prices[j] - prices[i]`.
- Track the maximum profit seen across all pairs.

```java
class Solution {
    public int maxProfitBrute(int[] prices) {
        int n = prices.length;
        int maxProfit = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int profit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        return maxProfit;
    }
}
```
- **Time:** O(n²)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (Single Pass, Running Minimum)
- Track the minimum price seen so far (`min`) while scanning left to right.
- At each day, compute the profit from selling today after buying at `min`.
- Keep a running `profit = max(profit, prices[i] - min)`.
- Since `profit` starts at `0` and is only ever updated via `max()` against itself, it can never go negative — no separate "if profit < 0" guard is needed.

```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int profit = 0;
        int min = prices[0];

        for (int i = 0; i < n; i++) {
            min = Math.min(prices[i], min);
            int sum = prices[i] - min;
            profit = Math.max(profit, sum);
        }
        return profit;
    }
}
```

### 📝 Dry Run (Optimal)
`prices = [7,1,5,3,6,4]`

| i | prices[i] | min (after update) | sum | profit (after max) |
|---|---|---|---|---|
| 0 | 7 | 7 | 0 | 0 |
| 1 | 1 | 1 | 0 | 0 |
| 2 | 5 | 1 | 4 | 4 |
| 3 | 3 | 1 | 2 | 4 |
| 4 | 6 | 1 | 5 | 5 |
| 5 | 4 | 1 | 3 | 5 |

**Result:** `5` ✅ (buy at 1, sell at 6)

**Edge case — strictly decreasing array:** `prices = [7,6,4,3,1]` → `min` keeps dropping to match `prices[i]` every step, so `sum` is always `0` and `profit` stays `0` the whole way through — correctly signaling "no profitable trade exists," with no negative values ever appearing.

**Why the running-minimum trick works:** At any index `i`, the best possible profit selling on that day is `prices[i] - (lowest price at or before i)`. Tracking that lowest price as you go — instead of re-scanning backward for it — is what collapses the O(n²) pair search into a single O(n) pass.

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force (Check Every Pair) | O(n²) | O(1) |
| **Running Minimum (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Running Min/Max — Single Pass — same "track-a-running-value-as-you-scan" idea as Largest/Second Largest Element, generalizes to Best Time to Buy and Sell Stock II/III/IV variants.

---

## 📈 Master Complexity Table

| # | Problem | Brute Time | Brute Space | Optimal Time | Optimal Space |
|---|---------|-------------|--------------|----------------|-----------------|
| 1 | Max Consecutive Ones | O(n²) | O(1) | O(n) | O(1) |
| 2 | Remove Duplicates | O(n) | O(n) | O(n) | O(1) |
| 3 | Concatenation of Array | O(n) | O(n) | O(n) | O(n) |
| 4 | Longest Common Prefix | O(n×m) | O(1) | O(n×m) | O(1) |
| 5 | Largest Element | O(n log n) | O(1) | O(n) | O(1) |
| 6 | Second Largest Element | O(n log n) | O(1) | O(n) | O(1) |
| 7 | Left Rotate by One | O(n) | O(n) | O(n) | O(1) |
| 8 | Rotate Array (Right, k) | O(n×k) | O(1) | O(n) | O(1) |
| 9 | Left Rotate by k | O(n×k) | O(1) | O(n) | O(1) |
| 10 | Move Zeroes | O(n) | O(n) | O(n) | O(1) |
| 11 | Single Number | O(n) | O(n) | O(n) | O(1) |
| 12 | Two Sum | O(n²) | O(1) | O(n) | O(n) |
| 13 | Sort Colors | O(n log n) | O(1) | O(n) | O(1) |
| 14 | Majority Element | O(n) | O(n) | O(n) | O(1) |
| 15 | Best Time to Buy and Sell Stock | O(n²) | O(1) | O(n) | O(1) |

---

## 🧠 Core Patterns in This Repo

- **Two-Pointer Technique** — Remove Duplicates, Move Zeroes, Reversal Trick
- **In-Place Reversal** — Rotate Array (left & right)
- **Single-Pass Traversal** — Max Consecutive Ones, Largest/Second Largest Element
- **Running Min/Max Tracking** — Best Time to Buy and Sell Stock
- **Simulation** — Concatenation, Longest Common Prefix, Left Rotate by One
- **XOR / Bit Manipulation** — Single Number
- **HashMap / Complement Lookup** — Two Sum
- **Three-Way Partitioning** — Sort Colors (Dutch National Flag)
- **Boyer-Moore Voting** — Majority Element (candidate/counter cancellation)

## 🚀 How to Use
Each solution is written as a standalone `class Solution` — paste directly into LeetCode, or run locally with a `main` method for quick testing.

---
<div align="center">

*Maintained as part of ongoing DSA practice — array & two-pointer patterns solved regularly.* 🧩

</div>
