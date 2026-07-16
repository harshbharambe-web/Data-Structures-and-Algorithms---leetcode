<div align="center">

# 📊 Arrays — DSA Practice Repository

![Java](https://img.shields.io/badge/Language-Java-orange?style=flat-square&logo=java)
![Topic](https://img.shields.io/badge/Topic-Arrays-blue?style=flat-square)
![Pattern](https://img.shields.io/badge/Pattern-Two%20Pointer-green?style=flat-square)
![Problems](https://img.shields.io/badge/Problems-10-purple?style=flat-square)

A curated set of **Array** problems solved with a **Brute Force → Optimal** progression, complete with dry runs, complexity analysis, and pattern notes — built for interview prep.

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

---

## 1. Maximum Consecutive Ones

**LeetCode:** [485. Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/)

### 🧩 Problem
Given a binary array `nums`, return the maximum number of consecutive `1`'s in the array.

### 💡 Approach (Optimal)
- Traverse the array once.
- Maintain a counter for consecutive `1`'s.
- If current element is `1` → increment count, update max.
- If current element is `0` → reset count to `0`.

### ☕ Code
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

### 📝 Dry Run
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

### ⚙️ Complexity
- **Time:** O(n)
- **Space:** O(1)

### 🎯 Pattern
👉 Array Traversal / Counting

---

## 2. Remove Duplicates from Sorted Array (LeetCode 26)

### 🧩 Problem
Given a **sorted** integer array `nums`, remove duplicates **in-place** so each unique element appears once. Return the count of unique elements `k`. The first `k` elements should hold the unique values in original order.

### 💡 Approach (Two Pointer) — Optimal
- If array is empty, return `0`.
- `i` → tracks the last unique element's index.
- `j` → scans ahead through the array.
- If `nums[i] != nums[j]` → increment `i`, copy `nums[j]` into `nums[i]`.
- Return `i + 1`.

### ☕ Code
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

### 📝 Dry Run
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

### ⚙️ Complexity
- **Time:** O(n)
- **Space:** O(1)

### 🎯 Pattern
👉 Two Pointers · In-Place Modification

### 🧠 Mnemonic
**"Compare → New? → Move → Copy"**

---

## 3. Concatenation of Array

**LeetCode:** [1929. Concatenation of Array](https://leetcode.com/problems/concatenation-of-array/)

### 🧩 Problem
Given an array `nums` of size `n`, return a new array of size `2n` where the second half is a duplicate of the first half.

### 💡 Approach (Simulation) — Only approach needed
- Create a new array of size `2n`.
- Copy `nums` into indices `0` to `n-1`.
- Copy `nums` again into indices `n` to `2n-1`.

### ☕ Code
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

### 📝 Dry Run
`nums = [1,2,1]`

| i | ans[i] | ans[i+n] |
|---|--------|----------|
| 0 | 1 | 1 |
| 1 | 2 | 2 |
| 2 | 1 | 1 |

**Result:** `[1,2,1,1,2,1]` ✅

### ⚙️ Complexity
- **Time:** O(n)
- **Space:** O(n) *(required — output itself is size 2n)*

### 🎯 Pattern
👉 Array Traversal / Simulation

---

## 4. Longest Common Prefix (LeetCode 14)

### 🧩 Problem
Find the longest common prefix string among an array of strings. If no common prefix exists, return `""`.

### 💡 Approach (Horizontal Scan) — Standard approach
- Take the first string as a reference prefix.
- Compare it against every other string, character by character.
- Shrink the prefix whenever a mismatch or string-end is found.
- Return the final prefix.

### ☕ Code
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

### 📝 Dry Run
`strs = ["flower","flow","flight"]`

| Step | Compare with | prefix (before) | prefix (after) |
|------|---------------|------------------|-----------------|
| 1 | "flow" | "flower" | "flow" |
| 2 | "flight" | "flow" | "fl" |

**Result:** `"fl"` ✅

### ⚙️ Complexity
- **Time:** O(n × m) — n = number of strings, m = length of shortest string
- **Space:** O(1)

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

### 💡 Approach (Optimal)
- Store `nums[0]` in a temp variable.
- Shift every element one position left: `nums[i] = nums[i+1]`.
- Place the stored value at the last index.

### ☕ Code
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

### 📝 Dry Run
`nums = [1,2,3,4,5]`

| Step | Array State |
|------|--------------|
| Store first = 1 | `[1,2,3,4,5]` |
| Shift left | `[2,3,4,5,5]` |
| Place first at end | `[2,3,4,5,1]` ✅ |

### ⚙️ Complexity
- **Time:** O(n)
- **Space:** O(1)

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

### 💡 Approach — Reversal Trick (Optimal)
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

### 📝 Dry Run
`nums = [1,2,3,4,5,6,7]`, `k = 2`

| Step | Action | Array State |
|------|--------|--------------|
| 1 | Reverse first k=2 | `[2,1,3,4,5,6,7]` |
| 2 | Reverse remaining n-k=5 | `[2,1,7,6,5,4,3]` |
| 3 | Reverse whole array | `[3,4,5,6,7,1,2]` ✅ |

### ⚙️ Complexity
- **Time:** O(n)
- **Space:** O(1)

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

## 📈 Master Complexity Table

| # | Problem | Brute Time | Brute Space | Optimal Time | Optimal Space |
|---|---------|-------------|--------------|----------------|-----------------|
| 1 | Max Consecutive Ones | — | — | O(n) | O(1) |
| 2 | Remove Duplicates | — | — | O(n) | O(1) |
| 3 | Concatenation of Array | O(n) | O(n) | — | — |
| 4 | Longest Common Prefix | O(n×m) | O(1) | — | — |
| 5 | Largest Element | O(n log n) | O(1) | O(n) | O(1) |
| 6 | Second Largest Element | O(n log n) | O(1) | O(n) | O(1) |
| 7 | Left Rotate by One | — | — | O(n) | O(1) |
| 8 | Rotate Array (Right, k) | O(n×k) | O(1) | O(n) | O(1) |
| 9 | Left Rotate by k | — | — | O(n) | O(1) |
| 10 | Move Zeroes | O(n) | O(n) | O(n) | O(1) |

---

## 🧠 Core Patterns in This Repo

- **Two-Pointer Technique** — Remove Duplicates, Move Zeroes, Reversal Trick
- **In-Place Reversal** — Rotate Array (left & right)
- **Single-Pass Traversal** — Max Consecutive Ones, Largest/Second Largest Element
- **Simulation** — Concatenation, Longest Common Prefix, Left Rotate by One

## 🚀 How to Use
Each solution is written as a standalone `class Solution` — paste directly into LeetCode, or run locally with a `main` method for quick testing.

---
<div align="center">

*Maintained as part of ongoing DSA practice — array & two-pointer patterns solved regularly.* 🧩

</div>
