<div align="center">

# 🔗 Linked List — DSA Mastery Repository

### 📘 Part 1: Singly Linked List (SLL)

![Java](https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=openjdk)
![DSA](https://img.shields.io/badge/Topic-Data%20Structures-blue?style=for-the-badge&logo=leetcode)
![Status](https://img.shields.io/badge/Status-In%20Progress-brightgreen?style=for-the-badge)
![Level](https://img.shields.io/badge/Level-Beginner%20to%20Advanced-purple?style=for-the-badge)
![Maintained](https://img.shields.io/badge/Maintained-Yes-success?style=for-the-badge)

*"An array is a house with fixed rooms. A linked list is a treasure hunt — each clue only tells you where the next one is."* 🧩

</div>

---

## 🧭 Navigation

| # | Section | Description |
|---|---|---|
| 1 | [🧠 What is a Linked List?](#what-is-a-linked-list) | Core definition, why it exists |
| 2 | [⚖️ Array vs Linked List](#array-vs-linked-list) | When to use what |
| 3 | [🏗️ Node Structure & Declaration](#node-structure-declaration) | Java class setup |
| 4 | [🚶 Traversal](#traversal) | Walking the list |
| 5 | [➕ Insertion](#insertion) | Head / Tail / Position |
| 6 | [➖ Deletion](#deletion) | Head / Tail / Position / By Value |
| 7 | [✏️ Updation](#updation) | Modify node values |
| 8 | [🔍 Searching](#searching) | Linear search in SLL |
| 9 | [🔁 Reversal](#reversal) | Iterative & recursive |
| 10 | [🐢🐇 Slow-Fast Pointer Technique](#slow-fast-pointer) | Middle, cycle detection |
| 11 | [🎭 Dummy Node Technique](#dummy-node-technique) | Simplify edge cases |
| 12 | [🌀 Recursion on Linked Lists](#recursion-on-linked-lists) | Recursive mental model |
| 13 | [📊 Master Complexity Table](#master-complexity-table) | Big-O cheat sheet |
| 14 | [🏷️ Pattern Tags](#pattern-tags) | Recognize problem types |
| 15 | [💼 Interview Q&A](#interview-qa) | Theory questions asked in interviews |
| 16 | [📝 Practice Problems Tracker](#practice-problems-tracker) | Problems to solve next |

---

<a id="what-is-a-linked-list"></a>

## 🧠 1. What is a Linked List?

A **Linked List** is a linear data structure where elements (called **nodes**) are stored in **non-contiguous memory locations**, and each node holds a **reference (pointer)** to the next node in the sequence.

Unlike an array, there is no indexing formula (`base + i*size`) to jump directly to an element — you must **walk from the head, one hop at a time**.

```
head → [10 | •] → [20 | •] → [30 | null]
```

Each box is a `Node` object living somewhere on the heap. The arrow `•` is a `next` reference pointing to another node's memory address, or `null` if it's the last node.

> 💡 **Why does this data structure exist?**
> Arrays need contiguous memory and (in Java, for raw arrays) a fixed size. Inserting/deleting in the middle of an array means **shifting every element after it** — O(n). A linked list avoids shifting entirely: insertion/deletion is O(1) **once you're at the right node**, because it's just pointer rewiring.

---

<a id="array-vs-linked-list"></a>

## ⚖️ 2. Array vs Linked List

| Feature | Array | Linked List |
|---|---|---|
| Memory | Contiguous | Scattered (non-contiguous) |
| Size | Fixed (static array) | Dynamic |
| Access by index | O(1) | O(n) |
| Insertion at head | O(n) (shift) | O(1) |
| Insertion at tail | O(1) amortized | O(n) (O(1) with tail pointer) |
| Insertion in middle | O(n) | O(n) to find + O(1) to insert |
| Deletion | O(n) (shift) | O(n) to find + O(1) to delete |
| Extra memory | None | Extra pointer per node |
| Cache locality | Excellent | Poor (scattered in memory) |

---

<a id="node-structure-declaration"></a>

## 🏗️ 3. Node Structure & Declaration

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
```

The list itself is just a wrapper holding the **head** reference:

```java
class LinkedList {
    Node head;

    // Optionally track size and tail for O(1) tail insertion
    Node tail;
    int size;
}
```

> 🧩 **Key mental model:** `head` is not "the list" — it's a **pointer to the first node**. An empty list is simply `head = null`.

---

<a id="traversal"></a>

## 🚶 4. Traversal

```java
void printList(Node head) {
    Node current = head;
    while (current != null) {
        System.out.print(current.data + " -> ");
        current = current.next;
    }
    System.out.println("null");
}
```

⚠️ **Golden rule:** Never move `head` itself while traversing. Always use a temporary pointer (`current`), or you'll permanently lose the start of the list.

**Dry Run** — list `10 -> 20 -> 30 -> null`:

| Step | current | Output so far |
|---|---|---|
| 1 | 10 | `10 ->` |
| 2 | 20 | `10 -> 20 ->` |
| 3 | 30 | `10 -> 20 -> 30 ->` |
| 4 | null | `10 -> 20 -> 30 -> null` (loop ends) |

---

<a id="insertion"></a>

## ➕ 5. Insertion

### a) Insert at Head — O(1)

```java
Node insertAtHead(Node head, int data) {
    Node newNode = new Node(data);
    newNode.next = head;
    return newNode;   // new node becomes the new head
}
```

### b) Insert at Tail — O(n) without tail pointer, O(1) with one

```java
Node insertAtTail(Node head, int data) {
    Node newNode = new Node(data);
    if (head == null) return newNode;

    Node current = head;
    while (current.next != null) {
        current = current.next;
    }
    current.next = newNode;
    return head;
}
```

### c) Insert at a Given Position (0-indexed) — O(k)

```java
Node insertAtPosition(Node head, int data, int position) {
    if (position == 0) return insertAtHead(head, data);

    Node newNode = new Node(data);
    Node current = head;
    for (int i = 0; i < position - 1; i++) {
        current = current.next;   // stop at node BEFORE the target index
    }
    newNode.next = current.next;
    current.next = newNode;
    return head;
}
```

**Dry Run** — insert `25` at position `2` into `10 -> 20 -> 30 -> null`:

| Step | current | Action |
|---|---|---|
| Start | 10 (index 0) | loop runs `position - 1 = 1` time |
| i=0 | move to 20 (index 1) | loop ends, `current = 20` |
| Link | — | `newNode.next = current.next` → `25.next = 30` |
| Link | — | `current.next = newNode` → `20.next = 25` |

Result: `10 -> 20 -> 25 -> 30 -> null` ✅

---

<a id="deletion"></a>

## ➖ 6. Deletion

### a) Delete Head — O(1)

```java
Node deleteHead(Node head) {
    if (head == null) return null;
    return head.next;
}
```

### b) Delete at Tail — O(n)

```java
void deleteAtTail(LinkedList list) {
    Node head = list.head;
    if (head == null) return;                 // empty list
    if (head.next == null) { list.head = null; return; }  // single node

    Node current = head;
    while (current.next.next != null) {
        current = current.next;
    }
    current.next = null;   // current is now second-last node
}
```

### c) Delete by Value — O(n)

```java
Node deleteByValue(Node head, int value) {
    if (head == null) return null;
    if (head.data == value) return head.next;

    Node current = head;
    while (current.next != null && current.next.data != value) {
        current = current.next;
    }
    if (current.next != null) {
        current.next = current.next.next;  // bypass target node
    }
    return head;
}
```

### d) Delete at Position — O(k)

```java
Node deleteAtPosition(Node head, int position) {
    if (position == 0) return head.next;

    Node current = head;
    for (int i = 0; i < position - 1; i++) {
        current = current.next;
    }
    current.next = current.next.next;
    return head;
}
```

**Dry Run** — delete tail from `1 -> 2 -> 3 -> 4 -> null`:

| Step | current | current.next | current.next.next | Loop continues? |
|---|---|---|---|---|
| Start | 1 | 2 | 3 | yes |
| Move | 2 | 3 | 4 | yes |
| Move | 3 | 4 | null | **stop** |

`current = 3` → `current.next = null` → result: `1 -> 2 -> 3 -> null` ✅

> 🧠 **Core insight:** Deletion is really just **rewiring `next` to skip the unwanted node**. Java's Garbage Collector cleans up the orphaned node automatically — no manual `free()` like in C/C++.

---

<a id="updation"></a>

## ✏️ 7. Updation

```java
void updateValue(Node head, int position, int newData) {
    Node current = head;
    for (int i = 0; i < position; i++) {
        current = current.next;
    }
    current.data = newData;
}
```

Complexity: **O(n)** — must traverse to the target index first (no direct indexing like arrays).

---

<a id="searching"></a>

## 🔍 8. Searching

```java
boolean search(Node head, int key) {
    Node current = head;
    while (current != null) {
        if (current.data == key) return true;
        current = current.next;
    }
    return false;
}
```

**Return index instead of boolean:**

```java
int searchIndex(Node head, int key) {
    Node current = head;
    int index = 0;
    while (current != null) {
        if (current.data == key) return index;
        current = current.next;
        index++;
    }
    return -1;  // not found
}
```

---

<a id="reversal"></a>

## 🔁 9. Reversal (Iterative & Recursive)

This is **the single most-asked linked list question** in interviews.

### Iterative — O(n) time, O(1) space

```java
Node reverseIterative(Node head) {
    Node prev = null;
    Node current = head;

    while (current != null) {
        Node nextTemp = current.next;  // save next before overwriting
        current.next = prev;           // reverse the pointer
        prev = current;                // move prev forward
        current = nextTemp;            // move current forward
    }
    return prev;   // prev is the new head
}
```

**Dry Run** — reverse `1 -> 2 -> 3 -> null`:

| Step | prev | current | nextTemp | current.next set to | State after |
|---|---|---|---|---|---|
| Start | null | 1 | — | — | `1 -> 2 -> 3` |
| 1 | null | 1 | 2 | prev (null) | `1 -> null`, prev=1, current=2 |
| 2 | 1 | 2 | 3 | prev (1) | `2 -> 1 -> null`, prev=2, current=3 |
| 3 | 2 | 3 | null | prev (2) | `3 -> 2 -> 1 -> null`, prev=3, current=null |
| End | 3 | null | — | — | loop ends, return prev = 3 |

Result: `3 -> 2 -> 1 -> null` ✅

### Recursive — O(n) time, O(n) space (call stack)

```java
Node reverseRecursive(Node head) {
    if (head == null || head.next == null) return head;  // base case

    Node newHead = reverseRecursive(head.next);  // reverse rest of list
    head.next.next = head;   // make next node point back to current
    head.next = null;        // break original forward link

    return newHead;
}
```

> 🌀 **Mental model:** Trust recursion to reverse everything *after* the current node. Your only job at each level is to fix **one** link: make the node ahead of you point back to you.

---

<a id="slow-fast-pointer"></a>

## 🐢🐇 10. Slow-Fast Pointer Technique

Used for: finding the **middle**, detecting **cycles**, finding **kth node from end**.

### Find the Middle Node

```java
Node findMiddle(Node head) {
    Node slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;   // slow is at the middle when fast reaches the end
}
```

**Dry Run** — `1 -> 2 -> 3 -> 4 -> 5 -> null`:

| Step | slow | fast |
|---|---|---|
| Start | 1 | 1 |
| 1 | 2 | 3 |
| 2 | 3 | 5 |
| 3 (fast.next is null) | — loop stops | — |

Middle = `3` ✅ (slow moves 1 step, fast moves 2 — fast covers the list twice as fast)

### Detect Cycle (Floyd's Algorithm)

```java
boolean hasCycle(Node head) {
    Node slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) return true;   // they meet → cycle exists
    }
    return false;   // fast reached null → no cycle
}
```

> 🎯 **Why it works:** If there's a loop, the fast pointer (2x speed) will eventually "lap" the slow pointer and they'll land on the same node. If there's no loop, fast simply hits `null` first.

---

<a id="dummy-node-technique"></a>

## 🎭 11. Dummy Node Technique

A **dummy node** is a fake node placed *before* the head, used to avoid writing special-case code for operations that affect the head itself (e.g., "remove Nth node from end", "remove all nodes with value X").

```java
Node removeElements(Node head, int val) {
    Node dummy = new Node(-1);
    dummy.next = head;
    Node current = dummy;

    while (current.next != null) {
        if (current.next.data == val) {
            current.next = current.next.next;  // skip node
        } else {
            current = current.next;
        }
    }
    return dummy.next;   // real new head
}
```

> 💡 Without the dummy node, you'd need an `if (head.data == val)` special case before the loop even starts, because deleting the head is different from deleting any other node (there's no "previous" node to rewire). The dummy node gives every node — including the original head — a predecessor.

---

<a id="recursion-on-linked-lists"></a>

## 🌀 12. Recursion on Linked Lists

A linked list is naturally recursive: **a list is a node + a smaller list**. This makes recursive solutions elegant for many problems.

**Print in reverse order (no data structure needed, uses call stack):**

```java
void printReverse(Node head) {
    if (head == null) return;
    printReverse(head.next);       // go to the end first
    System.out.print(head.data + " ");  // print on the way back
}
```

**Get length recursively:**

```java
int lengthRecursive(Node head) {
    if (head == null) return 0;
    return 1 + lengthRecursive(head.next);
}
```

---

<a id="master-complexity-table"></a>

## 📊 13. Master Complexity Table

| Operation | Time (Singly LL) | Space |
|---|---|---|
| Traversal | O(n) | O(1) |
| Search by value | O(n) | O(1) |
| Insert at head | O(1) | O(1) |
| Insert at tail (no tail ptr) | O(n) | O(1) |
| Insert at tail (with tail ptr) | O(1) | O(1) |
| Insert at position k | O(k) | O(1) |
| Delete head | O(1) | O(1) |
| Delete tail | O(n) | O(1) |
| Delete by value / position | O(n) | O(1) |
| Update value at position | O(n) | O(1) |
| Reverse (iterative) | O(n) | O(1) |
| Reverse (recursive) | O(n) | O(n) — call stack |
| Find middle (slow-fast) | O(n) | O(1) |
| Detect cycle (Floyd's) | O(n) | O(1) |

---

<a id="pattern-tags"></a>

## 🏷️ 14. Pattern Tags

Use these tags to categorize problems as you add them to this repo:

`#traversal` `#two-pointer` `#slow-fast-pointer` `#dummy-node` `#recursion` `#in-place-reversal` `#cycle-detection` `#merge-technique` `#kth-node`

---

<a id="interview-qa"></a>

## 💼 15. Interview Q&A

**Q1: Why use a linked list over an array?**
A: Dynamic size and O(1) insertion/deletion at known positions without shifting elements, at the cost of O(n) random access and extra memory for pointers.

**Q2: Why does reversing a linked list need a `nextTemp` variable?**
A: Because once you do `current.next = prev`, you lose the original forward link. You must save `current.next` *before* overwriting it, or you can't move forward in the original list.

**Q3: How do you detect a cycle without extra space?**
A: Floyd's Cycle Detection (slow-fast pointer). If a fast pointer moving 2 steps and a slow pointer moving 1 step ever meet, a cycle exists.

**Q4: What's the point of a dummy node?**
A: It gives the original head node a "predecessor," so head-deletion/insertion doesn't need special-case code separate from the rest of the list.

**Q5: What's the time complexity of accessing the nth element in a linked list vs an array?**
A: O(n) for linked list (must traverse), O(1) for array (direct index formula).

**Q6: Singly vs Doubly Linked List — when would you pick one over the other?**
A: Singly LL uses less memory (one pointer per node) and is simpler; Doubly LL allows O(1) backward traversal and O(1) tail deletion (with a tail pointer) at the cost of an extra pointer per node.

---

<a id="practice-problems-tracker"></a>

## 📝 16. Practice Problems Tracker

| # | Problem | Status | Pattern |
|---|---|---|---|
| 1 | Implement SLL (insert/delete/search/print) | ⬜ | `#traversal` |
| 2 | Reverse a Linked List | ⬜ | `#in-place-reversal` |
| 3 | Middle of the Linked List | ⬜ | `#slow-fast-pointer` |
| 4 | Detect Cycle in Linked List | ⬜ | `#cycle-detection` |
| 5 | Remove Nth Node From End | ⬜ | `#dummy-node` `#two-pointer` |
| 6 | Merge Two Sorted Lists | ⬜ | `#merge-technique` |
| 7 | Palindrome Linked List | ⬜ | `#slow-fast-pointer` `#in-place-reversal` |

---

<div align="center">

### 🚧 Part 2: Doubly Linked List — coming next 🚧

⭐ Keep this repo updated after every problem — brute force, optimal, dry run, complexity, pattern tag.

</div>
