# 🚀 Maximum Subarray (Kadane’s Algorithm)

## 📌 Problem

Given an integer array, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

---

## 💡 Approach (Kadane’s Algorithm)

Kadane’s Algorithm is an efficient technique to solve the maximum subarray problem in **O(n)** time.

Instead of checking all subarrays, we make a decision at each element:

* Either **start a new subarray**
* Or **continue the previous subarray**

At every index:

* We compute the best possible sum ending at that index
* Then update the global maximum

👉 Key Idea:

* If the previous sum is **negative**, it will reduce the result → discard it
* If the previous sum is **positive**, it helps → keep it

This works because the maximum subarray must either:

* End at the current element
* Or start fresh from the current element ([Raghu Vijaykumar][1])

---

## 🧠 Intuition

Think of it like:

* Positive sum = profit 💰
* Negative sum = loss 📉

If your running sum becomes negative → drop it and restart.

---

## 🔄 Dry Run Example

Array:
[5, 4, -1, 7, 8]

Step-by-step:

* Start with 5 → sum = 5
* Add 4 → sum = 9
* Add -1 → sum = 8
* Add 7 → sum = 15
* Add 8 → sum = 23

✅ Maximum Subarray Sum = **23**

---

## ⚡ Complexity

* Time Complexity: **O(n)** (single pass)
* Space Complexity: **O(1)** (no extra space)

Kadane’s algorithm is optimal because it processes the array in one traversal ([Codecademy][2])

---

## 🎯 Key Takeaways

* Always track:

  * Current subarray sum
  * Maximum sum so far
* Decision at each step:

  * Continue OR Restart
* Works for both positive and negative numbers

---

## 🏁 Conclusion

Kadane’s Algorithm is a simple yet powerful approach to solve maximum subarray problems efficiently.
It is widely used in coding interviews and competitive programming due to its optimal performance.

---

[1]: https://raghu-vijaykumar.github.io/docs/docs/dsa/algorithms/greedy/kadanes-algorithm/?utm_source=chatgpt.com "Kadane's Algorithm | Raghu's Notes"
[2]: https://www.codecademy.com/article/kadanes-algorithm-find-maximum-subarray-sum-in-an-array?utm_source=chatgpt.com "Kadane's Algorithm: Find Maximum Subarray Sum in an Array | Codecademy"
