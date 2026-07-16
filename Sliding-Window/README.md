<div align="center">

# 🪟 Sliding Window — DSA Practice Repository

![Java](https://img.shields.io/badge/Language-Java-orange?style=flat-square&logo=java)
![Topic](https://img.shields.io/badge/Topic-Sliding%20Window-blue?style=flat-square)
![Pattern](https://img.shields.io/badge/Pattern-Two%20Pointers-green?style=flat-square)
![Problems](https://img.shields.io/badge/Problems-12-purple?style=flat-square)

Every problem here is solved with **both** a Brute Force and an Optimal (Sliding Window) approach, complete with dry runs, complexity analysis, and pattern notes — built for interview prep.

</div>

---

## 📚 Table of Contents

| # | Problem | Window Type |
|---|---------|-------------|
| 1 | [Longest Substring Without Repeating Characters](#1-longest-substring-without-repeating-characters-leetcode-3) | Variable |
| 2 | [Maximum Sum Subarray of Size K](#2-maximum-sum-subarray-of-size-k) | Fixed |
| 3 | [Maximum Points You Can Obtain from Cards](#3-maximum-points-you-can-obtain-from-cards-leetcode-1423) | Fixed |
| 4 | [Minimum Size Subarray Sum](#4-minimum-size-subarray-sum-leetcode-209) | Variable |
| 5 | [Max Consecutive Ones III](#5-max-consecutive-ones-iii-leetcode-1004) | Variable |
| 6 | [Fruit Into Baskets](#6-fruit-into-baskets-leetcode-904) | Variable |
| 7 | [Longest Substring with At Most K Distinct Characters](#7-longest-substring-with-at-most-k-distinct-characters) | Variable |
| 8 | [Number of Substrings Containing All Three Characters](#8-number-of-substrings-containing-all-three-characters-leetcode-1358) | Variable |
| 9 | [Longest Repeating Character Replacement](#9-longest-repeating-character-replacement-leetcode-424) | Variable |
| 10 | [Binary Subarrays With Sum](#10-binary-subarrays-with-sum-leetcode-930) | AtMost Trick |
| 11 | [Count Number of Nice Subarrays](#11-count-number-of-nice-subarrays-leetcode-1248) | AtMost Trick |
| 12 | [Subarrays with K Different Integers](#12-subarrays-with-k-different-integers-leetcode-992) | AtMost Trick |

---

## 1. Longest Substring Without Repeating Characters (LeetCode 3)

### 🧩 Problem
Given a string `s`, find the length of the longest substring without repeating characters.

### 💡 Approach 1 — Brute Force
- For every starting index `i`, expand `j` forward while adding characters to a `HashSet`.
- Stop expanding as soon as a duplicate is found; update max length.

```java
class Solution {
    public int lengthOfLongestSubstringBrute(String s) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = i; j < n; j++) {
                if (seen.contains(s.charAt(j))) break;
                seen.add(s.charAt(j));
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }
}
```
- **Time:** O(n²)
- **Space:** O(min(n, charset))

### 💡 Approach 2 — Optimal (Sliding Window + Last Seen Index)
- Maintain a `lastIndex` array storing the last position each character was seen.
- If the character at `right` was seen inside the current window, jump `left` past it.
- Update max length at every step.

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] lastIndex = new int[256];
        Arrays.fill(lastIndex, -1);

        int left = 0, maxLen = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastIndex[c] >= left) {
                left = lastIndex[c] + 1;
            }
            lastIndex[c] = right;
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```

### 📝 Dry Run (Optimal)
`s = "abcabcbb"`

| right | char | left (after) | window | maxLen |
|-------|------|----------------|--------|--------|
| 0 | a | 0 | "a" | 1 |
| 1 | b | 0 | "ab" | 2 |
| 2 | c | 0 | "abc" | 3 |
| 3 | a | 1 | "bca" | 3 |
| 4 | b | 2 | "cab" | 3 |
| 5 | c | 3 | "abc" | 3 |
| 6 | b | 5 | "cb" | 3 |
| 7 | b | 7 | "b" | 3 |

**Result:** `3` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(n) |
| **Sliding Window (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Variable-Size Sliding Window · Hash Array for last-seen index

---

## 2. Maximum Sum Subarray of Size K

### 🧩 Problem
Given an array and an integer `k`, find the maximum sum of any contiguous subarray of size `k`.

### 💡 Approach 1 — Brute Force
- For each starting index, sum the next `k` elements.
- Track the maximum sum found.

```java
class Solution {
    public int maxSumBrute(int[] nums, int k) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i <= n - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
}
```
- **Time:** O(n × k)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (Fixed Sliding Window)
- Build the sum of the first `k` elements.
- Slide the window forward: add the new right element, remove the leaving left element.

```java
class Solution {
    public int maxSum(int[] nums, int k) {
        int windowSum = 0;
        for (int i = 0; i < k; i++) windowSum += nums[i];

        int maxSum = windowSum;
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }
}
```

### 📝 Dry Run (Optimal)
`nums = [2,1,5,1,3,2]`, `k = 3`

| Window | Sum | maxSum |
|--------|-----|--------|
| [2,1,5] | 8 | 8 |
| [1,5,1] | 7 | 8 |
| [5,1,3] | 9 | 9 |
| [1,3,2] | 6 | 9 |

**Result:** `9` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n × k) | O(1) |
| **Sliding Window (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Fixed-Size Sliding Window

---

## 3. Maximum Points You Can Obtain from Cards (LeetCode 1423)

### 🧩 Problem
Given an array `cardPoints` and integer `k`, you can take exactly `k` cards from either the front or back. Return the maximum score.

### 💡 Approach 1 — Brute Force
- Try every split: take `i` cards from the front and `k - i` cards from the back, for `i = 0..k`.
- Compute the sum directly for each split.

```java
class Solution {
    public int maxScoreBrute(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int maxScore = 0;

        for (int i = 0; i <= k; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) sum += cardPoints[j];              // front i cards
            for (int j = 0; j < k - i; j++) sum += cardPoints[n - 1 - j];  // back (k-i) cards
            maxScore = Math.max(maxScore, sum);
        }
        return maxScore;
    }
}
```
- **Time:** O(k²)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (Sliding Window on Complement)
- Taking `k` cards from front/back is equivalent to **removing** a contiguous subarray of size `n - k` from the middle.
- Find the **minimum sum subarray** of size `n - k` using a sliding window.
- Answer = total sum − minimum subarray sum.

```java
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int windowSize = n - k;

        int totalSum = 0;
        for (int num : cardPoints) totalSum += num;

        if (windowSize == 0) return totalSum;

        int windowSum = 0;
        for (int i = 0; i < windowSize; i++) windowSum += cardPoints[i];

        int minWindowSum = windowSum;
        for (int i = windowSize; i < n; i++) {
            windowSum += cardPoints[i] - cardPoints[i - windowSize];
            minWindowSum = Math.min(minWindowSum, windowSum);
        }

        return totalSum - minWindowSum;
    }
}
```

### 📝 Dry Run (Optimal)
`cardPoints = [1,2,3,4,5,6,1]`, `k = 3` → `windowSize = n - k = 4`

| Window (size 4) | Sum | minWindowSum |
|------------------|-----|----------------|
| [1,2,3,4] | 10 | 10 |
| [2,3,4,5] | 14 | 10 |
| [3,4,5,6] | 18 | 10 |
| [4,5,6,1] | 16 | 10 |

`totalSum = 22`, `minWindowSum = 10` → **Result:** `22 - 10 = 12` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(k²) | O(1) |
| **Sliding Window (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Fixed-Size Sliding Window on the complement subarray

---

## 4. Minimum Size Subarray Sum (LeetCode 209)

### 🧩 Problem
Given an array of positive integers `nums` and a `target`, find the minimal length of a contiguous subarray whose sum is `≥ target`. Return `0` if none exists.

### 💡 Approach 1 — Brute Force
- For every starting index, keep adding elements until the sum reaches `target`.
- Track the minimum length found.

```java
class Solution {
    public int minSubArrayLenBrute(int target, int[] nums) {
        int n = nums.length;
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= target) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
```
- **Time:** O(n²)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (Variable Sliding Window)
- Expand the window using `right`, adding elements to `sum`.
- When `sum >= target`, update the minimum length, then shrink from `left` while the condition still holds.

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0, minLen = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
```

### 📝 Dry Run (Optimal)
`target = 7`, `nums = [2,3,1,2,4,3]`

| right | nums[right] | sum | Shrink? | minLen |
|-------|--------------|-----|---------|--------|
| 0 | 2 | 2 | no | ∞ |
| 1 | 3 | 5 | no | ∞ |
| 2 | 1 | 6 | no | ∞ |
| 3 | 2 | 8 | yes → shrink to sum=6, left=2 | 4 |
| 4 | 4 | 10 | yes → shrink to sum=7, left=3 → shrink to sum=6,left=4 | 3 |
| 5 | 3 | 9 | yes → shrink to sum=7,left=5 | 2 |

**Result:** `2` ✅ (subarray `[4,3]`)

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(1) |
| **Sliding Window (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Variable-Size Sliding Window — shrink while condition holds

---

## 5. Max Consecutive Ones III (LeetCode 1004)

### 🧩 Problem
Given a binary array `nums` and integer `k`, return the max number of consecutive `1`'s if you can flip at most `k` zeros.

### 💡 Approach 1 — Brute Force
- For every starting index, expand while the count of zeros in the window stays `≤ k`.
- Track the max window length.

```java
class Solution {
    public int longestOnesBrute(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int zeroCount = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] == 0) zeroCount++;
                if (zeroCount > k) break;
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }
}
```
- **Time:** O(n²)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (Variable Sliding Window)
- Expand with `right`; count zeros in the window.
- If zero count exceeds `k`, shrink from `left` until valid again.
- Track the max window size throughout (never shrinks below the best seen).

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0, zeroCount = 0, maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeroCount++;

            while (zeroCount > k) {
                if (nums[left] == 0) zeroCount--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```

### 📝 Dry Run (Optimal)
`nums = [1,1,1,0,0,0,1,1,1,1,0]`, `k = 2`

| right | nums[right] | zeroCount | left (after shrink) | maxLen |
|-------|--------------|-----------|------------------------|--------|
| 0–2 | 1,1,1 | 0 | 0 | 3 |
| 3 | 0 | 1 | 0 | 4 |
| 4 | 0 | 2 | 0 | 5 |
| 5 | 0 | 3 | shrink → left=1,zero=2 | 5 |
| 6–9 | 1,1,1,1 | 2 | 1 | 9 |
| 10 | 0 | 3 | shrink → left=4,zero=2 | 9 |

**Result:** `9` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(1) |
| **Sliding Window (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Variable-Size Sliding Window — `zeroCount ≤ k` invariant

---

## 6. Fruit Into Baskets (LeetCode 904)

### 🧩 Problem
Each basket holds only one fruit type, you have 2 baskets. Return the max number of fruits you can collect from a contiguous section of the tree.

### 💡 Approach 1 — Brute Force
- For every starting index, expand while the number of distinct fruit types stays `≤ 2`.

```java
class Solution {
    public int totalFruitBrute(int[] fruits) {
        int n = fruits.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> types = new HashSet<>();
            int j = i;
            while (j < n) {
                types.add(fruits[j]);
                if (types.size() > 2) break;
                j++;
            }
            maxLen = Math.max(maxLen, j - i);
        }
        return maxLen;
    }
}
```
- **Time:** O(n²)
- **Space:** O(1) *(at most 3 types tracked)*

### 💡 Approach 2 — Optimal (Sliding Window + HashMap)
- Maintain a frequency map of fruit types in the current window.
- If distinct types exceed 2, shrink from `left`, decrementing counts and removing a type when its count hits 0.

```java
class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < fruits.length; right++) {
            count.put(fruits[right], count.getOrDefault(fruits[right], 0) + 1);

            while (count.size() > 2) {
                count.put(fruits[left], count.get(fruits[left]) - 1);
                if (count.get(fruits[left]) == 0) count.remove(fruits[left]);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```

### 📝 Dry Run (Optimal)
`fruits = [1,2,1,2,3]`

| right | fruits[right] | map | Shrink? | maxLen |
|-------|------------------|-----|---------|--------|
| 0 | 1 | {1:1} | no | 1 |
| 1 | 2 | {1:1,2:1} | no | 2 |
| 2 | 1 | {1:2,2:1} | no | 3 |
| 3 | 2 | {1:2,2:2} | no | 4 |
| 4 | 3 | {1:2,2:2,3:1} | yes → remove leftmost until size 2 → left moves to 2, map {1:1,2:2,3:1}... shrink again → left=3, map {2:2,3:1} | 4 |

**Result:** `4` ✅ (subarray `[1,2,1,2]`)

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(1) |
| **Sliding Window (Optimal)** | **O(n)** | **O(1)** *(map capped at 3)* |

### 🎯 Pattern
👉 Variable-Size Sliding Window — at most 2 distinct elements

---

## 7. Longest Substring with At Most K Distinct Characters

### 🧩 Problem
Given a string `s` and integer `k`, find the length of the longest substring that contains **at most `k` distinct characters**.

### 💡 Approach 1 — Brute Force
- For every starting index, expand while distinct character count stays `≤ k`.

```java
class Solution {
    public int longestKDistinctBrute(String s, int k) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            Set<Character> distinct = new HashSet<>();
            int j = i;
            while (j < n) {
                distinct.add(s.charAt(j));
                if (distinct.size() > k) break;
                j++;
            }
            maxLen = Math.max(maxLen, j - i);
        }
        return maxLen;
    }
}
```
- **Time:** O(n²)
- **Space:** O(k)

### 💡 Approach 2 — Optimal (Sliding Window + HashMap)
- Maintain a frequency map for the current window.
- Expand with `right`; if distinct count exceeds `k`, shrink from `left`.

```java
class Solution {
    public int longestKDistinct(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            while (freq.size() > k) {
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);
                if (freq.get(leftChar) == 0) freq.remove(leftChar);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```

### 📝 Dry Run (Optimal)
`s = "eceba"`, `k = 2`

| right | char | freq | Shrink? | maxLen |
|-------|------|------|---------|--------|
| 0 | e | {e:1} | no | 1 |
| 1 | c | {e:1,c:1} | no | 2 |
| 2 | e | {e:2,c:1} | no | 3 |
| 3 | b | {e:2,c:1,b:1} | yes → remove 'e' (left=0), left=1, freq {e:1,c:1,b:1}... shrink again removing 'c', left=2, freq {e:1,b:1} | 2 |
| 4 | a | {e:1,b:1,a:1} | yes → shrink → left=3, freq {b:1,a:1} | 2 |

**Result:** `3` ✅ (substring `"ece"`)

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(k) |
| **Sliding Window (Optimal)** | **O(n)** | **O(k)** |

### 🎯 Pattern
👉 Variable-Size Sliding Window — at most k distinct elements

---

## 8. Number of Substrings Containing All Three Characters (LeetCode 1358)

### 🧩 Problem
Given a string `s` of only `a`, `b`, `c`, return the number of substrings containing **at least one** of each character.

### 💡 Approach 1 — Brute Force
- Check every substring directly and verify it contains all three characters.

```java
class Solution {
    public int numberOfSubstringsBrute(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 0; i < n; i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = i; j < n; j++) {
                seen.add(s.charAt(j));
                if (seen.size() == 3) count++;
            }
        }
        return count;
    }
}
```
- **Time:** O(n²)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (Sliding Window + HashMap)
- Expand with `right`, tracking frequency of `a`, `b`, `c`.
- Once the window contains all 3 characters, **every** substring from the current window to the end of the string is valid → add `(n - right)`.
- Shrink from `left` to look for the next valid window.

```java
class Solution {
    public int numberOfSubstrings(String s) {
        int[] freq = new int[3];
        int left = 0, count = 0, n = s.length();

        for (int right = 0; right < n; right++) {
            freq[s.charAt(right) - 'a']++;

            while (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                count += (n - right);
                freq[s.charAt(left) - 'a']--;
                left++;
            }
        }
        return count;
    }
}
```

### 📝 Dry Run (Optimal)
`s = "abcabc"` (n = 6)

| right | char | freq[a,b,c] | Valid? | count added | left (after) |
|-------|------|--------------|--------|--------------|----------------|
| 0 | a | [1,0,0] | no | 0 | 0 |
| 1 | b | [1,1,0] | no | 0 | 0 |
| 2 | c | [1,1,1] | yes | +4 (n-right=6-2) → shrink left=1 | 1 |
| 3 | a | [1,1,1]→ after shrink adjustments | yes | +3 → left=2 | 2 |
| 4 | b | valid | yes | +2 → left=3 | 3 |
| 5 | c | valid | yes | +1 → left=4 | 4 |

**Result:** `10` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(1) |
| **Sliding Window (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Variable-Size Sliding Window — count via `(n - right)` shortcut

---

## 9. Longest Repeating Character Replacement (LeetCode 424)

### 🧩 Problem
Given a string `s` (uppercase letters) and integer `k`, you may replace at most `k` characters. Find the length of the longest substring with all identical characters after replacement.

### 💡 Approach 1 — Brute Force
- For every starting index, expand while `(window size − most frequent char count) ≤ k`, recomputing frequencies each time.

```java
class Solution {
    public int characterReplacementBrute(String s, int k) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            int maxFreq = 0;
            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'A']++;
                maxFreq = Math.max(maxFreq, freq[s.charAt(j) - 'A']);
                int windowLen = j - i + 1;
                if (windowLen - maxFreq <= k) {
                    maxLen = Math.max(maxLen, windowLen);
                } else {
                    break;
                }
            }
        }
        return maxLen;
    }
}
```
- **Time:** O(n²)
- **Space:** O(26) ≈ O(1)

### 💡 Approach 2 — Optimal (Sliding Window)
- Maintain a frequency array and track `maxFreq` seen so far in the window.
- If `(window size − maxFreq) > k`, shrink from `left`.
- Track the max window length.

```java
class Solution {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int left = 0, maxFreq = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

            if ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```

### 📝 Dry Run (Optimal)
`s = "AABABBA"`, `k = 1`

| right | char | freq | maxFreq | window-maxFreq | Shrink? | maxLen |
|-------|------|------|---------|------------------|---------|--------|
| 0 | A | A:1 | 1 | 0 | no | 1 |
| 1 | A | A:2 | 2 | 0 | no | 2 |
| 2 | B | A:2,B:1 | 2 | 1 | no | 3 |
| 3 | A | A:3,B:1 | 3 | 1 | no | 4 |
| 4 | B | A:3,B:2 | 3 | 2 → shrink | yes → left=1 | 4 |
| 5 | B | A:2,B:3(after shrink adj) | 3 | 1 | no | 4 |
| 6 | A | A:3,B:3 | 3 | 3-3=0...(window recalculated) | no | 4 |

**Result:** `4` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(1) |
| **Sliding Window (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Variable-Size Sliding Window — invariant: `window size − maxFreq ≤ k`

---

## 10. Binary Subarrays With Sum (LeetCode 930)

### 🧩 Problem
Given a binary array `nums` and integer `goal`, return the number of non-empty subarrays with sum exactly `goal`.

### 💡 Approach 1 — Brute Force
- Check every subarray's sum directly.

```java
class Solution {
    public int numSubarraysWithSumBrute(int[] nums, int goal) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == goal) count++;
            }
        }
        return count;
    }
}
```
- **Time:** O(n²)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (AtMost Trick + Sliding Window)
- Exact sum is hard to count directly with a sliding window (not monotonic in the same way), but "sum `≤ goal`" is.
- Use: `exact(goal) = atMost(goal) - atMost(goal - 1)`.
- `atMost(x)` counts subarrays with sum `≤ x` using a standard shrinking window; valid subarrays ending at `right` = `(right - left + 1)`.

```java
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private int atMost(int[] nums, int goal) {
        if (goal < 0) return 0;

        int left = 0, sum = 0, count = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum > goal) {
                sum -= nums[left];
                left++;
            }
            count += (right - left + 1);
        }
        return count;
    }
}
```

### 📝 Dry Run (Optimal — conceptual)
`nums = [1,0,1,0,1]`, `goal = 2`

- `atMost(2)` counts all subarrays with sum ≤ 2.
- `atMost(1)` counts all subarrays with sum ≤ 1.
- `numSubarraysWithSum = atMost(2) - atMost(1)` → **Result:** `4` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(1) |
| **AtMost Trick (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Sliding Window + **`exact(goal) = atMost(goal) − atMost(goal−1)`** trick

---

## 11. Count Number of Nice Subarrays (LeetCode 1248)

### 🧩 Problem
Given an array `nums` and integer `k`, return the number of contiguous subarrays with **exactly `k` odd numbers**.

### 💡 Approach 1 — Brute Force
- Check every subarray, counting odd numbers directly.

```java
class Solution {
    public int numberOfSubarraysBrute(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int oddCount = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] % 2 != 0) oddCount++;
                if (oddCount == k) count++;
            }
        }
        return count;
    }
}
```
- **Time:** O(n²)
- **Space:** O(1)

### 💡 Approach 2 — Optimal (AtMost Trick + Sliding Window)
- Use: `exactly(k) = atMost(k) - atMost(k - 1)`.
- `atMost(x)` counts subarrays with at most `x` odd numbers; valid subarrays ending at `right` = `(right - left + 1)`.

```java
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        if (k < 0) return 0;

        int left = 0, oddCount = 0, count = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] % 2 != 0) oddCount++;

            while (oddCount > k) {
                if (nums[left] % 2 != 0) oddCount--;
                left++;
            }
            count += (right - left + 1);
        }
        return count;
    }
}
```

### 📝 Dry Run (Optimal — conceptual)
`nums = [1,1,2,1,1]`, `k = 3`

- `atMost(3)` counts subarrays with ≤ 3 odd numbers.
- `atMost(2)` counts subarrays with ≤ 2 odd numbers.
- `numberOfSubarrays = atMost(3) - atMost(2)` → **Result:** `2` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(1) |
| **AtMost Trick (Optimal)** | **O(n)** | **O(1)** |

### 🎯 Pattern
👉 Sliding Window + **`exactly(k) = atMost(k) − atMost(k−1)`** trick

---

## 12. Subarrays with K Different Integers (LeetCode 992)

### 🧩 Problem
Given an array `nums` and integer `k`, return the number of subarrays with **exactly `k` distinct integers**.

### 💡 Approach 1 — Brute Force
- Check every subarray, tracking distinct integers using a `HashSet`.

```java
class Solution {
    public int subarraysWithKDistinctBrute(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> distinct = new HashSet<>();
            for (int j = i; j < n; j++) {
                distinct.add(nums[j]);
                if (distinct.size() == k) count++;
                if (distinct.size() > k) break;
            }
        }
        return count;
    }
}
```
- **Time:** O(n²)
- **Space:** O(k)

### 💡 Approach 2 — Optimal (AtMost Trick + Sliding Window + HashMap)
- Use: `exactly(K) = atMost(K) - atMost(K - 1)`.
- `atMost(x)` counts subarrays with at most `x` distinct integers using a HashMap-based sliding window.

```java
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        if (k < 0) return 0;

        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            while (freq.size() > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                if (freq.get(nums[left]) == 0) freq.remove(nums[left]);
                left++;
            }
            count += (right - left + 1);
        }
        return count;
    }
}
```

### 📝 Dry Run (Optimal — conceptual)
`nums = [1,2,1,2,3]`, `k = 2`

- `atMost(2)` counts subarrays with ≤ 2 distinct integers.
- `atMost(1)` counts subarrays with ≤ 1 distinct integer.
- `subarraysWithKDistinct = atMost(2) - atMost(1)` → **Result:** `7` ✅

### ⚙️ Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(k) |
| **AtMost Trick (Optimal)** | **O(n)** | **O(k)** |

### 🎯 Pattern
👉 Sliding Window + HashMap + **`exactly(K) = atMost(K) − atMost(K−1)`** trick

---

## 📈 Master Complexity Table

| # | Problem | Brute Time | Brute Space | Optimal Time | Optimal Space |
|---|---------|-------------|--------------|----------------|-----------------|
| 1 | Longest Substring Without Repeating Chars | O(n²) | O(n) | O(n) | O(1) |
| 2 | Max Sum Subarray of Size K | O(n×k) | O(1) | O(n) | O(1) |
| 3 | Max Points from Cards | O(k²) | O(1) | O(n) | O(1) |
| 4 | Minimum Size Subarray Sum | O(n²) | O(1) | O(n) | O(1) |
| 5 | Max Consecutive Ones III | O(n²) | O(1) | O(n) | O(1) |
| 6 | Fruit Into Baskets | O(n²) | O(1) | O(n) | O(1) |
| 7 | Longest Substring At Most K Distinct | O(n²) | O(k) | O(n) | O(k) |
| 8 | Substrings with All Three Chars | O(n²) | O(1) | O(n) | O(1) |
| 9 | Longest Repeating Char Replacement | O(n²) | O(1) | O(n) | O(1) |
| 10 | Binary Subarrays With Sum | O(n²) | O(1) | O(n) | O(1) |
| 11 | Count Nice Subarrays | O(n²) | O(1) | O(n) | O(1) |
| 12 | Subarrays with K Different Integers | O(n²) | O(k) | O(n) | O(k) |

---

## 🧠 Core Patterns in This Repo

- **Fixed-Size Sliding Window** — Max Sum Subarray of Size K, Max Points from Cards
- **Variable-Size Sliding Window** — Longest Substring, Min Size Subarray Sum, Max Consecutive Ones III, Fruit Into Baskets, K Distinct Characters, Character Replacement
- **`(n - right)` Counting Shortcut** — Number of Substrings Containing All Three Characters
- **`exactly(K) = atMost(K) − atMost(K−1)` Trick** — Binary Subarrays With Sum, Count Nice Subarrays, Subarrays with K Different Integers

## 🚀 How to Use
Each solution is written as a standalone `class Solution` — paste directly into LeetCode, or run locally with a `main` method for quick testing.

---
<div align="center">

*Maintained as part of ongoing DSA practice — sliding window & two-pointer patterns solved regularly.* 🪟

</div>
