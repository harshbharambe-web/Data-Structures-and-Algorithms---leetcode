<div align="center">

# 🧩 Singly Linked List — LeetCode Problems

![Java](https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=openjdk)
![LeetCode](https://img.shields.io/badge/Platform-LeetCode-yellow?style=for-the-badge&logo=leetcode)
![Problems Solved](https://img.shields.io/badge/Problems%20Solved-3-brightgreen?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Actively%20Updated-blue?style=for-the-badge)

*Every problem here follows the same format: Problem → Approach(es) → Code → Dry Run → Complexity → Pattern Tag.*

</div>

---

## 🧭 Navigation

| # | Problem | Difficulty | Pattern | Status |
|---|---|---|---|---|
| 1 | [Delete Node in a Linked List](#1-delete-node-in-a-linked-list) | 🟢 Easy | `#value-copy-trick` | ✅ Solved |
| 2 | [Middle of the Linked List](#2-middle-of-the-linked-list) | 🟢 Easy | `#slow-fast-pointers` | ✅ Solved |
| 3 | [Reverse Linked List](#3-reverse-linked-list) | 🟢 Easy | `#three-pointer-reversal` | ✅ Solved |

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
<a id="2-middle-of-the-linked-list"></a>
## 2. Middle of the Linked List

**LeetCode 876 — Easy**

### 📋 Problem Statement

Given the `head` of a singly linked list, return the **middle node** of the linked list.

If there are **two middle nodes**, return the **second middle node**.

**Example 1:**
```
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.
```

**Example 2:**
```
Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: There are two middle nodes (3 and 4), so we return the second one.
```

**Constraints:**
- The number of nodes in the list is in the range `[1, 100]`
- `1 <= Node.val <= 100`

### 🧠 Key Insight

The obvious way to find "the middle" is to first find out **how long the list is**, then walk halfway. That means touching the list twice. The clever way is to use **two pointers moving at different speeds** so that by the time the fast one finishes the list, the slow one is sitting exactly at the middle — all in a **single pass**.

### 🐌 Brute Force — Count Then Traverse

**Approach:** Traverse the list once to count the total number of nodes `n`. Then traverse again from `head` for `n / 2` steps (integer division), landing on the middle (or second-middle, for even-length lists).

```java
class Solution {
    public ListNode middleNode(ListNode head) {
        // Pass 1: count total nodes
        int n = 0;
        ListNode temp = head;
        while (temp != null) {
            n++;
            temp = temp.next;
        }

        // Pass 2: walk n/2 steps from head
        ListNode mid = head;
        for (int i = 0; i < n / 2; i++) {
            mid = mid.next;
        }

        return mid;
    }
}
```

**Why `n / 2` lands on the correct (second) middle:** For `n = 5` (odd), `n/2 = 2` → starting at index 0, two hops lands on index 2, which is the single middle. For `n = 6` (even), `n/2 = 3` → three hops lands on index 3, which is the **second** of the two middle nodes (indices 2 and 3). Integer division naturally rounds the right way here — no extra `if` needed.

**Downside:** Two full passes over the list. Also needs a variable to hold the count, and conceptually you're touching every node twice, which is wasted work once you realize you can do it in one pass.

### ✅ Optimal Solution — Slow & Fast Pointers (Tortoise and Hare)

```java
class Solution {
    public ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
```

### 🔍 Line-by-Line Explanation

1. **`slow = head; fast = head;`** — Both pointers start at the same place.
2. **`while (fast != null && fast.next != null)`** — Keep going only while it's *safe* to move `fast` two steps forward. (Full justification for `&&` below.)
3. **`fast = fast.next.next;`** — `fast` moves **two** steps per iteration.
4. **`slow = slow.next;`** — `slow` moves **one** step per iteration.
5. **`return slow;`** — When `fast` runs out of road, `slow` has covered exactly half the distance — it's at the middle.

The core idea: if `fast` moves twice as fast as `slow`, then whenever `fast` reaches the end, `slow` is at the halfway point. It's the same principle as two runners on a track where one runner is exactly twice as fast — when the fast one finishes a lap, the slow one is exactly at the midpoint.

### 🧪 Dry Run

**Case A — Odd length:** `1 -> 2 -> 3 -> 4 -> 5 -> null`

| Iteration | `fast` before check | Condition (`fast!=null && fast.next!=null`) | `fast` after move | `slow` after move |
|---|---|---|---|---|
| Start | — | — | `1` | `1` |
| 1 | `1` | `1≠null` ✅ and `1.next(2)≠null` ✅ → enter loop | `3` | `2` |
| 2 | `3` | `3≠null` ✅ and `3.next(4)≠null` ✅ → enter loop | `5` | `3` |
| 3 | `5` | `5≠null` ✅ but `5.next = null` ❌ → **loop stops** | — | `3` |

**Result:** `slow = 3` → returns `[3,4,5]` ✅

**Case B — Even length:** `1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null`

| Iteration | `fast` before check | Condition | `fast` after move | `slow` after move |
|---|---|---|---|---|
| Start | — | — | `1` | `1` |
| 1 | `1` | both non-null ✅ | `3` | `2` |
| 2 | `3` | both non-null ✅ | `5` | `3` |
| 3 | `5` | `5≠null` ✅ but `5.next(6)≠null` ✅ → enter loop | `null` (5.next.next) | `4` |
| 4 | `null` | `fast≠null` ❌ → **loop stops immediately** | — | `4` |

**Result:** `slow = 4` → returns `[4,5,6]` ✅ (correctly the **second** middle node)

### ⚖️ Why `&&` and Not `||` in the Optimal Approach

This is the crux of getting the loop condition right, for two separate reasons — one about **correctness**, one about **safety**:

**1. Correctness — what the condition is actually supposed to mean**

Each iteration needs to move `fast` **two full steps** (`fast.next.next`). That's only a valid operation if **both** `fast` and `fast.next` are non-null. So the loop should keep running only while it is safe to take two steps — which is exactly what `&&` expresses: *"continue only if condition 1 AND condition 2 both hold."*

If `||` were used instead (`fast != null || fast.next != null`), the loop would try to continue as long as **at least one** of those is true — which is the wrong logical statement. We don't want "at least one is fine," we want "both are fine," because the very next line depends on both being non-null simultaneously.

**2. Safety — avoiding a `NullPointerException`**

Java evaluates `&&` and `||` with **short-circuit evaluation**, and the order of evaluation matters here:

- With `fast != null && fast.next != null`: if `fast` is `null`, the first operand is `false`, and because of `&&`'s short-circuiting, `fast.next` is **never evaluated at all** — the loop just exits safely.
- With `fast != null || fast.next != null`: if `fast` is `null`, the first operand is `false`, but `||` needs to check the **second** operand to decide the result — so it evaluates `fast.next`. Since `fast` is `null`, this throws a `NullPointerException` on the spot.

In a natural run of this algorithm (e.g., the even-length case above), `fast` genuinely does become `null` at some point. So `||` isn't just "logically looser" — it would **crash the program** the moment that happens.

**In short:** `&&` is required both because the semantics call for "both must be safe" rather than "either one is fine," and because its short-circuit behavior is exactly what prevents the null-pointer crash that `||` would cause.

### ⏱️ Time & Space Complexity

| Approach | Time | Space | Why |
|---|---|---|---|
| Brute Force (count + traverse) | O(n) | O(1) | Two full passes over `n` nodes, but no extra structures |
| Optimal (slow/fast pointers) | O(n) | O(1) | Single pass — `fast` covers the list in ~`n/2` iterations |

Both are O(n) time asymptotically, but the optimal approach does it in **one traversal instead of two**, and generalizes to a pattern (slow/fast pointers) that reappears constantly in linked list problems — cycle detection, finding the k-th node from the end, palindrome checks, and more.

### 🏷️ Pattern Tag

`#slow-fast-pointers` `#tortoise-and-hare` `#single-pass`

> 🎯 **When to recognize this pattern:** Any time a problem needs you to find a midpoint, detect a cycle, or find something "n steps from the end" in a linked list *without knowing the length upfront* — think: *can two pointers moving at different speeds get me there in one pass?*

---
<a id="3-reverse-linked-list"></a>
## 3. Reverse Linked List

**LeetCode 206 — Easy**

### 📋 Problem Statement

Given the `head` of a singly linked list, **reverse the list**, and return the new head.

**Example 1:**
```
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```

**Example 2:**
```
Input: head = [1,2]
Output: [2,1]
```

**Example 3:**
```
Input: head = []
Output: []
```

**Constraints:**
- The number of nodes in the list is in the range `[0, 5000]`
- `-5000 <= Node.val <= 5000`

### 🧠 Key Insight

A linked list only knows how to point **forward** (`next`). Reversing it means every node's `next` must now point **backward**, to the node that used to come before it. The tricky part is that the moment you flip `current.next` to point backward, you **lose the only path forward** to the rest of the list — so you must save "what's next" *before* you break the link.

### 🐌 Brute Force — Store Values, Then Overwrite

**Approach:** Traverse the list once and store every node's value into an `ArrayList` (or array). Then traverse the list a second time from `head`, overwriting each node's `val` with values taken from the **end** of the stored list backward to the start. This reverses the data without touching a single `next` pointer.

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        // Pass 1: collect all values
        List<Integer> values = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            values.add(temp.val);
            temp = temp.next;
        }

        // Pass 2: overwrite node values in reverse order
        ListNode curr = head;
        int i = values.size() - 1;
        while (curr != null) {
            curr.val = values.get(i);
            i--;
            curr = curr.next;
        }

        return head;
    }
}
```

**Downside:** Uses an extra `ArrayList` of size `n`, so it costs O(n) **space** on top of the two O(n) passes. It also only *simulates* reversal by rewriting data — the actual node objects and their `next` links never change, which defeats the point if the interviewer wants a true structural reversal (e.g., if other references to the original nodes exist elsewhere, they'd still show the old order).

### ✅ Optimal Solution — Iterative Three-Pointer Reversal

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        ListNode next = null;

        while (current != null) {

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;

        }
        return prev;
    }
}
```

### 🔍 Line-by-Line Explanation

1. **`ListNode current = head; ListNode prev = null; ListNode next = null;`** — Three pointers: `prev` trails behind (starts at `null` since the new tail's `next` must eventually be `null`), `current` is the node being processed, `next` is a temporary parking spot so we don't lose the rest of the list.
2. **`next = current.next;`** — **Save the forward path first**, before it gets overwritten. This is the step that makes the whole thing possible.
3. **`current.next = prev;`** — **Flip the pointer.** `current` now points backward to `prev` instead of forward.
4. **`prev = current;`** — Advance `prev` up to where `current` is now, ready for the next node.
5. **`current = next;`** — Advance `current` to the node we saved in step 2, since `current.next` no longer points there.
6. **`return prev;`** — When `current` becomes `null`, `prev` is sitting on what used to be the **last** node — now the new head.

### 🧪 Dry Run

Given list: `1 -> 2 -> 3 -> null`

| Iteration | `current` (before) | `next = current.next` | `current.next = prev` | `prev` (after) | `current` (after) |
|---|---|---|---|---|---|
| Start | — | — | — | `null` | `1` |
| 1 | `1` | `2` | `1.next = null` | `1` | `2` |
| 2 | `2` | `3` | `2.next = 1` | `2` | `3` |
| 3 | `3` | `null` | `3.next = 2` | `3` | `null` |
| End (`current == null`) | — | — | — | **`3`** | — |

**List state after each iteration** (reading from `prev` backward shows the reversed chain building up):
- After iter 1: `1 -> null` (an isolated, correctly-terminated node)
- After iter 2: `2 -> 1 -> null`
- After iter 3: `3 -> 2 -> 1 -> null` ✅

**Result:** `return prev` → returns node `3`, and following `.next` gives `3 -> 2 -> 1 -> null`, matching the expected reversed output exactly.

> ⚠️ **Edge case — empty list:** If `head` is `null`, `current` starts as `null`, the `while` condition is immediately false, and the function returns `prev`, which is still `null`. Correct — an empty list reversed is still empty.

### ⏱️ Time & Space Complexity

| Approach | Time | Space | Why |
|---|---|---|---|
| Brute Force (store + overwrite) | O(n) | O(n) | Extra `ArrayList` holds all `n` values |
| Optimal (three-pointer in-place) | O(n) | O(1) | Only three pointer variables, no matter how long the list is; reverses actual `next` links, not just data |

### 🏷️ Pattern Tag

`#three-pointer-reversal` `#in-place-reversal` `#linked-list-fundamentals`

> 🎯 **When to recognize this pattern:** Any time a problem asks you to reverse a linked list (fully or in a range/sublist), think: *prev, current, next* — save the forward link before you break it, flip the pointer, then shift all three pointers one step ahead. This exact three-pointer skeleton reappears in "Reverse Linked List II," "Reverse Nodes in k-Group," and palindrome-checking problems.

---

## 📊 Master Complexity & Patterns Summary

| # | Problem | Time | Space | Pattern |
|---|---|---|---|---|
| 1 | Delete Node in a Linked List | O(1) | O(1) | `#value-copy-trick` |
| 2 | Middle of the Linked List | O(n) | O(1) | `#slow-fast-pointers` |
| 3 | Reverse Linked List | O(n) | O(1) | `#three-pointer-reversal` |

---

<div align="center">

### 🔄 This README updates after every new problem solved
### Next up: keep grinding singly LL problems — reversal, cycle detection, and two-pointer patterns incoming 🚀

</div>
