<div align="center">

# 🧩 Singly Linked List — LeetCode Problems

![Java](https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=openjdk)
![LeetCode](https://img.shields.io/badge/Platform-LeetCode-yellow?style=for-the-badge&logo=leetcode)
![Problems Solved](https://img.shields.io/badge/Problems%20Solved-1-brightgreen?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Actively%20Updated-blue?style=for-the-badge)

*Every problem here follows the same format: Problem → Approach(es) → Code → Dry Run → Complexity → Pattern Tag.*

</div>

---

## 🧭 Navigation

| # | Problem | Difficulty | Pattern | Status |
|---|---|---|---|---|
| 1 | [Delete Node in a Linked List](#1-delete-node-in-a-linked-list) | 🟢 Easy | `#value-copy-trick` | ✅ Solved |

📊 [Master Complexity & Patterns Summary](#-master-complexity--patterns-summary)

---
<a id="1-delete-node-in-a-linked-list"></a>
## 1. Delete Node in a Linked List

**LeetCode 237 — Easy**

### 📋 Problem Statement

You are given a node from a singly linked list — **not the head**, and **not given access to the head either**. Delete this given node from the list.

You will **not** be given access to the previous node, only a reference directly to the node that must be deleted.

**Example:**
```
Input: head = [4,5,1,9], node = 5 (2nd node)
Output: [4,1,9]
Explanation: The value 5 is removed. The list becomes 4 -> 1 -> 9.
```

**Constraints:**
- The number of nodes in the list is in the range `[2, 1000]`
- `-1000 <= Node.val <= 1000`
- The value of each node in the list is unique
- The node to be deleted is **not a tail node** in the list — it's guaranteed to have a valid `next`

### 🧠 Key Insight — Why This Problem is Tricky

Normally, to delete a node from a singly linked list, you need the **previous node**, so you can do `prev.next = current.next` to bypass the target node.

Here, you're only handed the node itself — **no head, no previous pointer, no way to traverse backward**. So the usual delete-by-rewiring-the-previous-node approach is **impossible** with the given access.

> 💡 **The trick:** Since you can't remove *this* node directly, instead **copy the next node's value into the current node**, then **delete the next node instead**. From the outside, it looks identical to deleting the current node — the value that was here is gone, and the list is one node shorter.

### 🐌 Brute Force — Not Applicable Here

In a normal "delete node by value/position" problem, brute force would be: traverse from `head`, track `prev`, find the target, then do `prev.next = current.next` — O(n) because you must walk from the head to find the previous node.

**That approach cannot be used in this problem** because we are never given `head` — only a direct reference to the node to delete. This constraint is exactly what forces the value-copy trick below; there is no fallback traversal method available.

### ✅ Optimal Solution — Copy & Forward-Delete

```java
class Solution {
    public void deleteNode(ListNode node) {
        // Step 1: Overwrite current node's value with the next node's value
        node.val = node.next.val;

        // Step 2: Bypass the next node (effectively deleting it)
        node.next = node.next.next;
    }
}
```

### 🔍 Line-by-Line Explanation

1. **`node.val = node.next.val;`** — Copy the value sitting in the *next* node into the *current* node. Now the current node holds a duplicate of the next node's value.
2. **`node.next = node.next.next;`** — Rewire the current node's `next` pointer to skip over the (now-duplicated) next node entirely, cutting it out of the chain.

The original next node still exists in memory for a moment, but nothing points to it anymore — Java's Garbage Collector reclaims it automatically.

### 🧪 Dry Run

Given list: `4 -> 5 -> 1 -> 9 -> null`, and `node` is a reference to the node holding `5` (we don't have `head`, only this reference).

| Step | Action | State of list |
|---|---|---|
| Before | — | `4 -> 5 -> 1 -> 9 -> null` (node points at `5`) |
| 1 | `node.val = node.next.val` → copies `1` into node | `4 -> 1 -> 1 -> 9 -> null` (two nodes now hold `1`) |
| 2 | `node.next = node.next.next` → skip the duplicate | `4 -> 1 -> 9 -> null` |

**Result:** `4 -> 1 -> 9 -> null` ✅ — matches expected output exactly. The node that visually "disappeared" is actually the *original* node holding `5`'s position — its identity in memory got overwritten, and the true node with value `1` further down got unlinked instead.

> ⚠️ **Why this only works because the node is guaranteed not to be the tail:** If `node` were the last node, `node.next` would be `null`, and `node.next.val` would throw a `NullPointerException`. This is exactly why the constraints guarantee the target is never the tail node.

### ⏱️ Time & Space Complexity

| Metric | Complexity | Why |
|---|---|---|
| Time | **O(1)** | Only two pointer/value operations — no traversal needed at all |
| Space | **O(1)** | No extra data structures used |

### 🏷️ Pattern Tag

`#value-copy-trick` `#constant-time-deletion` `#no-head-access`

> 🎯 **When to recognize this pattern:** Any time a problem gives you a node reference **without head/previous access** and asks you to "delete" or "remove" it — think: *can I fake the deletion by copying the next node's data forward instead of unlinking backward?*

---

## 📊 Master Complexity & Patterns Summary

| # | Problem | Time | Space | Pattern |
|---|---|---|---|---|
| 1 | Delete Node in a Linked List | O(1) | O(1) | `#value-copy-trick` |

---

<div align="center">

### 🔄 This README updates after every new problem solved
### Next up: keep grinding singly LL problems — reversal, cycle detection, and two-pointer patterns incoming 🚀

</div>
