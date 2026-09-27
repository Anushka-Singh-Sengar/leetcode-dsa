class Solution {
    public String reverseParentheses(String s) {

        Stack<Character> st = new Stack<>();

        for (char ch : s.toCharArray()) {

            if (ch == ')') {
                StringBuilder temp = new StringBuilder();

                while (st.peek() != '(') {
                    temp.append(st.pop());
                }

                // remove '('
                st.pop();

                // put reversed substring back
                for (int i = 0; i < temp.length(); i++) {
                    st.push(temp.charAt(i));
                }

            } else {
                st.push(ch);
            }
        }

        StringBuilder ans = new StringBuilder();

        while (!st.isEmpty()) {
            ans.append(st.pop());
        }

        return ans.reverse().toString();
    }
}