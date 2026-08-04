import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            // If the token is a number, push it onto the stack
            if (!token.equals("+") &&
                !token.equals("-") &&
                !token.equals("*") &&
                !token.equals("/")) {

                st.push(Integer.parseInt(token));
            } 
            // Otherwise, perform the operation
            else {
                int x = st.pop(); // Second operand
                int y = st.pop(); // First operand

                switch (token) {
                    case "+":
                        st.push(y + x);
                        break;
                    case "-":
                        st.push(y - x);
                        break;
                    case "*":
                        st.push(y * x);
                        break;
                    case "/":
                        st.push(y / x);
                        break;
                }
            }
        }

        return st.peek();
    }
}