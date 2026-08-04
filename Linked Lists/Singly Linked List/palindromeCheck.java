class Solution {
    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null)
            return true;

        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode first = head;
        ListNode second = reverseLinkedList(slow.next);
        ListNode old = second;

        while (second != null) {

            if (first.val != second.val) {
                slow.next = reverseLinkedList(old);
                return false;
            }

            first = first.next;
            second = second.next;
        }

        slow.next = reverseLinkedList(old);
        return true;
    }

    public ListNode reverseLinkedList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
