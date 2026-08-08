import java.util.*;

class MyQueue {

    Stack<Integer> st;
    Stack<Integer> temp;

    public MyQueue() {
        st = new Stack<>();
        temp = new Stack<>();
    }

    public void push(int x) {
        st.push(x);
    }

    public int pop() {

        if(temp.isEmpty()) {
            while(!st.isEmpty()) {
                temp.push(st.pop());
            }
        }

        return temp.pop();
    }

    public int peek() {

        if(temp.isEmpty()) {
            while(!st.isEmpty()) {
                temp.push(st.pop());
            }
        }

        return temp.peek();
    }

    public boolean empty() {
        return st.isEmpty() && temp.isEmpty();
    }
}