import java.util.Stack;

class Solution {
    public boolean isValid(String s) {

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } 
            else {

                if (st.isEmpty())
                    return false;

                char x = st.pop();

                if (ch == ')') {
                    if (x != '(')
                        return false;
                }
                else if (ch == ']') {
                    if (x != '[')
                        return false;
                }
                else if (ch == '}') {
                    if (x != '{')
                        return false;
                }
            }
        }

        return st.isEmpty();
    }
}