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

