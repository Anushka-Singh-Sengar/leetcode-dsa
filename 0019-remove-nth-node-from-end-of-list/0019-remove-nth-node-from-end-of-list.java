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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 0;
        if(head == null) return null;
        ListNode temp = head;
        
        while(temp != null){
            count++;
            
            temp = temp.next;
        }
        if (n == count) {
            return head.next;
        }

        temp = head;
        for(int i =1; i < count-n; i++){
            temp = temp.next;
        }
       temp.next = temp.next.next;
        return head;
        
    }
}