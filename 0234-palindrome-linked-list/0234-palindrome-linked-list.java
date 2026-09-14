/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next !=null){
            fast = fast.next.next;
            slow = slow.next;
            }
           
            ListNode curr = slow.next;
            ListNode next = slow;
            ListNode prev = null;
            while(curr != null){
                next = curr.next;
                curr.next= prev;
                prev = curr;
                curr = next;
                
            } 
            fast = head;
            slow = prev;
            while(slow != null){
            if(slow.val != fast.val){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
            }
              
     return true;

        
    }
}