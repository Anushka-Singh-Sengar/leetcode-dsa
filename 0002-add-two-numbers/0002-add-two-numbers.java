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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        int sum = temp1.val + temp2.val;
        int first = sum%10;
        int rem = sum/10;
        ListNode head = new ListNode(first);
        temp1 = temp1.next;
        temp2 = temp2.next;
        ListNode curr = head;

        while(temp1 != null && temp2 != null){
           sum =  rem + temp1.val + temp2.val;
           int data = sum % 10;
           rem = sum/10;
            ListNode temp = new ListNode(data);
            curr.next = temp;
            curr = curr.next;
            temp1 = temp1.next;
            temp2 = temp2.next;


        }
        while(temp1 != null){
             sum =  rem + temp1.val;
           int data = sum % 10;
           rem = sum/10;
             ListNode temp = new ListNode(data);
             curr.next = temp;
            curr = curr.next;
            
            temp1 = temp1.next;


        }
           while(temp2 != null){
             sum =  rem + temp2.val;
           int data = sum % 10;
           rem = sum/10;
             ListNode temp = new ListNode(data);
             curr.next = temp;
            curr = curr.next;
            temp2 = temp2.next;


        }
        if (rem != 0) {
    curr.next = new ListNode(rem);
}
        return head;

        
    }
}