class MyStack {
    Queue<Integer> q;
    

    public MyStack() {
        q = new LinkedList<>();
        
    }
    
    public void push(int x) {
        q.add(x);
        int n = q.size() - 1;
        for(int i =0; i <n; i++){
           int y = q.poll();
           q.add(y);

        }
        


        
    }
    
    public int pop() {
        int val = q.poll();
        return val;
        
    }
    
    public int top() {
        
        int val = q.peek();
        return val;
    }
    
    public boolean empty() {
        if(q.size() == 0) return true;
        else return false;
        
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */