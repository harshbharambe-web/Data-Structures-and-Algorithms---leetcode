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

