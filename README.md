<div align="center">

# 🚀 Data Structures & Algorithms — LeetCode Practice

### Hi, I'm Harsh 👋

![Java](https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java)
![LeetCode](https://img.shields.io/badge/Platform-LeetCode-FFA116?style=for-the-badge&logo=leetcode&logoColor=white)
![Focus](https://img.shields.io/badge/Focus-Backend%20%7C%20DSA-blue?style=for-the-badge)
![Consistency](https://img.shields.io/badge/Practice-Daily-brightgreen?style=for-the-badge)

A structured collection of my Data Structures & Algorithms solutions — organized by topic, solved with a **Brute Force → Optimal** progression, and documented with dry runs and complexity analysis for quick revision before interviews.

[Topics Covered](#-topics-covered) • [Progress Tracker](#-progress-tracker) • [Repo Structure](#-repo-structure) • [Problem Index](#-problem-index) • [Patterns](#-patterns-i-keep-seeing)

</div>

---

## 📌 Topics Covered

| Status | Topic | Problems Solved |
|--------|-------|:---:|
| ✅ | Arrays | 10 |
| ✅ | Sliding Window | 12 |
| 🔄 | Strings | — |
| ⏳ | Linked List | — |
| ⏳ | Stack & Queue | — |
| ⏳ | Recursion | — |
| ⏳ | Trees | — |
| ⏳ | Graphs | — |
| ⏳ | Dynamic Programming | — |
| ⏳ | Binary Search | — |
| ⏳ | Heaps | — |
| ⏳ | Backtracking | — |

`✅ In Progress / Solid Base` · `🔄 Just Started` · `⏳ Planned`

---

## 📊 Progress Tracker

| Metric | Count |
|--------|:---:|
| **Total Problems Solved** | 22+ |
| **Topics Started** | 2 / 11 |
| **Brute + Optimal Documented** | 100% (of solved) |
| **Platform** | LeetCode |
| **Primary Language** | Java |

> Updated as I go — this table reflects the current snapshot, not a final count.

---

## 🗂️ Repo Structure

```
Data-Structures-and-Algorithms---leetcode/
│
├── Arrays/
│   ├── README.md              → 10 problems, brute + optimal, dry runs
│   ├── RotateArray.java
│   ├── MoveZeroes.java
│   └── ...
│
├── SlidingWindow/
│   ├── README.md               → 12 problems, brute + optimal, dry runs
│   ├── LongestSubstring.java
│   ├── FruitIntoBaskets.java
│   └── ...
│
├── Strings/                    (coming soon)
├── LinkedList/                 (coming soon)
├── StackQueue/                 (coming soon)
├── Recursion/                  (coming soon)
├── Trees/                      (coming soon)
├── Graphs/                     (coming soon)
├── DynamicProgramming/         (coming soon)
│
└── README.md                   → you are here
```

---

## 📋 Problem Index

### 📊 Arrays
| # | Problem | LeetCode | Pattern |
|---|---------|----------|---------|
| 1 | Maximum Consecutive Ones | [485](https://leetcode.com/problems/max-consecutive-ones/) | Traversal / Counting |
| 2 | Remove Duplicates from Sorted Array | [26](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | Two Pointer |
| 3 | Concatenation of Array | [1929](https://leetcode.com/problems/concatenation-of-array/) | Simulation |
| 4 | Longest Common Prefix | [14](https://leetcode.com/problems/longest-common-prefix/) | Traversal / Simulation |
| 5 | Largest Element in an Array | — | Traversal / Simulation |
| 6 | Second Largest Element in an Array | — | Traversal / Simulation |
| 7 | Left Rotate Array by One Place | — | Simulation |
| 8 | Rotate Array | [189](https://leetcode.com/problems/rotate-array/) | Reversal Trick |
| 9 | Left Rotate Array by k Places | — | Reversal Trick |
| 10 | Move Zeroes | [283](https://leetcode.com/problems/move-zeroes/) | Two Pointer |

### 🪟 Sliding Window
| # | Problem | LeetCode | Window Type |
|---|---------|----------|-------------|
| 1 | Longest Substring Without Repeating Characters | [3](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Variable |
| 2 | Maximum Sum Subarray of Size K | — | Fixed |
| 3 | Maximum Points You Can Obtain from Cards | [1423](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/) | Fixed |
| 4 | Minimum Size Subarray Sum | [209](https://leetcode.com/problems/minimum-size-subarray-sum/) | Variable |
| 5 | Max Consecutive Ones III | [1004](https://leetcode.com/problems/max-consecutive-ones-iii/) | Variable |
| 6 | Fruit Into Baskets | [904](https://leetcode.com/problems/fruit-into-baskets/) | Variable |
| 7 | Longest Substring with At Most K Distinct Characters | [340](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/) | Variable |
| 8 | Number of Substrings Containing All Three Characters | [1358](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/) | Variable |
| 9 | Longest Repeating Character Replacement | [424](https://leetcode.com/problems/longest-repeating-character-replacement/) | Variable |
| 10 | Binary Subarrays With Sum | [930](https://leetcode.com/problems/binary-subarrays-with-sum/) | AtMost Trick |
| 11 | Count Number of Nice Subarrays | [1248](https://leetcode.com/problems/count-number-of-nice-subarrays/) | AtMost Trick |
| 12 | Subarrays with K Different Integers | [992](https://leetcode.com/problems/subarrays-with-k-different-integers/) | AtMost Trick |

---

## 🧠 Patterns I Keep Seeing

| Pattern | Shows Up In |
|---------|-------------|
| **Two Pointers** | Remove Duplicates, Move Zeroes, Array Reversal |
| **Sliding Window (Fixed)** | Max Sum Subarray of Size K, Max Points from Cards |
| **Sliding Window (Variable)** | Longest Substring, Min Size Subarray Sum, Fruit Into Baskets |
| **`exactly(K) = atMost(K) − atMost(K-1)`** | Binary Subarrays With Sum, Nice Subarrays, K Distinct Integers |
| **Reversal Trick** | Left/Right Rotate Array |
| **HashMap Frequency Tracking** | Fruit Into Baskets, K Distinct Chars, Char Replacement |

Recognizing these patterns early is the actual skill being built here — most "new" problems turn out to be a familiar pattern wearing a different costume.

---

## 🧭 Problem-Solving Approach

Every solution in this repo follows the same format:

1. **🧩 Problem** — restated clearly, with constraints
2. **💡 Brute Force** — the first working idea, however inefficient
3. **💡 Optimal** — the pattern-based improvement (two pointer / sliding window / etc.)
4. **📝 Dry Run** — a step-by-step table tracing the optimal solution on an example
5. **⚙️ Complexity** — Time & Space for both approaches, side by side
6. **🎯 Pattern** — the reusable technique, so it's recognizable in future problems

This progression mirrors how these problems actually get solved in interviews — brute force first, then optimize out loud.

---

## 🎯 Interview Prep Track

| Track | Focus Areas |
|-------|-------------|
| **Service-Based** (TCS, Infosys, Wipro, etc.) | Arrays, Strings, Basic Sorting/Searching, Linked List, Stacks, Queues |
| **Product-Based / Startups** | Sliding Window, Two Pointer, Hashing, Backtracking, Trees, Graphs, DP, Heaps, Binary Search on Answer |

Building toward readiness across **both** tracks.

---

## 🛠️ Tech Used

![Java](https://img.shields.io/badge/-Java-007396?style=flat-square&logo=java&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/-IntelliJ%20IDEA-000000?style=flat-square&logo=intellijidea&logoColor=white)
![Git](https://img.shields.io/badge/-Git-F05032?style=flat-square&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/-GitHub-181717?style=flat-square&logo=github&logoColor=white)

---

## 🎯 Goal

To strengthen problem-solving skills, build strong DSA fundamentals, and crack interviews at **top product-based companies** — while keeping every solution documented well enough to revise in under 5 minutes.

---

<div align="center">

### ⭐ If you find this useful, consider starring the repo!

*Consistency > Intensity. One problem at a time.* 💪

</div>
