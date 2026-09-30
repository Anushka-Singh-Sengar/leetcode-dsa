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
    public ListNode Middle(ListNode head){
        if(head == null || head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
    
    while(fast != null && fast.next != null){
        fast = fast.next.next;
        slow = slow.next;
    }
    return slow;
    }
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode l1 = list1;
        ListNode l2 = list2;
        if(l1 == null ) return l2;
        if(l2 == null) return l1;
        if(l1.val > l2.val){
            ListNode t = l2;
            l2 = l1;
            l1 = t;
        }
        ListNode ans = l1;
        while(l1 != null && l2 != null){
            ListNode temp = l1;
            while( l1 != null && l1.val <= l2.val){
                temp = l1;
                l1 = l1.next;
            }
            temp.next = l2;

        
          
            ListNode t1 = l2;
            l2 = l1;
            l1 = t1;
        


        } 
        return ans;
        
    }
    public ListNode sortList(ListNode head) {
        if(head ==  null || head.next == null){
            return head;
        }
        ListNode mid = Middle(head);
        ListNode lefthead = head;
        ListNode righthead = mid.next;
        mid.next = null;
        lefthead = sortList(lefthead);
        righthead = sortList(righthead);
        return mergeTwoLists(lefthead, righthead);
       
    }
     
}