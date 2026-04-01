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


## 2. Remove Duplicates from Sorted Array

## 📌 Problem

Given a sorted array `nums`, remove duplicates **in-place** such that each unique element appears only once. Return the number of unique elements `k`.

The first `k` elements of the array should contain the final result. ([LeetCode][1])

---

## 💡 Approach

* Use **Two Pointer Technique**
* One pointer keeps track of unique elements
* Another pointer scans the array
* Replace duplicates while traversing

---

## ⏱ Complexity

* **Time Complexity:** O(n)
* **Space Complexity:** O(1)

---

## 🎯 Key Concept

* Array is **sorted**, so duplicates are adjacent
* No extra space allowed → must modify array in-place
* Best solved using **Two Pointers**

---

## 3. Concatenation of Array

## 📌 Problem

Given an array `nums` of size `n`, return a new array of size `2n` where the second half is a duplicate of the first half. ([Prakash's Web Page][1])

---

## 💡 Approach

* Create a new array of size `2n`
* Copy elements of `nums` twice
* First half → original array
* Second half → same array again

---

## ⏱ Complexity

* **Time:** O(n) ([WalkCCC][2])
* **Space:** O(n)

---

## 4. Longest Common Prefix (LeetCode 14)
🧩 Problem

Find the longest common prefix among an array of strings.
If no common prefix exists, return an empty string "".

💡 Approach
Take the first string as reference
Compare characters with all other strings
Stop when mismatch or string ends
Return prefix till that point
⚙️ Complexity
Time: O(n × m)
Space: O(1)

## 🎯 Pattern

👉 Array Traversal / Simulation

---


