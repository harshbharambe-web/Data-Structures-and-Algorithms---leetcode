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


# 2. Remove Duplicates from Sorted Array (LeetCode 26)

🧩 **Problem**

Given a **sorted integer array** `nums`, remove the duplicates **in-place** such that each unique element appears only once.

Return the number of unique elements (`k`).

The first `k` elements of the array should contain the unique elements in their original order.

---

### 💡 Approach (Two Pointer)

* If the array is empty, return `0`.
* Use two pointers:
  * `i` → Points to the last unique element.
  * `j` → Traverses the array.
* Compare `nums[j]` with `nums[i]`.
* If they are different:
  * Increment `i`.
  * Copy `nums[j]` to `nums[i]`.
* Continue until the end of the array.
* Return `i + 1` as the count of unique elements.

---

### ⚙️ Complexity

* **Time:** `O(n)`
* **Space:** `O(1)`

---

### 🎯 Pattern

✅ Two Pointers  
✅ In-Place Array Modification

---

## ☕ Java Solution

```java
class Solution {
    public int removeDuplicates(int[] nums) {

        if (nums.length == 0)
            return 0;

        int i = 0;

        for (int j = 1; j < nums.length; j++) {

            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }

        }

        return i + 1;
    }
}
```

---

## 📝 Example

### Input

```text
nums = [0,0,1,1,1,2,2,3,3,4]
```

### Output

```text
5
```

### Modified Array

```text
[0,1,2,3,4,_,_,_,_,_]
```

(The remaining elements after index `k-1` are not important.)

---

## 🧠 Mnemonic

**"Compare → New? → Move → Copy"**

- `i` stores the last unique element.
- `j` scans the array.
- Whenever a new element is found:
  - Move `i`
  - Copy the new value
- Return `i + 1`.

---

## 📌 Key Points

- Works only because the array is **sorted**.
- Removes duplicates **without using extra space**.
- Maintains the original order of unique elements.
- One of the most important **Two Pointer** interview problems.
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

## 5. Largest Element in an Array

🧩 **Problem**

Given an array of integers, find the largest element in the array.

### 💡 Approach (Brute Force)

* Sort the array in ascending order.
* Return the last element as the largest.

⚙️ **Complexity**

* **Time:** `O(n log n)`
* **Space:** `O(1)`

### 💡 Approach (Optimal)

* Assume the first element is the largest.
* Traverse the array once.
* Update the largest element whenever a bigger element is found.
* Return the largest element.

⚙️ **Complexity**

* **Time:** `O(n)`
* **Space:** `O(1)`

🎯 **Pattern**

👉 Array Traversal / Simulation

---

## 6. Second Largest Element in an Array

🧩 **Problem**

Given an array of integers, find the second largest distinct element. If it does not exist, return `-1`.

### 💡 Approach (Brute Force)

* Sort the array in ascending order.
* Store the largest element.
* Traverse backward to find the first element different from the largest.
* Return that element.

⚙️ **Complexity**

* **Time:** `O(n log n)`
* **Space:** `O(1)`

### 💡 Approach (Optimal)

* Maintain two variables: `largest` and `secondLargest`.
* Traverse the array once.
* Update both variables whenever a larger element is found.
* Ignore duplicate values of the largest element.
* Return the second largest element.

⚙️ **Complexity**

* **Time:** `O(n)`
* **Space:** `O(1)`

🎯 **Pattern**

👉 Array Traversal / Simulation



