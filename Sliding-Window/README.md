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

- Minimum Size Subarray Sum
Approach
Use Sliding Window
Expand window using right
Add elements to sum
When sum ≥ target, shrink window from left
Update minimum length while shrinking
Complexity
Time: O(n)
Space: O(1)


## 4. Minimum Size Subarray Sum

### Problem
Given an array of positive integers `nums` and a target value `target`, find the minimal length of a contiguous subarray whose sum is greater than or equal to target. If no such subarray exists, return 0.

---

### Approach
- Use variable size sliding window
- Expand window by adding elements using right pointer
- When sum becomes >= target:
  - Update minimum length
  - Shrink window from left
- Continue until entire array is processed

---

### Key Idea
- Maintain a window where sum >= target
- Try to minimize the window size while maintaining the condition

---

### Complexity
- Time: O(n)
- Space: O(1)


## 5. Max Consecutive Ones III

### Problem
Given a binary array `nums` and an integer `k`, return the maximum number of consecutive 1's in the array if you can flip at most `k` zeros.

---

### Approach
- Use sliding window technique
- Expand window using right pointer
- Count zeros in current window
- If zeros exceed k:
  - Shrink window from left
- Maintain maximum length of valid window

---

### Key Idea
- At most k zeros allowed in window
- Window should always satisfy zeros <= k

---

### Complexity
- Time: O(n)
- Space: O(1)


## 6. Fruit Into Baskets

### Problem
You are given an array `fruits` where each element represents a type of fruit. You can collect fruits in two baskets, each basket can only hold one type of fruit. Return the maximum number of fruits you can collect.

---

### Approach
- Use sliding window with HashMap
- Store frequency of fruits in the current window
- Expand window using right pointer
- If number of fruit types exceeds 2:
  - Shrink window from left
  - Remove fruit when count becomes 0
- Track maximum window size

---

### Key Idea
- At most 2 distinct elements allowed in window
- Maintain valid window using HashMap

---

### Complexity
- Time: O(n)
- Space: O(1)

- ---

## 7. Maximum substring with atmost k char

### 📌 Overview

The Sliding Window technique is used to efficiently process **contiguous subarrays or substrings** by maintaining a dynamic window range instead of recalculating values repeatedly.

---

### ⚡ Use Cases

* Longest / shortest substring problems
* Maximum / minimum sum subarrays
* Problems with constraints like:

  * At most K distinct elements
  * Exactly K distinct elements
  * Fixed window size

---

### 🧠 Concept

* Maintain a window using two pointers:

  * `Left (l)` → start
  * `Right (r)` → end
* Expand the window by moving `r`
* Shrink the window by moving `l` when constraints are violated

---

### 🔄 Workflow

* Add element at `r` into the window
* Check if condition is satisfied
* If violated → shrink window from `l`
* If valid → update result
* Continue until full traversal

---

### 🎯 Types of Sliding Window

* Fixed Size Window
* Variable Size Window

---

### 🚀 Complexity

* Time Complexity: **O(n)**
* Space Complexity: **O(1)** or **O(k)** (depending on data structure)

---

### 💡 Key Insight

👉 Efficiently reuse previous computations by adjusting the window instead of recomputing from scratch

---

### 🏁 Summary

👉 “Expand the window to include elements, shrink it when constraints break, and track the best result.”

---

### 🏷️ Tags

#SlidingWindow #TwoPointers #DSA #Algorithms #InterviewPrep #LeetCode #Coding

---

## 8. Number of Substrings Containing All Three Characters

## 🧠 Problem

Given a string `s` consisting only of characters `a`, `b`, and `c`, return the number of substrings that contain **at least one occurrence of all three characters**.

---

## 🚀 Approach: Sliding Window + HashMap

We use a **sliding window technique** along with a **HashMap** to track the frequency of characters inside the current window.

---

## ⚙️ Algorithm

1. Initialize:

   * `left = 0`
   * `count = 0`
   * `HashMap<Character, Integer> map`

2. Expand the window using `right` pointer:

   * Add current character into the map

3. When the window contains all 3 characters (`map.size() == 3`):

   * Add `(n - right)` to count
     👉 Because all substrings from current window to end are valid

4. Shrink the window:

   * Remove `left` character from map
   * If frequency becomes 0 → remove it from map
   * Move `left++`

5. Repeat until end of string

---

## 🔑 Key Insight

When the window becomes valid (contains `a`, `b`, `c`),
we can directly count multiple substrings at once:

Number of valid substrings = `n - right`

---

## 📊 Complexity

* **Time Complexity:** `O(n)`
* **Space Complexity:** `O(1)` (at most 3 characters in map)

---

## 🧩 Example

Input:

```
s = "abcabc"
```

Output:

```
10
```

---

## 🎯 Summary

* Use sliding window to efficiently scan substrings
* Use HashMap to track character frequencies
* Count multiple substrings in one step using `(n - right)`
* Avoid brute force (`O(n²)`)

---

## 🏷️ Tags

`#SlidingWindow` `#HashMap` `#TwoPointers` `#LeetCode` `#Strings`

--- 
## 9. Longest Repeating Character Replacement (LeetCode 424)

## 🧠 Problem

Given a string `s` (uppercase letters) and an integer `k`, you can replace at most `k` characters.
Find the length of the longest substring where all characters are the same.

---

## 💡 Approach (Sliding Window)

* Use two pointers (`l`, `r`) to maintain a window
* Use a frequency array (`hash[26]`) to count characters
* Track the most frequent character (`maxFreq`)
* If `(window size - maxFreq) > k`, shrink the window
* Update the maximum length

---

## ⏱ Complexity

* Time: **O(n)**
* Space: **O(26)** ≈ O(1)

---

## 🚀 Key Idea

```
window size - max frequency ≤ k
```

---

## 📌 Example

```
Input: s = "AABABBA", k = 1
Output: 4
```
---

## 10. Binary Subarrays With Sum
🧠 Problem

Given a binary array nums (only 0s and 1s) and an integer goal, return the number of non-empty subarrays whose sum equals goal.

## 🚀 Approach (Sliding Window Trick)

Directly counting subarrays with exact sum = goal is difficult using sliding window.

👉 So we use:

exact(goal) = atMost(goal) - atMost(goal - 1)
atMost(goal) → counts subarrays with sum ≤ goal
atMost(goal - 1) → removes extra cases
Final result → gives exact sum = goal

## 💡 Key Idea

At every index r, number of valid subarrays ending at r is:

r - l + 1

Because all subarrays from index l to r are valid.

## ⚡ Complexity
Time: O(n)
Space: O(1)
## 🔥 Key Takeaway
Sliding window works easily for ≤ goal
Convert exact goal → using subtraction trick

---
## 11. Count Number of Nice Subarrays

## 🧩 Problem

Given an integer array nums and an integer k, find the number of contiguous subarrays that contain exactly k odd numbers.

A subarray is called nice if it has exactly k odd elements.

## 💡 Approach

Instead of directly counting subarrays with exactly k odd numbers, we use a trick:

exactly(k) = atMost(k) − atMost(k − 1)

atMost(k) → counts subarrays with at most k odd numbers
Subtracting removes extra cases and leaves only exactly k

## ⚙️ Algorithm (Sliding Window)
Use two pointers: left and right
Expand the window using right
Keep track of number of odd elements in the current window
If odd count exceeds k, shrink window from left
For each position, add the number of valid subarrays ending at that index

👉 Number of subarrays ending at index right:
(right − left + 1)

## 🧠 Intuition
Convert problem into counting subarrays with constraints
Sliding window works because condition is monotonic (≤ k)
The "exactly k" condition is solved using subtraction trick


## ⏱️ Complexity

| Type  | Complexity |
| ----- | ---------- |
| Time  | O(n)       |
| Space | O(1)       |


## 🔥 Key Takeaways
Use Sliding Window for subarray problems
Remember the trick:
👉 exactly(k) = atMost(k) − atMost(k−1)
(r - l + 1) is the core counting formula
Very common pattern in coding interviews


