/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        ListNode temp1 = headA;
        int len1 = 0;
        ListNode temp2 = headB;
         int len2 = 0;
         if(headA== headB){
            return headA;
         }
         while(temp1 != null){
            len1++;
            temp1 = temp1.next;
         }
          while(temp2 != null){
            len2++;
            temp2 = temp2.next;
         }
         temp1 = headA;
         temp2 = headB;
         
         int diff = Math.abs(len1 - len2);
         if(len1 > len2){
            for(int i =0; i < diff; i++){
                temp1 = temp1.next;

            }}
            else{
                
            for(int i =0; i < diff; i++){
                temp2 = temp2.next;

            
            }
         }

         while(temp1 != null && temp2 != null){
            if(temp1 == temp2){
                return temp1;
            }
           else if(temp1.next == temp2){
            return temp2;
           }
           else if(temp1 == temp2.next){
            return temp2;
           }
           temp1 = temp1.next;
           temp2= temp2.next;
         }

        
        return null;
        
        
        
        
        
        
        
        
        
        
        
        
    }
}